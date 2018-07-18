package com.conduit.plastic.ui.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.entity.ProductNameEntity;
import com.conduit.plastic.entity.SpecEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.entity.standar.StandardBean;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameFragment;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.ui.area.AreaActivity;
import com.conduit.plastic.ui.brand.BrandActivity;
import com.conduit.plastic.ui.deal.DealActivity;
import com.conduit.plastic.ui.main.activity.MainContract;
import com.conduit.plastic.ui.main.activity.MainModel;
import com.conduit.plastic.ui.main.activity.MainPresenter;
import com.conduit.plastic.ui.productname.ProductNameActivity;
import com.conduit.plastic.util.ScreenUtils;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.conduit.plastic.view.banner.BannerConfig;
import com.conduit.plastic.view.banner.BannerView;
import com.conduit.plastic.view.banner.Transformer;
import com.conduit.plastic.view.banner.loader.GlideImageLoader;
import com.conduit.plastic.view.flowlayout.FlowLayout;
import com.conduit.plastic.view.flowlayout.TagAdapter;
import com.conduit.plastic.view.flowlayout.TagFlowLayout;
import com.lzy.okgo.OkGo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class DealFragment extends BaseFrameFragment<MainPresenter, MainModel> implements MainContract.View {
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_TITLE = "ARG_TITLE";
    @BindView(R.id.main_banners)
    BannerView mainBanner;
    @BindView(R.id.main_spec)
    TagFlowLayout specTag;
    @BindView(R.id.main_standard)
    TagFlowLayout standardTag;
    @BindView(R.id.main_quality)
    TagFlowLayout qualityTag;
    @BindView(R.id.main_quality_txt)
    TextView qualityTxt;
    @BindView(R.id.brand_edit)
    EditText brandEdit;
    @BindView(R.id.product_edit)
    EditText productEdit;
    @BindView(R.id.area_edit)
    EditText areaEdit;
    @BindView(R.id.main_quality_two)
    Button qualityTwo;
    List<String> strings = new ArrayList<>();
    LayoutInflater mInflater;
    boolean isChinaN = false;
    String[] texture;
    String[] standard;
    int page = 0;
    SpecEntity specEntity;
    List<StandardBean> list = new ArrayList<>();
    Map<String, Object> map = new HashMap<>();

    public static DealFragment newInstance(int page, String str) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_TITLE, str);
        DealFragment pageFragment = new DealFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        ButterKnife.bind(this, mContentView);
        mInflater = LayoutInflater.from(getActivity());
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mainBanner.getLayoutParams();
        params.height = new BigDecimal(Double.valueOf(ScreenUtils.getScreenWidth(getContext())*9/16)).setScale(0).intValue();
        mainBanner.setLayoutParams(params);
        mainBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setDelayTime(2000)
                .setViewPagerIsScroll(true)
                .isAutoPlay(true)
                .setImageLoader(new GlideImageLoader())
                .setBannerAnimation(Transformer.Default)
                .setIndicatorGravity(BannerConfig.CENTER);
    }

    @Override
    public void initData() {
        super.initData();
        specList();
        texture = getResources().getStringArray(R.array.texture_array);
        standard = getResources().getStringArray(R.array.standard_array);
    }

    @Override
    public void initLoad() {
        super.initLoad();
        mPresenter.bannerList(20);
    }

    @Override
    public void initView() {
        super.initView();
        qualityTag.setAdapter(new TagAdapter<StandardBean>(list) {
            @Override
            public View getView(FlowLayout parent, int position, StandardBean standardBean) {
                TextView textView = null;
                if (!TextUtils.isEmpty(standardBean.getSpecName1())) {

                    textView = (TextView) mInflater.inflate(R.layout.tag_layout, parent, false);
                    if (isChinaN) {
                        if (TextUtils.isEmpty(standardBean.getSpecName2())) {
                            textView.setText(standardBean.getSpecName1());
                        } else {
                            textView.setText(standardBean.getSpecName1() + "/" + standardBean.getSpecName2());
                        }
                    } else {
                        textView.setText(standardBean.getSpecName1());
                    }
                }
                return textView;
            }


        });
        specTag.setAdapter(new TagAdapter<String>(texture) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = null;
                if (!TextUtils.isEmpty(s)) {
                    textView = (TextView) mInflater.inflate(R.layout.tag_layout, parent, false);
                    textView.setText(s);
                }
                return textView;
            }
        });
        qualityTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                StandardBean standardBean = (StandardBean) qualityTag.getAdapter().getItem(position);
                if (map.containsKey("specId")) {
                    if ( map.get("specId").equals(standardBean.getId())) {
                        map.remove("specId");
                    } else {
                        map.put("specId", standardBean.getId());
                    }
                }else {
                    map.put("specId", standardBean.getId());
                }
                return false;
            }
        });
        standardTag.setAdapter(new TagAdapter<String>(standard) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView textView = null;
                if (!TextUtils.isEmpty(s)) {
                    textView = (TextView) mInflater.inflate(R.layout.tag_layout, parent, false);
                    textView.setText(s);
                }
                return textView;
            }
        });
        standardTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                if (map.containsKey("standard")) {
                    if (map.get("standard").equals(String.valueOf((position + 1) * 10))) {

                        map.remove("standard");
                    } else {
                        map.put("standard", String.valueOf((position + 1) * 10));
                        qualityTwo.setSelected(false);
                        initQuality((String) map.get("standard"));
                    }
                } else {
                    map.put("standard", String.valueOf((position + 1) * 10));
                    qualityTwo.setSelected(false);
                    initQuality((String) map.get("standard"));
                }
                return false;
            }
        });
        specTag.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                if (map.containsKey("texture")) {
                    if (map.get("texture").equals(String.valueOf((position + 1) * 10))) {
                        map.remove("texture");
                    } else {
                        map.put("texture", String.valueOf((position + 1) * 10));
                    }
                }else {
                    map.put("texture", String.valueOf((position + 1) * 10));
                }
                return false;
            }
        });

    }

    public void setPage(int pages) {
        this.page = pages;
    }


    void initQuality(String type) {
        if (map.containsKey("standard")) {
            if ("10".equals(map.get("standard"))) {
                isChinaN = true;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard());
                }
            } else if ("20".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getAmericaStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getAmericaStandard());
                }
            } else if ("30".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getEnglandStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getEnglandStandard());
                }
            } else if ("40".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    qualityTag.getAdapter().setTagDatas(specEntity.getJapanStandard1());
                } else {
                    qualityTag.getAdapter().setTagDatas(specEntity.getJapanStandard());
                }

            }
            qualityTag.getAdapter().notifyDataChanged();
        } 
    }


    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void info(BaseEntity<UserInfo> baseEntity) {

    }

    @Override
    public void bannerList(List<BannerEntity> baseEntity) {
        if (baseEntity != null && baseEntity.size() > 0) {
            List<String> list = new ArrayList<>();
            for (BannerEntity entity : baseEntity) {
                list.add(entity.getImagePath());
            }
            mainBanner.setImages(list).start();
        }
    }


    void specList() {
        OkGo.get(UrlContants.API_BASE_URL + UrlContants.SPEC_ALL_LIST).execute(new JsonCallback<BaseEntity<SpecEntity>>() {
            @Override
            public void onSuccess(BaseEntity<SpecEntity> baseEntity, Call call, Response response) {
                specEntity = baseEntity.getData();
                isChinaN = true;
                qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard());
                if (map.containsKey("standard"))
                    initQuality((String) map.get("standard"));
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

            @Override
            public void onCacheSuccess(BaseEntity<SpecEntity> baseEntity, Call call) {
                specEntity = baseEntity.getData();
                isChinaN = true;
                qualityTag.getAdapter().setTagDatas(specEntity.getChinaStandard());
                if (map.containsKey("standard"))
                    initQuality((String) map.get("standard"));
            }
        });
    }


    @OnClick({R.id.brand_btn, R.id.main_search, R.id.product_btn, R.id.area_btn,R.id.main_quality_two})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_search:
                if (!TextUtils.isEmpty(areaEdit.getText().toString())) {

                    map.put("areaName", areaEdit.getText().toString());
                } else {
                    map.remove("areaName");
                }
                if (!TextUtils.isEmpty(brandEdit.getText().toString())) {

                    map.put("brandName", brandEdit.getText().toString());
                } else {
                    map.remove("brandName");
                }
                if (!TextUtils.isEmpty(productEdit.getText().toString())) {

                    map.put("productName", productEdit.getText().toString());
                } else {
                    map.remove("productName");
                }
                SerializableMap serializableMap = new SerializableMap();
                serializableMap.setMap(map);
                Intent intent = new Intent(getActivity(), DealActivity.class);
                intent.putExtra(Constants.Params.DealParams, serializableMap);
                startActivity(intent);
                break;
            case R.id.brand_btn:
                BrandActivity.navToBrand(getActivity(), true);
                break;
            case R.id.product_btn:
                ProductNameActivity.navToProductName(getActivity());
                break;
            case R.id.area_btn:
                AreaActivity.navToArea(getActivity());
                break;
            case R.id.main_quality_two:
                if (qualityTwo.isSelected()) {
                    qualityTwo.setSelected(false);
                } else {
                    qualityTwo.setSelected(true);
                }
                if (map.containsKey("standard")) {
                    initQuality((String) map.get("standard"));
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        if (requestCode == Constants.Activity.AreaActivity) {
            AreaEntity areaEntity = (AreaEntity) data.getExtras().getSerializable(Constants.Params.AreaParams);
            areaEdit.setText(areaEntity.getAreaName());
        } else if (requestCode == Constants.Activity.ProductNameActivity) {
            ProductNameEntity entity = (ProductNameEntity) data.getExtras().getSerializable(Constants.Params.ProductNameParams);
            productEdit.setText(entity.getProductName());
        } else if (requestCode == Constants.Activity.BrandActivity) {
            BrandEntity entity = (BrandEntity) data.getExtras().getSerializable(Constants.Params.BrandParams);
            if (TextUtils.isEmpty(entity.getBrandNameCn())) {
                brandEdit.setText(entity.getBrandNameEn());
            } else {
                brandEdit.setText(entity.getBrandNameCn());
            }
        }
    }
}