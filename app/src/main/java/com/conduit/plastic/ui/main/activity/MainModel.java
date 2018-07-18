package com.conduit.plastic.ui.main.activity;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.RecommendEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.ui.main.activity.MainContract.Model;

import java.util.List;

import io.reactivex.Observable;


/**
 * Created by android on 2017/3/29.
 */

public class MainModel implements Model {
    @Override
    public Observable<BaseEntity<UserInfo>> info() {
        return Networks.getInstance().getUserApi().info().compose(RxSchedulers.<BaseEntity<UserInfo>>io_main());
    }

    @Override
    public Observable<BaseEntity<List<DemandEntity>>> demandList(RequestParams requestParams) {
        return Networks.getInstance().getProductApi().demandList(requestParams.page, requestParams.pageSize, requestParams.content).compose(RxSchedulers.<BaseEntity<List<DemandEntity>>>io_main());
    }


    @Override
    public Observable<BaseEntity<List<BannerEntity>>> bannerList(int adType) {
        return Networks.getInstance().getBannerApi().bannarAdList(adType, 1, Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity<List<BannerEntity>>>io_main());
    }

    @Override
    public Observable<BaseEntity<List<RecommendEntity>>> recommendList(RequestParams params) {
        return Networks.getInstance().getProductApi().recommendList(params.page, Constants.PER_PAGE, params.region,params.productType).compose(RxSchedulers.<BaseEntity<List<RecommendEntity>>>io_main());
    }

}
