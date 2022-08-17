package cn.nr.hqrfid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zq.pullrefreshwebview.widget.PtrClassicFrameLayout;
import com.zq.pullrefreshwebview.widget.PtrDefaultHandler;
import com.zq.pullrefreshwebview.widget.PtrFrameLayout;
import com.zq.pullrefreshwebview.widget.PtrHandler;

import cn.nr.hqrfid.model.UserInfo;

public class MainActivity extends AppCompatActivity {
    private PtrClassicFrameLayout mPtrFrame;
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        UserInfo u = UserInfo.getInstance();
        Log.i("MainActivity", "onCreate: get userName:" + u.getUserName());

        String url = "file:///android_asset/pages/index/index?userName=" + u.getUserName();
//        String url = "http://2.0.0.1:20280/pages/index/index?userName=" + u.getUserName();

        mWebView = (WebView) findViewById(R.id.mainWebView);
        WebView.setWebContentsDebuggingEnabled(true);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.rotate_header_web_view_frame);
        initView(url);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initView(String url) {
        Log.i("MainActivity", "initView url=" + url);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.toLowerCase().startsWith("http://") || url.toLowerCase().startsWith("https://")) {
                    return false;
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mPtrFrame.refreshComplete();
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mWebView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData(url);
            }
        });
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        mPtrFrame.setPullToRefresh(false);
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);
    }

    private void updateData(String url) {
        mWebView.loadUrl(url);
        //mWebView.loadUrl("http://www.baidu.com");
    }

}