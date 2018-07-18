
package com.conduit.plastic.ui.release.contract;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;

import io.reactivex.observers.DisposableObserver;

public class DemandPresenter extends DemandContract.DemandPresenter {
    private DisposableObserver<BaseEntity> getOrdinary() {
        return new DisposableObserver<BaseEntity>() {

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
            public void onNext(BaseEntity baseEntity) {
                mView.success(baseEntity);
            }
        };
    }



    @Override
    public void edit(DemandEntity request) {
        mRxManager.add(mModel.edit(request).subscribeWith(getOrdinary()));
    }

    @Override
    public void release(DemandEntity request) {
        mRxManager.add(mModel.release(request).subscribeWith(getOrdinary()));
    }

    @Override
    public void delete(DemandEntity request) {
        mRxManager.add(mModel.delete(request).subscribeWith(getOrdinary()));
    }
}
