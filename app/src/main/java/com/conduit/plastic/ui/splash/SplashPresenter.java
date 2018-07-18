package com.conduit.plastic.ui.splash;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/4/6.
 */

public class SplashPresenter extends SplashContract.Presenter {
    private DisposableObserver<BaseEntity<List<BannerEntity>>> splashObserver() {
        return new DisposableObserver<BaseEntity<List<BannerEntity>>>() {

            @Override
            public void onNext(BaseEntity<List<BannerEntity>> listBaseEntity) {
                mView.bannerList(listBaseEntity.getData());
            }

            @Override
            public void onError(Throwable e) {
                mView.onRequestError(e.toString());
                mView.onInternetError();
            }

            @Override
            public void onComplete() {
                mView.onRequestEnd();
            }

        };
    }


    @Override
    public void bannerList(int adType) {
        mRxManager.add(mModel.bannerList(adType).subscribeWith(splashObserver()));
    }
}
