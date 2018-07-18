package com.conduit.plastic.ui.area;

import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.BaseModel;
import com.conduit.plastic.mvpframe.BasePresenter;
import com.conduit.plastic.mvpframe.BaseView;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by android on 2017/4/11.
 */

public interface AreaContract {
    interface Model extends BaseModel {
        Observable<BaseEntity<List<AreaEntity>>> areaList();
    }


    interface View extends BaseView {

        void areaList(BaseEntity<List<AreaEntity>> baseEntity);
    }


    abstract class AreaPresenter extends BasePresenter<Model, View> {
        public abstract void areaList();
    }
}
