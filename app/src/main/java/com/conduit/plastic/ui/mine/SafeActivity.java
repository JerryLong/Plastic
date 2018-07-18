package com.conduit.plastic.ui.mine;


import android.os.Bundle;

import com.allen.library.SuperTextView;
import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.user.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SafeActivity extends BaseActivity {

    @BindView(R.id.safe_account)
    SuperTextView safeAccount;
    @BindView(R.id.safe_phone)
    SuperTextView safePhone;

    @Override
    protected int LayoutId() {
        return R.layout.activity_safe;
    }

    @Override
    public void initView() {
        setBackTxt("设置");
        setTitleTxt("账户与安全");
        String str = UserUtils.getInstance().getUser().getUserCode();
        String phone = UserUtils.getInstance().getUser().getMobilePhone();
        safeAccount.setRightString("" + str);
        safePhone.setRightString("" + phone);


    }


    @OnClick(R.id.safe_modify)
    public void onViewClicked() {
        startActivity(AlertActivity.class);
    }

}
