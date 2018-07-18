package com.conduit.plastic.ui.info;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.VPagerAdapter;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.ui.info.fragment.InfoCompanyFragment;
import com.conduit.plastic.ui.info.fragment.InfoContactFragment;
import com.conduit.plastic.ui.info.fragment.InfoPersonFragment;
import com.conduit.plastic.view.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoEditActivity extends BaseActivity {
    @BindView(R.id.info_edit_tabs)
    SlidingTabLayout infoEditTabs;
    @BindView(R.id.info_edit_vpager)
    ViewPager infoEditVpager;
    private VPagerAdapter mAdapter;
    private String[] mTitles = {"基本信息", "企业信息", "联系方式"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    InfoPersonFragment personFragment;
    InfoCompanyFragment companyFragment;


    @Override
    protected int LayoutId() {
        return R.layout.activity_info_edit;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt(getString(R.string.app_edit_info));
        personFragment = InfoPersonFragment.newInstance(0, mTitles[0]);
        companyFragment = InfoCompanyFragment.newInstance(1, mTitles[1]);
        mFragments.add(personFragment);
        mFragments.add(companyFragment);
        mFragments.add(InfoContactFragment.newInstance(2, mTitles[2]));
        mAdapter = new VPagerAdapter(getSupportFragmentManager(), mFragments, mTitles);
        infoEditVpager.setAdapter(mAdapter);
        infoEditTabs.setViewPager(infoEditVpager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.Activity.BrandActivity) {
            companyFragment.onActivityResult(requestCode, resultCode, data);
        }

    }
}
