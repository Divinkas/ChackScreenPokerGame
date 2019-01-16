package com.ryan.poker.Model;

import com.ryan.poker.Model.Data.LoadResponse;
import com.ryan.poker.Model.Retrofit.RetrofitClient;
import com.ryan.poker.Model.Retrofit.ServerConnect;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class NetworkModel {
    private ServerConnect server;

    public NetworkModel() {
        Retrofit retrofit = RetrofitClient.getInstance();
        server = retrofit.create(ServerConnect.class);
    }

    public Observable<LoadResponse> getLoadingValue(){
        return server.getOpenValue();
    }
}
