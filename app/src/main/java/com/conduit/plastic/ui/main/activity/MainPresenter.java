package com.conduit.plastic.ui.main.activity;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.util.ToastUtils;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class MainPresenter extends MainContract.MainPresenter {
    private DisposableObserver<BaseEntity<UserInfo>> infoObserver() {
        return new DisposableObserver<BaseEntity<UserInfo>>() {

            @Override
            public void onError(Throwable e) {
                mView.onRequestError(e.toString());
                mView.onInternetError();
            }

            @Override
            public void onComplete() {
                mView.onRequestEnd();
            }

            @Override
            public void onNext(BaseEntity<UserInfo> baseEntity) {
                if (baseEntity.getErrorCode() == 0) {
                    mView.info(baseEntity);
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }
            }
        };
    }

    private DisposableObserver<BaseEntity<List<BannerEntity>>> bannerObserver() {
        return new DisposableObserver<BaseEntity<List<BannerEntity>>>() {

            @Override
            public void onError(Throwable e) {
                mView.onRequestError(e.toString());
                mView.onInternetError();
            }

            @Override
            public void onComplete() {
                mView.onRequestEnd();
            }

            @Override
            public void onNext(BaseEntity<List<BannerEntity>> baseEntity) {
                if (baseEntity.getErrorCode() == 0) {
                    mView.bannerList(baseEntity.getData());
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }
            }
        };
    }


    @Override
    public void info() {
        mRxManager.add(mModel.info().subscribeWith(infoObserver()));
    }

    @Override
    public void bannerList(int adType) {
        mRxManager.add(mModel.bannerList(adType).subscribeWith(bannerObserver()));
    }


}
