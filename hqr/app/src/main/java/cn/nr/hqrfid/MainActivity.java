package cn.nr.hqrfid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dlxx.mam.Internal.sdk.BaseResponse;
import com.dlxx.mam.Internal.sdk.IYDMHAPI;
import com.dlxx.mam.Internal.sdk.OauthManager;
import com.dlxx.mam.Internal.sdk.YDMHAPIFactory;
import cn.nr.hqrfid.ydmhapi.Constant;

public class MainActivity extends AppCompatActivity {
    private IYDMHAPI api;
    private OauthManager mOauthManager;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        WebView webView = findViewById(R.id.mainWebView);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("http://192.168.1.8:8080/");

        createHandler();
        //开发环境 2.0.0.1 port 29000
        mOauthManager = new OauthManager("http://2.0.0.1:29000/up/appstore/rest/user");
        mOauthManager.setAppid(Constant.renZhengCode);
        mOauthManager.setSecretString(Constant.secretString);
        mOauthManager.registerHandler(mHandler);
        mOauthManager.setDebug(true);

        api = YDMHAPIFactory.createYDMHAPI(this, Constant.renZhengCode);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result_ok = mOauthManager.ydmhAuth(api, MainActivity.this);
                if (!result_ok) {
                    Toast.makeText(MainActivity.this, "请先安装移动商店", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_FAILED:
                        BaseResponse userinfoFail = (BaseResponse) msg.obj;
                        Toast.makeText(MainActivity.this, userinfoFail.resultDesc, Toast.LENGTH_SHORT).show();
                        break;
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_SUCCESS:
                        //获取离线用户信息获取成功
                        //TODO:obj为获取到的用户信息，json格式，开发自行处理登录后逻辑
                        String obj = (String) msg.obj;
                        Toast.makeText(MainActivity.this, "离线", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, obj, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
    }
}