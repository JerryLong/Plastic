package com.conduit.plastic.ui.search;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SearchAdapter;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.widget.MultiStateView;
import com.plastic.xrecyclerview.XRecyclerView;
import com.plastic.xrecyclerview.decoration.SpacesItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryActivity extends BaseFrameActivity<SearchPresenter, SearchModel> implements SearchContract.View {

    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    private int mPageIndex = 1;
    SearchAdapter mAdapter;


    @Override
    protected int LayoutId() {
        return R.layout.activity_history;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("历史记录");
        mAdapter = new SearchAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, 2, R.color.bg_gray));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        super.initData();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.refresh();
            }
        }, 1000);
    }

    @Override
    public void initListener() {
        super.initListener();
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPageIndex = 1;
                        mPresenter.serachHistory(mPageIndex);
                    }
                }, 1000);


            }

            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPageIndex++;
                        mPresenter.serachHistory(mPageIndex);
                    }
                }, 1000);

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
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);

    }

    @Override
    public void refreshList(BaseEntity userMessageEntity) {

    }

    @Override
    public void loadMoreList(BaseEntity userMessageEntity) {

    }

//    @Override
//    public void refreshList(List<GoodsEntity> goodsEntities) {
//        Toast.makeText(getApplicationContext(), "===" + goodsEntities.size(), Toast.LENGTH_SHORT).show();
//        mAdapter.setNewData(goodsEntities);
//    }
//
//    @Override
//    public void loadMoreList(List<GoodsEntity> goodsEntities) {
//        if (goodsEntities.size() != 0)
//            mAdapter.setMoreData(goodsEntities);
//        else
//            recyclerView.setLoadingMoreEnabled(false);
//    }
}
