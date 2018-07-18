package com.conduit.plastic.ui.login;

import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/3/27.
 */

public class RegisterPresenter extends LoginContract.RegisterPresenter {
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

    private DisposableObserver<BaseEntity<UserEntity>> getRegister() {
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
                    mView.register(baseEntity);
                } else {
                    ToastUtils.showShort(baseEntity.getError());
                }

            }
        };
    }


    @Override
    public void register(UserRequest request) {
        mRxManager.add(mModel.register(request).subscribeWith(getRegister()));
    }

    @Override
    public void valicode(UserRequest request) {
        mRxManager.add(mModel.valicode(request).subscribeWith(getObserver()));
    }
}
