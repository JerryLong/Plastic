package com.conduit.plastic.ui.brand;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SectionedGridRecyclerViewAdapter;
import com.conduit.plastic.adapter.SimpleAdapter;
import com.conduit.plastic.entity.BrandAllEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.addbrand.AddBrandActivity;
import com.conduit.plastic.widget.MultiStateView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BrandActivity extends BaseFrameActivity<BrandPresenter, BrandModel> implements BrandContract.View/*, Toolbar.OnMenuItemClickListener*/ {

//    @BindView(R.id.brand_search_edit)
//    AppCompatEditText brandSearchEdit;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
//    @BindView(R.id.brand_top_layout)
//    FrameLayout topLayout;
    SimpleAdapter mAdapter;
    private int mPageIndex = 1;
    boolean mHideTop = false;
    List<BrandEntity> allList = new ArrayList<>();
    List<BrandEntity> northChinaList = new ArrayList<>();
    List<BrandEntity> eastChinaList = new ArrayList<>();
    List<BrandEntity> southChinaList = new ArrayList<>();
    List<BrandEntity> abroadList = new ArrayList<>();
    List<BrandEntity> taiwanList = new ArrayList<>();

    public static void navToBrand(Activity context, boolean isHideTop) {
        Intent intent = new Intent(context, BrandActivity.class);
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

//        if (mHideTop) {
//            topLayout.setVisibility(View.GONE);
//        } else {
//            topLayout.setVisibility(View.VISIBLE);
//        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 5));


    }

    @Override
    public void initListener() {
        super.initListener();

    }

//    @OnClick(R.id.brand_search_btn)
//    public void onClick() {
//        openActivity(AddBrandActivity.class);
//    }

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
                    northChinaList.addAll(brandAllEntity.getNorthChinaBrands());
                } else if (i == 1) {
                    eastChinaList.addAll(brandAllEntity.getEastChinaBrands());
                } else if (i == 2) {
                    southChinaList.addAll(brandAllEntity.getSouthChinaBrands());
                } else if (i == 3) {
                    abroadList.addAll(brandAllEntity.getAbroadBrands());
                } else if (i == 4) {
                    taiwanList.addAll(brandAllEntity.getTaiwanBrands());
                }
            }
            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            //Your RecyclerView.Adapter
            allList.addAll(northChinaList);
            allList.addAll(eastChinaList);
            allList.addAll(southChinaList);
            allList.addAll(abroadList);
            allList.addAll(taiwanList);
            mAdapter = new SimpleAdapter(this, allList);
            //This is the code to provide a sectioned grid
            List<SectionedGridRecyclerViewAdapter.Section> sections =
                    new ArrayList<SectionedGridRecyclerViewAdapter.Section>();

            //Sections
            sections.add(new SectionedGridRecyclerViewAdapter.Section(0, "管知优选"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section( abroadList.size(), "国外"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(taiwanList.size(), "台湾"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(eastChinaList.size(), "华东"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(northChinaList.size(), "华南"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(southChinaList.size(), "华北"));
            sections.add(new SectionedGridRecyclerViewAdapter.Section(southChinaList.size(), "胶水"));

            //Add your adapter to the sectionAdapter
            SectionedGridRecyclerViewAdapter.Section[] dummy = new SectionedGridRecyclerViewAdapter.Section[sections.size()];
            SectionedGridRecyclerViewAdapter mSectionedAdapter = new
                    SectionedGridRecyclerViewAdapter(this, R.layout.section_brand_header, R.id.session_header_tv, recyclerView, mAdapter);
            mSectionedAdapter.setSections(sections.toArray(dummy));

            //Apply this adapter to the RecyclerView
            recyclerView.setAdapter(mSectionedAdapter);
        }
    }

}
