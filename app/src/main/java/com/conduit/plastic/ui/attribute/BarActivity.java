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
import com.conduit.plastic.ui.area.AreaModel;
import com.conduit.plastic.ui.attribute.adapter.ParamsAdapter;
import com.conduit.plastic.ui.attribute.contracts.AttrContract;
import com.conduit.plastic.ui.attribute.contracts.AttrModel;
import com.conduit.plastic.ui.attribute.contracts.AttrPresenter;
import com.conduit.plastic.ui.seller.SellerActivity;
import com.conduit.plastic.ui.seller.SellerTwoActivity;
import com.conduit.plastic.util.ToastUtils;

import org.litepal.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class BarActivity extends BaseFrameActivity<AttrPresenter, AttrModel> implements AttrContract.AttrView {

    @BindView(R.id.spec_two)
    Button mSpecTwo;
    @BindView(R.id.spec_recycler)
    RecyclerView mSpecRecycler;
    @BindView(R.id.width_recycler)
    RecyclerView mWidthRecycler;
    @BindView(R.id.proportion_recycler)
    RecyclerView mProportionRecycler;
    private List<StringEntity> mSpec;// = new ArrayList<>();
    private List<StringEntity> mSpec1;// = new ArrayList<>();
    private List<String> mSpec2;// = new ArrayList<>();
    private List<String> mSpec3;// = new ArrayList<>();
    private ParamsAdapter mDiametersAdapter, mProportionAdapter;
    private TagAdapteer mTagAdapter;
    private int mSpecSelected = -1, mDiametersSelected = -1, mProportionSelected = -1;
    private List<ParamsEntity.ProportionsBean> proportions = new ArrayList<>();
    private List<ParamsEntity.ProportionsBean> diameters = new ArrayList<>();
    Map<String, Object> map;//= new HashMap<>();

    @Override
    protected int LayoutId() {
        return R.layout.activity_bar;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("棒材");

    }

    void initss() {
        map = new HashMap<>();
        mPresenter.stickList();
        String[] spec = getResources().getStringArray(R.array.broad_array);
        String[] spec1 = getResources().getStringArray(R.array.broad_cn_array);
        String[] spec2 = getResources().getStringArray(R.array.texture_array);
        String[] spec3 = getResources().getStringArray(R.array.texture_cn_array);
        mSpec = new ArrayList<>();
        mSpec1 = new ArrayList<>();
        mSpec2 = new ArrayList<>();
        mSpec3 = new ArrayList<>();
        for (int i = 0; i < spec.length; i++) {
            mSpec.add(new StringEntity(i, spec[i], false));
            mSpec1.add(new StringEntity(i, spec1[i], false));
        }
        mSpec2 = Arrays.asList(spec2);
        mSpec3 = Arrays.asList(spec3);
        mTagAdapter = new TagAdapteer();
        mDiametersAdapter = new ParamsAdapter();

        mProportionAdapter = new ParamsAdapter();
        mSpecRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mWidthRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mProportionRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mSpecRecycler.setAdapter(mTagAdapter);
        mWidthRecycler.setAdapter(mDiametersAdapter);
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
        mDiametersAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener()

        {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mDiametersSelected == -1) {
                    mDiametersAdapter.getData().get(position).setSelected(true);
                    mDiametersSelected = position;
                } else {
                    if (mDiametersAdapter.getData().get(position).isSelected()) {
                        mDiametersAdapter.getData().get(mDiametersSelected).setSelected(false);
                        mDiametersSelected = -1;
                    } else {
                        mDiametersAdapter.getData().get(mDiametersSelected).setSelected(false);
                        mDiametersAdapter.getData().get(position).setSelected(true);
                        mDiametersSelected = position;
                    }
                }
                if (mDiametersSelected == -1) {
                    if (map.containsKey("diameter")) {
                        map.remove("diameter");
                    }
                } else {
                    map.put("diameter", mDiametersAdapter.getData().get(position).getDiameter());
                }
                mDiametersAdapter.setNewData(mDiametersAdapter.getData());
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
        map = new HashMap<>();

        initss();
        super.onResume();
    }

    @OnClick({R.id.spec_fold, R.id.spec_two, R.id.width_fold, R.id.proportion_fold, R.id.borad_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.proportion_fold:
                if (mProportionRecycler.getVisibility() == View.VISIBLE) {
                    mProportionRecycler.setVisibility(View.GONE);
                } else {
                    mProportionRecycler.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.borad_confirm:
                SerializableMap serializableMap = new SerializableMap();
                if (map.containsKey("productName")) {
                    map.remove("productName");
                }
                map.put("productName", "棒材");
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

    private int getTextureIndex(String str) {

        return mSpec2.indexOf(str);
    }

    @Override
    public void paramsList(ParamsEntity baseEntity) {
        diameters = baseEntity.getDiameters();
        proportions = baseEntity.getProportions();
        mDiametersAdapter.setNewData(diameters);
        mProportionAdapter.setNewData(proportions);
        mTagAdapter.setNewData(mSpec);
    }
}
