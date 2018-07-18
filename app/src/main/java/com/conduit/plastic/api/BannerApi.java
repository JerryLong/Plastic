package com.conduit.plastic.api;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by android on 2017/4/6.
 */

public interface BannerApi {
    /**
     * 10导图
     * 20 首页
     * 30 个人中心
     * 40其它
     *
     * @param adType
     * @return
     */
    @GET("api/basic/bannarAdList")
    Observable<BaseEntity<List<BannerEntity>>> bannarAdList(@Query("adType") int adType, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 获取指定Bannar广告详情
     * @param id
     * @return
     */
    @GET("api/basic/bannarAd")
    Observable<BaseEntity> bannarAd(@Query("id") String id);
}
