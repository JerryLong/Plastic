package com.conduit.plastic.ui.main.activity;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.RecommendEntity;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.util.ToastUtils;

import java.util.List;

import io.reactivex.observers.DisposableObserver;


public class RecommendPresenter extends MainContract.RecommendPresenter {

    private DisposableObserver<BaseEntity<List<RecommendEntity>>> demandObserver() {
        return new DisposableObserver<BaseEntity<List<RecommendEntity>>>() {

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
            public void onNext(BaseEntity<List<RecommendEntity>> baseEntity) {
                if (baseEntity.getErrorCode() == 0) {
                    mView.recommendList(baseEntity.getData());
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }
            }
        };
    }


    @Override
    public void recommendList(RequestParams requestParams) {
        mRxManager.add(mModel.recommendList(requestParams).subscribeWith(demandObserver()));
    }
}
