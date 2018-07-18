package com.conduit.plastic.ui.detail;

import android.os.Bundle;

import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;

public class DetailActivity extends BaseActivity {

    @Override
    protected int LayoutId() {
        return R.layout.activity_detail;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("公司主页");
    }
}
