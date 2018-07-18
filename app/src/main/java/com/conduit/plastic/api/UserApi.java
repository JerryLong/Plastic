/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.conduit.plastic.api;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.entity.database.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Jun on 2016/4/16.
 */
public interface UserApi {
    /**
     * 注册
     *
     * @param mobilePhone
     * @param password
     * @param valicode
     * @param jpushId
     * @return
     */

    @FormUrlEncoded
    @POST("api/user/regist")
    Observable<BaseEntity<UserEntity>> register(@Field("mobilePhone") String mobilePhone, @Field("password") String password,
                                                @Field("valicode") String valicode, @Field("jpushId") String jpushId);

    /**
     * 发送验证码
     *
     * @param mobilePhone
     * @return
     */
    @GET("api/user/sendValicode")
    Observable<BaseEntity> valicode(@Query("mobilePhone") String mobilePhone);

    /**
     * 忘记密码
     *
     * @param mobilePhone
     * @param valicode
     * @param nPassword
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/resetPassword")
    Observable<BaseEntity<UserEntity>> forget(@Field("mobilePhone") String mobilePhone, @Field("valicode") String valicode, @Field("nPassword") String nPassword);

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param nPassword
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/updatePassword")
    Observable<BaseEntity<UserEntity>> alert(@Field("oldPassword") String oldPassword, @Field("nPassword") String nPassword);

    /**
     * 登录
     *
     * @param mobilePhone
     * @param password
     * @param valicode
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/login")
    Observable<BaseEntity<UserEntity>> login(@Field("mobilePhone") String mobilePhone, @Field("password") String password,
                                             @Field("jpushId") String valicode);

    /**
     * 绑定手机号
     *
     * @param mobilePhone
     * @param valicode
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/bindingPhoneNum")
    Observable<BaseEntity> bindingPhone(@Field("mobilePhone") String mobilePhone, @Field("valicode") String valicode);

    /**
     * 意见反馈
     *
     * @param content
     * @return
     */
    @FormUrlEncoded
    @POST("api/basic/feedback")
    Observable<BaseEntity> feedback(@Field("content") String content);

    /**
     * 注销
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/logout")
    Observable<BaseEntity> logout();

    /**
     * 获取用户信息
     * @return
     */
    @GET("api/user/me")
    Observable<BaseEntity<UserInfo>> info();

    /**
     * @param mobilePhone
     * @param sex
     * @param headImage   个人名片
     * @param birth
     * @param idCardNum
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/updateUserInfo")
    Observable<BaseEntity<UserInfo>> updateUserInfo(@Field("userName") String mobilePhone, @Field("sex") String sex,
                                                    @Field("headImage") String headImage, @Field("birth") String birth,
                                                    @Field("wxAccount") String wxAccount, @Field("qqAccount") String qqAccount,
                                                    @Field("wbAccount") String wbAccount, @Field("email") String email,
                                                    @Field("idCardNum") String idCardNum);


    /**
     * @param companyName   公司名称
     * @param companyType   公司类别10生产厂20销售公司30分公司40中国区总部50中国区总代60办事处70经销商]
     * @param contacts      联系人
     * @param contactNumber 手机
     * @param officePhone   座机
     * @param areaId        所在区域
     * @param address       地址
     * @param brandNames    经营品牌
     * @param license       营业执照
     * @return
     */
    @FormUrlEncoded
    @POST("api/user/updateCompanyInfo")
    Observable<BaseEntity<UserInfo>> updateCompanyInfo(@Field("companyName") String companyName, @Field("companyType") String companyType,
                                                       @Field("contacts") String contacts, @Field("contactNumber") String contactNumber,
                                                       @Field("officePhone") String officePhone, @Field("position") String position,
                                                       @Field("areaId") String areaId, @Field("address") String address,
                                                       @Field("brandNames") String brandNames, @Field("license") String license);

    /**
     * 9.9.	需求列表[我发布的]
     *
     * @param page
     * @param pageSize
     * @return
     */
//    @GET("api/demand/getMyDemandList")
//    Observable<BaseEntity<List<DemandEntity>>> demandList(@Query("page") int page, @Query("pageSize") int pageSize);
    @FormUrlEncoded
    @POST("api/demand/getMyDemandList")
    Observable<BaseEntity<List<DemandEntity>>> demandList(@Field("page") int page, @Field("pageSize") int pageSize);
}
