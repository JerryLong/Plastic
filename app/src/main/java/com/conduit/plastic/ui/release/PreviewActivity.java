package com.conduit.plastic.ui.release;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.allen.library.SuperTextView;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.GridImageAdapter;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.FileBeanEntity;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.ui.release.contract.DemandContract;
import com.conduit.plastic.ui.release.contract.DemandModel;
import com.conduit.plastic.ui.release.contract.DemandPresenter;
import com.conduit.plastic.util.CustomDialog;
import com.conduit.plastic.util.FullyGridLayoutManager;
import com.conduit.plastic.util.ShareUtil;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class PreviewActivity extends BaseFrameActivity<DemandPresenter, DemandModel> implements DemandContract.DemandView {

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
    @BindView(R.id.anonymous)
    RadioButton anonymous;
    @BindView(R.id.anonymous_not)
    RadioButton anonymousNot;
    @BindView(R.id.release_confirm)
    SuperTextView releaseConfirm;
    @BindView(R.id.release_release)
    SuperTextView releaseRelease;
    @BindView(R.id.release_phone)
    SuperTextView releasePhone;
    @BindView(R.id.release_company)
    SuperTextView releaseCompany;
    @BindView(R.id.release_release_not)
    SuperTextView releaseReleaseNot;
    @BindView(R.id.release_desc_tv)
    SuperTextView releaseDescTv;
    @BindView(R.id.release_cancel)
    SuperTextView releaseCancel;
    private static String ARG_PARAM = "entity";
    private static String ARG_PARAM_TWO = "isMain";
    DemandEntity mRequestParams = new DemandEntity();
    @BindView(R.id.release_recycler_layout)
    SuperTextView releaseLayout;
    private int isMain = 0;//0首页 1个人中心
    String[] standard;
    String[] texture;
    private GridImageAdapter adapter;
    List<LocalMedia> mList = new ArrayList<>();
    private CustomDialog customDialog;

    public static void openPreview(Context context, DemandEntity entity, int isMain) {
        Intent intent = new Intent(context, PreviewActivity.class);
        intent.putExtra(PreviewActivity.ARG_PARAM, entity);
        intent.putExtra(PreviewActivity.ARG_PARAM_TWO, isMain);
        context.startActivity(intent);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_release;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("预览");
        customDialog = new CustomDialog(this, R.style.CustomDialog);
        standard = getResources().getStringArray(R.array.standard_array);
        texture = getResources().getStringArray(R.array.texture_array);
        isMain = getIntent().getIntExtra(ARG_PARAM_TWO, 0);
        mRequestParams = (DemandEntity) getIntent().getSerializableExtra(ARG_PARAM);
        releaseRelease.setVisibility(View.VISIBLE);
        releaseReleaseNot.setVisibility(View.GONE);
        releaseHead.setVisibility(View.GONE);
        releaseDesc.setEnabled(false);
        adapter = new GridImageAdapter(this, null);
        if (isMain == 0) {
            setTitleTxt("需求详情");
            releaseConfirm.setVisibility(View.GONE);
            releaseCancel.setVisibility(View.GONE);
            releaseTitle.setCenterString(mRequestParams.title);
            releaseQuality.setCenterString(mRequestParams.getTextures() + "");
            releaseStandard.setCenterString(mRequestParams.getStandards() + "");
//            if (!TextUtils.isEmpty(mRequestParams.getMobilePhone())) {
//
//            }
            if (mRequestParams.isConceal == 1) {
                releasePhone.setVisibility(View.VISIBLE);
            }
            mList = com.conduit.plastic.entity.LocalMedia.getParcelImage(mRequestParams.getImageList());
        } else if (isMain == 1) {
            releaseConfirm.setVisibility(View.GONE);
            releaseCancel.setVisibility(View.VISIBLE);
        } else if (isMain == 2) {
            releaseCancel.setVisibility(View.VISIBLE);
            releaseCancel.setCenterString("删除");
            releaseConfirm.setVisibility(View.VISIBLE);
            releaseConfirm.setCenterString("修改");
            if (mRequestParams.getProductImg() != null && mRequestParams.getProductImg().length > 0) {
                releaseLayout.setVisibility(View.VISIBLE);
            } else {
                releaseLayout.setVisibility(View.GONE);
            }
            mList = com.conduit.plastic.entity.LocalMedia.getParcelImage(mRequestParams.getImageList());
        } else if (isMain == 4) {
        } else {
            releaseConfirm.setVisibility(View.VISIBLE);
            releaseCancel.setVisibility(View.GONE);
            releaseConfirm.setCenterString("发布");
            releaseQuality.setCenterString(mRequestParams.getTextures() + "");
            releaseStandard.setCenterString(mRequestParams.getStandards() + "");
        }
        FullyGridLayoutManager manager = new FullyGridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        releaseRecycler.setLayoutManager(manager);

        releaseRecycler.setAdapter(adapter);
        mList = com.conduit.plastic.entity.LocalMedia.getParcelImage(mRequestParams.getmList());
        adapter.setList(mList);
        adapter.notifyDataSetChanged();
        releaseTitle.setCenterString(mRequestParams.getTitle() + "");
        releaseDesc.setText(mRequestParams.getDescribes() + " ");
        releaseSpec.setCenterString(mRequestParams.getSpec() + "");
        releaseTime.setCenterString(mRequestParams.validityDate + "");
        releaseNumber.setCenterString(mRequestParams.getQuantitys() + "");
        releaseLogo.setCenterString(mRequestParams.getBrandName() + "");
        releaseArticle.setCenterString(mRequestParams.getProductName() + "");


        releasePhone.setCenterString(mRequestParams.getMobilePhone() + "");
        if (mRequestParams.isConceal == 0) {
            releaseRelease.setCenterString("匿名");
            releasePhone.setVisibility(View.GONE);
            releaseCompany.setVisibility(View.GONE);
        } else {
            releaseCompany.setCenterString(mRequestParams.getCompanyName() + "").setVisibility(View.VISIBLE);
            releaseRelease.setCenterString(mRequestParams.getUserName() + "");
            releasePhone.setCenterString(mRequestParams.getMobilePhone() + "").setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {
        customDialog.dismiss();
    }

    @Override
    public void success(BaseEntity baseEntity) {
        customDialog.dismiss();
        if (baseEntity.errorCode == 0) {
            if (isMain != 2) {
                ReleaseActivity.thisFinish();
                showShortToast("发布成功");
            } else {

            }
//            startActivity(new Intent(this,FinishActivity.class));
            finish();

        } else {
            ToastUtils.showShort(baseEntity.getError());
        }
    }


    @OnClick({R.id.release_cancel, R.id.release_confirm, R.id.release_phone, R.id.release_release})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.release_cancel:
                switch (isMain) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(mRequestParams.getId())) {
                            mPresenter.delete(mRequestParams);
                            customDialog.show();
                        } else {
                            showShortToast("数据异常请重新加载");
                        }
                        break;
                    case 3:
//                        customDialog.show();
//                        if (mRequestParams.isImage()) {
//                            upload();
//                        } else {
//
//                            mPresenter.release(mRequestParams);
//                        }
                        break;

                }
//                ReleaseActivity.openRelease(this, mRequestParams, true);
                break;
            case R.id.release_confirm:
                switch (isMain) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:

                        ReleaseActivity.openRelease(PreviewActivity.this, mRequestParams, true);
                        isFinish();
                        break;
                    case 3:
                        customDialog.show();
                        if (mRequestParams.isImage()) {
                            upload();
                        } else {
                            mPresenter.release(mRequestParams);
                        }
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (customDialog.isShowing()) {
                                    customDialog.dismiss();
                                    showShortToast("网络错误，请稍后再试");
                                }

                            }
                        }, 60000);
                        break;
                    case 5:
                        customDialog.show();
                            mPresenter.edit(mRequestParams);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (customDialog.isShowing()) {
                                    customDialog.dismiss();
                                    showShortToast("网络错误，请稍后再试");
                                }

                            }
                        }, 60000);
                        break;

                }
                break;
            case R.id.release_release:
                if (mRequestParams.isConceal == 0) {
                    startActivity(FinishActivity.class);
                }
                break;
            case R.id.release_phone:
                ShareUtil.callPhone(this, mRequestParams.getMobilePhone());
                break;
        }

    }

    void upload() {
        List<String> docTypes = new ArrayList<>();
        List<String> fileType = new ArrayList<>();
        List<File> files = new ArrayList<>();
        for (LocalMedia media : mList) {
            files.add(new File(media.getCompressPath()));
            docTypes.add("10");
            fileType.add("10");
        }

        OkGo.post(UrlContants.API_BASE_URL + UrlContants.UPLOAD)
                .addUrlParams("fileType", fileType)
                .addUrlParams("docType", docTypes)
                .addFileParams("files", files)
                .execute(new JsonCallback<BaseEntity<FileBeanEntity>>() {
                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);

                    }

                    @Override
                    public void onSuccess(BaseEntity<FileBeanEntity> baseEntity, Call call, Response response) {
                        customDialog.dismiss();
                        if (baseEntity.getErrorCode() == 0) {
                            StringBuffer stringBuffer = new StringBuffer();
                            for (String s : baseEntity.getData().getResourcePaths()) {
                                stringBuffer.append(s + ",");
                            }
                            mRequestParams.productImgs = stringBuffer.substring(0, stringBuffer.length() - 1);
                            mPresenter.release(mRequestParams);
                        } else {

                            ToastUtils.showShort(baseEntity.getError());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showShortToast(e.getMessage());
                        customDialog.dismiss();
                    }

                });
    }
}
