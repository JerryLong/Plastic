package com.conduit.plastic.ui.area;

import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BaseEntity;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by android on 2017/4/11.
 */

public class AreaPresenter extends AreaContract.AreaPresenter {
    private DisposableObserver<BaseEntity<List<AreaEntity>>> area() {
        return new DisposableObserver<BaseEntity<List<AreaEntity>>>() {
            @Override
            public void onNext(BaseEntity<List<AreaEntity>> baseEntity) {
                mView.areaList(baseEntity);
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
    public void areaList() {
        mRxManager.add(mModel.areaList().subscribeWith(area()));
    }
}
