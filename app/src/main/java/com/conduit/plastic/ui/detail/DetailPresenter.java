package com.conduit.plastic.ui.detail;

import com.conduit.plastic.entity.BaseEntity;

import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/4/11.
 */

public class DetailPresenter extends DetailContract.DealPresenter {
    private DisposableObserver<BaseEntity> company(final int page) {
        return new DisposableObserver<BaseEntity>() {
            @Override
            public void onNext(BaseEntity baseEntity) {
//                if (page == 1) {
//                    mView.refreshList(baseEntity.getData());
//                } else {
//                    mView.loadMoreList(baseEntity.getData());
//                }
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
    public void handleProduct(Map<String, Object> queryMap, int page) {
        mRxManager.add(mModel.handleProduct(queryMap, page).subscribeWith(company(page)));
    }
}
