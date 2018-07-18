package com.conduit.plastic.ui.attribute;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.TagAdapteer;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.ParamsEntity;
import com.conduit.plastic.entity.StringEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.ui.attribute.adapter.ParamsAdapter;
import com.conduit.plastic.ui.attribute.contracts.AttrContract;
import com.conduit.plastic.ui.attribute.contracts.AttrModel;
import com.conduit.plastic.ui.attribute.contracts.AttrPresenter;
import com.conduit.plastic.ui.seller.SellerActivity;
import com.conduit.plastic.ui.seller.SellerTwoActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BoardActivity extends BaseFrameActivity<AttrPresenter, AttrModel> implements AttrContract.AttrView {

    @BindView(R.id.spec_two)
    Button mSpecTwo;
    @BindView(R.id.spec_recycler)
    RecyclerView mSpecRecycler;
    @BindView(R.id.width_recycler)
    RecyclerView mWidthRecycler;
    @BindView(R.id.thickness_recycler)
    RecyclerView mThicknessRecycler;
    @BindView(R.id.proportion_recycler)
    RecyclerView mProportionRecycler;
    private List<StringEntity> mSpec = new ArrayList<>();
    private List<StringEntity> mSpec1 = new ArrayList<>();
    private List<String> mSpec2;// = new ArrayList<>();
    private List<String> mSpec3;// = new ArrayList<>();
    private ParamsAdapter mWidthAdapter, mThicknessAdapter, mProportionAdapter;
    private TagAdapteer mTagAdapter;
    private int mSpecSelected = -1, mWidthSelected = -1, mThicknessSelected = -1, mProportionSelected = -1;
    private List<ParamsEntity.ProportionsBean> proportions = new ArrayList<>();
    private List<ParamsEntity.ProportionsBean> diameters = new ArrayList<>();
    private List<ParamsEntity.ProportionsBean> thickneses = new ArrayList<>();
    private List<ParamsEntity.ProportionsBean> widths = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();

    @Override
    protected int LayoutId() {
        return R.layout.activity_board;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("板材");

    }

    void inits() {
        mPresenter.boardList();
        String[] spec = getResources().getStringArray(R.array.broad_array);
        String[] spec1 = getResources().getStringArray(R.array.broad_cn_array);
        String[] spec2 = getResources().getStringArray(R.array.texture_array);
        String[] spec3 = getResources().getStringArray(R.array.texture_cn_array);
        mSpec2 = new ArrayList<>();
        mSpec3 = new ArrayList<>();
        mSpec2 = Arrays.asList(spec2);
        mSpec3 = Arrays.asList(spec3);
        mSpec.clear();
        mSpec1.clear();
        for (int i = 0; i < spec.length; i++) {
            mSpec.add(new StringEntity(i, spec[i], false));
            mSpec1.add(new StringEntity(i, spec1[i], false));
        }
        map=new HashMap<>();
        mTagAdapter = new TagAdapteer();
        mWidthAdapter = new ParamsAdapter();
        mThicknessAdapter = new ParamsAdapter();
        mProportionAdapter = new ParamsAdapter();
        mSpecRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mWidthRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mThicknessRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mProportionRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mSpecRecycler.setAdapter(mTagAdapter);
        mWidthRecycler.setAdapter(mWidthAdapter);
        mThicknessRecycler.setAdapter(mThicknessAdapter);
        mProportionRecycler.setAdapter(mProportionAdapter);
        mTagAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpecSelected == -1) {
                    mTagAdapter.getData().get(position).setSelected(true);
                    mSpecSelected = position;
                } else {
                    if (mTagAdapter.getData().get(position).isSelected()) {
                        mTagAdapter.getData().get(mSpecSelected).setSelected(false);
                        mSpecSelected = -1;
                    } else {
                        mTagAdapter.getData().get(mSpecSelected).setSelected(false);
                        mTagAdapter.getData().get(position).setSelected(true);
                        mSpecSelected = position;
                    }
                }
                if (mSpecSelected == -1) {
                    if (map.containsKey("texture")) {
                        map.remove("texture");
                    }
                } else {
                    int indexsss = 0;
                    if (mSpecTwo.isSelected()) {
                        indexsss = ((mSpec3.indexOf(mTagAdapter.getData().get(position).getTitle()) + 1) * 10);
                    } else {
                        indexsss = ((mSpec2.indexOf(mTagAdapter.getData().get(position).getTitle()) + 1) * 10);
                    }
                    map.put("texture", String.valueOf(indexsss));
                }

                mTagAdapter.setNewData(mTagAdapter.getData());
            }
        });
        mWidthAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mWidthSelected == -1) {
                    mWidthAdapter.getData().get(position).setSelected(true);
                    mWidthSelected = position;
                } else {
                    if (mWidthAdapter.getData().get(position).isSelected()) {
                        mWidthAdapter.getData().get(mWidthSelected).setSelected(false);
                        mWidthSelected = -1;
                    } else {
                        mWidthAdapter.getData().get(mWidthSelected).setSelected(false);
                        mWidthAdapter.getData().get(position).setSelected(true);
                        mWidthSelected = position;
                    }
                }
                if (mWidthSelected == -1) {
                    if (map.containsKey("width")) {
                        map.remove("width");
                    }
                } else {
                    map.put("width", mWidthAdapter.getData().get(position).getWidth());
                }
                mWidthSelected = position;
                mWidthAdapter.setNewData(mWidthAdapter.getData());
            }
        });
        mThicknessAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mThicknessSelected == -1) {
                    mThicknessAdapter.getData().get(position).setSelected(true);
                    mThicknessSelected = position;
                } else {
                    if (mThicknessAdapter.getData().get(position).isSelected()) {
                        mThicknessAdapter.getData().get(mThicknessSelected).setSelected(false);
                        mThicknessSelected = -1;
                    } else {
                        mThicknessAdapter.getData().get(mThicknessSelected).setSelected(false);
                        mThicknessAdapter.getData().get(position).setSelected(true);
                        mThicknessSelected = position;
                    }
                }
                if (mThicknessSelected == -1) {
                    if (map.containsKey("thickness")) {
                        map.remove("thickness");
                    }
                } else {
                    map.put("thickness", mThicknessAdapter.getData().get(position).getThickness());
                }
                mThicknessAdapter.setNewData(thickneses);
            }
        });
        mProportionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mProportionSelected == -1) {
                    mProportionAdapter.getData().get(position).setSelected(true);
                    mProportionSelected = position;
                } else {
                    if (mProportionAdapter.getData().get(position).isSelected()) {
                        mProportionAdapter.getData().get(mProportionSelected).setSelected(false);
                        mProportionSelected = -1;
                    } else {
                        mProportionAdapter.getData().get(mProportionSelected).setSelected(false);
                        mProportionAdapter.getData().get(position).setSelected(true);
                        mProportionSelected = position;
                    }
                }
                if (mProportionSelected == -1) {
                    if (map.containsKey("proportion")) {
                        map.remove("proportion");
                    }
                } else {
                    map.put("proportion", mProportionAdapter.getData().get(position).getProportion());
                }

                mProportionAdapter.setNewData(proportions);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        inits();
    }

    @OnClick({R.id.spec_fold, R.id.spec_two, R.id.width_fold, R.id.thickness_fold, R.id.proportion_fold, R.id.borad_confirm})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.spec_fold:
                if (mSpecRecycler.getVisibility() == View.VISIBLE) {
                    mSpecRecycler.setVisibility(View.GONE);
                } else {
                    mSpecRecycler.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.width_fold:
                if (mWidthRecycler.getVisibility() == View.VISIBLE) {
                    mWidthRecycler.setVisibility(View.GONE);
                } else {
                    mWidthRecycler.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.thickness_fold:
                if (mThicknessRecycler.getVisibility() == View.VISIBLE) {
                    mThicknessRecycler.setVisibility(View.GONE);
                } else {
                    mThicknessRecycler.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.proportion_fold:
                if (mProportionRecycler.getVisibility() == View.VISIBLE) {
                    mProportionRecycler.setVisibility(View.GONE);
                } else {
                    mProportionRecycler.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.spec_two:
                if (mSpecSelected != -1) {
                    mTagAdapter.getData().get(mSpecSelected).setSelected(false);
                }
                if (mSpecTwo.isSelected()) {
                    mSpecTwo.setSelected(false);
//                    mSpec1.get(mSpecSelected).setSelected(false);
                    mTagAdapter.setNewData(mSpec);
                } else {
//                    mSpec.get(mSpecSelected).setSelected(false);
                    mTagAdapter.setNewData(mSpec1);
                    mSpecTwo.setSelected(true);
                }
                mSpecSelected = -1;
                break;
            case R.id.borad_confirm:
                SerializableMap serializableMap = new SerializableMap();
                if (map.containsKey("productName")) {
                    map.remove("productName");
                }
                map.put("productName", "板材");
                serializableMap.setMap(map);
                Intent intent = new Intent(this, SellerTwoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.Params.SearchParams, serializableMap);
                bundle.putBoolean(Constants.Params.SearchType, false);
                intent.putExtras(bundle);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void initData() {
        super.initData();


    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }


    @Override
    public void paramsList(ParamsEntity baseEntity) {
        widths = baseEntity.getWidths();
        thickneses = baseEntity.getThickneses();
        proportions = baseEntity.getProportions();

        mWidthAdapter.setNewData(widths);
        mThicknessAdapter.setNewData(thickneses);
        mProportionAdapter.setNewData(proportions);
        mTagAdapter.setNewData(mSpec);
    }
}
