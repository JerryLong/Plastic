package com.conduit.plastic.ui.mine;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.conduit.plastic.R;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.ui.mine.contract.MineContract;
import com.conduit.plastic.ui.mine.contract.MineModel;
import com.conduit.plastic.ui.mine.contract.MinePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AlertActivity extends BaseFrameActivity<MinePresenter, MineModel> implements MineContract.MineView {

    @BindView(R.id.alert_old)
    EditText alertOld;
    @BindView(R.id.alert_new)
    EditText alertNew;
    @BindView(R.id.alert_news)
    EditText alertNews;
    UserRequest request = new UserRequest();

    @Override
    protected int LayoutId() {
        return R.layout.activity_alert;
    }

    @Override
    public void initView() {
        setBackTxt("设置");
        setTitleTxt("修改密码");
    }

    @OnClick(R.id.alert_confirm)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(alertOld.getText()) && !TextUtils.isEmpty(alertNew.getText()) && !TextUtils.isEmpty(alertNews.getText())) {
            request.setPassword(alertOld.getText().toString());
            request.setnPassword(alertNew.getText().toString());
            mPresenter.alert(request);
        } else {
            Toast.makeText(this, "密码输入错误或不匹配", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void ordinary(BaseEntity baseEntity) {
        if (baseEntity.errorCode == 0) {
            alertOld.setText(null);
            alertNew.setText(null);
            alertNews.setText(null);
            showShortToast("修改成功");
        } else {
            showShortToast("修改失败");
        }
    }

    @Override
    public void modify(BaseEntity<UserInfo> baseEntity) {

    }

    @Override
    public void demandList(List<DemandEntity> baseEntity) {

    }
}
