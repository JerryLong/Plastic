package com.conduit.plastic.ui.info.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.conduit.plastic.R;
import com.conduit.plastic.api.UrlContants;
import com.conduit.plastic.common.PlasticApp;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.FileBeanEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameFragment;
import com.conduit.plastic.request.ModifyRequest;
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

public class InfoPersonFragment extends BaseFrameFragment<PersonPresenter, PersonModel> implements PersonContract.View {
    @BindView(R.id.name_tv)
    TextView nameTv;
    //    @BindView(R.id.sex_tv)
//    TextView sexTv;
    @BindView(R.id.sex_switch)
    SwitchCompat sexSwitch;
    @BindView(R.id.idcard_tv)
    TextView idcardTv;
    //    @BindView(R.id.user_position_tv)
//    TextView userPositionTv;
    @BindView(R.id.user_card_img)
    ImageView userCardImg;
    private MaterialDialog.Builder mInputDialog;
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_TITLE = "ARG_TITLE";
    UserInfo mUserEntity;
    ModifyRequest request = new ModifyRequest();

    public static InfoPersonFragment newInstance(int page, String str) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_TITLE, str);
        InfoPersonFragment pageFragment = new InfoPersonFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_person);
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
        sexSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    request.setSex("1");
                } else {
                    request.setSex("2");
                }
            }
        });

        initPersonalInfo();

        initDialog();
    }

    private void initPersonalInfo() {
        String userName = mUserEntity.getUserName();
        String sex = mUserEntity.getSex();
        String idcard = mUserEntity.getIdCardNum();
        String headcard = mUserEntity.getHeadImage();
//        Glide.with(getActivity()).load(headcard).bitmapTransform(new CropCircleTransformation(getActivity())).into(userCardImg);

        if (userName != null) {
            nameTv.setText(userName);
        }

        if (sex != null) {
            if (Integer.parseInt(sex) == 1)
                sexSwitch.setChecked(true);
            else
                sexSwitch.setChecked(false);
        }

        if (idcard != null) {
            idcardTv.setText(idcard);
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
            case R.id.user_name_ll:
                mInputDialog.title(getString(R.string.dialog_edit_name));
                mInputDialog.input(getString(R.string.dialog_edit_name), nameTv.getText(), nameCallback);
//                mInputDialog.input("", mUserEntity.getName(),nameCallback);
                break;
            case R.id.user_idcard_ll:
                mInputDialog.title(getString(R.string.dialog_edit_idcard));
                mInputDialog.input(getString(R.string.dialog_edit_idcard), idcardTv.getText(), idcardCallback);
                break;

//            case R.id.user_position_ll:
//                mInputDialog.title(getString(R.string.dialog_edit_address));
//                mInputDialog.input("", mUserEntity.getCity(), addressCallback);
//                break;
//            case R.id.user_github_ll:
//                mInputDialog.title(getString(R.string.dialog_edit_github));
//                mInputDialog.input("", mUserEntity.getGithub_name(), githubCallback);
//                break;
        }

        mInputDialog.show();
    }

    MaterialDialog.InputCallback nameCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                nameTv.setText(input);
//                ContentValues values = new ContentValues();
//                values.put("userName", input.toString());
//                DataSupport.updateAll(UserInfo.class, values, "isLogin = ?", "1");
                UserUtils.getInstance().setUserName(input.toString());
                request.setUserName(input.toString());
                mPresenter.modify(request);
            }
        }
    };

    MaterialDialog.InputCallback idcardCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                idcardTv.setText(input);
//                ContentValues values = new ContentValues();
//                values.put("idCardNum", input.toString());
//                DataSupport.updateAll(UserInfo.class, values, "isLogin = ?", "1");
                UserUtils.getInstance().setIdCardNum(input.toString());
                request.setIdCardNum(input.toString());
                mPresenter.modify(request);
            }
        }
    };


    @OnClick({R.id.user_name_ll, R.id.user_idcard_ll, R.id.user_card_ll, R.id.user_card_img})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_name_ll:
//            case R.id.user_sex_ll:
            case R.id.user_idcard_ll:
                showDialogs(view.getId());
                break;
            case R.id.user_card_img:
            case R.id.user_card_ll:
//                // 先初始化参数配置，在启动相册
//                FunctionConfig config = PlasticApp.initPickerConfig();
//
//                PictureConfig.init(config);
//                PictureConfig.getPictureConfig().openPhoto(getActivity(), resultCallback);
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
//        List<UserInfo> info = DataSupport.where("isLogin = ?", "1").find(UserInfo.class);
//        String sk = info.get(0).getSk();
//        UserInfo userInfo = userEntity.getData();
//        userInfo.setLogin(true);
//        userInfo.setSk(sk);
//        userInfo.updateAll("uid = ? ", userInfo.getUid());
        UserUtils.getInstance().saveUser(baseEntity.getData());
    }

//    void upload(List<LocalMedia> list) {
//        List<String> docTypes = new ArrayList<>();
//        List<String> fileType = new ArrayList<>();
//        List<File> files = new ArrayList<>();
//        for (LocalMedia media : list) {
//            files.add(new File(media.getCompressPath()));
//            docTypes.add("30");
//            fileType.add("10");
//        }
//        Logger.e("==aaaa="+files.get(0).length());
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
//                            request.setHeadImage(baseEntity.getData().getResourcePaths().get(0));
//                            mPresenter.modify(request);
//                            Glide.with(getActivity()).load(baseEntity.getData().getAccessUrls().get(0)).bitmapTransform(new CropCircleTransformation(getActivity())).into(userCardImg);
//                        } else {
//                            ToastUtils.showShort(baseEntity.getError());
//                        }
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        ToastUtils.showShort(e.getMessage());
//                    }
//                });
//    }
}
