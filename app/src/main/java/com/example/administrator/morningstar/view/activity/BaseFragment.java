package com.example.administrator.morningstar.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.morningstar.R;

import java.io.File;

/**
 * Created by tuyx on 2016/4/22.
 */
public abstract class BaseFragment extends Fragment {

    public static final String TAG = BaseFragment.class.getSimpleName();

    protected Context mContext;
    protected Activity mActivity;

    private ViewGroup mBaseRootView;
    private View view;

    protected abstract int getViewLayout();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBaseRootView = (ViewGroup) inflater.inflate(R.layout.fragment_base, container, false);
        int viewLayoutId = getViewLayout();
        if (viewLayoutId != 0) {
            view = View.inflate(mContext, viewLayoutId, mBaseRootView);
        }
        return mBaseRootView;
    }



    public View getBaseRootView() {
        return mBaseRootView;
    }

    public View getRootView() {
        return view;
    }


    /**
     * 显示TOAST
     */
/*    public void showToast(final String text) {
        final Activity activity = getActivity();
        if (!TextUtils.isEmpty(text) && activity != null) {
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    new DialogBundle.ToastDialog.Builder(mContext).setAlert(text).build().show();
                }
            });

        }
    }*/

    /**
     * 显示TOAST
     */
    /*public void showToast(final int resId) {
        final Activity activity = getActivity();
        if (resId > 0 && activity != null) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new DialogBundle.ToastDialog.Builder(mContext).setAlert(resId).build().show();
                }
            });
        }
    }*/

}
