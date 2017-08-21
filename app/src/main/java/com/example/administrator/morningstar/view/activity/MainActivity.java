package com.example.administrator.morningstar.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.morningstar.R;
import com.example.administrator.morningstar.view.base.BaseActivity;
import com.example.administrator.morningstar.view.tool.UserCache;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

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
    private TextView tvToolBarTitle;
    private Toolbar tvToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initIntent();
        initView();
        initData(savedInstanceState);
    }

    private void initIntent() {
        Bundle bundle = getIntent().getBundleExtra("haha");
        if(bundle != null){
            //如果bundle存在，取出其中的参数，启动DetailActivity
            String name = bundle.getString("name");
            String price = bundle.getString("price");
            String detail = bundle.getString("detail");
            Intent intent = new Intent(this,RxJavaActivity.class);
            intent.putExtra("name", name);
            intent.putExtra("price", price);
            intent.putExtra("detail", detail);
            startActivity(intent);
            Log.i("mainactivity", "launchParam exists, redirect to DetailActivity");
        }
    }

    @Override
    protected int getViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected CharSequence getToolBarTitle() {
        return null;
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
        tvToolBarTitle.setText("泰国、新加波、印度尼西亚");
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

        //网络监听入口
        flMainNavPublishCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetrofitMvpActivity.startMe(mContext);
            }
        });

        /*replaceFragment(0);*/

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
        tvToolBarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvToolBarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvToolBar = (Toolbar) findViewById(R.id.tb_app_bar);
        setSupportActionBar(tvToolBar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, tvToolBar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected boolean isShowToolbar() {
        return false;
    }

    public static void startMe(BaseActivity mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        if(mContext.getIntent().getBundleExtra("haha") != null){
            intent.putExtra("haha", mContext.getIntent().getBundleExtra("haha"));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mContext.startActivity(intent);
    }
    public static void startMe(BaseMvpActivity mContext) {
        Intent intent = new Intent(mContext, MainActivity.class);
        if(mContext.getIntent().getBundleExtra("haha") != null){
            intent.putExtra("haha", mContext.getIntent().getBundleExtra("haha"));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        mContext.startActivity(intent);
    }

    static public Intent startAppOutside(Context context, @Nullable Intent gotoIntent) {
        Intent intent = new Intent(context, MainActivity.class);
        if (gotoIntent != null) {
            intent.putExtra(GOTO_INTENT, gotoIntent);
        }
        //添加清空top标志
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        return intent;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        gotoIntent(getIntent());
        dealWithIntent();
    }

    private void gotoIntent(Intent intent) {
        if (intent != null) {
            Intent gotoIntent = intent.getParcelableExtra(GOTO_INTENT);
            //如果请求跳转，则需要判断跳转时候前置Fragment是否被显示
            //如果前置页面被否定，则不能跳转
            if (gotoIntent != null) {
                startActivity(gotoIntent);
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                FashionSplashActivity.Companion.startMe(mContext);
                break;
            case R.id.nav_gallery:
                MessageListActivity.startMe(mContext);
                break;
            case R.id.nav_slideshow:
                UserCache.clear();
                LoginActivity.startMe(mContext);
                break;
            case R.id.nav_manage:
//                FashionLiveActivity.Companion.startMe(mContext);
                DownLoadActivity.startMe(mContext);
                break;
            case R.id.nav_share:
                GSYPlayerActivity.startMe(mContext);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
