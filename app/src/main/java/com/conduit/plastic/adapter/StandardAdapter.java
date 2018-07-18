package com.conduit.plastic.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;

import java.util.List;

/**
 * Created by Jim Long on 2017/12/6.
 */

public class StandardAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public StandardAdapter(@Nullable List<String> data) {
        super(R.layout.tag_blue_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tag_tv,item);
    }
}
