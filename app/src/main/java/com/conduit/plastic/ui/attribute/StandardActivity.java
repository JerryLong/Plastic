package com.conduit.plastic.ui.attribute;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.StandardAdapter;
import com.conduit.plastic.common.BaseActivity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.ui.productname.ProductNameActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StandardActivity extends BaseActivity {
    String[] standard;
    //    @BindView(R.id.standard_recycler)
//    RecyclerView recycler;
    String mStandard = "0";
    List<String> mList = new ArrayList<>();
    StandardAdapter mStandardAdapter;
    @BindView(R.id.standard_one)
    RadioButton standardOne;
    @BindView(R.id.standard_two)
    RadioButton standardTwo;
    @BindView(R.id.standard_three)
    RadioButton standardThree;
    @BindView(R.id.standard_four)
    RadioButton standardFour;
    @BindView(R.id.standard_group)
    RadioGroup standardGroup;

    public static void navToStandard(Activity context) {
        Intent intent = new Intent(context, StandardActivity.class);
        context.startActivityForResult(intent, Constants.Activity.StandardActivity);
    }

    @Override
    protected int LayoutId() {
        return R.layout.activity_standard;
    }

    @Override
    public void initView() {
        setTitleTxt("标准");
        standard = getResources().getStringArray(R.array.standard_array);
        mList = Arrays.asList(standard);

        standardGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.standard_one:
                        standardOne.setSelected(true);
                        standardThree.setSelected(false);
                        standardTwo.setSelected(false);
                        standardFour.setSelected(false);
                        mStandard = "10";
                        break;
                    case R.id.standard_two:
                        standardTwo.setSelected(true);
                        standardOne.setSelected(false);
                        standardThree.setSelected(false);
                        standardFour.setSelected(false);
                        mStandard = "20";
                        break;
                    case R.id.standard_three:
                        standardThree.setSelected(true);
                        standardOne.setSelected(false);
                        standardTwo.setSelected(false);
                        standardFour.setSelected(false);
                        mStandard = "30";
                        break;
                    case R.id.standard_four:
                        standardOne.setSelected(false);
                        standardThree.setSelected(false);
                        standardTwo.setSelected(false);
                        standardFour.setSelected(true);
                        mStandard = "40";
                        break;
                }
            }
        });
    }

    void initBack(String entity) {
        if (!entity.equals("0")) {
            Intent intent = new Intent();
            intent.putExtra(Constants.Params.StandardParams, entity);
            setResult(RESULT_OK, intent);
        }
        finish();

    }

    @OnClick(R.id.standard_confirm)
    public void onViewClicked() {
        initBack(mStandard);
    }

}
