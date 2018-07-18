package com.conduit.plastic.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.base.CommonAdapter;
import com.conduit.plastic.adapter.base.base.ViewHolder;
import com.conduit.plastic.entity.SellerEntity;

/**
 * Created by android on 2017/3/20.
 */

public class SellerAdapter extends BaseQuickAdapter<SellerEntity,BaseViewHolder> {
    public SellerAdapter( ) {
        super( R.layout.adapter_seller);
    }


    @Override
    protected void convert(BaseViewHolder helper, SellerEntity item) {
        helper.setText(R.id.seller_item_company, item.getCompanyName())
        .setText(R.id.seller_item_contacts, item.getBrandNames())
        .setText(R.id.seller_item_phone, item.getAreaName())
        .setText(R.id.seller_item_inventory, "少量");
    }
}
