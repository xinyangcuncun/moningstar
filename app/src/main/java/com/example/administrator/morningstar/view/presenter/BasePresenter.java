package com.example.administrator.morningstar.view.presenter;

import com.example.administrator.morningstar.view.activity.BaseMvpActivity;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by anson on 2017/5/5.
 */

public class BasePresenter <V>  {
    public BaseMvpActivity mContext;

    public BasePresenter(BaseMvpActivity context) {
        mContext = context;
    }

    protected Reference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }
}
