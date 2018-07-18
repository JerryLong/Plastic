package com.conduit.plastic.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.ui.brand.BrandActivity;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.SimpleViewHolder> {
    private static final int COUNT = 100;

    private final BrandActivity mContext;
    private final List<BrandEntity> mItems;
    private int mCurrentItemId = 0;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final ImageView image;
        public final TextView nameTv;

        public SimpleViewHolder(View view) {
            super(view);
            image = (ImageView) view.findViewById(R.id.adapter_brand_img);
            nameTv = (TextView) view.findViewById(R.id.adapter_brand_text);
        }
    }

    public SimpleAdapter(BrandActivity context, List<BrandEntity> items) {
        mContext = context;
        mItems = items;
    }

    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.adapter_brand, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        final BrandEntity brandEntity = mItems.get(position);
        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = holder.image.getLayoutParams();
        params.height = (width - 10) / 5;
        params.width = (width - 10) / 5;
        holder.image.setLayoutParams(params);

        Glide.with(mContext).load(brandEntity.getBrandLogo()).apply(RequestOptions.overrideOf((width - 20) / 5, (width - 20) / 5)).into(holder.image);
        if (TextUtils.isEmpty(brandEntity.getBrandNameCn())) {
            holder.nameTv.setText(brandEntity.getBrandNameEn());
        } else {
            holder.nameTv.setText(brandEntity.getBrandNameCn());
        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(Constants.Params.BrandParams, brandEntity);
                mContext.setResult(RESULT_OK, intent);
                mContext.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}