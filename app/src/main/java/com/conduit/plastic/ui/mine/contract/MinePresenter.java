
package com.conduit.plastic.ui.mine.contract;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.UserEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.ui.login.LoginContract;
import com.conduit.plastic.util.ToastUtils;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

public class MinePresenter extends MineContract.MinePresenter {
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
                mView.ordinary(baseEntity);
            }
        };
    }
    private DisposableObserver<BaseEntity<List<DemandEntity>>> getdemand() {
        return new DisposableObserver<BaseEntity<List<DemandEntity>>>() {

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
            public void onNext(BaseEntity<List<DemandEntity>> baseEntity) {
                mView.demandList(baseEntity.getData());
            }
        };
    }
    private DisposableObserver<BaseEntity<UserInfo>> getModify() {
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
    public void logout() {
        mRxManager.add(mModel.logout().subscribeWith(getOrdinary()));
    }


    @Override
    public void feedback(RequestParams request) {
        mRxManager.add(mModel.feedback(request).subscribeWith(getOrdinary()));
    }



    @Override
    public void demandList(int page) {
        mRxManager.add(mModel.demandList(page).subscribeWith(getdemand()));
    }

    @Override
    public void alert(UserRequest request) {
        mRxManager.add(mModel.alert(request).subscribeWith(getOrdinary()));
    }
    @Override
    public void company(ModifyRequest request) {
        mRxManager.add(mModel.modifyCompany(request).subscribeWith(getModify()));
    }
    @Override
    public void modify(ModifyRequest request) {
        mRxManager.add(mModel.updateUserInfo(request).subscribeWith(getModify()));
    }

}
