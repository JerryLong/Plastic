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
package com.conduit.plastic.ui.info.phone;


import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;
import com.conduit.plastic.request.UserRequest;

import io.reactivex.Observable;

import static com.conduit.plastic.mvpframe.rx.RxSchedulers.IO_TRANSFORMER;


public class PhoneModel implements PhoneContract.Model {

    @Override
    public Observable<BaseEntity> valicode(UserRequest request) {
        return Networks.getInstance().getUserApi().valicode(request.getMobilePhone())
                .compose(RxSchedulers.<BaseEntity>io_main());
    }

    @Override
    public Observable<BaseEntity> bindingPhone(UserRequest request) {
        return Networks.getInstance().getUserApi().bindingPhone(request.getMobilePhone(),request.getValicode())
                .compose(RxSchedulers.<BaseEntity>io_main());
    }
}
