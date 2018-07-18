/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.conduit.plastic.ui.main.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.VPagerAdapter;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.main.fragment.DealFragment;
import com.conduit.plastic.ui.main.fragment.DemandFragment;
import com.conduit.plastic.ui.main.fragment.MainFragment;
import com.conduit.plastic.ui.main.fragment.MainFragments;
import com.conduit.plastic.ui.main.fragment.MineFragment;
import com.conduit.plastic.ui.main.fragment.PreferredFragment;
import com.conduit.plastic.ui.mine.UserInfoActivity;
import com.conduit.plastic.ui.release.ReleaseActivity;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.util.ActivityUtils;
import com.conduit.plastic.view.tablayout.SlidingTabLayout;
import com.conduit.plastic.view.tablayout.listener.OnTabSelectListener;
import com.conduit.plastic.widget.ThemeDialog;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseFrameActivity<MainPresenter, MainModel> implements MainContract.View,
        MaterialDialog.SingleButtonCallback, RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.main_fragment_layout)
    FrameLayout mFragmentLayout;
    @BindView(R.id.RadioG_Bottem)
    RadioGroup mRadioGroup;
    @BindView(R.id.tab_main)
    RadioButton tabMain;
    @BindView(R.id.tab_preferred)
    RadioButton tabPreferred;
    @BindView(R.id.tab_add)
    RadioButton tabAdd;
    @BindView(R.id.tab_need)
    RadioButton tabNeed;
    @BindView(R.id.tab_mine)
    RadioButton tabMine;
    @BindView(R.id.tab_iv_add)
    ImageView tabIvAdd;
    AlertDialog mAlertDialog;
    private List<Fragment> fragments = new ArrayList<>();
    private Fragment fragment;
    private FragmentManager fm;
    private FragmentTransaction transaction;


    private MaterialDialog.Builder mExitLoginDialog;
    //    private VPagerAdapter mAdapter;
//    private ArrayList<Fragment> mFragments = new ArrayList<>();
    MainFragments mainFragment;

    @Override
    public void initData() {
        super.initData();
        mExitLoginDialog = new MaterialDialog.Builder(this);
        initExitLoginDialog();

    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        hideToolBar();
        tabMain.setChecked(true);
        mRadioGroup.setOnCheckedChangeListener(this);
//        ActivityUtils.removeAllActivity();
        fragments = getFragments();
        setDefaultFragment();

        Bundle bundle = new Bundle();
        bundle.putString(Constants.Key.TOPIC_TYPE, Constants.Topic.EXCELLENT);
        initDialog();
    }

    public List<Fragment> getFragments() {
        mainFragment = MainFragments.newInstance(0);
        fragments.add(mainFragment);
        fragments.add(PreferredFragment.newInstance());
        fragments.add(DemandFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        return fragments;
    }

    @Override
    public void initLoad() {
        super.initLoad();
        mPresenter.info();

    }

    private void setDefaultFragment() {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        fragment = fragments.get(0);
        transaction.replace(R.id.main_fragment_layout, fragment);
        transaction.commit();
    }

    private void initExitLoginDialog() {
        mExitLoginDialog.content(getString(R.string.dialog_exit_login))
                .positiveColorRes(R.color.colorPrimary)
                .negativeColorRes(R.color.colorPrimary)
                .positiveText(getString(R.string.dialog_exit_positive_text))
                .negativeText(getString(R.string.dialog_exit_negative_text));
    }

    @Override
    public void initListener() {
        mExitLoginDialog.onPositive(this);
    }

    void initDialog() {
        mAlertDialog = new AlertDialog.Builder(this)
                .setTitle("温馨提示")
                .setMessage("暂停使用，请完善资料后使用")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(MainActivity.this, UserInfoActivity.class));
                    }
                })
                .create();

    }


    /**
     * 退出登录 dialog 按钮点击监听事件
     *
     * @param dialog
     * @param which
     */
    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        switch (which) {
            case POSITIVE:
                if (getUserConstant().logout()) { //退出登录
//                    mLoginShowRl.setVisibility(View.GONE);
//                    mLoginTv.setVisibility(View.VISIBLE);
//                    mMainMenuList.removeAll(mMyMenuList);
//                    mMenuAdapter.notifyDataSetChanged();
//                    mUserImgIv.setImageURI(Uri.parse("res://com.jun.elephant/" + R.color.main_bg));
//                    dialog.dismiss();
                }
                break;
            case NEGATIVE:
                dialog.dismiss();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserInfo userInfo = UserUtils.getInstance().getUser();
        if (userInfo.isEditUser() &&mAlertDialog!=null&& !mAlertDialog.isShowing()) {
            mAlertDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.Activity.AreaActivity || requestCode == Constants.Activity.ProductNameActivity || requestCode == Constants.Activity.BrandActivity) {
            if (mRadioGroup.getCheckedRadioButtonId() == R.id.tab_main) {
                mainFragment.onActivityResult(requestCode, resultCode, data);
            }
//            else if (mainVpager.getCurrentItem() == 1) {
////                dealFragment.onActivityResult(requestCode, resultCode, data);
//
//            }
        }
    }


    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }


    @Override
    public void info(BaseEntity<UserInfo> baseEntity) {
        BaseEntity<UserInfo> userEntity = baseEntity;
        UserInfo userInfo = userEntity.getData();
        if (userInfo != null) {
            userInfo.setLogin(true);
            userInfo.setSk(baseEntity.getData().getSk());
            UserUtils.getInstance().saveUser(userInfo);
        }
    }

    @Override
    public void bannerList(List<BannerEntity> baseEntity) {

    }

    @OnClick(R.id.tab_iv_add)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_iv_add:
                ReleaseActivity.openRelease(this, null, false);
                break;
        }
    }

    //   fragments.add(PreferredFragment.newInstance());
//        fragments.add(DemandFragment.newInstance());
//        fragments.add(MineFragment.newInstance());
    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        switch (checkedId) {
            case R.id.tab_main:
                tabMain.setChecked(true);
                tabPreferred.setChecked(false);
                tabNeed.setChecked(false);
                tabMine.setChecked(false);
//                fragment = fragments.get(0);
                transaction.replace(R.id.main_fragment_layout, MainFragments.newInstance(0));
                break;
            case R.id.tab_preferred:
                tabPreferred.setChecked(true);
                tabMain.setChecked(false);
                tabNeed.setChecked(false);
                tabMine.setChecked(false);
//                fragment = fragments.get(1);
                transaction.replace(R.id.main_fragment_layout, PreferredFragment.newInstance());
                break;
            case R.id.tab_need:
                tabNeed.setChecked(true);
                tabMain.setChecked(false);
                tabPreferred.setChecked(false);
                tabMine.setChecked(false);
//                fragment = fragments.get(2);
                transaction.replace(R.id.main_fragment_layout, DemandFragment.newInstance());
                break;
            case R.id.tab_mine:
                tabMine.setChecked(true);
                tabMain.setChecked(false);
                tabPreferred.setChecked(false);
                tabNeed.setChecked(false);
//                fragment = fragments.get(3);
                transaction.replace(R.id.main_fragment_layout, MineFragment.newInstance());
                break;
        }
//        setTabState();
        transaction.commitAllowingStateLoss();
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if (mAlertDialog!=null&&mAlertDialog.isShowing())
            {
                Toast.makeText(MainActivity.this, "请完善个人资料", Toast.LENGTH_SHORT).show();
            }else {
                if (secondTime - firstTime < 2000) {
                    System.exit(0);
                } else {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = System.currentTimeMillis();
                }
            }

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
