
package com.conduit.plastic.ui.info.fragment;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.util.ToastUtils;

import io.reactivex.observers.DisposableObserver;


public class PersonPresenter extends PersonContract.PersonPresenter {
    private DisposableObserver<BaseEntity<UserInfo>> getObserver() {
        return new DisposableObserver<BaseEntity<UserInfo>>() {

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
            public void onNext(BaseEntity<UserInfo> baseEntity) {
                if (baseEntity.getErrorCode() == 0) {
                    mView.modify(baseEntity);
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }
            }
        };
    }

    @Override
    public void modify(ModifyRequest request) {
        mRxManager.add(mModel.modify(request).subscribeWith(getObserver()));
    }

    @Override
    public void modifyCompany(ModifyRequest request) {
        mRxManager.add(mModel.modifyCompany(request).subscribeWith(getObserver()));
    }
}
