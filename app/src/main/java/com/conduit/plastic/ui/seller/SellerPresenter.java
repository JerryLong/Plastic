package com.conduit.plastic.ui.seller;

import android.util.Log;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.orhanobut.logger.Logger;

import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/4/11.
 */

public class SellerPresenter extends SellerContract.SellerPresenter {
    private DisposableObserver<BaseEntity<List<SellerEntity>>> company(final int page) {
        return new DisposableObserver<BaseEntity<List<SellerEntity>>>() {
            @Override
            public void onNext(BaseEntity<List<SellerEntity>> baseEntity) {
                if (page == 1) {
                    mView.refreshList(baseEntity.getData());
                } else {
                    mView.loadMoreList(baseEntity.getData());
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
    public void companyList(Map<String, Object> queryMap, int page) {
        Logger.i("aa", "=======  " + page);
        mRxManager.add(mModel.companyList(queryMap, page).subscribeWith(company(page)));
    }

    @Override
    public void handleProduct(Map<String, Object> queryMap, int page) {
//        mRxManager.add(mModel.handleProduct(queryMap,page).subscribeWith(company(page)));
    }
}
