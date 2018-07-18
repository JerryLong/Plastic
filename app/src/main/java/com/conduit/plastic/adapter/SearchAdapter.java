package com.conduit.plastic.adapter;

import android.content.Context;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.base.CommonAdapter;
import com.conduit.plastic.adapter.base.base.ViewHolder;
import com.conduit.plastic.entity.GoodsEntity;

/**
 * Created by android on 2017/3/20.
 */

public class SearchAdapter extends CommonAdapter<GoodsEntity> {
    Context mContext;

    public SearchAdapter(Context context) {
        super(context, R.layout.adapter_product);
        mContext = context;
    }


    @Override
    protected void convert(ViewHolder holder, GoodsEntity entity, int position) {
//        holder.setText(R.id.product_item_company, entity.getBrand());
//        holder.setText(R.id.product_item_contacts, entity.getName());
//        holder.setText(R.id.product_item_phone, entity.getStandard());
//        holder.setText(R.id.product_item_inventory, entity.getSpec());

    }
}
