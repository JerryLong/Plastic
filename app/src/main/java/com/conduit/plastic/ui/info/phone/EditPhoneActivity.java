package com.conduit.plastic.ui.info.phone;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.conduit.plastic.R;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditPhoneActivity extends BaseFrameActivity<PhonePresenter, PhoneModel> implements PhoneContract.View {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.phone_edit_txt)
    TextView phoneTxt;
    @BindView(R.id.phone_edit)
    EditText phoneEdit;
    @BindView(R.id.phone_edit_code)
    EditText phoneEditCode;
    @BindView(R.id.phone_code)
    Button phoneCode;
    UserRequest request = new UserRequest();

    @Override
    protected int LayoutId() {
        return R.layout.activity_edit_phone;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("修改手机号");
    }


    @OnClick({R.id.phone_code, R.id.phone_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phone_code:
                timer.start();
                request.setMobilePhone(phoneEdit.getText().toString());
                mPresenter.valicode(request);
                break;
            case R.id.phone_btn:
                mPresenter.bindingPhone(request);
                break;
        }
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            phoneCode.setText((millisUntilFinished / 1000) + "秒后可重发");
            phoneCode.setEnabled(false);
        }

        @Override
        public void onFinish() {
            phoneCode.setEnabled(true);
            phoneCode.setText("获取验证码");
        }
    };

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void bindingPhone(BaseEntity baseEntity) {
        if (baseEntity.getErrorCode() == 0) {
            ToastUtils.showShort("修改成功");
            finish();
        } else {
            ToastUtils.showShort(baseEntity.getError());
        }
    }

    @Override
    public void valicode(BaseEntity baseEntity) {
        Map<String, String> map = (Map<String, String>) baseEntity.getData();
        String valicode = map.get("valicode");

        request.setValicode(valicode);
    }
}
