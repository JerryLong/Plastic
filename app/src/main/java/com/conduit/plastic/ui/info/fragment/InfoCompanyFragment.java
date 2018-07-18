package com.conduit.plastic.ui.info.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.common.PlasticApp;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.entity.FileBeanEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.base.BaseFrameFragment;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.ui.brand.BrandActivity;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.util.ToastUtils;
import com.conduit.plastic.util.okgo.JsonCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by android on 2017/3/7.
 */

public class InfoCompanyFragment extends BaseFrameFragment<PersonPresenter, PersonModel> implements PersonContract.View {
    @BindView(R.id.company_tv)
    TextView companyTv;
    @BindView(R.id.address_tv)
    TextView addressTv;
    @BindView(R.id.user_logo_tv)
    TextView userLogoTv;
    @BindView(R.id.license_img)
    ImageView licenseImg;
    @BindView(R.id.user_position_tv)
    TextView positionTv;
    private UserInfo mUserEntity;
    private MaterialDialog.Builder mInputDialog;
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_TITLE = "ARG_TITLE";
    ModifyRequest request = new ModifyRequest();

    public static InfoCompanyFragment newInstance(int page, String str) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_TITLE, str);
        InfoCompanyFragment pageFragment = new InfoCompanyFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_company);
        ButterKnife.bind(this, getContentView());
    }

    @Override
    public void initData() {
        super.initData();
        mUserEntity = UserUtils.getInstance().getUser();
//        mUserEntity = DataSupport.where("isLogin=?", "1").find(UserInfo.class).get(0);
    }

    @Override
    public void initView() {
        super.initView();

        initPersonalInfo();

        initDialog();
    }

    private void initPersonalInfo() {
        String companyName = mUserEntity.getCompanyName();
        String address = mUserEntity.getAddress();
        String brandNames = mUserEntity.getBrandNames();
        String position = mUserEntity.getPosition();
        String logoImg = mUserEntity.getLicense();
        Glide.with(getActivity()).load(logoImg).into(licenseImg);
        if (companyName != null) {
            companyTv.setText(companyName);
        }

        if (address != null) {
            addressTv.setText(address);
        }
        if (brandNames != null) {
            userLogoTv.setText(brandNames);
        }
        if (position != null) {
            positionTv.setText(position);
        }

    }

    private void initDialog() {
        mInputDialog = new MaterialDialog.Builder(getActivity());
        mInputDialog.negativeText(getString(R.string.dialog_btn_cancel));
        mInputDialog.positiveText(getString(R.string.dialog_btn_confirm));
        mInputDialog.positiveColorRes(R.color.colorPrimary);
        mInputDialog.negativeColorRes(R.color.colorPrimary);
        mInputDialog.titleColorRes(R.color.text_common);
        mInputDialog.inputType(InputType.TYPE_CLASS_TEXT);
        mInputDialog.widgetColorRes(R.color.colorPrimary);
    }

    private void showDialogs(int id) {
        switch (id) {
            case R.id.user_company_ll:
                mInputDialog.title(getString(R.string.dialog_edit_company));
                mInputDialog.input(companyTv.getText(), "", companyCallback);
//                mInputDialog.input("", mUserEntity.getName(),nameCallback);
                break;
            case R.id.user_address_ll:
                mInputDialog.title(getString(R.string.dialog_edit_address));
                mInputDialog.input(addressTv.getText(), "", addressCallback);
                break;
            case R.id.user_position_ll:
                mInputDialog.title(getString(R.string.dialog_edit_position));
                mInputDialog.input(positionTv.getText(), "", posCallback);
                break;
        }

        mInputDialog.show();
    }

    MaterialDialog.InputCallback companyCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                companyTv.setText(input);
                request.setCompanyName(input.toString());
                mPresenter.modifyCompany(request);
            }
        }
    };

    MaterialDialog.InputCallback addressCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                addressTv.setText(input);
                request.setAddress(input.toString());
                mPresenter.modifyCompany(request);
            }
        }
    };

    MaterialDialog.InputCallback posCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                positionTv.setText(input);
                request.setPosition(input.toString());
                mPresenter.modifyCompany(request);
            }
        }
    };


    @OnClick({R.id.user_company_ll, R.id.user_address_ll, R.id.user_logo_ll, R.id.user_position_ll, R.id.user_license_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_license_ll:
//                FunctionConfig config = PlasticApp.initPickerConfig();
//
//                PictureConfig.init(config);
//                PictureConfig.getPictureConfig().openPhoto(getActivity(), resultCallback);
                break;
            case R.id.user_logo_ll:
                BrandActivity.navToBrand(getActivity(), false);
                break;
            case R.id.user_company_ll:
            case R.id.user_position_ll:
            case R.id.user_address_ll:
                showDialogs(view.getId());
                break;
            default:
                getActivity().finish();
                break;
        }
    }

    /**
//     * 图片回调方法
//     */
//    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
//        @Override
//        public void onSelectSuccess(List<LocalMedia> resultList) {
//            upload(resultList);
//        }
//    };

    @Override
    public void onRequestStart() {

    }

    @Override
    public void onRequestEnd() {

    }


    @Override
    public void modify(BaseEntity<UserInfo> baseEntity) {
        BaseEntity<UserInfo> userEntity = baseEntity;
//        List<UserInfo> info = DataSupport.where("isLogin = ?", "1").find(UserInfo.class);
//        String sk = info.get(0).getSk();
//        UserInfo userInfo = userEntity.getData();
//        userInfo.setLogin(true);
//        userInfo.setSk(sk);
//        userInfo.updateAll("uid = ? ", userInfo.getUid());
        UserUtils.getInstance().saveUser(baseEntity.getData());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.Activity.BrandActivity && data != null) {
            BrandEntity brandEntity = (BrandEntity) data.getExtras().getSerializable(Constants.Params.BrandParams);
            userLogoTv.setText(brandEntity.getBrandNameCn());
            request.setBrandNames(brandEntity.getBrandNameCn());
            mPresenter.modifyCompany(request);
        }
    }

//    void upload(List<LocalMedia> list) {
//        List<String> docTypes = new ArrayList<>();
//        List<String> fileType = new ArrayList<>();
//        List<File> files = new ArrayList<>();
//        for (LocalMedia media : list) {
//            File file = new File(media.getCompressPath());
//            files.add(file);
//            docTypes.add("30");
//            fileType.add("10");
//        }
//
//        OkGo.post(UrlContants.API_BASE_URL + UrlContants.UPLOAD)
//                .addUrlParams("fileType", fileType)
//                .addUrlParams("docType", docTypes)
//                .addFileParams("files", files)
//                .execute(new JsonCallback<BaseEntity<FileBeanEntity>>() {
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//
//                    }
//
//                    @Override
//                    public void onSuccess(BaseEntity<FileBeanEntity> baseEntity, Call call, Response response) {
//                        if (baseEntity.getErrorCode() == 0) {
//                            request.setLicense(baseEntity.getData().getResourcePaths().get(0));
//                            mPresenter.modifyCompany(request);
//                            Glide.with(getActivity()).load(baseEntity.getData().getAccessUrls().get(0)).bitmapTransform(new CropCircleTransformation(getActivity())).into(licenseImg);
//                        } else {
//                            ToastUtils.showShort(baseEntity.getError());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        super.onError(call, response, e);
//                    }
//                });
//    }
}
