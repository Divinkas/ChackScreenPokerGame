package com.ryan.poker.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

import com.ryan.poker.Game.HomeScreen;
import com.ryan.poker.Model.Data.LoadResponse;
import com.ryan.poker.Presenter.LoadingPresenter;
import com.ryan.poker.R;
import com.ryan.poker.View.Contract.LoadingContract;

public class SplashActivity extends AppCompatActivity implements LoadingContract {
    private LoadingPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoadingPresenter(this, this);
        if(presenter.isNetWorkConnection()) {
            presenter.checkAnswerServer();
        }
    }

    @Override
    public void showGame() {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
        finish();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void showBrowser(String link) {
        setContentView(R.layout.activity_webview);
        WebView webView = findViewById(R.id.web_view);
        ImageButton btnClose = findViewById(R.id.close_btn);

        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);

        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(link);

        btnClose.setOnClickListener(view -> showGame());
        btnClose.postDelayed(() -> btnClose.setVisibility(View.VISIBLE), 2000);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.unSubscribe();
    }

    @Override
    public void onBackPressed() {
        showGame();
        super.onBackPressed();
    }
}

    /*
    for android 4+
            CookieSyncManager.createInstance(this);
            CookieSyncManager.getInstance().startSync();
            CookieSyncManager.getInstance().stopSync();
            webView.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    CookieSyncManager.getInstance().sync();
                }
            });
     */
