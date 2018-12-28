package com.spd.abkey.fragment.presenter;

import com.spd.abkey.base.BasePresenter;
import com.spd.abkey.fragment.AkeyFragment;
import com.spd.abkey.fragment.contract.KeyContract;
import com.spd.abkey.fragment.model.KeyModel;

/**
 * @author xuyan
 */
public class AkeyPresenter extends BasePresenter<AkeyFragment, KeyModel> implements KeyContract.Presenter {
    @Override
    protected KeyModel createModel() {
        return new KeyModel();
    }
}
