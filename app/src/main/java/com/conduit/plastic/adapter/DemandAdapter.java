package com.conduit.plastic.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.util.DateUtils;

import java.util.List;

/**
 * Created by Jim Long on 2017/11/30.
 */

public class DemandAdapter extends BaseQuickAdapter<DemandEntity, BaseViewHolder> {
    Context mContext;

    public DemandAdapter(Context context) {
        super(R.layout.adapter_demand);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DemandEntity item) {
        helper.setText(R.id.demand_name, item.title)
                .setText(R.id.demand_desc, item.describes)
                .setText(R.id.demand_distance, item.quantity)
                .setText(R.id.demand_logo, item.getProductName() + " | " + item.getBrandName())
                .setText(R.id.demand_texture, item.getStandards() + " | " + item.getSpec() + " | " + item.getTextures())
                .setText(R.id.demand_person, "发布人:" + item.getUserName())
                .setText(R.id.demand_times, DateUtils.timemd(item.getCreateDate()))
                .setText(R.id.demand_time, "有效期:" + item.getValidityDate());
        if (item.getProductImg() != null && item.getProductImg().length > 0) {
            Glide.with(mContext).load(item.getProductImg()[0]).into((ImageView) helper.getView(R.id.demand_img));
        }
    }
}
