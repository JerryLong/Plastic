package com.conduit.plastic.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.base.CommonAdapter;
import com.conduit.plastic.adapter.base.base.ViewHolder;
import com.conduit.plastic.entity.ProductNameEntity;

/**
 * Created by android on 2017/4/13.
 */

public class ProductNameAdapter extends BaseQuickAdapter<ProductNameEntity, BaseViewHolder> {
    public ProductNameAdapter() {
        super(R.layout.adapter_area);
    }


    @Override
    protected void convert(BaseViewHolder helper, ProductNameEntity item) {
        helper.setText(android.R.id.text1, item.getProductName());
    }
}
