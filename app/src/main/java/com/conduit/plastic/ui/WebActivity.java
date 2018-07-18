package com.conduit.plastic.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected int LayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("使用说明");
        webView.loadUrl(UrlContants.API_BASE_URL + "Explain.html");
    }

}
