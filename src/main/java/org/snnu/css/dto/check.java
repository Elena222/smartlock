package org.snnu.css.dto;

/**
 * Created by lhy on 2017/1/10.
 */
public class check {

    private int userId;

    private String account;

    private String password;

    private String username;

    private int userStatus;

    // 职位
    private String position;

    private int higherId;

    private String userRemark;

    public check(int userId, String account, String password, String username, int userStatus, String position, int higherId, String userRemark) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.username = username;
        this.userStatus = userStatus;
        this.position = position;
        this.higherId = higherId;
        this.userRemark = userRemark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHigherId() {
        return higherId;
    }

    public void setHigherId(int higherId) {
        this.higherId = higherId;
    }

    public String getUserRemark() {
        return userRemark;
    }

    public void setUserRemark(String userRemark) {
        this.userRemark = userRemark;
    }
}
