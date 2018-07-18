
package com.conduit.plastic.ui.brand;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandAllEntity;

import io.reactivex.observers.DisposableObserver;


public class BrandPresenter extends BrandContract.Presenter {
    private DisposableObserver<BaseEntity<BrandAllEntity>> getBrandObserver(final int pageIndex) {
        return new DisposableObserver<BaseEntity<BrandAllEntity>>() {

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
            public void onNext(BaseEntity<BrandAllEntity> userMessageEntity) {
                    mView.brandList(userMessageEntity.getData());
            }
        };
    }

    @Override
    public void brandList(int pageIndex) {
        mRxManager.add(mModel.brandList(pageIndex).subscribeWith(getBrandObserver(pageIndex)));
    }
}
