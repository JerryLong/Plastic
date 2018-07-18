package com.conduit.plastic.ui.main.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.library.SuperTextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.ui.mine.ContactsActivity;
import com.conduit.plastic.ui.mine.ExplanActivity;
import com.conduit.plastic.ui.mine.ImageActivity;
import com.conduit.plastic.ui.mine.NeedsActivity;
import com.conduit.plastic.ui.mine.OpinionActivity;
import com.conduit.plastic.ui.mine.SettingActivity;
import com.conduit.plastic.ui.mine.UserInfoActivity;
import com.conduit.plastic.user.UserUtils;
import com.conduit.plastic.util.ShareUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * A fragment with a Google +1 button.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MineFragment extends Fragment {


    @BindView(R.id.toolbar_title)
    TextView mTitle;
    Unbinder unbinder;
    @BindView(R.id.tab_mine_head)
    SuperTextView mTvHead;
    UserInfo mUserInfo = null;

    public MineFragment() {

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MineFragment.
     */

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserInfo = UserUtils.getInstance().getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        unbinder = ButterKnife.bind(this, view);
        mTitle.setText("我的");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mUserInfo = UserUtils.getInstance().getUser();
        mTvHead.setLeftTopString(mUserInfo.getContacts())
                .setLeftBottomString("账户:" + mUserInfo.getUserCode())
                .setOnSuperTextViewClickListener(new SuperTextView.OnSuperTextViewClickListener() {
                    @Override
                    public void onClickListener(SuperTextView superTextView) {
                        startActivity(new Intent(getContext(), UserInfoActivity.class));
                    }
                }).setLeftImageViewClickListener(new SuperTextView.OnLeftImageViewClickListener() {
            @Override
            public void onClickListener(ImageView imageView) {
                startActivity(new Intent(getContext(), ImageActivity.class));
            }
        });
        Glide.with(this)
                .load(mUserInfo.getHeadImage()).apply(bitmapTransform(new CircleCrop()))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        mTvHead.setLeftIcon(resource);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tab_mine_head, R.id.tab_mine_demand, R.id.tab_mine_explain, R.id.tab_mine_invitation, R.id.tab_mine_setting, R.id.tab_mine_opinion, R.id.tab_mine_contact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tab_mine_head:
                break;
            case R.id.tab_mine_demand:
                startActivity(new Intent(getContext(), NeedsActivity.class));
                break;
            case R.id.tab_mine_explain:
                startActivity(new Intent(getContext(), ExplanActivity.class));
                break;
            case R.id.tab_mine_invitation:
                break;
            case R.id.tab_mine_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
            case R.id.tab_mine_opinion:
                startActivity(new Intent(getContext(), OpinionActivity.class));
                break;
            case R.id.tab_mine_contact:
                startActivity(new Intent(getContext(), ContactsActivity.class));
                break;
        }
    }
}
