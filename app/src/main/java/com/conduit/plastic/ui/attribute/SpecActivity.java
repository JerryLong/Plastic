package com.conduit.plastic.ui.attribute;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SpecEntity;
import com.conduit.plastic.entity.standar.StandardBean;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.ui.main.adapter.TagStandardAdapteer;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.lzy.okgo.OkGo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class SpecActivity extends BaseActivity {

    @BindView(R.id.spec_two)
    Button mSpecTwo;
    @BindView(R.id.spec_quality)
    RecyclerView mSpecQuality;
    private static String ARG_PARAM = "entity";
    private static String ARG_PARAM_TWO = "entitys";
    private int mStandardSelected = -1;
    SpecEntity specEntity;
    boolean isChinaN = false;
    private StandardBean mStandardBean;
    private String mType = "10";
    private TagStandardAdapteer mStandardAdapter;
    private List<StandardBean> mStandardList = new ArrayList<>();

    public static void openSpec(Activity context, String type, boolean isChinaN) {
        Intent intent = new Intent(context, SpecActivity.class);
        intent.putExtra(ARG_PARAM, type);
        intent.putExtra(ARG_PARAM_TWO, isChinaN);
        context.startActivityForResult(intent, Constants.Activity.SpecActivity);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_spec;
    }

    @Override
    public void initView() {
        mType = getIntent().getStringExtra(ARG_PARAM);
        isChinaN = getIntent().getBooleanExtra(ARG_PARAM_TWO, false);
        setBackTxt("返回");
        setTitleTxt("规格列表");
        mStandardAdapter = new TagStandardAdapteer();
        mSpecQuality.setLayoutManager(new GridLayoutManager(this, 4));
        mSpecQuality.setAdapter(mStandardAdapter);
        mStandardAdapter.setIsChina(isChinaN);
        specList();
        mStandardAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (mStandardSelected == -1) {
                    mStandardAdapter.getData().get(position).setSelected(true);
                    mStandardSelected = position;
                } else {
                    if (mStandardAdapter.getData().get(position).isSelected()) {
                        mStandardAdapter.getData().get(mStandardSelected).setSelected(false);
                        mStandardSelected = -1;
                    } else {
                        mStandardAdapter.getData().get(mStandardSelected).setSelected(false);
                        mStandardAdapter.getData().get(position).setSelected(true);
                        mStandardSelected = position;
                    }
                }

                if (mStandardSelected == -1) {
                    mStandardBean = null;
                } else {
                    mStandardBean = mStandardAdapter.getData().get(position);
                }
                mStandardAdapter.setNewData(mStandardAdapter.getData());
            }
        });
    }

    void specList() {
        OkGo.get(UrlContants.API_BASE_URL + UrlContants.SPEC_ALL_LIST).execute(new JsonCallback<BaseEntity<SpecEntity>>() {
            @Override
            public void onSuccess(BaseEntity<SpecEntity> baseEntity, Call call, Response response) {
                specEntity = baseEntity.getData();
                initQuality();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

            @Override
            public void onCacheSuccess(BaseEntity<SpecEntity> baseEntity, Call call) {
                specEntity = baseEntity.getData();
                initQuality();
            }
        });
    }

    void initBack(StandardBean entity) {
        if (entity != null) {
            Intent intent = new Intent();
            intent.putExtra(Constants.Params.SpecParams, entity);
            setResult(RESULT_OK, intent);
        }
        specEntity = null;
        finish();
    }

    void initQuality() {
        if (!mType.equals("0") && specEntity != null) {
            if ("10".equals(mType)) {
                isChinaN = false;
                if (mSpecTwo.isSelected()) {
                    mStandardList = specEntity.getChinaStandard1();
                } else {
                    mStandardList = specEntity.getChinaStandard();
                }
            } else if ("20".equals(mType)) {
                isChinaN = false;
                if (mSpecTwo.isSelected()) {
                    mStandardList = specEntity.getAmericaStandard1();
                } else {
                    mStandardList = specEntity.getAmericaStandard();
                }
            } else if ("40".equals(mType)) {
                isChinaN = false;
                if (mSpecTwo.isSelected()) {
                    mStandardList = specEntity.getEnglandStandard1();
                } else {
                    mStandardList = specEntity.getEnglandStandard();
                }
            } else if ("30".equals(mType)) {
                isChinaN = false;

                if (mSpecTwo.isSelected()) {
                    mStandardList = specEntity.getJapanStandard1();
                } else {
                    mStandardList = specEntity.getJapanStandard();
                }

            }
            mStandardAdapter.setIsChina(isChinaN);
            mStandardAdapter.setNewData(mStandardList);
        } else {
            mStandardList = new ArrayList<>();
            mStandardAdapter.setNewData(mStandardList);
        }
    }


    @OnClick({R.id.spec_two, R.id.spec_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.spec_two:
                if (mSpecTwo.isSelected()) {
                    mSpecTwo.setSelected(false);
                } else {
                    mSpecTwo.setSelected(true);
                }
                if (mStandardSelected != -1)
                    mStandardAdapter.getData().get(mStandardSelected).setSelected(false);
                mStandardSelected = -1;
                if (!mType.equals("0")) {
                    initQuality();
                }
                break;
            case R.id.spec_confirm:
                initBack(mStandardBean);
                break;
        }
    }
}
