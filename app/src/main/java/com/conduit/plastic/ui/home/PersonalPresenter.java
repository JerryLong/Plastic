package com.conduit.plastic.ui.home;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class PersonalPresenter extends HomeContract.PersonalPresenter {
    private DisposableObserver<BaseEntity<List<BannerEntity>>> banner(final int adType) {
        return new DisposableObserver<BaseEntity<List<BannerEntity>>>() {
            @Override
            public void onNext(BaseEntity<List<BannerEntity>> baseEntity) {
                mView.bannerList(baseEntity.getData(), adType);
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

    private DisposableObserver<BaseEntity<SellerEntity>> company() {
        return new DisposableObserver<BaseEntity<SellerEntity>>() {
            @Override
            public void onNext(BaseEntity<SellerEntity> baseEntity) {
                mView.company(baseEntity.getData());
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

    private DisposableObserver<BaseEntity> callPhone() {
        return new DisposableObserver<BaseEntity>() {
            @Override
            public void onNext(BaseEntity baseEntity) {
                    mView.callPhone(baseEntity.errorCode);
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

    @Override
    public void company(String id) {
        mRxManager.add(mModel.company(id).subscribeWith(company()));
    }

    @Override
    public void callPhone(String phone, String companyId) {
        mRxManager.add(mModel.callPhone(phone,companyId).subscribeWith(callPhone()));
    }
}
