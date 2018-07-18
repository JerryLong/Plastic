package com.conduit.plastic.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.conduit.plastic.R;
import com.conduit.plastic.adapter.base.CommonAdapter;
import com.conduit.plastic.adapter.base.base.ViewHolder;
import com.conduit.plastic.entity.BrandEntity;

/**
 * Created by android on 2017/3/20.
 */

public class BrandAdapter extends CommonAdapter<BrandEntity> {
    Context mContext;

    public BrandAdapter(Context context) {
        super(context, R.layout.adapter_brand);
        mContext = context;
    }


    @Override
    protected void convert(ViewHolder holder, BrandEntity brandEntity, int position) {
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        ImageView image = holder.getView(R.id.adapter_brand_text);
        ViewGroup.LayoutParams params = image.getLayoutParams();
        params.height = (width-10) / 5;
        params.width = (width-10) / 5;
        image.setLayoutParams(params);
//        Glide.with(mContext).load(brandEntity.getBrandLogo()).preload((width-20) / 5,(width-20) / 5).into(image);
    }


}
