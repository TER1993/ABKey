package com.spd.abkey.fragment.adapter;


import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.spd.abkey.R;
import com.spd.abkey.fragment.model.AppBean;

import java.util.List;

/**
 * @author xuyan
 */
public class AkeyAdapter extends BaseQuickAdapter<AppBean, BaseViewHolder> {

    public AkeyAdapter(@Nullable List<AppBean> data) {
        super(R.layout.view_amongst_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AppBean item) {
        helper.setText(R.id.pName, item.getAppName());
        helper.setText(R.id.post, item.getPackageName());
        helper.setImageDrawable(R.id.ivAvatar, item.getAppIcon());
        helper.setChecked(R.id.radioButton, item.isChecked());
    }
}
