package com.conduit.plastic.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.StringEntity;

import java.util.List;

import me.grantland.widget.AutofitHelper;

/**
 * Created by Jim Long on 2017/12/25.
 */

public class TagAdapteer extends BaseQuickAdapter<StringEntity, BaseViewHolder> {
    public TagAdapteer() {
        super(R.layout.tag_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, StringEntity item) {
        ((TextView) helper.getView(R.id.tag_txt)).setMaxLines(3);
//        if (item.getTitle().length() > 10) {
//            ((TextView) helper.getView(R.id.tag_txt)).setTextSize(11);
//        }
        helper.setText(R.id.tag_txt, item.getTitle());
        helper.getView(R.id.tag_txt).setSelected(item.isSelected());
    }
}
