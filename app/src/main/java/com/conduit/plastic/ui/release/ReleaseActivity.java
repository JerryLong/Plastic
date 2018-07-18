package com.conduit.plastic.ui.release;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.GridImageAdapter;
import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.LocalMedia;
import com.conduit.plastic.entity.ProductNameEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.entity.standar.StandardBean;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.attribute.SpecActivity;
import com.conduit.plastic.ui.attribute.StandardActivity;
import com.conduit.plastic.ui.attribute.TextureActivity;
import com.conduit.plastic.ui.brand.BrandActivity;
import com.conduit.plastic.ui.productname.ProductNameActivity;
import com.conduit.plastic.ui.release.contract.DemandContract;
import com.conduit.plastic.ui.release.contract.DemandModel;
import com.conduit.plastic.ui.release.contract.DemandPresenter;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.util.FullyGridLayoutManager;
import com.conduit.plastic.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.util.ConvertUtils;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class ReleaseActivity extends BaseFrameActivity<DemandPresenter, DemandModel> implements DemandContract.DemandView {

    @BindView(R.id.release_head)
    SuperTextView releaseHead;
    @BindView(R.id.release_title)
    SuperTextView releaseTitle;
    @BindView(R.id.release_article)
    SuperTextView releaseArticle;
    @BindView(R.id.release_logo)
    SuperTextView releaseLogo;
    @BindView(R.id.release_standard)
    SuperTextView releaseStandard;
    @BindView(R.id.release_spec)
    SuperTextView releaseSpec;
    @BindView(R.id.release_quality)
    SuperTextView releaseQuality;
    @BindView(R.id.release_number)
    SuperTextView releaseNumber;
    @BindView(R.id.release_time)
    SuperTextView releaseTime;
    @BindView(R.id.release_desc)
    EditText releaseDesc;
    @BindView(R.id.release_recycler)
    RecyclerView releaseRecycler;
    @BindView(R.id.anonymousGroup)
    RadioGroup anonymousGroup;
    @BindView(R.id.release_desc_tv)
    SuperTextView releaseDescTv;
    @BindView(R.id.release_release)
    SuperTextView releaseRelease;
    @BindView(R.id.release_cancel)
    SuperTextView releaseCancel;
    @BindView(R.id.release_confirm)
    SuperTextView releaseConfirm;
    @BindView(R.id.release_recycler_layout)
    SuperTextView releaseLayout;
    @BindView(R.id.anonymous)
    RadioButton anonymous;
    @BindView(R.id.anonymous_not)
    RadioButton anonymousNot;
    private List<LocalMedia> selectList = new ArrayList<>();
    private GridImageAdapter adapter;
    private static String ARG_PARAM = "entity";
    private static String ARG_PARAM_TWO = "isEdit";
    DemandEntity mDemand = new DemandEntity();
    private boolean isEdit = false;
    String[] standard;
    String[] texture;
    UserInfo mUserInfo = null;
    private MaterialDialog.Builder mInputDialog;
    List<com.luck.picture.lib.entity.LocalMedia> mList = new ArrayList<>();

    private static ReleaseActivity mActivity;

    public static void openRelease(Context context, DemandEntity entity, boolean isEdit) {
        Intent intent = new Intent(context, ReleaseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ReleaseActivity.ARG_PARAM, entity);
        bundle.putBoolean(ReleaseActivity.ARG_PARAM_TWO, isEdit);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_release;
    }

    @Override
    public void initData() {
        super.initData();
        mActivity = this;


    }

    private void initDialog() {
        mInputDialog = new MaterialDialog.Builder(this);
        mInputDialog.negativeText(getString(R.string.dialog_btn_cancel));
        mInputDialog.positiveText(getString(R.string.dialog_btn_confirm));
        mInputDialog.positiveColorRes(R.color.colorPrimary);
        mInputDialog.negativeColorRes(R.color.colorPrimary);
        mInputDialog.titleColorRes(R.color.black);
        mInputDialog.inputType(InputType.TYPE_CLASS_TEXT);
        mInputDialog.widgetColorRes(R.color.colorPrimary);
    }

    @Override
    public void initView() {
        setBackTxt("取消");
        setTitleTxt("发布");

        isEdit = getIntent().getExtras().getBoolean(ARG_PARAM_TWO, false);
        if (isEdit)
            mDemand = (DemandEntity) getIntent().getExtras().getSerializable(ARG_PARAM);
        mUserInfo = UserUtils.getInstance().getUser();
        standard = getResources().getStringArray(R.array.standard_array);
        texture = getResources().getStringArray(R.array.texture_array);
        if (isEdit) {

            adapter = new GridImageAdapter(ReleaseActivity.this, null);
            mList = com.conduit.plastic.entity.LocalMedia.getParcelImage(mDemand.getImageList());
//            if (TextUtils.isEmpty(mDemand.getProductImg())) {
//                releaseConfirm.setCenterString("修改");
//                releaseCancel.setCenterString("删除");
//                releaseConfirm.setVisibility(View.VISIBLE);
//                releaseCancel.setVisibility(View.VISIBLE);
//            } else {
//                releaseConfirm.setVisibility(View.GONE);
//                releaseCancel.setCenterString("删除");
//            }
            releaseQuality.setCenterString(mDemand.getTextures() + "");
            releaseStandard.setCenterString(mDemand.getStandards() + "");
            releaseTitle.setCenterString(mDemand.getTitle() + "");
            releaseDesc.setText(mDemand.describes + "");
            releaseSpec.setCenterString(mDemand.getSpec() + "");
            releaseTime.setCenterString(mDemand.validityDate + "");
            releaseNumber.setCenterString(mDemand.quantity + "");
            releaseLogo.setCenterString(mDemand.getBrandName() + "");
            releaseArticle.setCenterString(mDemand.getProductName() + "");
            releaseRelease.setCenterString(mDemand.getUserName() + "");
            if (mDemand.isConceal == 0) {
                anonymous.setChecked(true);
            } else {
                anonymousNot.setChecked(true);
            }
//            releaseLayout.setVisibility(View.GONE);
        } else {
            adapter = new GridImageAdapter(ReleaseActivity.this, onAddPicClickListener);
            adapter.setList(mList);
            adapter.setSelectMax(9);
            releaseTime.setCenterString(mYear + "-" + mMonth + "-" + mDay);
        }
        releaseHead.setLeftTopString(mUserInfo.getContacts());
        releaseHead.setLeftBottomString(mUserInfo.getUserCode());
        Glide.with(this)
                .load(mUserInfo.getHeadImage()).apply(bitmapTransform(new CircleCrop()))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        releaseHead.setLeftIcon(resource);
                    }
                });
        FullyGridLayoutManager manager = new FullyGridLayoutManager(ReleaseActivity.this, 3, GridLayoutManager.VERTICAL, false);
        releaseRecycler.setLayoutManager(manager);


        releaseRecycler.setAdapter(adapter);
        adapter.setList(mList);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if (selectList.size() > 0) {
                    LocalMedia media = selectList.get(position);
                    String pictureType = media.getPictureType();
                    int mediaType = PictureMimeType.pictureToVideo(pictureType);
                    switch (mediaType) {
                        case 1:
                            // 预览图片 可自定长按保存路径
                            //PictureSelector.create(MainActivity.this).externalPicturePreview(position, "/custom_file", selectList);
                            PictureSelector.create(ReleaseActivity.this).externalPicturePreview(position, mList);
                            break;
                        case 2:
                            // 预览视频
                            PictureSelector.create(ReleaseActivity.this).externalPictureVideo(media.getPath());
                            break;
                        case 3:
                            // 预览音频
                            PictureSelector.create(ReleaseActivity.this).externalPictureAudio(media.getPath());
                            break;
                    }
                }
            }
        });
        anonymousGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.anonymous_not:
                        mDemand.isConceal = 1;
                        mDemand.userName = mUserInfo.getContacts();
                        break;
                    case R.id.anonymous:
                        mDemand.isConceal = 0;
                        mDemand.userName = "";
                        break;
                }
            }
        });
        initDialog();
        releaseArticle.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                releaseArticle.setRightIcon(null).setCenterString("");
                mDemand.productNameId = "";
                mDemand.productName = "";
            }
        });
        releaseLogo.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                releaseLogo.setRightIcon(null).setCenterString("");
                mDemand.brandId = "";
                mDemand.brandName = "";
            }
        });
        releaseStandard.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                releaseStandard.setRightIcon(null).setCenterString("");
                mDemand.standard = "";
            }
        });
        releaseSpec.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                releaseSpec.setRightIcon(null).setCenterString("");
                mDemand.spec = "";
            }
        });
        releaseQuality.setRightImageViewClickListener(new SuperTextView.OnRightImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                releaseQuality.setRightIcon(null).setCenterString("");
                mDemand.quantity = "";
            }
        });
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            PictureSelector.create(ReleaseActivity.this)
                    .openGallery(PictureMimeType.ofImage())
                    .theme(R.style.picture_default_style)
                    .maxSelectNum(9)
                    .compress(true)
                    .forResult(PictureConfig.CHOOSE_REQUEST);
        }

    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    mList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的
                    selectList = LocalMedia.setImage(mList);
                    if (selectList.size() > 0) {
                        mDemand.setImage(true);
                        mDemand.mList.addAll(selectList);
                    }
                    adapter.setList(mList);
                    adapter.notifyDataSetChanged();
                    break;
                case Constants.Activity.AreaActivity:
                    AreaEntity areaEntity = (AreaEntity) data.getExtras().getSerializable(Constants.Params.AreaParams);
                    break;
                case Constants.Activity.StandardActivity:
                    String str = data.getExtras().getString(Constants.Params.StandardParams);
                    mDemand.standard = str;
                    releaseStandard.setCenterString(standard[Integer.parseInt(str) / 10 - 1])
                            .setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
                    break;
                case Constants.Activity.TextureActivity:
                    String textures = data.getExtras().getString(Constants.Params.TextureParams);
                    mDemand.texture = textures;
                    releaseQuality.setCenterString(texture[Integer.parseInt(textures) / 10 - 1])
                            .setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
                    break;
                case Constants.Activity.SpecActivity:
                    StandardBean standardBean = (StandardBean) data.getExtras().getSerializable(Constants.Params.SpecParams);
                    mDemand.specId = standardBean.getId();
                    mDemand.spec = standardBean.getSpecName1();
                    releaseSpec.setCenterString(standardBean.getSpecName1())
                            .setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
                    break;
                case Constants.Activity.ProductNameActivity:
                    ProductNameEntity entity = (ProductNameEntity) data.getExtras().getSerializable(Constants.Params.ProductNameParams);
                    mDemand.productNameId = entity.getId();
                    mDemand.productName = entity.getProductName();
                    releaseArticle.setCenterString(entity.getProductName())
                            .setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
                    break;
                case Constants.Activity.BrandActivity:
                    BrandEntity entitys = (BrandEntity) data.getExtras().getSerializable(Constants.Params.BrandParams);
                    mDemand.brandId = entitys.getId();
                    if (TextUtils.isEmpty(entitys.getBrandNameCn())) {
                        mDemand.brandName = entitys.getBrandNameEn();
                        releaseLogo.setCenterString(entitys.getBrandNameEn())
                                .setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
                    } else {
                        mDemand.brandName = entitys.getBrandNameCn();
                        releaseLogo.setCenterString(entitys.getBrandNameCn())
                                .setRightIcon(ContextCompat.getDrawable(this, R.mipmap.ic_cancel));
                    }
                    break;
            }
        }
    }

    @OnClick({R.id.release_head, R.id.release_cancel, R.id.release_confirm, R.id.release_title, R.id.release_article, R.id.release_logo, R.id.release_standard, R.id.release_spec, R.id.release_quality, R.id.release_number, R.id.release_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.release_head:
                break;
            case R.id.release_title:
                showInputDialog("填写标题", InputType.TYPE_CLASS_TEXT, titleCallback);
                break;
            case R.id.release_article:
                ProductNameActivity.navToProductName(this);
                break;
            case R.id.release_logo:
                BrandActivity.navToBrand(this, true);
                break;
            case R.id.release_standard:
                StandardActivity.navToStandard(this);
                break;
            case R.id.release_spec:
                if (!TextUtils.isEmpty(mDemand.standard)) {
                    SpecActivity.openSpec(ReleaseActivity.this, String.valueOf(mDemand.standard), false);
                } else {
                    showShortToast("必须先选择标准");
                }
                break;
            case R.id.release_quality:
                TextureActivity.navToTexture(this);
                break;
            case R.id.release_number:
                showInputDialog("添加数量", InputType.TYPE_CLASS_NUMBER, numberCallback);
                break;
            case R.id.release_time:
                onYearMonthDayPicker(view);
                break;
            case R.id.release_cancel:

                break;
            case R.id.release_confirm:
                if (TextUtils.isEmpty(mDemand.validityDate)) {
                    mDemand.validityDate = mYear + "-" + mMonth + "-" + mDay;
                }
                mDemand.describes = releaseDesc.getText().toString();
//                if (mDemand.title)
                if (adapter.getList().size() > 0) {
                    mDemand.setImage(true);
                } else {
                    mDemand.setImage(false);
                }
                mDemand.setmList(LocalMedia.setImage(adapter.getList()));
                if (!TextUtils.isEmpty(mDemand.title)) {
                    if (isEdit) {
                        PreviewActivity.openPreview(this, mDemand, 5);
                        isFinish();
                    }else {
                        PreviewActivity.openPreview(this, mDemand, 3);
                    }
                } else {
                    showShortToast("标题不能为空");
                }
//                if (mList.isEmpty()) {
//                    mPresenter.release(requestParams);
//                } else {
//                    upload();
//                }
                break;
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void success(BaseEntity baseEntity) {
//        startActivity(PreviewActivity.class);
    }

    public void onYearMonthDayPicker(View view) {

        final DatePicker picker = new DatePicker(this);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);
        picker.setTopPadding(ConvertUtils.toPx(this, 10));
        picker.setRangeEnd(2111, 1, 11);
        picker.setRangeStart(mYear, mMonth, mDay);
        picker.setSelectedItem(mYear, mMonth, mDay);

        picker.setResetWhileWheel(false);
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                releaseTime.setCenterString(year + "-" + month + "-" + day);
                mDemand.validityDate = year + "-" + month + "-" + day;
            }
        });
        picker.setOnWheelListener(new DatePicker.OnWheelListener() {
            @Override
            public void onYearWheeled(int index, String year) {
                picker.setTitleText(year + "-" + picker.getSelectedMonth() + "-" + picker.getSelectedDay());
            }

            @Override
            public void onMonthWheeled(int index, String month) {
                picker.setTitleText(picker.getSelectedYear() + "-" + month + "-" + picker.getSelectedDay());
            }

            @Override
            public void onDayWheeled(int index, String day) {
                picker.setTitleText(picker.getSelectedYear() + "-" + picker.getSelectedMonth() + "-" + day);
            }
        });
        picker.show();
    }

    void showInputDialog(String title, int inputType, MaterialDialog.InputCallback inputCallback) {
        mInputDialog.title(title);
        mInputDialog.inputType(inputType);
        mInputDialog.input(title, "", inputCallback);
        mInputDialog.show();
    }

    MaterialDialog.InputCallback titleCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                releaseTitle.setCenterString(input);
                mDemand.title = input.toString();
            }
        }
    };
    MaterialDialog.InputCallback numberCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                releaseNumber.setCenterString(input);
                mDemand.quantity = input.toString();
            }
        }
    };

    public static void thisFinish() {
        mActivity.isFinish();
    }

}