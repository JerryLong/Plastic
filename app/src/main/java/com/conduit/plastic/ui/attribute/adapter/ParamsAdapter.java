package com.conduit.plastic.ui.attribute.adapter;

import android.support.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.ParamsEntity;

/**
 * Created by Jim Long on 2017/12/28.
 */

public class ParamsAdapter extends BaseQuickAdapter<ParamsEntity.ProportionsBean, BaseViewHolder> {
    public ParamsAdapter() {
        super(R.layout.tag_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ParamsEntity.ProportionsBean item) {
        switch (item.getPropertyType()) {
            case 1:
                helper.setText(R.id.tag_txt, item.getWidth() + "");
                break;
            case 2:
                helper.setText(R.id.tag_txt, item.getThickness() + "");
                break;
            case 3:
                helper.setText(R.id.tag_txt, item.getDiameter() + "");
                break;
            case 4:
                helper.setText(R.id.tag_txt, item.getProportion() + "");
                break;
        }

        helper.getView(R.id.tag_txt).setSelected(item.isSelected());
    }
}
