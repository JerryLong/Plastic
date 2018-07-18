package com.conduit.plastic.ui.mine;


import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.FileBeanEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.ui.mine.contract.MineContract;
import com.conduit.plastic.ui.mine.contract.MineModel;
import com.conduit.plastic.ui.mine.contract.MinePresenter;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.util.CustomDialog;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.adapter.Call;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Response;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.bumptech.glide.request.RequestOptions.errorOf;
import static com.bumptech.glide.request.RequestOptions.placeholderOf;

public class ImageActivity extends BaseFrameActivity<MinePresenter, MineModel> implements MineContract.MineView {


    @BindView(R.id.img_head)
    ImageView mImgHead;
    UserInfo mUserInfo = null;
    ModifyRequest modifyRequest = new ModifyRequest();
    private CustomDialog customDialog;

    @Override
    protected int LayoutId() {
        return R.layout.activity_image;
    }

    RequestOptions options = new RequestOptions()
//            .centerCrop()
//            .placeholder(R.drawable.img_one)
//            .error(R.drawable.img_two)
            .bitmapTransform(new CircleCrop())
            .priority(Priority.HIGH);

    @Override
    public void initView() {
        setTitleTxt("个人头像");
        setBackTxt("返回");
        customDialog = new CustomDialog(this, R.style.CustomDialog);
        mUserInfo = UserUtils.getInstance().getUser();
//        Logger.i(mUserInfo.getHeadImage() + "  " + mUserInfo.getMobilePhone());
//        showShortToast(mUserInfo.getHeadImage());
        Glide.with(this).load(mUserInfo.getHeadImage())
                .apply(options)
                .into(mImgHead);
    }


    @OnClick(R.id.img_head)
    public void onViewClicked() {
        show();
    }

    private void show() {
        final Dialog bottomDialog = new Dialog(this, R.style.BottomDialog);
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_content_normal, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
        contentView.findViewById(R.id.dialog_img_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog.dismiss();
            }
        });
        contentView.findViewById(R.id.dialog_img_camare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(ImageActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .theme(R.style.picture_default_style)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                bottomDialog.dismiss();
            }
        });
        contentView.findViewById(R.id.dialog_img_gallery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(ImageActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .theme(R.style.picture_default_style)
                        .maxSelectNum(1)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                bottomDialog.dismiss();
            }
        });
    }

    void upload(List<LocalMedia> list) {
        List<String> docTypes = new ArrayList<>();
        List<String> fileType = new ArrayList<>();
        List<File> files = new ArrayList<>();
        for (LocalMedia media : list) {
            files.add(new File(media.getCompressPath()));
            docTypes.add("30");
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
                    public void onSuccess(BaseEntity<FileBeanEntity> baseEntity, okhttp3.Call call, Response response) {
                        if (baseEntity.getErrorCode() == 0) {
                            modifyRequest.setHeadImage(baseEntity.getData().getResourcePaths().get(0));
                            mPresenter.modify(modifyRequest);

                        } else {
                            ToastUtils.showShort(baseEntity.getError());
                        }
                    }

                    @Override
                    public void onError(okhttp3.Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        showShortToast(e.getMessage());
                    }

                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
//                    Glide.with(this).load(selectList.get(0).getPath())
//                            .apply(options)
//                            .into(mImgHead);
                    upload(selectList);
                    customDialog.show();
                    break;
            }
        }
    }

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }

    @Override
    public void ordinary(BaseEntity baseEntity) {

    }

    @Override
    public void modify(BaseEntity<UserInfo> baseEntity) {
        UserUtils.getInstance().saveUser(baseEntity.getData());
        Glide.with(ImageActivity.this).load(UserUtils.getInstance().getUser().getHeadImage())
                .apply(options)
                .into(mImgHead);
        customDialog.dismiss();
    }

    @Override
    public void demandList(List<DemandEntity> baseEntity) {

    }
}
