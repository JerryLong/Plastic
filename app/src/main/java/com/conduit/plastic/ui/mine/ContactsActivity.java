package com.conduit.plastic.ui.mine;

import android.os.Bundle;

import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.util.ShareUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsActivity extends BaseActivity {

    @Override
    protected int LayoutId() {
        return R.layout.activity_contacts;
    }

    @Override
    public void initView() {
        setBackTxt("我的");
        setTitleTxt("联系我们");
    }


    @OnClick(R.id.welcome_phone)
    public void onViewClicked() {
        ShareUtil.callPhone(this, "028-86271825");
    }
}
