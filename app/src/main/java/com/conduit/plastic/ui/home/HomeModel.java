package com.conduit.plastic.ui.home;

import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import java.util.List;

import io.reactivex.Observable;


public class HomeModel implements HomeContract.Model {



    @Override
    public Observable<BaseEntity<List<BannerEntity>>> bannerList(int adType) {
        return Networks.getInstance().getBannerApi().bannarAdList(adType,1,Constants.PER_PAGE).compose(RxSchedulers.<BaseEntity<List<BannerEntity>>>io_main());
    }

    @Override
    public Observable<BaseEntity<SellerEntity>> company(String id) {
        return Networks.getInstance().getProductApi().companyInfo(id).compose(RxSchedulers.<BaseEntity<SellerEntity>>io_main());
    }

    @Override
    public Observable<BaseEntity> callPhone(String phone, String companyId) {
        return Networks.getInstance().getProductApi().callPhone(phone,companyId).compose(RxSchedulers.<BaseEntity>io_main());
    }
}
