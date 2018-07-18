package com.conduit.plastic.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SplashAdapter;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.login.LoginActivity;
import com.conduit.plastic.ui.main.activity.MainActivity;
import com.conduit.plastic.widget.HackyViewPager;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashActivity extends BaseFrameActivity<SplashPresenter, SplashModel> implements SplashContract.View, ViewPager.OnPageChangeListener {


    @BindView(R.id.very_image_viewpager)
    HackyViewPager verypager;
    @BindView(R.id.very_image_viewpager_text)
    TextView veryText;
    SplashAdapter adapter;
    List<BannerEntity> mList = new ArrayList<>();
    @BindView(R.id.splash_btn)
    TextView splashBtn;
    private Handler h = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                jump();
            } else if (msg.what == 0) {
                if (verypager==null)
                    return;
                if (verypager.getCurrentItem() + 1 < mList.size()) {
                    verypager.setCurrentItem(verypager.getCurrentItem() + 1);
                    veryText.setText(verypager.getCurrentItem() + 1 + " / " + mList.size());
                    h.sendEmptyMessageDelayed(0, 2000);
                } else {
                    h.sendEmptyMessageDelayed(1, 2000);
                }
            }

        }
    };

    @Override
    protected int LayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        hideToolBar();
        adapter = new SplashAdapter(this);
        verypager.setAdapter(adapter);
        verypager.addOnPageChangeListener(this);
        verypager.setEnabled(false);
        // 设定当前的页数和总页数


    }
    void jump() {
        h.removeMessages(1);
        h.removeMessages(0);
        if (Constants.isLogin()) {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        finish();
    }

    @Override
    public void initLoad() {
        super.initLoad();
        mPresenter.bannerList(10);
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        veryText.setText((position+1) + " / " + mList.size());
        if ((position+1)==mList.size()){
            splashBtn.setVisibility(View.VISIBLE);
        }else {
            splashBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void bannerList(List<BannerEntity> baseEntity) {
        mList = baseEntity;
        adapter.setData(mList);
        veryText.setText(1 + " / " + mList.size());
        h.sendEmptyMessageDelayed(0, 2000);
    }

    @OnClick(R.id.splash_btn)
    public void onClick() {
        h.sendEmptyMessageDelayed(1, 0);
    }
}
