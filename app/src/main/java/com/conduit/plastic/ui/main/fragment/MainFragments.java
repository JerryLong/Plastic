package com.conduit.plastic.ui.main.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.TagAdapteer;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.entity.ProductNameEntity;
import com.conduit.plastic.entity.SpecEntity;
import com.conduit.plastic.entity.StringEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.entity.standar.StandardBean;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameFragment;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.ui.area.AreaActivity;
import com.conduit.plastic.ui.attribute.BarActivity;
import com.conduit.plastic.ui.attribute.BoardActivity;
import com.conduit.plastic.ui.brand.Brand2Activity;
import com.conduit.plastic.ui.brand.BrandActivity;
import com.conduit.plastic.ui.main.activity.MainContract;
import com.conduit.plastic.ui.main.activity.MainModel;
import com.conduit.plastic.ui.main.activity.MainPresenter;
import com.conduit.plastic.ui.main.adapter.TagStandardAdapteer;
import com.conduit.plastic.ui.mine.UserInfoActivity;
import com.conduit.plastic.ui.productname.ProductNameActivity;
import com.conduit.plastic.ui.seller.SellerActivity;
import com.conduit.plastic.util.ScreenUtils;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.conduit.plastic.view.banner.BannerConfig;
import com.conduit.plastic.view.banner.BannerView;
import com.conduit.plastic.view.banner.Transformer;
import com.conduit.plastic.view.banner.loader.GlideImageLoader;
import com.lzy.okgo.OkGo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Response;

public class MainFragments extends BaseFrameFragment<MainPresenter, MainModel> implements MainContract.View {

    public static final String ARG_PAGE = "ARG_PAGE";
    @BindView(R.id.main_banners)
    BannerView mainBanner;
    @BindView(R.id.main_spec)
    RecyclerView mSpecRecycler;
    @BindView(R.id.main_standard)
    RecyclerView mStandardRecycler;
    @BindView(R.id.main_quality)
    RecyclerView mQualityRecycler;
    @BindView(R.id.main_quality_txt)
    TextView qualityTxt;
    @BindView(R.id.main_quality_two)
    Button qualityTwo;
    @BindView(R.id.main_spec_two)
    Button specTwo;
    @BindView(R.id.brands_edit)
    EditText mBrandEdit;
    @BindView(R.id.product_edit)
    EditText productEdit;
    @BindView(R.id.area_edit)
    EditText areaEdit;
    @BindView(R.id.toolbar_title)
    TextView mTitle;
    AlertDialog mAlertDialog;
    boolean isChinaN = false;
    private String[] texture;
    private String[] standard1;
    private String[] standard;
    private int page = 0;
    private SpecEntity specEntity;
    private List<StandardBean> mStandardList;// = new ArrayList<>();
    private Map<String, Object> map;
    private TagAdapteer mSpecAdapter, mTexAdapter;
    private List<StringEntity> mSpec;//= new ArrayList<>();
    private List<StringEntity> mSpec1;//= new ArrayList<>();
    private List<StringEntity> mTex;//= new ArrayList<>();
    private Unbinder unbinder;
    private int mSpecSelected = -1, mTexSelected = -1, mStandardSelected = -1;
    private TagStandardAdapteer mStandardAdapter;
    private View mView;

