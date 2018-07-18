package com.conduit.plastic.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.allen.library.SuperTextView;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.ui.main.activity.MainActivity;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.widget.EditWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseFrameActivity<LoginPresenter, LoginModel> implements LoginContract.LoginView {


    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    UserRequest request = new UserRequest();
    @BindView(R.id.login_btn)
    SuperTextView loginBtn;


    @Override
    protected int LayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        hideToolBar();
    }

    @OnClick({R.id.login_btn, R.id.register_txt,R.id.forget_txt})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                request.setMobilePhone(editUsername.getText().toString());
                request.setPassword(editPwd.getText().toString());
                request.setJpushId(System.currentTimeMillis() + request.getPassword());
                mPresenter.login(request);
                break;
            case R.id.register_txt:
                startActivity(RegisterActivity.class);
                break;
            case R.id.forget_txt:
                startActivity(ForgetActivity.class);
                break;
        }
    }


    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {
    }

    @Override
    public void login(BaseEntity<UserEntity> baseEntity) {
        BaseEntity<UserEntity> userEntity = baseEntity;
        if (baseEntity.getErrorCode() == 0) {
            UserInfo userInfo = userEntity.getData().getUser();
            userInfo.setLogin(true);
            userInfo.setSk(baseEntity.getData().getSk());
            UserUtils.getInstance().saveUser(userInfo);
            openActivity(MainActivity.class);
            finish();
        } else {
            editPwd.setError(baseEntity.getError());
        }
    }
}
