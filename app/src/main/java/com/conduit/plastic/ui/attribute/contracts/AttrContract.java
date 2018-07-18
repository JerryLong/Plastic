package com.conduit.plastic.ui.attribute.contracts;


import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.ParamsEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


public interface AttrContract {
    interface Model extends BaseModel {

        Observable<BaseEntity<ParamsEntity>> boardList();

        Observable<BaseEntity<ParamsEntity>> stickList();


    }


    interface AttrView extends BaseView {

        void paramsList(ParamsEntity baseEntity);

    }

    abstract class AttrPresenter extends BasePresenter<Model, AttrView> {

        public abstract void boardList();


        public abstract void stickList();

    }


}
