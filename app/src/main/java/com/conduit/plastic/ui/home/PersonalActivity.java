package com.conduit.plastic.ui.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allen.library.SuperTextView;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.util.ScreenUtils;
import com.conduit.plastic.util.ShareUtil;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.view.banner.BannerConfig;
import com.conduit.plastic.view.banner.BannerView;
import com.conduit.plastic.view.banner.Transformer;
import com.conduit.plastic.view.banner.listener.OnBannerListener;
import com.conduit.plastic.view.banner.loader.GlideImageLoader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class PersonalActivity extends BaseFrameActivity<PersonalPresenter, HomeModel> implements HomeContract.PersonalView {

    @BindView(R.id.personal_banners)
    BannerView personalBanners;
//    @BindView(R.id.personal_headimg)
//    ImageView personalHeadimg;
    @BindView(R.id.personal_head_tv)
    TextView personalHeadTv;
    @BindView(R.id.personal_head_desc)
    TextView personalHeadDesc;
    @BindView(R.id.personal_ren_tv)
    TextView personalRenTv;
    @BindView(R.id.personal_qi_tv)
    TextView personalQiTv;
    @BindView(R.id.personal_head_layout)
    LinearLayout personalHeadLayout;
    @BindView(R.id.personal_location)
    SuperTextView personalLocation;
    @BindView(R.id.personal_phone)
    SuperTextView personalPhone;
    @BindView(R.id.personal_tel)
    SuperTextView personalTel;
    private String companyId;
    private String phone;
    private SellerEntity mSellerEntity;

    @Override
    protected int LayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    public void initView() {
        setTitleTxt("商家详情");

    }

    @Override
    public void initLoad() {
        companyId = getIntent().getStringExtra("companyId");
        mPresenter.company(companyId);
        mPresenter.bannerList(10);
    }

    @Override
    public void initListener() {
        super.initListener();
        personalBanners.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {

            }
        });
    }

    @OnClick({R.id.personal_phone, R.id.personal_tel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.personal_phone:
                phone = mSellerEntity.getContactNumber();
                break;
            case R.id.personal_tel:
                phone = mSellerEntity.getOfficePhone();
                break;
        }
        mPresenter.callPhone(phone, companyId);
    }

    @Override
    public void onRequestStart() {

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
//                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) personalBanners.getLayoutParams();
//                params.height = new BigDecimal(Double.valueOf(ScreenUtils.getScreenWidth(this) * 9 / 16)).setScale(0).intValue();
//                personalBanners.setLayoutParams(params);
                personalBanners.setImages(list)
                        .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                        .setDelayTime(2000)
                        .setViewPagerIsScroll(true)
                        .isAutoPlay(true)
                        .setImageLoader(new GlideImageLoader())
                        .setBannerAnimation(Transformer.Default)
                        .setIndicatorGravity(BannerConfig.CENTER)
                        .start();
            }
        }
    }

    @Override
    public void company(SellerEntity baseEntity) {
        mSellerEntity = baseEntity;
        personalHeadTv.setText(baseEntity.getCompanyName());
        personalHeadDesc.setText(baseEntity.getBrandNames() + " | " + baseEntity.getContacts());
        personalLocation.setLeftString(baseEntity.getAddress());
        personalPhone.setLeftString(baseEntity.getContactNumber());
        personalTel.setLeftString(baseEntity.getOfficePhone());
    }

    @Override
    public void callPhone(int type) {
        if (type == 0) {
            ShareUtil.callPhone(PersonalActivity.this,phone);
        } else {
            ToastUtils.showShort("拨号失败请重试");
        }
    }
}
