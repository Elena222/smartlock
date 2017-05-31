package org.snnu.css.entity;

import java.util.Date;

/**
 * Created by Dante on 2017/1/4.
 */
public class Log {
    private int logId;
    private Date logTime;
    private int userId;
    private String account;
    private String username;
    private String buildingName;
    private int roomPart;
    private int roomIndex;
    private int behavior;


    public Log() {
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public int getRoomPart() {
        return roomPart;
    }

    public void setRoomPart(int roomPart) {
        this.roomPart = roomPart;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public int getBehavior() {
        return behavior;
    }

    public void setBehavior(int behavior) {
        this.behavior = behavior;
    }


    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", logTime=" + logTime +
                ", userId=" + userId +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", buildingName='" + buildingName + '\'' +
                ", roomPart=" + roomPart +
                ", roomIndex=" + roomIndex +
                ", behavior=" + behavior +
                '}';
    }
}
