package com.spd.abkey.activity.main.presenter;

import com.spd.abkey.activity.main.MainActivity;
import com.spd.abkey.activity.main.contract.MainContract;
import com.spd.abkey.activity.main.model.MainModel;
import com.spd.abkey.base.BasePresenter;

/**
 * @author xuyan
 */
public class MainPresenter extends BasePresenter<MainActivity, MainModel> implements MainContract.Presenter {
    @Override
    protected MainModel createModel() {
        return new MainModel();
    }
}
