package com.conduit.plastic.api;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandAllEntity;
import com.conduit.plastic.entity.BrandEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.HomeEntity;
import com.conduit.plastic.entity.ParamsEntity;
import com.conduit.plastic.entity.ProductNameAllEntity;
import com.conduit.plastic.entity.ProductNameEntity;
import com.conduit.plastic.entity.RecommendEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.entity.SpecEntity;
import com.conduit.plastic.entity.database.UserInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ProductApi {
    /**
     * 6.8.	√√规格列表
     *
     * @return
     */
    @GET("api/basic/specAllList")
    Observable<BaseEntity<SpecEntity>> specList();

    /**
     * 6.10.	√√条件查询商家维护列表
     *
     * @param map
     * @param page
     * @param pageSize
     * @return
     */
    @GET("api/basic/getBaseCompanyList")
    Observable<BaseEntity<List<SellerEntity>>> companyList(@QueryMap Map<String, Object> map, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 6.10.	√√条件查询商家信息
     *
     * @return
     */
    @GET("api/basic/getBaseCompanyInfo")
    Observable<BaseEntity<SellerEntity>> companyInfo(@Query("companyId") String companyId);

    /**
     * 6.12.	√√根据商家维护ID获取产品列表
     *
     * @param companyId
     * @param page
     * @param pageSize
     * @return
     */
    @GET("api/basic/getBaseProductByList")
    Observable<BaseEntity<HomeEntity>> productById(@Query("companyId") String companyId, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 6.6.	√√新增品牌
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("api/basic/addBrand")
    Observable<BaseEntity<BrandEntity>> addBrand(@FieldMap Map<String, String> map);

    /**
     * 6.5.	√√品牌列表
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GET("api/basic/brandList")
    Observable<BaseEntity<BrandAllEntity>> brandList(@Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 品名列表
     *
     * @return
     */
    @GET("api/basic/productNameList")
    Observable<BaseEntity<List<ProductNameEntity>>> productName();

    /**
     * 品名列表[所有]
     *
     * @return
     */
    @GET("api/basic/productNameAllList")
    Observable<BaseEntity<ProductNameAllEntity>> productNameAll();

    /**
     * 条件查询处理产品列表
     *
     * @return
     */
    @GET("api/product/getDisposeProductList")
    Observable<BaseEntity<List<SellerEntity>>> handleProduct(@QueryMap Map<String, Object> map, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 发布处理产品
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/product/releaseDisposeProduct")
    Observable<BaseEntity> disposeProduct(@FieldMap Map<String, Object> map);

    /**
     * 拔打电话请求
     *
     * @param phone
     * @param baseCompanyId
     * @return
     */
    @FormUrlEncoded
    @POST("api/basic/callPhone")
    Observable<BaseEntity> callPhone(@Field("phone") String phone, @Field("baseCompanyId") String baseCompanyId);

    /**
     * 发布需求产品
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/product/release")
    Observable<BaseEntity> release();

    /**
     * 板材
     *
     * @return
     */
    @GET("api/basic/getBoardParameterList")
    Observable<BaseEntity<ParamsEntity>> boardList();

    /**
     * 棒材
     *
     * @return
     */
    @GET("api/basic/getStickParameterList")
    Observable<BaseEntity<ParamsEntity>> stickList();

    /**
     * @return
     */
    @FormUrlEncoded
    @POST("api/demand/delDemand")
    Observable<BaseEntity> delDemand(@Field("demandId") String demandId);

    /**
     * 发布需求
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/demand/releaseDemand")
    Observable<BaseEntity> releaseDemand(@Field("title") String title, @Field("productNameId") String productNameId,
                                         @Field("brandId") String brandId, @Field("standard") String standard,
                                         @Field("specId") String specId, @Field("texture") String texture,
                                         @Field("quantity") String quantity, @Field("validityDate") String validityDate,
                                         @Field("describes") String describes, @Field("productImg") String productImg,
                                         @Field("unit") String unit, @Field("isConceal") int isConceal);

    @FormUrlEncoded
    @POST("api/demand/updateDemand")
    Observable<BaseEntity> updateDemand(@Field("title") String title, @Field("productNameId") String productNameId,
                                        @Field("brandId") String brandId, @Field("standard") String standard,
                                        @Field("specId") String specId, @Field("texture") String texture,
                                        @Field("quantity") String quantity, @Field("validityDate") String validityDate,
                                        @Field("describes") String describes, @Field("productImg") String productImg,
                                        @Field("unit") String unit, @Field("isConceal") int isConceal,
                                        @Field("id") String id);

    @FormUrlEncoded
    @POST("api/demand/getDemandByList")
    Observable<BaseEntity<List<DemandEntity>>> demandList(@Field("page") int page, @Field("pageSize") int pageSize, @Field("keyword") String keyword);

    /**
     * 优选列表
     *
     * @param page
     * @param pageSize
     * @param region
     * @param productType
     * @return
     */
    @FormUrlEncoded
    @POST("api/recommend/getRecommendList")
    Observable<BaseEntity<List<RecommendEntity>>> recommendList(@Field("page") int page, @Field("pageSize") int pageSize,
                                                                @Field("region") String region, @Field("productType") String productType);
}
