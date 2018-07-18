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
package com.conduit.plastic.ui.brand;


import com.conduit.plastic.api.Networks;
import com.conduit.plastic.entity.BaseEntity;
import com.conduit.plastic.entity.BrandAllEntity;
import com.conduit.plastic.mvpframe.rx.RxSchedulers;

import io.reactivex.Observable;


public class BrandModel implements BrandContract.Model {

    @Override
    public Observable<BaseEntity<BrandAllEntity>> brandList(int pageIndex) {
        return Networks.getInstance().getProductApi().brandList(pageIndex,1000).compose(RxSchedulers.<BaseEntity<BrandAllEntity>>io_main());
    }
}
