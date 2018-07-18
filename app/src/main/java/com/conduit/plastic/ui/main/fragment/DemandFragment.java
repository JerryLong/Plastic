package com.conduit.plastic.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.DemandAdapter;
import com.conduit.plastic.adapter.DemandTwoAdapter;
import com.conduit.plastic.decoration.SpacesItemDecoration;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameFragment;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.ui.main.activity.DemandPresenter;
import com.conduit.plastic.ui.main.activity.MainContract;
import com.conduit.plastic.ui.main.activity.MainModel;
import com.conduit.plastic.ui.release.PreviewActivity;
import com.conduit.plastic.util.ShareUtil;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DemandFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemandFragment extends BaseFrameFragment<DemandPresenter, MainModel> implements MainContract.DemandView {
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.search_view)
    EditText searchView;
    @BindView(R.id.search_confirm)
    SuperTextView searchBtn;
    @BindView(R.id.demand_recycler)
    RecyclerView demandRecycler;
    @BindView(R.id.demand_swipe)
    SwipeRefreshLayout demandSwipe;
    Unbinder unbinder;
    //    DemandAdapter mAdapter;
    DemandTwoAdapter mAdapter;
    List<DemandEntity> mList = new ArrayList<DemandEntity>();
    int index = 0;
    RequestParams requestParams = new RequestParams();
    View mView;

    public DemandFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DemandFragment.
     */
    public static DemandFragment newInstance() {
        DemandFragment fragment = new DemandFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mAdapter = new DemandAdapter(getActivity());
        mAdapter = new DemandTwoAdapter(getActivity());
        index = 1;
        requestParams.page = index;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_demand, container, false);
        unbinder = ButterKnife.bind(this, mView);
        toolbarTitle.setText("需求");
        demandRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        demandRecycler.addItemDecoration(new SpacesItemDecoration(0, 20));
        demandRecycler.setAdapter(mAdapter);
        mAdapter.bindToRecyclerView(demandRecycler);
        mAdapter.setEmptyView(R.layout.state_empty);
        mAdapter.setEnableLoadMore(false);
        searchBtn.setVisibility(View.VISIBLE);
        demandSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                index = 1;
                requestParams.page = 1;
                load();

            }
        });
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
                if (arg1 == EditorInfo.IME_ACTION_SEARCH) {
                    index = 1;
                    requestParams.page = 1;
                    load();
                }
                return false;
            }

        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.demandList(requestParams);
            }
        }, demandRecycler);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DemandEntity demand = mAdapter.getItem(position);
                PreviewActivity.openPreview(getContext(), demand, 0);
            }
        });
        mPresenter.demandList(requestParams);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index = 1;
                requestParams.page = 1;
                load();
            }
        });
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    void load() {
        ShareUtil.hideInput(getActivity(), mView);
        requestParams.content = searchView.getText().toString();
        mPresenter.demandList(requestParams);
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
        demandSwipe.setRefreshing(false);
        mAdapter.loadMoreComplete();
        mAdapter.loadMoreEnd();
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
            requestParams.page = index;
            mAdapter.addData(baseEntity);
        }
        demandSwipe.post(new Runnable() {
            @Override
            public void run() {
                demandSwipe.setRefreshing(false);
            }
        });

        mAdapter.loadMoreComplete();
        mAdapter.loadMoreEnd();
        mAdapter.notifyDataSetChanged();
    }
}
