package com.conduit.plastic.ui.mine;

import android.view.View;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.login.LoginActivity;
import com.conduit.plastic.ui.mine.contract.MineContract;
import com.conduit.plastic.ui.mine.contract.MineModel;
import com.conduit.plastic.ui.mine.contract.MinePresenter;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.util.ActivityUtils;
import com.conduit.plastic.util.DataManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseFrameActivity<MinePresenter, MineModel> implements MineContract.MineView {

    @BindView(R.id.setting_session)
    SuperTextView settingSession;

    @Override
    protected int LayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setBackTxt("我的");
        setTitleTxt("设置");
        String total = null;
        try {
            total = DataManager.getTotalCacheSize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        settingSession.setRightString(total);

    }


    @OnClick({R.id.setting_safe, R.id.setting_session, R.id.tab_mine_invitation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_safe:
                startActivity(SafeActivity.class);
                break;
            case R.id.setting_session:
                DataManager.clearAllCache(SettingActivity.this);
                settingSession.setRightString("0M");

                break;
            case R.id.tab_mine_invitation:
//                mPresenter.logout();
                UserUtils.getInstance().setSk(null);
                UserUtils.getInstance().clear();
                ActivityUtils.removeAllActivity();
                startActivity(LoginActivity.class);
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
    public void ordinary(BaseEntity baseEntity) {
        showShortToast("退出登录成功");
    }

    @Override
    public void modify(BaseEntity<UserInfo> baseEntity) {

    }

    @Override
    public void demandList(List<DemandEntity> baseEntity) {

    }
}
