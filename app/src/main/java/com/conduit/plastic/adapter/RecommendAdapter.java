package com.conduit.plastic.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.conduit.plastic.R;
import com.conduit.plastic.entity.RecommendEntity;
import com.conduit.plastic.view.RatingBar;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by Jim Long on 2017/12/12.
 */

public class RecommendAdapter extends BaseQuickAdapter<RecommendEntity, BaseViewHolder> {
    public RecommendAdapter() {
        super(R.layout.adapter_recommend);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendEntity item) {
        RatingBar ratingOne = helper.getView(R.id.recommend_start_one);
        RatingBar ratingTwo = helper.getView(R.id.recommend_start_two);
        helper.setText(R.id.recommend_name, item.getBrandName())
                .setText(R.id.recommend_desc, item.getFullName());
        ratingOne.setStar(item.getQualityStar());
        ratingTwo.setStar(item.getPriceStar());
    }
}
