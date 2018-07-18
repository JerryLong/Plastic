package com.conduit.plastic.ui.area;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.AreaAdapter;
import com.conduit.plastic.adapter.base.MultiItemTypeAdapter;
import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.widget.MultiStateView;
import com.plastic.xrecyclerview.XRecyclerView;
import com.plastic.xrecyclerview.decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaActivity extends BaseFrameActivity<AreaPresenter, AreaModel> implements AreaContract.View {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    AreaAdapter mAdapter;
    List<AreaEntity> provice = new ArrayList<>();
    List<AreaEntity> city = new ArrayList<>();
    List<AreaEntity> county = new ArrayList<>();
    int index = 0;

    public static void navToArea(Activity context) {
        Intent intent = new Intent(context, AreaActivity.class);
        context.startActivityForResult(intent, Constants.Activity.AreaActivity);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_area;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("区域列表");
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, 2));
        mAdapter = new AreaAdapter(this);
        recyclerView.setAdapter(mAdapter);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void initListener() {
        super.initListener();
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object obj, int position) {
                AreaEntity entity = (AreaEntity) obj;
                if (index == 0) {
                    city.clear();
                    if (entity.getAreas().size() > 0) {
                        city.addAll(entity.getAreas());
                        mAdapter.setNewData(city);
                        index = 1;
                    } else {
                        initBack(entity);
                    }
                } else if (index == 1) {
                    county.clear();
                    if (entity.getAreas().size() > 0) {
                        county.addAll(entity.getAreas());
                        mAdapter.setNewData(county);
                        index = 2;
                    } else {
                        initBack(entity);
                    }
                } else if (index == 2) {
                    initBack(entity);
                }
            }
        });
    }

    void initBack(AreaEntity entity) {
        Intent intent = new Intent();
        intent.putExtra(Constants.Params.AreaParams, entity);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (index == 0) {
                finish();
            } else if (index == 1) {
                mAdapter.setNewData(provice);
                city.clear();
                index = 0;
            } else if (index == 2) {
                mAdapter.setNewData(city);
                county.clear();
                index = 1;
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initLoad() {
        super.initLoad();
        mPresenter.areaList();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onRequestStart() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void onRequestEnd() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void areaList(BaseEntity<List<AreaEntity>> baseEntity) {
        mAdapter.setNewData(baseEntity.getData());
    }
}
