package com.example.fanzhiyong20200506.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 登录页面的Bean类
 */
@Entity
public class LoginBean {
    private String headPic;
    private String nickName;
    private String phone;
    private String sessionId;
    private int sex;
    @Id
    private long userId;
    private int status; //判断登录状态
    @Generated(hash = 730373554)
    public LoginBean(String headPic, String nickName, String phone,
            String sessionId, int sex, long userId, int status) {
        this.headPic = headPic;
        this.nickName = nickName;
        this.phone = phone;
        this.sessionId = sessionId;
        this.sex = sex;
        this.userId = userId;
        this.status = status;
    }
    @Generated(hash = 1112702939)
    public LoginBean() {
    }
    public String getHeadPic() {
        return this.headPic;
    }
    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getSessionId() {
        return this.sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public int getStatus() {
        return this.status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

}
