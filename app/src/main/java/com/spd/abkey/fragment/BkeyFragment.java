package com.spd.abkey.fragment;

import android.os.Bundle;
import android.os.SystemProperties;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.spd.abkey.AppAbKey;
import com.spd.abkey.R;
import com.spd.abkey.base.BaseMvpFragment;
import com.spd.abkey.fragment.adapter.BkeyAdapter;
import com.spd.abkey.fragment.contract.KeyContract;
import com.spd.abkey.fragment.model.AppBean;
import com.spd.abkey.fragment.model.KeyModel;
import com.spd.abkey.fragment.presenter.BkeyPresenter;
import com.spd.abkey.utils.AppInfoUtil;
import com.spd.abkey.utils.SpUtils;
import com.spd.abkey.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import static com.spd.abkey.fragment.model.KeyModel.SCAN_HEAD;
import static com.spd.abkey.fragment.model.KeyModel.SCAN_KEY;
import static com.spd.abkey.fragment.model.KeyModel.SCAN_SET;

/**
 * @author xuyan
 */
public class BkeyFragment extends BaseMvpFragment<BkeyPresenter> implements KeyContract.View, BaseQuickAdapter.OnItemClickListener {

    private List<AppBean> mList;
    private BkeyAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected BkeyPresenter createPresenter() {
        return new BkeyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bkey_fragment;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mList = new ArrayList<>();
        getAppList();
        //初始化显示数据
        RecyclerView recyclerView = view.findViewById(R.id.rv_content);
        mAdapter = new BkeyAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
    }

    private void getAppList() {

        if ("n63".equals(SystemProperties.get(SCAN_HEAD))) {
            AppBean appBean = new AppBean();
            appBean.setAppName("Scan");
            appBean.setPackageName(SCAN_SET);
            appBean.setAppIcon(getResources().getDrawable(R.drawable.ic_scan));
            mList.add(appBean);
        }

        AppInfoUtil.getAllProgramInfo(mList, AppAbKey.getInstance());
        String pkgName = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_BKEY, "");
        if ("".equals(pkgName)) {
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            if (pkgName.equals(mList.get(i).getPackageName())) {
                mList.get(i).setChecked(true);
            }
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String pkgName = (String) SpUtils.get(AppAbKey.getInstance(), KeyModel.KAY_AKEY, "");
        if (pkgName.equals(mList.get(position).getPackageName())) {
            ToastUtils.showShortToastSafe(R.string.key_b_a);
            return;
        }
        if (mList.get(position).checked) {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setChecked(false);
            }
            SpUtils.put(AppAbKey.getInstance(), KeyModel.KAY_BKEY, "");
            if (SCAN_SET.equals(mList.get(position).getPackageName())) {
                SystemProperties.set(SCAN_KEY, "null");
            }
        } else {
            for (int i = 0; i < mList.size(); i++) {
                mList.get(i).setChecked(false);
            }
            mList.get(position).setChecked(true);
            SpUtils.put(AppAbKey.getInstance(), KeyModel.KAY_BKEY, mList.get(position).getPackageName());
            if (SCAN_SET.equals(mList.get(position).getPackageName())) {
                SystemProperties.set(SCAN_KEY, "f2");
            }
        }
        mAdapter.notifyDataSetChanged();
    }

}
