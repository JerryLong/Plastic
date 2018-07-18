package com.conduit.plastic.ui.main.adapter;

import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.standar.StandardBean;

/**
 * Created by Jim Long on 2017/12/25.
 */

public class TagStandardAdapteer extends BaseQuickAdapter<StandardBean, BaseViewHolder> {
    private boolean mIsChina = false;

    @Override
    public boolean onFailedToRecycleView(BaseViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    public TagStandardAdapteer() {
        super(R.layout.tag_layout);
    }


    @Override
    protected void convert(BaseViewHolder helper, StandardBean item) {
        String string = "";

        if (mIsChina) {
            if (TextUtils.isEmpty(item.getSpecName2())) {
                string = item.getSpecName1();
            } else {
                string = item.getSpecName1() + "/" + item.getSpecName2();
            }
        } else {
//            if (TextUtils.isEmpty(item.getSpecName2())) {
            string = item.getSpecName1();
//            } else {
//                helper.setText(R.id.tag_txt, item.getSpecName2());
//            }
//            helper.setText(R.id.tag_txt, item.getSpecName1());
        }
//        if (string.length() > 8) {
//            ((TextView) helper.getView(R.id.tag_txt)).setTextSize(11);
//        }
        helper.setText(R.id.tag_txt, string);
        helper.getView(R.id.tag_txt).setSelected(item.isSelected());
        ((TextView) helper.getView(R.id.tag_txt)).setMaxLines(1);
    }

    public void setIsChina(boolean isChina) {
        mIsChina = isChina;
        notifyDataSetChanged();
    }
}
