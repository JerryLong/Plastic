package com.conduit.plastic.ui.adddeal;

import com.conduit.plastic.entity.BaseEntity;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/4/11.
 */

public class AddDealPresenter extends AddDealContract.AddDealPresenter {
    private DisposableObserver<BaseEntity> company() {
        return new DisposableObserver<BaseEntity>() {
            @Override
            public void onNext(BaseEntity baseEntity) {
                if (baseEntity.errorCode == 0) {
                    mView.finishAdd();
                }
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
    public void disposeProduct(Map<String, Object> queryMap) {
        mRxManager.add(mModel.disposeProduct(queryMap).subscribeWith(company()));
    }
}
