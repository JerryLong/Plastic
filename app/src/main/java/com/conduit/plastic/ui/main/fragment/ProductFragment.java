package com.conduit.plastic.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.RecommendAdapter;
import com.conduit.plastic.decoration.SpacesItemDecoration;
import com.conduit.plastic.entity.RecommendEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameFragment;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.ui.home.PersonalActivity;
import com.conduit.plastic.ui.main.activity.MainContract;
import com.conduit.plastic.ui.main.activity.MainModel;
import com.conduit.plastic.ui.main.activity.RecommendPresenter;
import com.conduit.plastic.util.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends BaseFrameFragment<RecommendPresenter, MainModel> implements MainContract.RecommendView {
    private static final String ARG_PARAM1 = "param1";
    Unbinder unbinder;
    @BindView(R.id.product_home)
    RadioButton productHome;
    @BindView(R.id.product_borad)
    RadioButton productBorad;
    @BindView(R.id.product_tw)
    RadioButton productTw;
    @BindView(R.id.product_group)
    RadioGroup productGroup;
    @BindView(R.id.product_recycler)
    RecyclerView productRecycler;
    private int mProduct = 5;
    private int index = 1;
    RecommendAdapter mRecommendAdapter;
    RequestParams params = new RequestParams();

    public ProductFragment() {

    }

    public static ProductFragment newInstance(int position) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProduct = getArguments().getInt(ARG_PARAM1);
        }
        mRecommendAdapter = new RecommendAdapter();
        switch (mProduct) {
            case 0:
                params.productType = "5";
                break;
            case 1:
                params.productType = "10";
                break;
            case 2:
                params.productType = "20";
                break;
            case 3:
                params.productType = "30";
                break;
            case 4:
                params.productType = "70";
                break;
            default:
                params.region = "10";
                index = 1;
                break;
        }
        index = 1;
        params.page = index;
        params.region = "10";
        mPresenter.recommendList(params);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null, false);
        unbinder = ButterKnife.bind(this, view);
        productRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        productRecycler.addItemDecoration(new SpacesItemDecoration(0, 2));
        mRecommendAdapter.bindToRecyclerView(productRecycler);
        mRecommendAdapter.setEmptyView(R.layout.state_empty);
        productRecycler.setAdapter(mRecommendAdapter);
        productGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.product_home:
                        params.region = "10";
                        break;
                    case R.id.product_tw:
                        params.region = "40";
                        break;
                    case R.id.product_borad:
                        params.region = "50";
                        break;
                }
                index = 1;
                params.page = index;
                mPresenter.recommendList(params);
            }
        });
        mRecommendAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                index += 1;
                params.page = index;
                mPresenter.recommendList(params);
            }
        }, productRecycler);
        mRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                intent.putExtra("companyId", mRecommendAdapter.getItem(position).getBaseCompanyId());
                startActivity(intent);
            }
        });
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {
        mRecommendAdapter.loadMoreEnd();
    }

    @Override
    public void recommendList(List<RecommendEntity> baseEntity) {
        if (baseEntity.size() >= Constants.PER_PAGE) {
            mRecommendAdapter.setEnableLoadMore(true);
        } else {
            mRecommendAdapter.setEnableLoadMore(false);
        }
        if (index == 1) {
            mRecommendAdapter.setNewData(baseEntity);
        } else {
            mRecommendAdapter.addData(baseEntity);
        }
        mRecommendAdapter.loadMoreComplete();

    }
}
