
package com.conduit.plastic.ui.attribute.contracts;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.ParamsEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;

import java.util.List;
import java.util.Map;

import io.reactivex.observers.DisposableObserver;

public class AttrPresenter extends AttrContract.AttrPresenter {
    private DisposableObserver<BaseEntity<ParamsEntity>> getParamsList() {
        return new DisposableObserver<BaseEntity<ParamsEntity>>() {

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
            public void onNext(BaseEntity<ParamsEntity> baseEntity) {
                if (baseEntity.getErrorCode() == 0)
                    mView.paramsList(baseEntity.getData());
                else
                    ToastUtils.showShort("服务器异常，请稍后再试");
            }
        };
    }


    @Override
    public void boardList() {
        mRxManager.add(mModel.boardList().subscribeWith(getParamsList()));
    }

    @Override
    public void stickList() {
        mRxManager.add(mModel.stickList().subscribeWith(getParamsList()));
    }

}
