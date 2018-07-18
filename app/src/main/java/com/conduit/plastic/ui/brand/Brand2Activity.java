package com.conduit.plastic.ui.brand;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SectionedGridRecyclerViewAdapter;
import com.conduit.plastic.adapter.SimpleAdapter;
import com.conduit.plastic.adapter.expandgrid.ItemClickListener;
import com.conduit.plastic.adapter.expandgrid.Section;
import com.conduit.plastic.adapter.expandgrid.SectionedExpandableLayoutHelper;
import com.conduit.plastic.entity.BrandAllEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.widget.MultiStateView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jim Long on 2018/5/3.
 */

public class Brand2Activity extends BaseFrameActivity<BrandPresenter, BrandModel> implements BrandContract.View, ItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    SectionedExpandableLayoutHelper mSExpandableHelper;
    private int mPageIndex = 1;
    boolean mHideTop = false;

    public static void navToBrand(Activity context, boolean isHideTop) {
        Intent intent = new Intent(context, Brand2Activity.class);
        intent.putExtra(Constants.Params.BrandParams, isHideTop);
        context.startActivityForResult(intent, Constants.Activity.BrandActivity);
    }


    @Override
    public void initData() {
        super.initData();
        mHideTop = getIntent().getBooleanExtra(Constants.Params.BrandParams, false);
    }

    @Override
    public void initLoad() {
        super.initLoad();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        mPresenter.brandList(1);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_brand;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("品牌列表");
        mSExpandableHelper = new SectionedExpandableLayoutHelper(this,
                mRecyclerView, this, 5);
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {
    }

    @Override
    public void onRequestError(String msg) {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }


    @Override
    public void brandList(BrandAllEntity brandAllEntity) {
        if (brandAllEntity == null) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        } else {
            List<String> list = Arrays.asList(getResources().getStringArray(R.array.area_array));
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    mSExpandableHelper.addSection(list.get(i), true, brandAllEntity.getRecomChinaBrands());
                } else if (i == 1) {
                    mSExpandableHelper.addSection(list.get(i), brandAllEntity.getAbroadBrands());
                } else if (i == 2) {
                    mSExpandableHelper.addSection(list.get(i), brandAllEntity.getTaiwanBrands());
                } else if (i == 3) {
                    mSExpandableHelper.addSection(list.get(i), brandAllEntity.getEastChinaBrands());
                } else if (i == 4) {
                    mSExpandableHelper.addSection(list.get(i), brandAllEntity.getSouthChinaBrands());
                } else if (i == 5) {
                    mSExpandableHelper.addSection(list.get(i), brandAllEntity.getNorthChinaBrands());
                } else if (i == 6) {
                    mSExpandableHelper.addSection(list.get(i), brandAllEntity.getGlueBrands());
                }
            }

            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            mSExpandableHelper.notifyDataSetChanged();
        }
    }

    @Override
    public void itemClicked(BrandEntity item) {
        Intent intent = new Intent();
        intent.putExtra(Constants.Params.BrandParams, item);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void itemClicked(Section section) {

    }
}
