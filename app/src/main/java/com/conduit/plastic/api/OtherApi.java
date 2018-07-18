package com.conduit.plastic.api;

import com.conduit.plastic.entity.AreaEntity;
import com.conduit.plastic.entity.BaseEntity;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by android on 2017/4/12.
 */

public interface OtherApi {
    @GET("api/basic/areaAllList")
    Observable<BaseEntity<List<AreaEntity>>> areaList();
}
