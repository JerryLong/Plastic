package com.conduit.plastic.ui.seller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.SellerAdapter;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.ui.home.PersonalActivity;
import com.conduit.plastic.ui.release.ReleaseActivity;
import com.conduit.plastic.util.ToastUtils;
import com.plastic.xrecyclerview.decoration.SpacesItemDecoration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SellerTwoActivity extends BaseFrameActivity<SellerPresenter, SellerModel> implements SellerContract.View, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;
    @BindView(R.id.seller_productName)
    SuperTextView mProductName;
    @BindView(R.id.seller_texture)
    SuperTextView mTexture;
    @BindView(R.id.seller_width)
    SuperTextView mWidth;
    @BindView(R.id.seller_thickness)
    SuperTextView mThickness;
    @BindView(R.id.seller_diameter)
    SuperTextView mDiameter;
    @BindView(R.id.seller_proportion)
    SuperTextView mProportion;
    private int mPageIndex = 1;
    Map<String, Object> map;
    SellerAdapter mAdapter;
    boolean isMain = true;

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_seller_two;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("搜索列表");
        SerializableMap maps = (SerializableMap) getIntent().getExtras().getSerializable(Constants.Params.SearchParams);
        isMain = getIntent().getExtras().getBoolean(Constants.Params.SearchType, false);
        map = new HashMap<>();
        map = maps.getMap();
        mAdapter = new SellerAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration(0, 2, R.color.main_bg));
        mAdapter.bindToRecyclerView(recyclerView);
        mAdapter.setEmptyView(R.layout.state_empty);
        mAdapter.setEnableLoadMore(false);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SellerTwoActivity.this, PersonalActivity.class);
                intent.putExtra("companyId", mAdapter.getItem(position).getId());
                startActivity(intent);
            }
        });
        swipeLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (mAdapter.getData().size() >= (mPageIndex * 15)) {
                    mPageIndex++;
                    mPresenter.companyList(map, mPageIndex);
                }else {
                    mAdapter.setEnableLoadMore(false);
                    mAdapter.loadMoreComplete();
                    mAdapter.loadMoreEnd();
                }
            }
        }, recyclerView);


        if (map.containsKey("productName")) {
            mProductName.setLeftString(map.get("productName").toString());
        }
        if (map.containsKey("diameter")) {//直径
            mDiameter.setLeftString(map.get("diameter").toString()).setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
        }
        if (map.containsKey("width")) {//宽度
            mWidth.setLeftString(map.get("width").toString()).setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
        }
        if (map.containsKey("thickness")) {//厚度
            mThickness.setLeftString(map.get("thickness").toString()).setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
        }
        ToastUtils.showShort("===="+map.containsKey("proportion"));
        if (map.containsKey("proportion")) {//比重
            mProportion.setLeftString(map.get("proportion").toString()).setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
        }

        if (map.containsKey("texture")) {
            int texture = Integer.parseInt(map.get("texture").toString()) / 10 - 1;
            if (texture >= 0) {
                String textureStr = "";
                if (isMain) {
                    textureStr = getResources().getStringArray(R.array.texture_array)[texture];
                } else {
                    textureStr = getResources().getStringArray(R.array.broad_array)[texture];
                }
                mTexture.setLeftString(textureStr + "").setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
            }
        }

    }

    @Override
    public void initLoad() {
        super.initLoad();

        mPageIndex = 1;
        mPresenter.companyList(map, mPageIndex);
    }

    @Override
    public void initListener() {
        super.initListener();
        mTexture.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                mTexture.setRightIcon(null).setLeftString("");
                map.remove("texture");
                refresh();
            }
        });
        mWidth.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                mWidth.setRightIcon(null).setLeftString("");
                map.remove("width");
                refresh();
            }
        });
        mDiameter.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                mDiameter.setRightIcon(null).setLeftString("");
                map.remove("diameter");
                refresh();
            }
        });
        mThickness.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                mThickness.setRightIcon(null).setLeftString("");
                map.remove("thickness");
                refresh();
            }
        });
        mProportion.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                mProportion.setRightIcon(null).setLeftString("");
                map.remove("proportion");
                refresh();
            }
        });

    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String msg) {
        super.onRequestError(msg);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
                mAdapter.loadMoreComplete();
                mAdapter.loadMoreEnd();
            }
        });

    }

    @Override
    public void onRequestEnd() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
                mAdapter.loadMoreComplete();
            }
        });

    }

    void refresh() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeLayout.setRefreshing(false);
                swipeLayout.setRefreshing(true);
                onRefresh();
                mAdapter.loadMoreComplete();
            }
        });
    }

    @Override
    public void refreshList(List<SellerEntity> baseEntity) {
        if (baseEntity != null && baseEntity.size() >= Constants.PER_PAGE) {
            mAdapter.setEnableLoadMore(true);
        } else {
            mAdapter.setEnableLoadMore(false);
        }
        mAdapter.setNewData(baseEntity);
    }

    @Override
    public void loadMoreList(List<SellerEntity> baseEntity) {

        if (baseEntity != null && baseEntity.size() >= Constants.PER_PAGE) {

            mAdapter.setEnableLoadMore(true);
        } else {
            mAdapter.setEnableLoadMore(false);
        }
        mAdapter.addData(baseEntity);
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        mPresenter.companyList(map, mPageIndex);
    }

    @OnClick({R.id.seller_release, R.id.seller_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.seller_release:
                ReleaseActivity.openRelease(this, null, false);
                break;
            case R.id.seller_finish:
                finish();
                break;
        }
    }

}
