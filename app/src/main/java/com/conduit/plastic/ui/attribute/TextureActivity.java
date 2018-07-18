package com.conduit.plastic.ui.attribute;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.conduit.plastic.R;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.entity.StringEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.adapter.TagAdapteer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class TextureActivity extends BaseActivity {
    @BindView(R.id.texture_tag)
    RecyclerView mRecycler;
    String mTexture = "10";
    TagAdapteer mAdapter;
    String[] texture1;
    String[] texture;
    @BindView(R.id.main_spec_two)
    Button specTwo;
    private int mSpecSelected = -1;
    List<StringEntity> mSpec = new ArrayList<>();
    List<StringEntity> mSpec1 = new ArrayList<>();

    public static void navToTexture(Activity context) {
        Intent intent = new Intent(context, TextureActivity.class);
        context.startActivityForResult(intent, Constants.Activity.TextureActivity);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_texture;
    }

    @Override
    public void initView() {
        setTitleTxt("材质");
        mAdapter = new TagAdapteer();
        texture = getResources().getStringArray(R.array.texture_array);
        texture1 = getResources().getStringArray(R.array.texture_cn_array);

        for (int i = 0; i < texture.length; i++) {
            mSpec.add(new StringEntity(i, texture[i], false));
        }
        for (int i = 0; i < texture1.length; i++) {
            mSpec1.add(new StringEntity(i, texture1[i], false));
        }
        mAdapter.setNewData(mSpec);
        mRecycler.setLayoutManager(new GridLayoutManager(this, 4));
        mRecycler.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                if (mSpecSelected == -1) {
                    mAdapter.getData().get(position).setSelected(true);
                    mSpecSelected = position;
                } else {
                    if (mAdapter.getData().get(position).isSelected()) {
                        mAdapter.getData().get(mSpecSelected).setSelected(false);
                        mSpecSelected = -1;
                    } else {
                        mAdapter.getData().get(mSpecSelected).setSelected(false);
                        mAdapter.getData().get(position).setSelected(true);
                        mSpecSelected = position;
                    }
                }
                if (mSpecSelected == -1) {
                    if (!mTexture.equals("0")) {
                        mTexture = "0";
                    }
                } else {
                    mTexture = String.valueOf((mSpecSelected + 1) * 10);
                }
                mAdapter.setNewData(mAdapter.getData());
            }
        });
    }

    void initBack(String entity) {
        if (!entity.equals("0")) {
            Intent intent = new Intent();
            intent.putExtra(Constants.Params.TextureParams, entity);
            setResult(RESULT_OK, intent);
        }
        finish();
    }


    @OnClick({R.id.main_spec_two, R.id.texture_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_spec_two:
                if (mSpecSelected!=-1){
                    mAdapter.getData().get(mSpecSelected).setSelected(false);
                }
                if (specTwo.isSelected()) {
                    specTwo.setSelected(false);
//                    mSpec1.get(mSpecSelected).setSelected(false);
                    mAdapter.setNewData(mSpec);
                } else {
//                    mSpec.get(mSpecSelected).setSelected(false);
                    mAdapter.setNewData(mSpec1);
                    specTwo.setSelected(true);
                }
                mSpecSelected = -1;
                break;
            case R.id.texture_confirm:
                initBack(mTexture);
                break;
        }
    }
}
