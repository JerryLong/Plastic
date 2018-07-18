package com.conduit.plastic.ui.main.activity;

import com.conduit.plastic.entity.BannerEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.util.ToastUtils;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class DemandPresenter extends MainContract.DemandPresenter {

    private DisposableObserver<BaseEntity<List<DemandEntity>>> demandObserver() {
        return new DisposableObserver<BaseEntity<List<DemandEntity>>>() {

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
            public void onNext(BaseEntity<List<DemandEntity>> baseEntity) {
                if (baseEntity.getErrorCode() == 0) {
                    mView.demandList(baseEntity.getData());
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }
            }
        };
    }

    @Override
    public void demandList(RequestParams requestParams) {
        mRxManager.add(mModel.demandList(requestParams).subscribeWith(demandObserver()));
    }

}
