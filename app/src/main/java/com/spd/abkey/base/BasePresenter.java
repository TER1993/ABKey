package com.spd.abkey.base;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * @author :Reginer in  2017/9/18 13:14.
 * 联系方式:QQ:282921012
 * 功能描述:Presenter基类
 */
public abstract class BasePresenter<V, M extends BaseModel> {
    protected M mModel;

    public BasePresenter() {
        mModel = createModel();
    }

    private Reference<V> mViewRef;
    private CompositeDisposable disposables;

    void attachView(V view) {
        mViewRef = new WeakReference<>(view);
        disposables = new CompositeDisposable();
    }

    @Nullable
    public V getView() {
        if (mViewRef == null) {
            return null;
        }
        return mViewRef.get();
    }

    private boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }


    void detachView() {
        if (isViewAttached()) {
            mViewRef.clear();
            mViewRef = null;
        }
        if (disposables != null) {
            disposables.clear();
        }
    }

    public void addDisposable(Disposable disposable) {
        disposables.add(disposable);
    }

    /**
     * 创建model
     *
     * @return model
     */
    protected abstract M createModel();
}