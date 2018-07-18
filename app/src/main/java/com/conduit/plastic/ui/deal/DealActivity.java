package com.conduit.plastic.ui.deal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SellerAdapter;
import com.conduit.plastic.adapter.base.MultiItemTypeAdapter;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.ui.home.HomeActivity;
import com.conduit.plastic.widget.MultiStateView;
import com.plastic.xrecyclerview.XRecyclerView;
import com.plastic.xrecyclerview.decoration.SpacesItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DealActivity extends BaseFrameActivity<DealPresenter, DealModel> implements DealContract.View {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    private int mPageIndex = 1;
    SellerAdapter mAdapter;
    Map<String, Object> map = new HashMap<>();


    @Override
    public void initData() {
        super.initData();
        SerializableMap maps = (SerializableMap) getIntent().getExtras().getSerializable(Constants.Params.DealParams);
        map = maps.getMap();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_deal;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("处理产品");
        mAdapter = new SellerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, 2, R.color.main_bg));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setPullRefreshEnabled(true);
//        mAdapter.setHeadCount(recyclerView.getHeadCount());
//        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
//                SellerEntity entity = (SellerEntity) o;
//                Intent intent = new Intent(DealActivity.this, HomeActivity.class);
//                SerializableMap serializableMap = new SerializableMap();
//                serializableMap.setMap(map);
//                intent.putExtra(Constants.Params.SearchParams, serializableMap);
//                intent.putExtra(Constants.Params.HomeParams, entity);
//                startActivity(intent);
//
//            }
//        });
    }

    @Override
    public void initLoad() {
        super.initLoad();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        recyclerView.refresh();
    }

    @Override
    public void initListener() {
        super.initListener();
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
                mPresenter.handleProduct(map, mPageIndex);

            }

            @Override
            public void onLoadMore() {
                mPageIndex++;
                mPresenter.handleProduct(map, mPageIndex);

            }
        });
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String msg) {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onRequestEnd() {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
//        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);

    }


//    @Override
//    public void refreshList(List<Product> baseEntity) {
//
//    }
//
//    @Override
//    public void loadMoreList(List<Product> baseEntity) {
//        mAdapter.setMoreData(baseEntity);
//    }

    @Override
    public void refreshList(List<SellerEntity> baseEntity) {
        if (baseEntity == null) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        } else {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            mAdapter.setNewData(baseEntity);
        }
    }

    @Override
    public void loadMoreList(List<SellerEntity> baseEntity) {
//        mAdapter.setMoreData(baseEntity);
    }
}
