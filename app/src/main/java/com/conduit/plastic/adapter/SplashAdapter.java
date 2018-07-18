package com.conduit.plastic.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.BannerEntity;

import java.util.ArrayList;
import java.util.List;

public class SplashAdapter extends PagerAdapter {
    Activity mContext;
    LayoutInflater mInflater;
    List<BannerEntity> mList = new ArrayList<>();

    public SplashAdapter(Activity context) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.adapter_splash, container, false);
        final ImageView image_view = (ImageView) view.findViewById(R.id.splash_image);
        final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
        // 保存网络图片的路径
        BannerEntity bannerEntity = (BannerEntity) getItem(position);
        //TODO
        String imageUrl = bannerEntity.getImagePath();

        spinner.setVisibility(View.VISIBLE);
        spinner.setClickable(false);
        Glide.with(mContext).load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(mContext, "资源加载异常", Toast.LENGTH_SHORT).show();
                        spinner.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        spinner.setVisibility(View.GONE);

                        /**这里应该是加载成功后图片的高*/
                        int height = image_view.getHeight();

                        int wHeight = mContext.getWindowManager().getDefaultDisplay().getHeight();
                        if (height > wHeight) {
                            image_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else {
                            image_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        }
                        return false;
                    }

                }).into(image_view);

        container.addView(view, 0);
        return view;
    }

    public void setData(List<BannerEntity> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (mList == null || mList.size() == 0) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    Object getItem(int position) {
        return mList.get(position);
    }
}
