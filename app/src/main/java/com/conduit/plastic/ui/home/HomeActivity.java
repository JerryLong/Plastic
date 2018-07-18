package com.conduit.plastic.ui.home;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.conduit.plastic.R;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.SerializableMap;
import com.conduit.plastic.util.ScreenUtils;
import com.conduit.plastic.view.banner.BannerConfig;
import com.conduit.plastic.view.banner.BannerView;
import com.conduit.plastic.view.banner.Transformer;
import com.conduit.plastic.view.banner.listener.OnBannerListener;
import com.conduit.plastic.view.banner.loader.GlideImageLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseFrameActivity<HomePresenter, HomeModel> implements HomeContract.View {

    //    @BindView(R.id.recyclerView)
//    XRecyclerView recyclerView;
//    @BindView(R.id.multiStateView)
//    MultiStateView multiStateView;
//    @BindView(R.id.user_name_tv)
//    TextView nameTv;
//    @BindView(R.id.user_desc_tv)
//    TextView descTv;
    @BindView(R.id.home_nick_tv)
    TextView nickTv;
    @BindView(R.id.home_ren_tv)
    TextView renTv;
    @BindView(R.id.home_qi_tv)
    TextView qiTv;
    @BindView(R.id.home_company_tv)
    TextView companyTv;
    @BindView(R.id.home_brand_tv)
    TextView brandTv;
    @BindView(R.id.home_telephone_tv)
    TextView telephoneTv;
    @BindView(R.id.home_phone_tv)
    TextView phoneTv;
    @BindView(R.id.home_address_tv)
    TextView addressTv;
    @BindView(R.id.home_top_banners)
    BannerView TopBanners;
    @BindView(R.id.home_bottom_banners)
    BannerView BottomBanners;
    private int mPageIndex = 1;
    SellerEntity mSellerEntity;
    Map<String, Object> map = new HashMap<>();

    @Override
    protected int LayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("公司主页");
        SerializableMap maps = (SerializableMap) getIntent().getExtras().getSerializable(Constants.Params.SearchParams);
        map = maps.getMap();
        mSellerEntity = (SellerEntity) getIntent().getExtras().getSerializable(Constants.Params.HomeParams);
        initSellerData(mSellerEntity);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) TopBanners.getLayoutParams();
        params.height =new BigDecimal(Double.valueOf(ScreenUtils.getScreenWidth(this)*9/16)).setScale(0).intValue();
        TopBanners.setLayoutParams(params);
        TopBanners.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setDelayTime(2000)
                .setViewPagerIsScroll(true)
                .isAutoPlay(true)
                .setImageLoader(new GlideImageLoader())
                .setBannerAnimation(Transformer.Default)
                .setIndicatorGravity(BannerConfig.CENTER);
        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) BottomBanners.getLayoutParams();
        params.height =new BigDecimal(Double.valueOf(ScreenUtils.getScreenWidth(this)*9/16)).setScale(0).intValue();
        BottomBanners.setLayoutParams(params2);
        BottomBanners.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setDelayTime(2000)
                .setViewPagerIsScroll(true)
                .isAutoPlay(true)
                .setImageLoader(new GlideImageLoader())
                .setBannerAnimation(Transformer.Default)
                .setIndicatorGravity(BannerConfig.CENTER);
    }

    void initSellerData(SellerEntity entity) {
        nickTv.setText(entity.getContacts()+" ");
        companyTv.setText("名称：" + entity.getCompanyName());
        brandTv.setText("品牌：" + entity.getBrandNames());
        telephoneTv.setText("座机：" + entity.getOfficePhone());
        phoneTv.setText("手机：" + entity.getContactNumber());
        addressTv.setText("地址：" + entity.getAddress());

    }

    @Override
    public void initLoad() {
        super.initLoad();
        mPresenter.bannerList(10);
        mPresenter.bannerList(10);
    }

    @Override
    public void initListener() {
        super.initListener();
        TopBanners.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
        BottomBanners.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestError(String msg) {
    }

    @Override
    public void onRequestEnd() {

    }


    @Override
    public void bannerList(List<BannerEntity> baseEntity, int adType) {
        if (adType == 10) {
            if (baseEntity != null && baseEntity.size() > 0) {
                List<String> list = new ArrayList<>();
                for (BannerEntity entity : baseEntity) {
                    list.add(entity.getImagePath());
                }
                TopBanners.setImages(list).start();
                BottomBanners.setImages(list).start();
            }
        } else {
            if (baseEntity != null && baseEntity.size() > 0) {
                List<String> list = new ArrayList<>();
                for (BannerEntity entity : baseEntity) {
                    list.add(entity.getImagePath());
                }
                BottomBanners.setImages(list).start();
            }
        }
    }

}
