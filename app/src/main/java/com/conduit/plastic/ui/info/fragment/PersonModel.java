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
package com.conduit.plastic.ui.info.fragment;


import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.database.UserInfo;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;
import com.conduit.plastic.request.ModifyRequest;

import io.reactivex.Observable;


public class PersonModel implements PersonContract.Model {


    @Override
    public Observable<BaseEntity<UserInfo>> modify(ModifyRequest request) {
        return Networks.getInstance().getUserApi().updateUserInfo(request.getMobilePhone(), request.getSex(), request.getHeadImage(),
                request.getBirth(), request.getWxAccount(), request.getQqAccount(),
                request.getWbAccount(), request.getEmail(), request.getIdCardNum())
                .compose(RxSchedulers.<BaseEntity<UserInfo>>io_main());
    }

    @Override
    public Observable<BaseEntity<UserInfo>> modifyCompany(ModifyRequest request) {
        return Networks.getInstance().getUserApi().updateCompanyInfo(request.getCompanyName(), request.getCompanyType(),
                request.getContacts(), request.getContactNumber(), request.getOfficePhone(), request.getPosition(),
                request.getAreaId(), request.getAddress(), request.getBrandNames(), request.getLicense())
                .compose(RxSchedulers.<BaseEntity<UserInfo>>io_main());
    }
}
