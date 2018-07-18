package com.conduit.plastic.ui.login;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;

import io.reactivex.observers.DisposableObserver;


public class ForgetPresenter extends LoginContract.ForgetPresenter {
    private DisposableObserver<BaseEntity> getObserver() {
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
                    mView.valicode(baseEntity);
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }

            }
        };
    }

    private DisposableObserver<BaseEntity<UserEntity>> getForget() {
        return new DisposableObserver<BaseEntity<UserEntity>>() {

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
                    mView.forget(baseEntity);
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }

            }
        };
    }


    @Override
    public void forget(UserRequest request) {
        mRxManager.add(mModel.forget(request).subscribeWith(getForget()));
    }

    @Override
    public void valicode(UserRequest request) {
        mRxManager.add(mModel.valicode(request).subscribeWith(getObserver()));
    }
}
