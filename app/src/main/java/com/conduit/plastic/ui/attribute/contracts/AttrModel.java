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
package com.conduit.plastic.ui.attribute.contracts;


import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandAllEntity;
import com.conduit.plastic.entity.DemandEntity;
import com.conduit.plastic.entity.ParamsEntity;
import com.conduit.plastic.entity.SellerEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.global.Constants;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;
import com.conduit.plastic.request.ModifyRequest;
import com.conduit.plastic.request.RequestParams;
import com.conduit.plastic.request.UserRequest;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;


public class AttrModel implements AttrContract.Model {



    @Override
    public Observable<BaseEntity<ParamsEntity>> boardList() {
        return Networks.getInstance().getProductApi().boardList().compose(RxSchedulers.<BaseEntity<ParamsEntity>>io_main());
    }

    @Override
    public Observable<BaseEntity<ParamsEntity>> stickList() {
        return Networks.getInstance().getProductApi().stickList().compose(RxSchedulers.<BaseEntity<ParamsEntity>>io_main());
    }
}
