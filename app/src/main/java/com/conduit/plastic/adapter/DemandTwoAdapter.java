package com.conduit.plastic.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.util.DateUtils;

/**
 * Created by Jim Long on 2017/11/30.
 */

public class DemandTwoAdapter extends BaseQuickAdapter<DemandEntity, BaseViewHolder> {
    Context mContext;

    public DemandTwoAdapter(Context context) {
        super(R.layout.adapter_demand_two);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, DemandEntity item) {
        helper.setText(R.id.demand_two_name, item.title)
                .setText(R.id.demand_two_user, "发布人:\t\t" + item.getUserName())
                .setText(R.id.demand_two_times, "发布时间:\t" + DateUtils.timeymd(item.getCreateDate()))
                .setText(R.id.demand_two_time, "有效期:\t\t" + DateUtils.timeymd(item.validityDate ));
    }
}
