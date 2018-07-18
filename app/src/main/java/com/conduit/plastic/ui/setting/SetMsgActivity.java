package com.conduit.plastic.ui.setting;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;

import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetMsgActivity extends BaseActivity {

    @BindView(R.id.msg_notice_switch)
    SwitchCompat noticeSwitch;
    @BindView(R.id.msg_vibration_switch)
    SwitchCompat vibrationSwitch;
    @BindView(R.id.msg_voice_switch)
    SwitchCompat voiceSwitch;
    @BindView(R.id.msg_disturb_switch)
    SwitchCompat disturbSwitch;

    @Override
    protected int LayoutId() {
        return R.layout.activity_set_msg;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("消息通知");
    }

}
