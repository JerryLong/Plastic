package com.conduit.plastic.ui.mine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.base.BaseFrameActivity;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.ui.mine.contract.MineContract;
import com.conduit.plastic.ui.mine.contract.MineModel;
import com.conduit.plastic.ui.mine.contract.MinePresenter;
import com.conduit.plastic.user.UserUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class UserInfoActivity extends BaseFrameActivity<MinePresenter, MineModel> implements MineContract.MineView {

    @BindView(R.id.uinfo_head)
    SuperTextView uinfoHead;
    @BindView(R.id.uinfo_nick)
    SuperTextView uinfoNick;
    @BindView(R.id.uinfo_name)
    SuperTextView uinfoName;
    @BindView(R.id.uinfo_company)
    SuperTextView uinfoCompany;
    @BindView(R.id.uinfo_address)
    SuperTextView uinfoAddress;
    @BindView(R.id.uinfo_tel)
    SuperTextView uinfoTel;
    @BindView(R.id.uinfo_qq)
    SuperTextView uinfoQq;
    @BindView(R.id.uinfo_phone)
    SuperTextView uinfoPhone;
    @BindView(R.id.uinfo_email)
    SuperTextView uinfoEmail;
    @BindView(R.id.radioGroup)
    RadioGroup mRadioGroup;
    @BindView(R.id.radioMale)
    RadioButton radioMale;
    @BindView(R.id.radioFemale)
    RadioButton radioFemale;
    private MaterialDialog.Builder mInputDialog;
    ModifyRequest modifyRequest = new ModifyRequest();
    UserInfo mUserInfo = null;
    UserUtils mUserUtils = null;

    @Override
    protected int LayoutId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void initView() {
        setTitleTxt("个人信息");
        setBackTxt("我的");
        mUserUtils = UserUtils.getInstance();
        mUserInfo = UserUtils.getInstance().getUser();
        Glide.with(this)
                .load(mUserInfo.getHeadImage()).apply(bitmapTransform(new CircleCrop()))
//                .load(mUserInfo.getHeadImage()).apply(bitmapTransform(new CircleCrop()))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        uinfoHead.setRightTvDrawableRight(resource);
                    }
                });
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (checkedId == R.id.radioMale) {
                    modifyRequest.setSex("0");
                    mUserUtils.setSex("0");
                } else if (checkedId == R.id.radioFemale) {
                    modifyRequest.setSex("1");
                    mUserUtils.setSex("1");
                }
            }
        });
        initDialog();
    }

    @Override
    public void initData() {
        super.initData();
        if (mUserInfo.getSex().equals("0")) {
            radioMale.setChecked(true);
        } else {
            radioFemale.setChecked(true);
        }
        uinfoNick.setRightString(mUserInfo.getUserName());
        uinfoName.setRightString(mUserInfo.getContacts());
        uinfoCompany.setRightString(mUserInfo.getCompanyName());
        uinfoAddress.setRightString(mUserInfo.getAddress());
        uinfoTel.setRightString(mUserInfo.getOfficePhone());
        uinfoQq.setRightString(mUserInfo.getWxAccount());
        uinfoPhone.setRightString(mUserInfo.getContactNumber());
        uinfoEmail.setRightString(mUserInfo.getEmail());

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

    @OnClick({R.id.uinfo_head, R.id.uinfo_confirm, R.id.uinfo_nick, R.id.uinfo_name, R.id.uinfo_company, R.id.uinfo_address, R.id.uinfo_tel, R.id.uinfo_qq, R.id.uinfo_phone, R.id.uinfo_email})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.uinfo_head:
                startActivity(new Intent(this, ImageActivity.class));
                break;
            case R.id.uinfo_nick:
                showInputDialog("修改昵称", InputType.TYPE_CLASS_TEXT, nickCallback);
                break;
            case R.id.uinfo_name:
                showInputDialog("修改名字", InputType.TYPE_CLASS_TEXT, nameCallback);
                break;
            case R.id.uinfo_company:
                showInputDialog("修改公司", InputType.TYPE_CLASS_TEXT, companyCallback);
                break;
            case R.id.uinfo_address:
                showInputDialog("修改地址", InputType.TYPE_CLASS_TEXT, addressCallback);
                break;
            case R.id.uinfo_tel:
                showInputDialog("修改座机", InputType.TYPE_CLASS_PHONE, telCallback);
                break;
            case R.id.uinfo_qq:
                showInputDialog("修改QQ或微信", InputType.TYPE_CLASS_TEXT, qqCallback);
                break;
            case R.id.uinfo_phone:
                showInputDialog("修改手机", InputType.TYPE_CLASS_PHONE, phoneCallback);
                break;
            case R.id.uinfo_email:
                showInputDialog("修改邮箱", InputType.TYPE_CLASS_TEXT, emailCallback);
                break;
            case R.id.uinfo_confirm:
                mPresenter.modify(modifyRequest);
                mPresenter.company(modifyRequest);
                break;
        }

    }

    void showInputDialog(String title, int inputType, MaterialDialog.InputCallback inputCallback) {
        mInputDialog.title(title);
        mInputDialog.inputType(inputType);
        mInputDialog.input(title, "", inputCallback);
        mInputDialog.show();
    }

    MaterialDialog.InputCallback nickCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoNick.setRightString(input);
                mUserUtils.setUserName(input.toString());
                modifyRequest.setUserName(input.toString());
            }
        }
    };
    MaterialDialog.InputCallback nameCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoName.setRightString(input);
                mUserUtils.setContacts(input.toString());
                modifyRequest.setContacts(input.toString());
            }
        }
    };
    MaterialDialog.InputCallback companyCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoCompany.setRightString(input);
                mUserUtils.setCompanyName(input.toString());
                modifyRequest.setCompanyName(input.toString());
            }
        }
    };
    MaterialDialog.InputCallback addressCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoAddress.setRightString(input);
                mUserUtils.setAddress(input.toString());
                modifyRequest.setAddress(input.toString());
            }
        }
    };
    MaterialDialog.InputCallback telCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoTel.setRightString(input);
                mUserUtils.setOfficePhone(input.toString());
                modifyRequest.setOfficePhone(input.toString());
            }
        }
    };
    MaterialDialog.InputCallback qqCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoQq.setRightString(input);
                mUserUtils.setQqAccount(input.toString());
                modifyRequest.setQqAccount(input.toString());
            }
        }
    };
    MaterialDialog.InputCallback emailCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoEmail.setRightString(input);
                mUserUtils.setEmail(input.toString());
                modifyRequest.setEmail(input.toString());
            }
        }
    };
    MaterialDialog.InputCallback phoneCallback = new MaterialDialog.InputCallback() {
        @Override
        public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
            if (!TextUtils.isEmpty(input)) {
                uinfoPhone.setRightString(input);
                mUserUtils.setContactNumber(input.toString());
                modifyRequest.setContactNumber(input.toString());
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mUserInfo = UserUtils.getInstance().getUser();
        Glide.with(this)
                .load(mUserInfo.getHeadImage()).apply(bitmapTransform(new CircleCrop()))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        uinfoHead.setRightTvDrawableRight(resource);
                    }
                });
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
        showShortToast("修改成功");
    }

    @Override
    public void demandList(List<DemandEntity> baseEntity) {

    }

}