    public static MainFragments newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);

        MainFragments pageFragment = new MainFragments();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLoad();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_mains, container, false);
        unbinder = ButterKnife.bind(this, mView);
        mTitle.setText("主页");

        return mView;
    }

    void init() {
        mSpecAdapter = new TagAdapteer();
        mTexAdapter = new TagAdapteer();
        mSpec = new ArrayList<>();
        mSpec1 = new ArrayList<>();
        mTex = new ArrayList<>();
        mStandardList = new ArrayList<>();
        mStandardAdapter = new TagStandardAdapteer();
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mainBanner.getLayoutParams();
        params.height = new BigDecimal(Double.valueOf(ScreenUtils.getScreenWidth(getContext()) * 9 / 16)).setScale(0).intValue();
        mainBanner.setLayoutParams(params);
        mainBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setDelayTime(2000)
                .setViewPagerIsScroll(true)
                .isAutoPlay(true)
                .setImageLoader(new GlideImageLoader())
                .setBannerAnimation(Transformer.Default)
                .setIndicatorGravity(BannerConfig.CENTER);
        mSpecRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mStandardRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mQualityRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mSpecRecycler.setAdapter(mSpecAdapter);
        mStandardRecycler.setAdapter(mTexAdapter);
        mQualityRecycler.setAdapter(mStandardAdapter);
        specList();
        texture = getResources().getStringArray(R.array.texture_array);
        standard1 = getResources().getStringArray(R.array.texture_cn_array);
        standard = getResources().getStringArray(R.array.standard_array);
        for (int i = 0; i < standard.length; i++) {
//            if (i == 0) {
//                mTexSelected = 0;
//                mTex.add(new StringEntity(i, standard[i], true));
//            } else {
            mTex.add(new StringEntity(i, standard[i], false));
//            }
        }
        for (int i = 0; i < standard1.length; i++) {
            mSpec1.add(new StringEntity(i, standard1[i], false));
        }
        for (int i = 0; i < texture.length; i++) {
            mSpec.add(new StringEntity(i, texture[i], false));
        }
        mSpecAdapter.setNewData(mSpec);
        mTexAdapter.setNewData(mTex);
        initView();
        mPresenter.bannerList(20);
    }

    @Override
    public void initLoad() {
        super.initLoad();

    }

    public void initView() {

        mSpecAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mSpecSelected == -1) {
                    mSpecAdapter.getData().get(position).setSelected(true);
                    mSpecSelected = position;
                } else {
                    if (mSpecAdapter.getData().get(position).isSelected()) {
                        mSpecAdapter.getData().get(mSpecSelected).setSelected(false);
                        mSpecSelected = -1;
                    } else {
                        mSpecAdapter.getData().get(mSpecSelected).setSelected(false);
                        mSpecAdapter.getData().get(position).setSelected(true);
                        mSpecSelected = position;
                    }
                }
                if (mSpecSelected == -1) {
                    if (map.containsKey("texture")) {
                        map.remove("texture");
                    }
                } else {
//                    ToastUtils.showShort("aaaa    "+String.valueOf((mSpecSelected + 1) * 10));
                    map.put("texture", String.valueOf((mSpecSelected + 1) * 10));
                }

                mSpecAdapter.setNewData(mSpecAdapter.getData());
            }
        });
        mTexAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mTexSelected == -1) {
                    mTexAdapter.getData().get(position).setSelected(true);
                    mTexSelected = position;
                } else {
                    if (mTexAdapter.getData().get(position).isSelected()) {
                        mTexAdapter.getData().get(mTexSelected).setSelected(false);
                        mTexSelected = -1;
                    } else {
                        mTexAdapter.getData().get(mTexSelected).setSelected(false);
                        mTexAdapter.getData().get(position).setSelected(true);
                        mTexSelected = position;
                    }
                }
                if (mTexSelected == -1) {
                    if (map.containsKey("standard")) {
                        map.remove("standard");
                        if (map.containsKey("specId")) {
                            map.remove("specId");
                            map.remove("spec");
                        }
                    }
                } else {
                    map.put("standard", String.valueOf((mTexSelected + 1) * 10));
                }

                if (mStandardSelected != -1) {
                    mStandardAdapter.getData().get(mStandardSelected).setSelected(false);
                }
                qualityTwo.setSelected(false);
                mStandardSelected = -1;
                initQuality();
                mTexAdapter.setNewData(mTexAdapter.getData());
            }
        });
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
                    if (map.containsKey("specId")) {
                        map.remove("specId");
                    }
                } else {
                    map.put("specId", mStandardAdapter.getData().get(mStandardSelected).getId());
                    String string = "";
                    if (isChinaN) {
                        if (TextUtils.isEmpty(mStandardAdapter.getData().get(mStandardSelected).getSpecName2())) {
                            string = mStandardAdapter.getData().get(mStandardSelected).getSpecName1();
                        } else {
                            string = mStandardAdapter.getData().get(mStandardSelected).getSpecName1() + "/" + mStandardAdapter.getData().get(mStandardSelected).getSpecName2();
                        }
                    } else {
                        string = mStandardAdapter.getData().get(mStandardSelected).getSpecName1();
                    }
                    map.put("spec", string);
                }

                mStandardAdapter.setNewData(mStandardAdapter.getData());
            }
        });

    }

    @Override
    public void onResume() {
        map = new HashMap<>();
        init();
        super.onResume();
    }

    public void setPage(int pages) {
        this.page = pages;
    }


    void initQuality() {
        if (map.containsKey("standard") && specEntity != null && qualityTwo != null) {
            if ("10".equals(map.get("standard"))) {
                isChinaN = true;
                if (qualityTwo.isSelected()) {
                    mStandardList = specEntity.getChinaStandard1();
                } else {
                    mStandardList = specEntity.getChinaStandard();
                }
            } else if ("20".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    mStandardList = specEntity.getAmericaStandard1();
                } else {
                    mStandardList = specEntity.getAmericaStandard();
                }
            } else if ("40".equals(map.get("standard"))) {
                isChinaN = false;
                if (qualityTwo.isSelected()) {
                    mStandardList = specEntity.getEnglandStandard1();
                } else {
                    mStandardList = specEntity.getEnglandStandard();
                }
            } else if ("30".equals(map.get("standard"))) {
                isChinaN = false;

                if (qualityTwo.isSelected()) {
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
            list.clear();
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
                mStandardAdapter.setIsChina(isChinaN);
                mStandardList = specEntity.getChinaStandard();
                mStandardAdapter.setNewData(mStandardList);
//                map.put("standard", "10");
//                if (map.containsKey("standard"))
//                    initQuality();
            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

            @Override
            public void onCacheSuccess(BaseEntity<SpecEntity> baseEntity, Call call) {
                specEntity = baseEntity.getData();
                isChinaN = true;
                mStandardAdapter.setIsChina(isChinaN);
                mStandardList = specEntity.getChinaStandard();
                mStandardAdapter.setNewData(mStandardList);
//                map.put("standard", "10");
//                if (map.containsKey("standard"))
//                    initQuality();
            }
        });
    }


    @OnClick({R.id.brand_btn, R.id.main_bar, R.id.main_board, R.id.main_quality_fold, R.id.main_spec_two, R.id.main_spec_fold, R.id.main_search, R.id.product_btn, R.id.area_btn, R.id.main_quality_two})
    public void onClick(View view) {
        SerializableMap serializableMap;//= new SerializableMap();
        Intent intent;
        Bundle bundle;
        switch (view.getId()) {
            case R.id.main_search:
                if (!TextUtils.isEmpty(areaEdit.getText().toString())) {

                    map.put("areaName", areaEdit.getText().toString());
                } else {
                    map.remove("areaName");
                }
                if (!TextUtils.isEmpty(mBrandEdit.getText().toString())) {

                    map.put("brandName", mBrandEdit.getText().toString());
                } else {
                    map.remove("brandName");
                }
                if (!TextUtils.isEmpty(productEdit.getText().toString())) {

                    map.put("productName", productEdit.getText().toString());
                } else {
                    map.remove("productName");
                }
                serializableMap = new SerializableMap();
                serializableMap.setMap(map);
                intent = new Intent(getActivity(), SellerActivity.class);
                bundle = new Bundle();
                bundle.putSerializable(Constants.Params.SearchParams, serializableMap);
                bundle.putBoolean(Constants.Params.SearchType, true);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.brand_btn:
//                BrandActivity.navToBrand(getActivity(), true);
                Brand2Activity.navToBrand(getActivity(), true);
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
                if (mStandardSelected != -1)
                    mStandardAdapter.getData().get(mStandardSelected).setSelected(false);
                mStandardSelected = -1;
                if (map.containsKey("standard")) {
                    initQuality();
                }

                break;
            case R.id.main_quality_fold:
                if (mQualityRecycler.getVisibility() == View.VISIBLE) {
                    mQualityRecycler.setVisibility(View.GONE);
                } else {
                    mQualityRecycler.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.main_spec_two:
                if (mSpecSelected != -1) {
                    mSpecAdapter.getData().get(mSpecSelected).setSelected(false);
                }
                if (specTwo.isSelected()) {
                    specTwo.setSelected(false);
                    mSpecAdapter.setNewData(mSpec);
                } else {
                    mSpecAdapter.setNewData(mSpec1);
                    specTwo.setSelected(true);
                }
                mSpecSelected = -1;

                break;
            case R.id.main_spec_fold:
                if (mSpecRecycler.getVisibility() == View.VISIBLE) {
                    mSpecRecycler.setVisibility(View.GONE);
                } else {
                    mSpecRecycler.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.main_board:
                serializableMap = new SerializableMap();
                serializableMap.setMap(map);
                intent = new Intent(getActivity(), BarActivity.class);
                bundle = new Bundle();
                bundle.putSerializable(Constants.Params.SearchParams, serializableMap);
                intent.putExtras(bundle);
                startActivity(intent);

                break;
            case R.id.main_bar:
                serializableMap = new SerializableMap();
                serializableMap.setMap(map);
                intent = new Intent(getActivity(), BoardActivity.class);
                bundle = new Bundle();
                bundle.putSerializable(Constants.Params.SearchParams, serializableMap);
                intent.putExtras(bundle);
                startActivity(intent);
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
                mBrandEdit.setText(entity.getBrandNameEn() + "");
            } else {
                mBrandEdit.setText(entity.getBrandNameCn() + "");
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}