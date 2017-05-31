package org.snnu.css.entity;

/**
 * Created by Thinkpad on 2017/1/4.
 */
public class Room {
    //房间的id
    private String roomId;
    //房号，
    private int roomIndex;
    //房间的ip
    private String ip;
    //房间所在的段数
    private int roomPart;
    //房间所在的层数
    private int roomFloor;
    //房间所在的教学楼的id
    private String buildingName;

    //房间负责人的登录账号
    private String account;

    //房间类型
    private String roomType;
    //对房间的描述
    private String roomDes;
    //房间的状态，是开着的还是关着的
    private int roomStatus;
    //房间的mac地址
    private String roomMac;


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getRoomPart() {
        return roomPart;
    }

    public void setRoomPart(int roomPart) {
        this.roomPart = roomPart;
    }

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRoomDes() {
        return roomDes;
    }

    public void setRoomDes(String roomDes) {
        this.roomDes = roomDes;
    }

    public int getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(int roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomMac() {
        return roomMac;
    }

    public void setRoomMac(String roomMac) {
        this.roomMac = roomMac;
    }



    public Room() {

    }

    public Room(String roomId,int roomIndex, int roomPart, String buildingName, String account, String roomType, String roomDes) {
        this.roomId = roomId;
        this.roomIndex = roomIndex;
        this.roomPart = roomPart;
        this.buildingName = buildingName;
        this.account = account;
        this.roomType = roomType;
        this.roomDes = roomDes;
    }

    public Room(String roomId, int roomIndex, int roomPart, String roomType, String roomDes) {
        this.roomId = roomId;
        this.roomIndex = roomIndex;
        this.roomPart = roomPart;
        this.roomType = roomType;
        this.roomDes = roomDes;
    }

    public Room(String roomId, int roomIndex, int roomPart, String roomType, String roomDes,String buildingName) {
        this.roomId = roomId;
        this.roomIndex = roomIndex;
        this.roomPart = roomPart;
        this.roomType = roomType;
        this.roomDes = roomDes;
        this.buildingName = buildingName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomIndex=" + roomIndex +
                ", ip='" + ip + '\'' +
                ", roomPart=" + roomPart +
                ", roomFloor=" + roomFloor +
                ", buildingName='" + buildingName + '\'' +
                ", account='" + account + '\'' +
                ", roomType='" + roomType + '\'' +
                ", roomDes='" + roomDes + '\'' +
                ", roomStatus=" + roomStatus +
                ", roomMac='" + roomMac + '\'' +
                '}';
    }
}
