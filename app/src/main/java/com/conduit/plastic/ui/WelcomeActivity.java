package com.conduit.plastic.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.ui.login.LoginActivity;
import com.conduit.plastic.ui.main.activity.MainActivity;
import com.conduit.plastic.ui.splash.SplashActivity;
import com.conduit.plastic.util.ShareUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {
    Handler mHandler=new Handler();
    Runnable updateThread = new Runnable() {
        // 将要执行的曹祖哦写在线程对象的run方法当中
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this, SplashActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
//            jump();
        }
    };

    @Override
    protected int LayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        hideToolBar();
    }

    void jump() {
        if (Constants.isLogin()) {
            startActivity(MainActivity.class);
        } else {
            startActivity(LoginActivity.class);
        }
        isFinish();
    }


    @OnClick(R.id.welcome_phone)
    public void onViewClicked() {
        ShareUtil.callPhone(this,"13688136811");
        mHandler.removeCallbacks(updateThread);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(updateThread, 2000);
    }
}
