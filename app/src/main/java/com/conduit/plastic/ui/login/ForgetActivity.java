package com.conduit.plastic.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.conduit.plastic.R;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetActivity extends BaseFrameActivity<ForgetPresenter, LoginModel> implements LoginContract.ForgetView {

    @BindView(R.id.alert_phone)
    EditText alertPhone;
    @BindView(R.id.alert_code)
    EditText alertCode;
    @BindView(R.id.alert_code_btn)
    Button alertCodeBtn;
    @BindView(R.id.alert_new)
    EditText alertNew;
    UserRequest request = new UserRequest();

    @Override
    protected int LayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("找回密码");
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }


    @Override
    public void forget(BaseEntity<UserEntity> baseEntity) {
        if (baseEntity.errorCode == 0) {
            timer.cancel();
            ToastUtils.showShort("修改成功,请重新登录");
            isFinish();
        }
    }

    @Override
    public void valicode(BaseEntity baseEntity) {
        Map<String, String> map = (Map<String, String>) baseEntity.getData();
        String valicode = map.get("valicode");

        request.setValicode(valicode);
    }

    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            if (alertCodeBtn!=null) {
                alertCodeBtn.setText((millisUntilFinished / 1000) + "秒后可重发");
                alertCodeBtn.setEnabled(false);
            }
        }

        @Override
        public void onFinish() {
            alertCodeBtn.setEnabled(true);
            alertCodeBtn.setText("获取验证码");
        }
    };

    @OnClick({R.id.alert_code_btn, R.id.alert_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.alert_code_btn:
                if (!TextUtils.isEmpty(alertPhone.getText())) {
                        timer.start();
                    request.setMobilePhone(alertPhone.getText().toString());
                    mPresenter.valicode(request);
                } else {
                    showShortToast("手机号码不能为空");
                }
                break;
            case R.id.alert_confirm:
                if (!TextUtils.isEmpty(alertPhone.getText()) && !TextUtils.isEmpty(alertCode.getText()) && !TextUtils.isEmpty(alertNew.getText())) {
                    request.setnPassword(alertNew.getText().toString());
                    request.setValicode(alertCode.getText().toString());
                    request.setMobilePhone(request.getMobilePhone());
                    mPresenter.forget(request);
                } else {
                    showShortToast("以上内容不能为空");
                }
                break;
        }
    }
}
