package com.conduit.plastic.adapter;

import android.content.Context;

import com.conduit.plastic.R;
import com.conduit.plastic.adapter.base.CommonAdapter;
import com.conduit.plastic.adapter.base.base.ViewHolder;
import com.conduit.plastic.entity.AreaEntity;

/**
 * Created by android on 2017/3/1.
 */

public class AreaAdapter extends CommonAdapter<AreaEntity> {

    public AreaAdapter(Context context) {
        super(context, R.layout.adapter_area);
    }

    @Override
    protected void convert(ViewHolder holder, AreaEntity entity, int position) {
        holder.setText(android.R.id.text1, entity.getAreaName());
    }
}
