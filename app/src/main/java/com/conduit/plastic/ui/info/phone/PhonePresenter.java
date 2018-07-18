
package com.conduit.plastic.ui.info.phone;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;

import io.reactivex.observers.DisposableObserver;


public class PhonePresenter extends PhoneContract.PhonePresenter {
    private DisposableObserver<BaseEntity> getObserver(final int index) {
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
                if (baseEntity.getErrorCode() == 0) {
                    if (index == 1) {
                        mView.bindingPhone(baseEntity);
                    } else if (index == 2) {
                        mView.valicode(baseEntity);
                    }
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }

            }
        };
    }

    @Override
    public void valicode(UserRequest request) {
        mRxManager.add(mModel.valicode(request).subscribeWith(getObserver(2)));
    }

    @Override
    public void bindingPhone(UserRequest request) {
        mRxManager.add(mModel.bindingPhone(request).subscribeWith(getObserver(1)));
    }
}
