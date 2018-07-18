/*
 * Copyright 2016 Freelander
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.conduit.plastic.ui.release.contract;


import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.request.UserRequest;
import com.conduit.plastic.util.ToastUtils;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;


public class DemandModel implements DemandContract.Model {


    @Override
    public Observable<BaseEntity> edit(DemandEntity request) {
        return Networks.getInstance().getProductApi().updateDemand(request.title, request.productNameId, request.brandId,
                request.standard, request.specId, request.texture, request.quantity, request.validityDate, request.describes,
                request.productImgs, request.unit, request.isConceal, request.id)
                .compose(RxSchedulers.<BaseEntity>io_main());
    }

    @Override
    public Observable<BaseEntity> release(DemandEntity request) {
        return Networks.getInstance().getProductApi().releaseDemand(request.title, request.productNameId, request.brandId,
                request.standard, request.specId, request.texture, request.quantity, request.validityDate, request.describes,
                request.productImgs, request.unit, request.isConceal)
                .compose(RxSchedulers.<BaseEntity>io_main());
    }

    @Override
    public Observable<BaseEntity> delete(DemandEntity request) {
        return Networks.getInstance().getProductApi().delDemand(request.id)
                .compose(RxSchedulers.<BaseEntity>io_main());
    }
}
