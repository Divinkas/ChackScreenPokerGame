package com.ryan.poker.Model.Retrofit;

import com.ryan.poker.Model.Data.LoadResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ServerConnect {

    @GET("/Home/Open")
    Observable<LoadResponse> getOpenValue();

}
