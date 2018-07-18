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
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.ui.mine.contract.MineContract;
import com.conduit.plastic.ui.mine.contract.MineModel;
import com.conduit.plastic.ui.mine.contract.MinePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpinionActivity extends BaseFrameActivity<MinePresenter, MineModel> implements MineContract.MineView {

    @BindView(R.id.alert_old)
    EditText alertOld;
    RequestParams params = new RequestParams();

    @Override
    protected int LayoutId() {
        return R.layout.activity_opinion;
    }

    @Override
    public void initView() {
        setTitleTxt("意见反馈");
        setBackTxt("我的");
    }

    @OnClick(R.id.alert_confirm)
    public void onViewClicked() {
        if (!TextUtils.isEmpty(alertOld.getText())) {
            params.content = alertOld.getText().toString();
            mPresenter.feedback(params);
        } else {
            Toast.makeText(OpinionActivity.this, "问题或意见不能为空", Toast.LENGTH_SHORT).show();
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
        alertOld.setText(null);
        Toast.makeText(OpinionActivity.this, "反馈成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void modify(BaseEntity<UserInfo> baseEntity) {

    }

    @Override
    public void demandList(List<DemandEntity> baseEntity) {

    }
}
