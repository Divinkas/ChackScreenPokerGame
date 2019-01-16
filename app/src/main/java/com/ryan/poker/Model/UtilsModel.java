package com.ryan.poker.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.ryan.poker.Model.Data.LoadResponse;

public class UtilsModel {
    public UtilsModel() {
    }

    public boolean isLink(LoadResponse loadResponse){
        if(loadResponse.getOpen().equals("application")) return false;
        return loadResponse.getOpen().equals("link") && !loadResponse.getOpenLink().isEmpty();
    }

    public boolean isNetworkConnection(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm != null ? cm.getActiveNetworkInfo() : null;
        if (netinfo != null) {
            if (netinfo.isConnected()) {
                NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                return (mobile != null && mobile.isConnected()) || (wifi != null && wifi.isConnected());
            } else
                return false;
        }
        return false;
    }

}
