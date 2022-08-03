package cn.nr.hqrfid.model;

public class UserInfo {
    private static UserInfo instance;

    private String userInfo;

    public static UserInfo getInstance() {
        if (instance == null) {
            instance = new UserInfo();
        }
        return instance;
    }

    public void saveUserInfo(String info) {
        this.userInfo = info;
    }

    public String getUserInfo() {
        return this.userInfo;
    }
}
