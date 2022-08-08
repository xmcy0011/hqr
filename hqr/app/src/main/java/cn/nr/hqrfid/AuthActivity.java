package cn.nr.hqrfid;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dlxx.mam.Internal.sdk.BaseResponse;
import com.dlxx.mam.Internal.sdk.IYDMHAPI;
import com.dlxx.mam.Internal.sdk.OauthManager;
import com.dlxx.mam.Internal.sdk.YDMHAPIFactory;

import cn.nr.hqrfid.model.UserInfo;
import cn.nr.hqrfid.ydmhapi.Constant;

public class AuthActivity extends AppCompatActivity {
    private IYDMHAPI api;
    private OauthManager mOauthManager;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (UserInfo.getInstance().getUserName() != null && !UserInfo.getInstance().getUserName().isEmpty()) {
            startActivity(new Intent(AuthActivity.this, MainActivity.class));
            finish();
        }

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
                boolean result_ok = mOauthManager.ydmhAuth(api, AuthActivity.this);
                if (!result_ok) {
                    Toast.makeText(AuthActivity.this, "请先安装移动商店", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        boolean result_ok = mOauthManager.ydmhAuth(api, AuthActivity.this);
        if (!result_ok) {
            Toast.makeText(AuthActivity.this, "请先安装移动商店", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

    private void createHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_FAILED:
                        BaseResponse userinfoFail = (BaseResponse) msg.obj;
                        Toast.makeText(AuthActivity.this, userinfoFail.resultDesc, Toast.LENGTH_SHORT).show();
                        break;
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_SUCCESS:
                        //获取离线用户信息获取成功
                        //TODO:obj为获取到的用户信息，json格式，开发自行处理登录后逻辑
                        String obj = (String) msg.obj;
                        Log.i("AuthActivity", "获取离线用户信息获取成功:" + obj);

                        Toast.makeText(AuthActivity.this, "离线", Toast.LENGTH_SHORT).show();
                        Toast.makeText(AuthActivity.this, obj, Toast.LENGTH_SHORT).show();

                        UserInfo.getInstance().saveUserInfo(obj);
                        startActivity(new Intent(AuthActivity.this, MainActivity.class));
                        finish();
                        break;
                    default:
                        break;
                }
            }
        };
    }
}