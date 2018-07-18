package com.conduit.plastic.ui.home;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class HomePresenter extends HomeContract.HomePresenter {
    private DisposableObserver<BaseEntity<List<BannerEntity>>> banner(final int adType) {
        return new DisposableObserver<BaseEntity<List<BannerEntity>>>() {
            @Override
            public void onNext(BaseEntity<List<BannerEntity>> baseEntity) {
               mView.bannerList(baseEntity.getData(),adType);
            }

            @Override
            public void onError(Throwable e) {
                mView.onRequestError(e.toString());

            }

            @Override
            public void onComplete() {
                mView.onRequestEnd();
            }
        };
    }


    @Override
    public void bannerList(int adType) {
        mRxManager.add(mModel.bannerList(adType).subscribeWith(banner(adType)));
    }


}
