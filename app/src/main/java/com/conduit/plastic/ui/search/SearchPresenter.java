
package com.conduit.plastic.ui.search;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.GoodsEntity;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;


public class SearchPresenter extends SearchContract.Presenter {
    private DisposableObserver<BaseEntity> serachObserver(final int pageIndex) {
        return new DisposableObserver<BaseEntity>() {

            @Override
            public void onError(Throwable e) {
                Logger.i(e.getMessage());
                mView.onRequestError(e.toString());
                mView.onInternetError();
            }

            @Override
            public void onComplete() {
                mView.onRequestEnd();
            }

            @Override
            public void onNext(BaseEntity listBaseEntity) {
                if (pageIndex == 1) {
                    Logger.i("======================");
                    mView.refreshList(listBaseEntity);
                } else {
                    mView.loadMoreList(listBaseEntity);
                }
            }

        };
    }

    private DisposableObserver<BaseEntity<List<GoodsEntity>>> historyObserver(final int pageIndex) {
        return new DisposableObserver<BaseEntity<List<GoodsEntity>>>() {

            @Override
            public void onError(Throwable e) {
                Logger.i(e.toString() + "==msg==" + e.getMessage());
                mView.onRequestError(e.toString());
                mView.onInternetError();
            }

            @Override
            public void onComplete() {
                mView.onRequestEnd();
            }

            @Override
            public void onNext(BaseEntity<List<GoodsEntity>> listBaseEntity) {
                Logger.i("==msg==" + listBaseEntity.getData().size());
                if (pageIndex == 1) {
                    mView.refreshList(listBaseEntity);
                } else {
                    mView.loadMoreList(listBaseEntity);
                }
            }

        };
    }


    @Override
    public void serachList(Map<String, Object> request, int page) {
        mRxManager.add(mModel.serachList(request, page).subscribeWith(serachObserver(page)));
    }

    @Override
    public void serachHistory(int pageIndex) {
        mRxManager.add(mModel.serachHistory(pageIndex).subscribeWith(historyObserver(pageIndex)));
    }
}
