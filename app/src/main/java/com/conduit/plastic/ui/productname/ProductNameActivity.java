package com.conduit.plastic.ui.productname;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.ProductNameAdapter;
import com.conduit.plastic.adapter.base.MultiItemTypeAdapter;
import com.conduit.plastic.entity.ProductNameAllEntity;
import com.conduit.plastic.entity.ProductNameEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.widget.MultiStateView;
import com.plastic.xrecyclerview.XRecyclerView;
import com.plastic.xrecyclerview.decoration.SpacesItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductNameActivity extends BaseFrameActivity<ProductNamePresenter, ProductNameModel> implements ProductNameContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;
    ProductNameAdapter mAdapter;
    List<ProductNameEntity> level1 = new ArrayList<>();
    List<ProductNameEntity> level2 = new ArrayList<>();
    List<ProductNameEntity> level3 = new ArrayList<>();
    int index = 0;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    public static void navToProductName(Activity context) {
        Intent intent = new Intent(context, ProductNameActivity.class);
        context.startActivityForResult(intent, Constants.Activity.ProductNameActivity);
    }


    @Override
    public void initData() {
        super.initData();

    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_product_name;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("品名列表");
//        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, 2));
        mAdapter = new ProductNameAdapter();
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        super.initListener();
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.productName();
            }
        });
//        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
//            @Override
//            public void onRefresh() {
//                mPresenter.productName();
//            }
//
//            @Override
//            public void onLoadMore() {
//
//            }
//        });
//        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, RecyclerView.ViewHolder holder, Object obj, int position) {
//                ProductNameEntity entity = (ProductNameEntity) obj;
//                Log.i("aaa  ", "  " + entity.getProductName());
//                if (index == 0) {
//                    level2.clear();
//                    Log.i("aaa 111 ", "  " + entity.getProductName());
//                    if (entity.getProductNames().size() > 0) {
//                        level2.add(new ProductNameEntity(entity.getProductName(), entity.getId(), new ArrayList<ProductNameEntity>()));
//                        level2.addAll(entity.getProductNames());
//                        mAdapter.setNewData(level2);
//                        index = 1;
//                    } else {
//                        ToastUtils.showShort("暂无数据");
//                    }
//                } else if (index == 1) {
//                    Log.i("aaa 222 ", "  " + entity.getProductName());
//                    level3.clear();
//                    if (entity.getProductNames().size() > 0) {
//                        level3.add(new ProductNameEntity(entity.getProductName(), entity.getId(), new ArrayList<ProductNameEntity>()));
//                        level3.addAll(entity.getProductNames());
//                        mAdapter.setNewData(level3);
//                        index = 2;
//                    } else {
//                        initBack(entity);
//                    }
//                } else if (index == 2) {
//                    initBack(entity);
//                }
//            }
//        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProductNameEntity entity = (ProductNameEntity) adapter.getData().get(position);
                if (index == 0) {
                    level2.clear();
                    if (entity.getProductNames().size() > 0) {
                        level2.add(new ProductNameEntity(entity.getProductName(), entity.getId(), new ArrayList<ProductNameEntity>()));
                        level2.addAll(entity.getProductNames());
                        mAdapter.setNewData(level2);
                        index = 1;
                    } else {
                        ToastUtils.showShort("暂无数据");
                    }
                } else if (index == 1) {
                    level3.clear();
                    if (entity.getProductNames().size() > 0) {
                        level3.add(new ProductNameEntity(entity.getProductName(), entity.getId(), new ArrayList<ProductNameEntity>()));
                        level3.addAll(entity.getProductNames());
                        mAdapter.setNewData(level3);
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

    void initBack(ProductNameEntity entity) {
        if (entity != null) {
            Intent intent = new Intent();
            intent.putExtra(Constants.Params.ProductNameParams, entity);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            if (index == 0) {
                finish();
            } else if (index == 1) {
                mAdapter.setNewData(level1);
                level2.clear();
                index = 0;
            } else if (index == 2) {
                mAdapter.setNewData(level2);
                level3.clear();
                index = 1;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initLoad() {
        super.initLoad();
        mPresenter.productName();
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (index == 0) {
            finish();
        } else if (index == 1) {
            mAdapter.setNewData(level1);
            level2.clear();
            index = 0;
        } else if (index == 2) {
            mAdapter.setNewData(level2);
            level3.clear();
            index = 1;
        }
        return true;
    }

    @Override
    public void onRequestStart() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    @Override
    public void onRequestEnd() {
        swipeLayout.setRefreshing(false);
//        recyclerView.refreshComplete();
//        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void productNameList(ProductNameAllEntity baseEntity) {

        if (baseEntity == null) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
        } else {
            List<String> list = Arrays.asList(getResources().getStringArray(R.array.productName_array));
            level1.clear();
            level1.add(new ProductNameEntity(list.get(0), list.get(0), baseEntity.getProductNames1()));
            level1.add(new ProductNameEntity(list.get(1), list.get(1), baseEntity.getProductNames2()));
            level1.add(new ProductNameEntity(list.get(2), list.get(2), baseEntity.getProductNames3()));
            level1.add(new ProductNameEntity(list.get(3), list.get(3), baseEntity.getProductNames6()));
            level1.add(new ProductNameEntity(list.get(4), list.get(4), baseEntity.getProductNames7()));
//            level1.add(new ProductNameEntity(list.get(3), list.get(3), baseEntity.getProductNames4()));
//            level1.add(new ProductNameEntity(list.get(4), list.get(4), baseEntity.getProductNames5()));
//            level1.add(new ProductNameEntity(list.get(5), list.get(5), baseEntity.getProductNames6()));
//            level1.add(new ProductNameEntity(list.get(6), list.get(6), baseEntity.getProductNames7()));

            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            mAdapter.setNewData(level1);
        }
    }

}
