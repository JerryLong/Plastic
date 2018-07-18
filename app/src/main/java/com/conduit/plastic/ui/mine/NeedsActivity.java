package com.conduit.plastic.ui.mine;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.DemandAdapter;
import com.conduit.plastic.decoration.SpacesItemDecoration;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.detail.DetailActivity;
import com.conduit.plastic.ui.mine.contract.MineContract;
import com.conduit.plastic.ui.mine.contract.MineModel;
import com.conduit.plastic.ui.mine.contract.MinePresenter;
import com.conduit.plastic.ui.release.PreviewActivity;
import com.conduit.plastic.ui.release.ReleaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NeedsActivity extends BaseFrameActivity<MinePresenter, MineModel> implements MineContract.MineView {

    @BindView(R.id.needs_recycler)
    RecyclerView needsRecycler;
    @BindView(R.id.needs_swipe)
    SwipeRefreshLayout needsSwipe;
    DemandAdapter mAdapter;
    List<DemandEntity> mList = new ArrayList<DemandEntity>();
    int index = 0;

    @Override
    protected int LayoutId() {
        return R.layout.activity_needs;
    }



    @Override
    public void initData() {
        super.initData();
        index = 1;
        mPresenter.demandList(index);
    }

    @Override
    protected void onResume() {
        initData();
        super.onResume();
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("我的需求");
        index = 0;
        needsRecycler.setLayoutManager(new LinearLayoutManager(this));
        needsRecycler.addItemDecoration(new SpacesItemDecoration(0, 20));
        mAdapter = new DemandAdapter(this);
        needsRecycler.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(needsRecycler);
        mAdapter.setEmptyView(R.layout.state_empty);

        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                needsRecycler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPresenter.demandList(index);
                    }

                }, 1500);
            }
        }, needsRecycler);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DemandEntity demand = mAdapter.getItem(position);
                PreviewActivity.openPreview(NeedsActivity.this, demand, 2);
            }
        });
        needsSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
            }
        });
        needsSwipe.setRefreshing(true);
    }


    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {
        needsSwipe.setRefreshing(false);
        mAdapter.loadMoreComplete();
        mAdapter.loadMoreEnd();
    }

    @Override
    public void ordinary(BaseEntity baseEntity) {

    }

    @Override
    public void modify(BaseEntity<UserInfo> baseEntity) {

    }

    @Override
    public void demandList(List<DemandEntity> baseEntity) {
        if (index == 1) {

            if (baseEntity != null && baseEntity.size() <= Constants.PER_PAGE) {
                mAdapter.setEnableLoadMore(false);
            } else {
                mAdapter.setEnableLoadMore(true);
            }
            mAdapter.setNewData(baseEntity);
        } else {
            if (baseEntity != null && baseEntity.size() <= Constants.PER_PAGE) {
                showShortToast("没有更多数据了");
                mAdapter.setEnableLoadMore(false);
            } else {
                mAdapter.setEnableLoadMore(true);
            }
            index += 1;
            mAdapter.addData(baseEntity);
        }
        needsSwipe.post(new Runnable() {
            @Override
            public void run() {
                needsSwipe.setRefreshing(false);
            }
        });

        mAdapter.loadMoreComplete();
    }
}
