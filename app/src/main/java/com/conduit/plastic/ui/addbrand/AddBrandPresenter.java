package com.conduit.plastic.ui.addbrand;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandEntity;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/4/7.
 */

public class AddBrandPresenter extends AddBrandContract.Presenter {
    private DisposableObserver<BaseEntity<BrandEntity>> addBrandObserver() {
        return new DisposableObserver<BaseEntity<BrandEntity>>() {

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
            public void onNext(BaseEntity<BrandEntity> listBaseEntity) {
                mView.addBrand(listBaseEntity);
            }

        };
    }


    @Override
    public void addBrand(Map<String, String> map) {
        mRxManager.add(mModel.addBrand(map).subscribeWith(addBrandObserver()));
    }
}
