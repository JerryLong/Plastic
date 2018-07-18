package com.conduit.plastic.ui.productname;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.ProductNameAllEntity;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/4/11.
 */

public class ProductNamePresenter extends ProductNameContract.ProductNamePresenter {
    private DisposableObserver<BaseEntity<ProductNameAllEntity>> productNameObserver() {
        return new DisposableObserver<BaseEntity<ProductNameAllEntity>>() {
            @Override
            public void onNext(BaseEntity<ProductNameAllEntity> baseEntity) {
                mView.productNameList(baseEntity.getData());
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
    public void productName() {
        mRxManager.add(mModel.productName().subscribeWith(productNameObserver()));
    }
}
