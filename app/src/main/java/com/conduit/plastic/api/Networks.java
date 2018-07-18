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

import com.conduit.plastic.BuildConfig;
import com.conduit.plastic.common.PlasticApp;
import com.conduit.plastic.global.Constants;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jun on 2016/7/27.
 */
public class Networks {

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private static BannerApi mBannerApi;

    private static OtherApi mOtherApi;
    private static ProductApi mProductApi;
    private static UserApi mUserApi;

    private static Networks mNetworks;

    public static Networks getInstance() {
        if (mNetworks == null) {
            mNetworks = new Networks();
        }
        return mNetworks;
    }

    public ProductApi getProductApi() {
        return mProductApi == null ? configRetrofit(ProductApi.class) : mProductApi;
    }

    public BannerApi getBannerApi() {
        return mBannerApi == null ? configRetrofit(BannerApi.class) : mBannerApi;
    }


    public OtherApi getOtherApi() {
        return mOtherApi == null ? configRetrofit(OtherApi.class) : mOtherApi;
    }

    public UserApi getUserApi() {
        return mUserApi == null ? configRetrofit(UserApi.class) : mUserApi;
    }

    private <T> T configRetrofit(Class<T> service) {
        retrofit = new Retrofit.Builder()
                .baseUrl(UrlContants.API_BASE_URL)
                .client(configClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(service);

    }

    private OkHttpClient configClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        //设置缓存
        File cacheFile = new File(PlasticApp.getInstance().getExternalCacheDir(), "Plastic");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);

        //为所有请求添加头部 Header 配置的拦截器
        Interceptor headerIntercept = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request oldRequest = chain.request();


                HttpUrl.Builder httpBuilder = oldRequest.url()
                        .newBuilder()
                        .scheme(oldRequest.url().scheme())
                        .host(oldRequest.url().host());
                if (Constants.isLogin()) {
                    // 添加新的参数
                    httpBuilder.addQueryParameter("sk", Constants.SK);
                    Logger.i("sksksksk==="+ Constants.SK);
                }
                Request.Builder builder = oldRequest.newBuilder();
                builder.addHeader(UrlContants.HEAD_KEY, UrlContants.HEAD_VALUE);
                builder.addHeader("X-Client-Version", BuildConfig.VERSION_NAME);
                builder.addHeader("X-Client-Build", String.valueOf(BuildConfig.VERSION_CODE));

                builder.removeHeader("Accept");

                builder.method(oldRequest.method(), oldRequest.body())
                        .url(httpBuilder.build())
                        .build();
                Request request = builder.build();
                return chain.proceed(request);
            }
        };

        // Log信息拦截器
        if (BuildConfig.DEBUG) {
            Interceptor loggingIntercept = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request request = chain.request();
                    Response response = chain.proceed(request);
                    ResponseBody responseBody = response.body();
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE); // Buffer the entire body.
                    Buffer buffer = source.buffer();
                    Charset UTF8 = Charset.forName("UTF-8");
                    Logger.i("REQUEST_URL==  " + request.toString());
                    Logger.i("REQUEST_JSON==  " + buffer.clone().readString(UTF8));
                    return response;
                }
            };
            okHttpClient.addInterceptor(loggingIntercept);
        }

        okHttpClient.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.addNetworkInterceptor(headerIntercept).cache(cache);

        return okHttpClient.build();
    }


}
