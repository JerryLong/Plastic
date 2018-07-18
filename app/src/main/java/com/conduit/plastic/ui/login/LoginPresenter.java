
package com.conduit.plastic.ui.login;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.request.UserRequest;

import io.reactivex.observers.DisposableObserver;

public class LoginPresenter extends LoginContract.LoginPresenter {
    private DisposableObserver<BaseEntity<UserEntity>> getObserver() {
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
            public void onNext(BaseEntity<UserEntity> baseEntity) {
                    mView.login(baseEntity);
            }
        };
    }

    @Override
    public void login(UserRequest request) {
        mRxManager.add(mModel.login(request).subscribeWith(getObserver()));
    }
}
