package cn.nr.hqrfid.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public void saveUserInfo(String info){
        this.userInfo = info;

        try {
            JSONObject jsonObject = new JSONObject(info);
            this.userName = jsonObject.getJSONObject("data").getString("namecode");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }
}
