package com.conduit.plastic.ui.login;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.conduit.plastic.R;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.ui.main.activity.MainActivity;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.util.ToastUtils;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseFrameActivity<RegisterPresenter, LoginModel> implements LoginContract.RegisterView {
    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.register_code)
    Button registerCode;
    UserRequest request = new UserRequest();

    @Override
    protected int LayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        hideToolBar();
    }


    private CountDownTimer timer = new CountDownTimer(60000, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
            if (registerCode != null) {
                registerCode.setText((millisUntilFinished / 1000) + "秒后可重发");
                registerCode.setEnabled(false);
            }
        }

        @Override
        public void onFinish() {
            if (registerCode != null) {
                registerCode.setEnabled(true);
                registerCode.setText("获取验证码");
            }
        }
    };

    @OnClick({R.id.register_code, R.id.register_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_code:
                timer.start();
                request.setMobilePhone(editUsername.getText().toString());
                mPresenter.valicode(request);
                break;
            case R.id.register_btn:
                request.setPassword(editPwd.getText().toString());
                request.setUserName(editUsername.getText().toString());
                request.setValicode(editCode.getText().toString());
                request.setJpushId(System.currentTimeMillis() + request.getPassword());
                mPresenter.register(request);
                break;
        }
    }

    @Override
    public void onRequestError(String msg) {
//        super.onRequestError(msg);
//        ToastUtils.showShort(msg);
//        registerCode.setEnabled(true);
//        registerCode.setText("获取验证码");
//        timer.cancel();
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {
//        registerCode.setEnabled(true);
//        registerCode.setText("获取验证码");
        timer.cancel();
    }

    @Override
    public void register(BaseEntity<UserEntity> baseEntity) {
        BaseEntity<UserEntity> userEntity = baseEntity;
        UserInfo userInfo = userEntity.getData().getUser();
        userInfo.setLogin(true);
        userInfo.setSk(baseEntity.getData().getSk());
        UserUtils.getInstance().saveUser(userInfo);
        openActivity(MainActivity.class);
        timer.cancel();
    }

    @Override
    public void valicode(BaseEntity baseEntity) {
        Map<String, String> map = (Map<String, String>) baseEntity.getData();
        String valicode = map.get("valicode");

        request.setValicode(valicode);
    }
}
