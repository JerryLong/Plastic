package com.conduit.plastic.ui.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SearchAdapter;
import com.conduit.plastic.adapter.base.MultiItemTypeAdapter;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.ui.home.HomeActivity;
import com.conduit.plastic.widget.MultiStateView;
import com.melnykov.fab.FloatingActionButton;
import com.orhanobut.logger.Logger;
import com.plastic.xrecyclerview.XRecyclerView;
import com.plastic.xrecyclerview.decoration.SpacesItemDecoration;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseFrameActivity<SearchPresenter, SearchModel> implements SearchContract.View, Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolBar)
    Toolbar mToolBar;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private int mPageIndex = 1;
    SearchAdapter mAdapter;
    Map<String, Object> map = new HashMap<>();


    @Override
    protected int LayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("搜索列表");
        mToolBar.inflateMenu(R.menu.menu_confirm);
        mAdapter = new SearchAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, 2, R.color.bg_gray));
        recyclerView.setAdapter(mAdapter);
        fab.attachToRecyclerView(recyclerView);
        mAdapter.setHeadCount(recyclerView.getHeadCount());
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object o, int position) {
                openActivity(HomeActivity.class);

            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
                recyclerView.refresh();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        SerializableMap maps = (SerializableMap) getIntent().getExtras().getSerializable(Constants.Params.SearchParams);
        map = maps.getMap();
        Logger.i("=====" + map.get("standard"));


        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void initListener() {
        super.initListener();

        mToolBar.setOnMenuItemClickListener(this);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                startRotate();
                Logger.i("==20===" + map.get("standard"));
                mPageIndex = 1;
                mPresenter.serachList(map, mPageIndex);


            }

            @Override
            public void onLoadMore() {
                mPageIndex++;
                mPresenter.serachList(map, mPageIndex);

            }
        });
        recyclerView.refresh();
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String msg) {
        Logger.i("onRequestError" + msg);
        stopRotate();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onRequestEnd() {
        Logger.i("onRequestEnd");
        stopRotate();
        recyclerView.refreshComplete();
        recyclerView.loadMoreComplete();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);

    }

//    @Override
//    public void refreshList(List<GoodsEntity> goodsEntities) {
//
//        mAdapter.setNewData(goodsEntities);
//    }
//
//    @Override
//    public void loadMoreList(List<GoodsEntity> goodsEntities) {
//        mAdapter.setMoreData(goodsEntities);
//    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_confirm:
                openActivity(HistoryActivity.class);
                break;
        }
        return false;
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
    public void refreshList(BaseEntity userMessageEntity) {

    }

    @Override
    public void loadMoreList(BaseEntity userMessageEntity) {

    }
}
