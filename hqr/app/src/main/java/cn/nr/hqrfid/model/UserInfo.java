package cn.nr.hqrfid.model;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class UserInfo {
    private static UserInfo instance;

    private String userInfo;
    private String userName;

    public static UserInfo getInstance() {
        if (instance == null) {
            instance = new UserInfo();
        }
        return instance;
    }

    /**
     * 设置获取到的用户信息
     *
     * @param info
     */
    public void saveUserInfo(Context context, String info) {
        this.userInfo = info;

        try {
            JSONObject jsonObject = new JSONObject(info);
            this.userName = jsonObject.getJSONObject("data").getString("namecode");
            writeToFile(context, this.userName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    private void writeToFile(Context context, String userName) {
//        String path = context.getCacheDir() + "/userSetting.bat";
        String path = context.getExternalCacheDir() + "/userSetting.bat";
        File f = new File(path);
        try {
            FileWriter w = new FileWriter(f);
            w.write(userName);
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("UserInfo", "writeToFile: " + path);
    }
}
