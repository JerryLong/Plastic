package com.conduit.plastic.ui.release;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.util.ShareUtil;

public class FinishActivity extends BaseActivity {

    @Override
    protected int LayoutId() {
        return R.layout.activity_finish;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("联系管家");
        findViewById(R.id.finish_phone).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareUtil.callPhone(FinishActivity.this, "028-86271825");
                    }
                });
    }
}
