package com.conduit.plastic.ui.mine;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExplanActivity extends BaseActivity {

    @BindView(R.id.explan_web)
    WebView explanWeb;

    @Override
    protected int LayoutId() {
        return R.layout.activity_explan;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("使用说明");
        WebSettings wSet = explanWeb.getSettings();
        wSet.setJavaScriptEnabled(true);
        explanWeb.loadUrl("http://120.27.222.243:8080/gz-admin/instructions.html");

    }

}
