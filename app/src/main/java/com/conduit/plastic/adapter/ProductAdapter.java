package com.conduit.plastic.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.base.CommonAdapter;
import com.conduit.plastic.adapter.base.base.ViewHolder;
import com.conduit.plastic.entity.database.Product;

/**
 * Created by android on 2017/3/22.
 */

public class ProductAdapter extends CommonAdapter<Product> {
    public ProductAdapter(Context context) {
        super(context, R.layout.adapter_product);
    }


    @Override
    protected void convert(ViewHolder holder, Product entity, int position) {
        holder.setText(R.id.product_item_brand, entity.getBrandName());
        holder.setText(R.id.product_item_spec, entity.getSpec());
        holder.setText(R.id.product_item_texture, entity.getTexture());
        holder.setText(R.id.product_item_name, entity.getProductName());
        holder.setText(R.id.product_item_standard, entity.getStandard());
//        Glide.with(mContext).load(entity.getProductImg()).error(R.mipmap.default_img).into((ImageView) holder.getView(R.id.product_item_img));
    }
}
