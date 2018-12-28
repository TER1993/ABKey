package com.spd.abkey.fragment.presenter;

import com.spd.abkey.base.BasePresenter;
import com.spd.abkey.fragment.BkeyFragment;
import com.spd.abkey.fragment.contract.KeyContract;
import com.spd.abkey.fragment.model.KeyModel;

/**
 * @author xuyan
 */
public class BkeyPresenter extends BasePresenter<BkeyFragment, KeyModel> implements KeyContract.Presenter {
    @Override
    protected KeyModel createModel() {
        return new KeyModel();
    }
}
