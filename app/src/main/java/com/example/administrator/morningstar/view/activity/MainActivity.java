package com.example.administrator.morningstar.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.tool.ApplicationIo;

public class MainActivity extends BaseActivity {

    private RadioGroup rgMainNavGroup;
    private int currentCheckedId;
    private BaseFragment[] mainFragments = new BaseFragment[4];
    private FrameLayout flMainNavPublishCar;
    private int currentFragmentIndex = 0;

    //fragment 索引
    public static final int HOME_FRAGMENT_INDEX = 0;
    public static final int SOURCE_FRAGMENT_INDEX = 1;
    public static final int SERVICE_FRAGMENT_INDEX = 2;
    public static final int MINE_FRAGMENT_INDEX = 3;
    //外部启动目标intent
    static final String GOTO_INTENT = "GOTO_INTENT";
    //目标fragment索引
    public static final String TARGET_FRAGMENT_INDEX = "targetFragmentIndex";
    //保存的Fragment索引
    public static final String SAVED_FRAGMENT_INDEX = "saveFragmentIndex";

    public static final int REQUEST_CODE = 200;
    private int targetIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData(savedInstanceState);
    }

    void dealWithIntent() {
        //索引不超过3
        targetIndex = HOME_FRAGMENT_INDEX ;
        replaceFragment(targetIndex);

    }
    private void restoreFragments(int fragmentIndex) {
        Log.e("restoreFragments", "fragmentIndex == " + fragmentIndex);
        mainFragments[0] = (BaseFragment) getSupportFragmentManager().findFragmentByTag(HomeFragment.TAG);
        mainFragments[1] = (BaseFragment) getSupportFragmentManager().findFragmentByTag(CarSourceFragment.TAG);
        mainFragments[2] = (BaseFragment) getSupportFragmentManager().findFragmentByTag(CustomerServiceFragment.TAG);
        mainFragments[3] = (BaseFragment) getSupportFragmentManager().findFragmentByTag(MineFragment.TAG);
        //当前fragment
        BaseFragment currentFragment = mainFragments[fragmentIndex];

        if (currentFragment == null) {
            newFragmentAndSwitchAtIndex(fragmentIndex);
        }
        setCurChecked(fragmentIndex);

        for (int i = 0; i < mainFragments.length; i++) {
            if (fragmentIndex != i) {
                if (mainFragments[i] != null) {
                    HideFragment(mainFragments[i]);
                }
            }
        }
    }

    private void initData(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            dealWithIntent();
        } else {
            //存在缓存，恢复
            currentFragmentIndex = savedInstanceState.getInt(SAVED_FRAGMENT_INDEX);
            restoreFragments(currentFragmentIndex);
        }
        rgMainNavGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //判断是否选择当前页面 是-不用处理
                if (checkedId == currentCheckedId) {
                    return;
                }
                switch (checkedId) {
                    case R.id.rb_main_nav_home: {
                        replaceFragment(0);
                        break;
                    }
                    case R.id.rb_main_nav_car_source: {
                        replaceFragment(1);
                        break;
                    }
                    case R.id.rb_main_customer_service_xml: {
                        replaceFragment(2);
                        break;
                    }
                    case R.id.rb_main_nav_mine: {
                         replaceFragment(3);
                        }

                        break;
                    }
                }
            });

        //发布车源入口监听
        flMainNavPublishCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void replaceFragment(int index) {
        for (int i = 0; i < mainFragments.length; i++) {
            if (index != i) {
                if (mainFragments[i] != null) {
                    HideFragment(mainFragments[i]);
                }
            }
        }

        BaseFragment fragment = mainFragments[index];
        if (fragment != null) {
            Log.d("replaceFragment", "addAndShowFragment index=" + index);
            addAndShowFragment(fragment);
            setCurChecked(index);
        } else {
            Log.d("replaceFragment", "newFragmentAndSwitchAtIndex index=" + index);
            newFragmentAndSwitchAtIndex(index);
            setCurChecked(index);
        }
    }

    private void newFragmentAndSwitchAtIndex(int index) {
        BaseFragment baseFragment = null;
        String tag = null;
        switch (index) {
            case 0:
                baseFragment = HomeFragment.newInstance();
                tag = HomeFragment.TAG;
                break;
            case 1:
                baseFragment = CarSourceFragment.newInstance();
                tag = CarSourceFragment.TAG;
                break;
            case 2:
                baseFragment = CustomerServiceFragment.newInstance();
                tag = CustomerServiceFragment.TAG;
                break;
            case 3:
                baseFragment = MineFragment.newInstance();
                tag = MineFragment.TAG;
                break;
        }
        initFragment(index, tag, baseFragment);
        addAndShowFragment(baseFragment);
    }

    private void initFragment(int index, String tag, BaseFragment fragment) {
        Bundle args = new Bundle();
        args.putString(BaseFragment.TAG, tag);
        mainFragments[index] = fragment;
        //Arguments的生命周期等于Fragment生命周期，在Fragment被销毁时会被保存
        mainFragments[index].setArguments(args);
    }

    private void setCurChecked(int index) {
        switch (index) {
            case 0: {
                currentCheckedId = R.id.rb_main_nav_home;
                currentFragmentIndex = 0;

            }
            break;
            case 1: {
                currentCheckedId = R.id.rb_main_nav_car_source;
                currentFragmentIndex = 1;
            }
            break;
            case 2: {
                currentCheckedId = R.id.rb_main_customer_service_xml;
                currentFragmentIndex = 2;
            }
            break;
            case 3: {
                currentCheckedId = R.id.rb_main_nav_mine;
                currentFragmentIndex = 3;
            }
            break;
        }
    }
    private void addAndShowFragment(BaseFragment baseFragment) {
        if (!baseFragment.isAdded()) {
            Bundle bundle = baseFragment.getArguments();
            getSupportFragmentManager().beginTransaction().add(R.id.fl_main_content, baseFragment, bundle.getString(BaseFragment.TAG)).commit();
        } else if (!baseFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction().show(baseFragment).commit();
        }
    }

    private void HideFragment(BaseFragment baseFragment) {
        if (baseFragment == null) {
            return;
        }
        if (baseFragment.isVisible()) {
            Log.i("HideFragment", "baseFragment ==  not null ");
            getSupportFragmentManager().beginTransaction().hide(baseFragment).commit();
        }
    }
    //控件数据初始化
    private void initView() {
        rgMainNavGroup = (RadioGroup) findViewById(R.id.rg_main_nav_group);
        flMainNavPublishCar = (FrameLayout) findViewById(R.id.fl_main_nav_publish_car);
    }

    @Override
    protected boolean isShowToolbar() {
        return false;
    }

    public static void startMe(BaseActivity mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mContext.startActivity(intent);
    }
}
