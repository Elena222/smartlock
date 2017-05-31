package org.snnu.css.entity;

/**
 * Created by Dante on 2017/1/14.
 */
public class User {
    private int userId;

    private String account;

    private String password;

    private String username;

    private String phone;

    private int userStatus;

    private String type;

    private int higherId;

    private String management;

    private String mac;


    public User() {
    }

    public User(String account, String password, String username, String phone, String type, int higherId,String management) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.type = type;
        this.higherId = higherId;
        this.management=management;
    }
    //插入用户时用的构造函数
    public User(String account, String password, String username, String phone, int userStatus, String type,
                int higherId, String management) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.userStatus = userStatus;
        this.type = type;
        this.higherId = higherId;
        this.management = management;
    }

    public User(int userId, String account, String password, String username, String phone, int userStatus,
                String type, int higherId, String management) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.userStatus = userStatus;
        this.type = type;
        this.higherId = higherId;
        this.management = management;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHigherId() {
        return higherId;
    }

    public void setHigherId(int higherId) {
        this.higherId = higherId;
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", userStatus=" + userStatus +
                ", type='" + type + '\'' +
                ", higherId=" + higherId +
                ", management='" + management + '\'' +
                ", mac='" + mac + '\'' +
                '}';
    }
}
