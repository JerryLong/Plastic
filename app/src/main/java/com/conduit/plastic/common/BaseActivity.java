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
package com.conduit.plastic.common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.conduit.plastic.R;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.global.UserConstant;
import com.conduit.plastic.util.ActivityUtils;
import com.conduit.plastic.util.ThemeUtil;

import java.sql.Time;
import java.util.Calendar;

import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

public abstract class BaseActivity extends AppCompatActivity implements BaseFuncIml, View.OnClickListener {
    /**
     * 内容区域
     */
    private RelativeLayout mContent;
    public Calendar mCalendar;
//    public Time mTime;
    public int mYear = 0, mDay = 0, mMonth = 0;
    protected abstract int LayoutId();

    public abstract void initView();

    protected Fragment mCurrFragment;

    private int mFragmentId;
    public final String TAG = getClass().getSimpleName();

    private UserConstant mUserConstant;

    private MaterialDialog.Builder mLoadingDialog;

    public ThemeUtil mThemeUtil;

    private long mExitTime;
    private boolean isExit = false;
    FragmentTransaction transaction;
    /**
     * 通用的Toolbar
     */
    protected Toolbar mToolBar;
    protected TextView mBackTxt;
    protected TextView mSubTxt;
    protected TextView mTitleTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initTitleBar();
        setContentLayout();
        ButterKnife.bind(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 启动activity时添加Activity到堆栈
        ActivityUtils.addActivity(this);
        initView();
        mCalendar= Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH) + 1;
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    void initTitleBar() {
        mToolBar = (Toolbar) findViewById(R.id.base_toolbar);
        mBackTxt = (TextView) findViewById(R.id.toolbar_back);
        mSubTxt = (TextView) findViewById(R.id.toolbar_subtitle);
        mTitleTxt = (TextView) findViewById(R.id.toolbar_title);
        mContent = (RelativeLayout) findViewById(R.id.base_content);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mBackTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFinish();
            }
        });

    }

    public void isFinish() {
        ActivityUtils.removeActivity(this);
        this.finish();
    }

    public void setBackTxt(String backTxt) {
        if (mBackTxt == null) {
            initTitleBar();
        }
        mBackTxt.setText(backTxt);
    }

    public void setBackTxt(@StringRes int backIdRes) {
        if (mBackTxt == null) {
            initTitleBar();
        }
        mBackTxt.setText(backIdRes);
    }

    public void setSubTxt(String subTxt) {
        if (mSubTxt == null) {
            initTitleBar();
        }
        mSubTxt.setText(subTxt);
    }

    public void setSubTxt(@StringRes int subRes) {
        if (mSubTxt == null) {
            initTitleBar();
        }
        mSubTxt.setText(subRes);
    }

    public void setTitleTxt(String titleTxt) {
        if (mTitleTxt == null) {
            initTitleBar();
        }
        mTitleTxt.setText(titleTxt);
    }

    public void setTitleTxt(@StringRes int titleRes) {
        if (mTitleTxt == null) {
            initTitleBar();
        }
        mTitleTxt.setText(titleRes);
    }

    public void init() {

    }

    public void hideBack() {
        if (mBackTxt == null) {
            initTitleBar();
        }
        mBackTxt.setVisibility(View.GONE);
    }

    /**
     * 获取ToolBar
     */
    public Toolbar getToolBar() {
        if (mToolBar == null) {
            initTitleBar();
        }
        return mToolBar;
    }

    /**
     * 隐藏ToolBar，通过setToolBar重新定制ToolBar
     */
    public void hideToolBar() {
        mToolBar.setVisibility(View.GONE);
    }

    /**
     * 显示ToolBar，通过setToolBar重新定制ToolBar
     */
    public void showToolBar() {
        mToolBar.setVisibility(View.VISIBLE);
    }


    /**
     * 设置toolbar下面内容区域的内容
     */
    public void setContentLayout() {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(LayoutId(), null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContent.addView(contentView, params);
    }

    /**
     * 波纹形打开页面
     *
     * @param cls
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getApplicationContext(), cls);
        if (null != bundle)
            intent.putExtras(bundle);
        startActivity(intent);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        initData();
        initListener();
        initLoad();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mUserConstant = UserConstant.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.bind(this).unbind();
    }

    public void setFrameId(int mFragmentId) {
        this.mFragmentId = mFragmentId;
    }

    /**
     * 将 Fragment添加到Acitvtiy
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        checkNotNull(fragment);
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    protected void toFragment(Fragment toFragment) {
        if (mCurrFragment == null) {
            showShortToast("mCurrFragment is null!");
            return;
        }

        if (toFragment == null) {
            showShortToast("toFragment is null!");
            return;
        }

        if (toFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().hide(mCurrFragment)
                    .show(toFragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().hide(mCurrFragment)
                    .add(mFragmentId, toFragment).show(toFragment)
                    .commit();
        }

        mCurrFragment = toFragment;

    }

    public Fragment getCurrFragment() {
        return mCurrFragment;
    }

    public void setCurrFragment(Fragment mCurrFragment) {
        this.mCurrFragment = mCurrFragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initData() {
    }


    @Override
    public void initListener() {

    }

    @Override
    public void initLoad() {

    }


    @Override
    public void onClick(View v) {

    }

    protected void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity) {
        openActivity(toActivity, null);
    }

    protected void openActivity(Class<? extends BaseActivity> toActivity, Bundle parameter) {
        Intent intent = new Intent(this, toActivity);
        if (parameter != null) {
            intent.putExtras(parameter);
        }
        startActivity(intent);

    }

    public UserConstant getUserConstant() {
        return mUserConstant;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit) {
                if ((System.currentTimeMillis() - mExitTime) > 2000) {
                    showShortToast("再按一次退出程序");
                    mExitTime = System.currentTimeMillis();
                } else {
                    finish();
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}