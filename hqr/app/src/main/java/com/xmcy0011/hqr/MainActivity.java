package com.xmcy0011.hqr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.mainWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.1.8:8080/");
    }
}