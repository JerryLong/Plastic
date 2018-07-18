package com.conduit.plastic.ui.info.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameFragment;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.user.UserUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android on 2017/3/7.
 */

public class InfoContactFragment extends BaseFrameFragment<PersonPresenter, PersonModel> implements PersonContract.View {
    @BindView(R.id.mphone_tv)
    TextView mphoneTv;
    @BindView(R.id.telephone_tv)
    TextView telephoneTv;
    @BindView(R.id.qq_tv)
    TextView qqTv;
    @BindView(R.id.wechat_tv)
    TextView wechatTv;
    @BindView(R.id.weibo_tv)
    TextView weiboTv;
    @BindView(R.id.email_tv)
    TextView emailTv;
    private MaterialDialog.Builder mInputDialog;
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String ARG_TITLE = "ARG_TITLE";
    ModifyRequest request = new ModifyRequest();
    UserInfo mUserEntity;

    public static InfoContactFragment newInstance(int page, String str) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putString(ARG_TITLE, str);
        InfoContactFragment pageFragment = new InfoContactFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_info_contact);
        ButterKnife.bind(this, getContentView());
    }

    @Override
    public void initData() {
        super.initData();
//        mUserEntity = DataSupport.where("isLogin=?", "1").find(UserInfo.class).get(0);
        mUserEntity = UserUtils.getInstance().getUser();
    }

    @Override
    public void initView() {
        super.initView();

        initPersonalInfo();

        initDialog();
    }

    private void initPersonalInfo() {
        String phone = mUserEntity.getContactNumber();
        String telephone = mUserEntity.getOfficePhone();
        String qqAcount = mUserEntity.getQqAccount();
        String wxAcount = mUserEntity.getWxAccount();
        String wbAcount = mUserEntity.getWbAccount();
        String email = mUserEntity.getEmail();

        if (phone != null) {
            mphoneTv.setText(phone);
        }

        if (telephone != null) {
            telephoneTv.setText(telephone);
        }

        if (qqAcount != null) {
            qqTv.setText(qqAcount);
        }

        if (wxAcount != null) {
            wechatTv.setText(wxAcount);
        }
        if (wbAcount != null) {
            weiboTv.setText(wbAcount);
        }
        if (email != null) {
            emailTv.setText(email);
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
            case R.id.user_mphone_ll:
                mInputDialog.title(getString(R.string.dialog_edit_mphone));
                mInputDialog.inputType(InputType.TYPE_CLASS_PHONE);
                mInputDialog.input(getString(R.string.dialog_edit_mphone), mUserEntity.getContactNumber(), phoneCallback);
                break;
            case R.id.user_telephone_ll:
                mInputDialog.title(getString(R.string.dialog_edit_telephone));
                mInputDialog.inputType(InputType.TYPE_CLASS_PHONE);
                mInputDialog.input(getString(R.string.dialog_edit_telephone), mUserEntity.getOfficePhone(), telCallback);
                break;
            case R.id.user_qq_ll:
                mInputDialog.title(getString(R.string.dialog_edit_qq));
                mInputDialog.inputType(InputType.TYPE_CLASS_PHONE);
                mInputDialog.input(getString(R.string.dialog_edit_qq), mUserEntity.getQqAccount(), qqCallback);
                break;
            case R.id.user_wechat_ll:
                mInputDialog.title(getString(R.string.dialog_edit_wchat));
                mInputDialog.inputType(InputType.TYPE_CLASS_TEXT);
                mInputDialog.input(getString(R.string.dialog_edit_wchat), mUserEntity.getWxAccount(), wxCallback);
                break;
            case R.id.user_weibo_ll:
                mInputDialog.title(getString(R.string.dialog_edit_weibo));
                mInputDialog.inputType(InputType.TYPE_CLASS_TEXT);
                mInputDialog.input(getString(R.string.dialog_edit_weibo), mUserEntity.getWbAccount(), wbCallback);
                break;
            case R.id.user_email_ll:
                mInputDialog.title(getString(R.string.dialog_edit_email));
                mInputDialog.inputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                mInputDialog.input(getString(R.string.dialog_edit_email), mUserEntity.getEmail(), mailCallback);
                break;
        }

        mInputDialog.show();
    }

    MaterialDialog.InputCallback phoneCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                mphoneTv.setText(input);
                request.setContactNumber(input.toString());
                mPresenter.modifyCompany(request);
            }
        }
    };

    MaterialDialog.InputCallback telCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                telephoneTv.setText(input);
                request.setOfficePhone(input.toString());
                mPresenter.modifyCompany(request);
            }
        }
    };

    MaterialDialog.InputCallback qqCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                qqTv.setText(input);
                request.setQqAccount(input.toString());
                mPresenter.modify(request);
            }
        }
    };

    MaterialDialog.InputCallback wxCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                wechatTv.setText(input);
                request.setWxAccount(input.toString());
                mPresenter.modify(request);
            }
        }
    };

    MaterialDialog.InputCallback wbCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                weiboTv.setText(input);
                request.setWbAccount(input.toString());
                mPresenter.modify(request);
            }
        }
    };
    MaterialDialog.InputCallback mailCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                emailTv.setText(input);
                request.setEmail(input.toString());
                mPresenter.modify(request);
            }
        }
    };


    @OnClick({R.id.user_mphone_ll, R.id.user_telephone_ll, R.id.user_qq_ll, R.id.user_wechat_ll, R.id.user_weibo_ll, R.id.user_email_ll})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_mphone_ll:
            case R.id.user_telephone_ll:
            case R.id.user_qq_ll:
            case R.id.user_wechat_ll:
            case R.id.user_weibo_ll:
            case R.id.user_email_ll:
                showDialogs(view.getId());
                break;
            default:
                getActivity().finish();
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
    public void modify(BaseEntity<UserInfo> baseEntity) {
        BaseEntity<UserInfo> userEntity = baseEntity;
//        List<UserInfo> info = DataSupport.where("isLogin = ?", "1").find(UserInfo.class);
//        String sk = info.get(0).getSk();
//        UserInfo userInfo = userEntity.getData();
//        userInfo.setLogin(true);
//        userInfo.setSk(sk);
//        userInfo.updateAll("uid = ? and isLogin=?", userInfo.getUid(), "1");
        UserUtils.getInstance().saveUser(baseEntity.getData());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
