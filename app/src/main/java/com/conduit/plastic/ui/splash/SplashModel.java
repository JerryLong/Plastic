package com.conduit.plastic.ui.splash;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/6.
 */

public class SplashModel implements SplashContract.Model {


    @Override
    public Observable<BaseEntity<List<BannerEntity>>> bannerList(int adType) {
        return Networks.getInstance().getBannerApi().bannarAdList(adType,1, Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity<List<BannerEntity>>>io_main());
    }
}
