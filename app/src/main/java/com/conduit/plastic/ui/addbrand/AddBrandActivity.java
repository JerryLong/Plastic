package com.conduit.plastic.ui.addbrand;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.common.PlasticApp;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.FileBeanEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.conduit.plastic.widget.EditWatcher;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class AddBrandActivity extends BaseFrameActivity<AddBrandPresenter, AddBrandModel> implements AddBrandContract.View {

    @BindView(R.id.product_img)
    ImageView productImg;
    @BindView(R.id.product_title_edit)
    EditText titleEdit;
    @BindView(R.id.product_des_edit)
    EditText desEdit;
    @BindView(R.id.product_btn)
    Button productBtn;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    Map<String, String> map = new HashMap<>();
    UserInfo mUserEntity;


    @Override
    protected int LayoutId() {
        return R.layout.activity_add_product;
    }

    @Override
    public void initView() {
        setBackTxt("返回");
        setTitleTxt("上传");
        new EditWatcher(productBtn, desEdit, titleEdit);
    }


    @Override
    public void initData() {
        super.initData();

        mUserEntity = Constants.getCurrentUser();
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
                    public void onSuccess(BaseEntity<FileBeanEntity> baseEntity, Call call, Response response) {
                        if (baseEntity.getErrorCode() == 0) {

                            map.put("brandLogo", baseEntity.getData().getResourcePaths().get(0));
                            Glide.with(AddBrandActivity.this).load(baseEntity.getData().getAccessUrls().get(0)).into(productImg);
                        } else {
                            ToastUtils.showShort(baseEntity.getError());
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                    }
                });
    }

    @OnClick({R.id.product_img, R.id.product_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.product_img:
                // 先初始化参数配置，在启动相册
                PictureSelector.create(AddBrandActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .theme(R.style.picture_default_style)
                        .maxSelectNum(1)
                        .compress(true)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.product_btn:
                map.put("brandNameCn", titleEdit.getText().toString());
                map.put("companyName", mUserEntity.getCompanyName());
                map.put("describe", desEdit.getText().toString());
                mPresenter.addBrand(map);
                break;
        }

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
                    upload(selectList);
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
    public void addBrand(BaseEntity baseEntity) {

    }

}
