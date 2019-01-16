package com.ryan.poker.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.ryan.poker.Model.Data.LoadResponse;
import com.ryan.poker.Model.NetworkModel;
import com.ryan.poker.Model.UtilsModel;
import com.ryan.poker.View.Contract.LoadingContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoadingPresenter {
    private Context context;
    private NetworkModel networkModel;
    private UtilsModel utilsModel;
    private LoadingContract contract;

    private Disposable disposable;

    public LoadingPresenter(Context context, LoadingContract contract) {
        this.context = context;
        this.contract = contract;
        networkModel = new NetworkModel();
        utilsModel = new UtilsModel();
    }

    public void checkAnswerServer(){
        disposable = networkModel
                .getLoadingValue()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loadResponse ->{
                    if(utilsModel.isLink(loadResponse)){
                        contract.showBrowser(loadResponse.getOpenLink());
                    }
                    else {
                        contract.showGame();
                    }
                },
                throwable -> {
                    Toast.makeText(context, "Error loading", Toast.LENGTH_SHORT).show();
                    contract.showGame();
                    }
                );
    }

    public boolean isNetWorkConnection(){
        return utilsModel.isNetworkConnection(context);
    }

    public void unSubscribe(){
        if(disposable!= null) {
            disposable.dispose();
        }
    }
}
