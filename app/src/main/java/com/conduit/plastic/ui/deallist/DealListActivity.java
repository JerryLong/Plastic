package com.conduit.plastic.ui.deallist;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SellerAdapter;
import com.conduit.plastic.adapter.base.MultiItemTypeAdapter;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.widget.MultiStateView;
import com.melnykov.fab.FloatingActionButton;
import com.plastic.xrecyclerview.XRecyclerView;
import com.plastic.xrecyclerview.decoration.SpacesItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DealListActivity extends BaseFrameActivity<DealListPresenter, DealListModel> implements DealListContract.View/*, Toolbar.OnMenuItemClickListener*/ {


    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    SellerAdapter mAdapter;
    private int mPageIndex = 1;
    Map<String, Object> map = new HashMap<>();


    @Override
    public void initData() {
        super.initData();
        SerializableMap maps = (SerializableMap) getIntent().getExtras().getSerializable(Constants.Params.SearchParams);
        map = maps.getMap();
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_seller;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("搜索列表");
        mAdapter = new SellerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, 2, R.color.main_bg));
        recyclerView.setEmptyView(LayoutInflater.from(this).inflate(R.layout.state_empty, null));
        recyclerView.setAdapter(mAdapter);
        fab.attachToRecyclerView(recyclerView);
//        mAdapter.setHeadCount(recyclerView.getHeadCount());
//        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
//                SellerEntity entity = (SellerEntity) o;
////                Intent intent = new Intent(SellerActivity.this, HomeActivity.class);
////                SerializableMap serializableMap
//
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
                startRotate();
                recyclerView.refresh();
            }
        });
    }

    @Override
    public void initLoad() {
        super.initLoad();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        mPageIndex = 1;
    }

    @Override
    public void initListener() {
        super.initListener();
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mPageIndex = 1;
            }

            @Override
            public void onLoadMore() {
                mPageIndex++;
            }
        });

    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);
        stopRotate();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onRequestEnd() {
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
    }


    public void startRotate() {
        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            fab.startAnimation(operatingAnim);
        }
    }

    /**
     * 关闭动画
     */
    public void stopRotate() {
        fab.clearAnimation();
    }


    @Override
    public void refreshList(List<SellerEntity> baseEntity) {
        if (baseEntity == null) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        } else {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            mAdapter.setNewData(baseEntity);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loadMoreList(List<SellerEntity> baseEntity) {
//        mAdapter.setMoreData(baseEntity);
    }

}
