package cn.nr.hqrfid.ydmhapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.dlxx.mam.Internal.sdk.BaseResponse;
import com.dlxx.mam.Internal.sdk.IYDMHAPI;
import com.dlxx.mam.Internal.sdk.IYDMHAPIEventHandler;
import com.dlxx.mam.Internal.sdk.OAuthResponse;
import com.dlxx.mam.Internal.sdk.OauthManager;
import com.dlxx.mam.Internal.sdk.SendAuth;
import com.dlxx.mam.Internal.sdk.YDMHAPIFactory;

import cn.nr.hqrfid.R;

public class YDMHEntryActivity extends Activity implements IYDMHAPIEventHandler {
    private static final String TAG = "YDMHEntryActivity";
    private IYDMHAPI api;
    private OauthManager mOauthManager;
    private Handler mHandler;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_layout);
        textView = (TextView) findViewById(R.id.button);
        createHandler();
        mOauthManager = new OauthManager("http://2.0.0.1:29000/up/appstore/rest/user");
        mOauthManager.setAppid(Constant.renZhengCode);
        mOauthManager.setSecretString(Constant.secretString);
        mOauthManager.clearHandler();
        mOauthManager.setDebug(true);
        mOauthManager.registerHandler(mHandler);
        api = YDMHAPIFactory.createYDMHAPI(this, Constant.renZhengCode);
        if (null != api) {
            api.handleIntent(getIntent(), this);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (null != api) {
            api.handleIntent(intent, this);
        }
    }


    private void createHandler() {
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case OauthManager.MSG_WHAT_ACCESS_TOKEN_FAILED:
                        OAuthResponse tokenFail = (OAuthResponse) msg.obj;
                        Toast.makeText(YDMHEntryActivity.this, tokenFail.resultDesc, Toast.LENGTH_SHORT).show();
                        break;
                    case OauthManager.MSG_WHAT_ACCESS_TOKEN_SUCCESS:
                        OAuthResponse tokenSuc = (OAuthResponse) msg.obj;
                        mOauthManager.achieveUserInfo(tokenSuc.accessToken, tokenSuc.mamid, YDMHEntryActivity.this);
                        break;
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_FAILED:
                        BaseResponse userinfoFail = (BaseResponse) msg.obj;
                        Toast.makeText(YDMHEntryActivity.this, userinfoFail.resultDesc, Toast.LENGTH_SHORT).show();
                        break;
                    case OauthManager.MSG_WHAT_ACHIEVE_USERINFO_SUCCESS:
                        //用户信息获取成功
                        //TODO:obj为获取到的用户信息，json格式，开发自行处理登录后逻辑
                        String obj = (String) msg.obj;
                        Toast.makeText(YDMHEntryActivity.this, obj, Toast.LENGTH_SHORT).show();
                        if (obj != null) {
                            textView.setText(obj);
                        }
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void onResp(SendAuth.Resp resp) {
        Toast.makeText(YDMHEntryActivity.this, "onResp, authCode:" + resp.authCode + ",state:" + resp.mState,
                Toast.LENGTH_SHORT).show();

        switch (resp.errCode) {
            case SendAuth.ErrCode.ERR_USER_CANCEL:
                Log.d(TAG, "取消授权");
                finish();
                break;
            case SendAuth.ErrCode.ERR_AUTH_DENIED:
                Log.d(TAG, "拒绝授权");
                finish();
                break;
            case SendAuth.ErrCode.ERR_OK:
                String authCode = resp.authCode;
                String state = resp.mState;
                mOauthManager.accessToken(authCode);
                break;
            default:
                break;
        }
    }

}
