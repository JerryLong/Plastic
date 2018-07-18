package com.conduit.plastic.ui.release.contract;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.request.UserRequest;

import io.reactivex.Observable;


public interface DemandContract {
    interface Model extends BaseModel {
        Observable<BaseEntity> edit(DemandEntity request);

        Observable<BaseEntity> release(DemandEntity request);
        Observable<BaseEntity> delete(DemandEntity request);

    }


    interface DemandView extends BaseView {

        void success(BaseEntity baseEntity);

    }

    abstract class DemandPresenter extends BasePresenter<Model, DemandView> {

        public abstract void edit(DemandEntity request);
        public abstract void release(DemandEntity request);
        public abstract void delete(DemandEntity request);

    }


}
