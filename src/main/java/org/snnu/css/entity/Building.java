package org.snnu.css.entity;

/**
 * Created by Thinkpad on 2017/1/4.
 */
public class


Building {

    //教学楼的id
    private int buildingId;
    //教学楼的名字
    private String buildingName;
    //教学楼楼主的姓名
    private String username;
    //教学楼的每层每段房间数
    private int roomcount;
    //教学楼的层数
    private int floorcount;
    //教学楼的段数
    private int partcount;
    /**
     *
     */
    //教学楼的描述
    private String buildingDes;



    public Building() {
    }

    public Building(String buildingName) {
        this.buildingName = buildingName;
    }

    //插入一栋教学楼时的构造方法，因为教学楼的id会在数据库中自动生成，所以不需要插入教学楼的id
    public Building(String buildingName,int roomcount, int floorcount, int partcount, String buildingDes) {
        this.buildingName = buildingName;
        this.roomcount = roomcount;
        this.floorcount = floorcount;
        this.partcount = partcount;
        this.buildingDes = buildingDes;
    }

    public Building(int buildingId, String buildingName,int roomcount, int floorcount, int partcount, String buildingDes) {
        this.buildingId = buildingId;
        this.buildingName = buildingName;
        this.username = username;
        this.roomcount = roomcount;
        this.floorcount = floorcount;
        this.partcount = partcount;
        this.buildingDes = buildingDes;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoomcount() {
        return roomcount;
    }

    public void setRoomcount(int roomcount) {
        this.roomcount = roomcount;
    }

    public int getFloorcount() {
        return floorcount;
    }

    public void setFloorcount(int floorcount) {
        this.floorcount = floorcount;
    }

    public int getPartcount() {
        return partcount;
    }

    public void setPartcount(int partcount) {
        this.partcount = partcount;
    }

    public String getBuildingDes() {
        return buildingDes;
    }

    public void setBuildingDes(String buildingDes) {
        this.buildingDes = buildingDes;
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingId=" + buildingId +
                ", buildingName='" + buildingName + '\'' +
                ", username='" + username + '\'' +
                ", roomcount=" + roomcount +
                ", floorcount=" + floorcount +
                ", partcount=" + partcount +
                ", buildingDes='" + buildingDes + '\'' +
                '}';
    }
}
