package org.snnu.css.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snnu.css.entity.Room;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

/**
 * Created by Thinkpad on 2017/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RoomDaoTest {
    @Resource
    private RoomDao roomDao;
    @Resource
    private BuildingDao buildingDao;
    @Test
    public void queryByUserId() throws Exception {
        List<Room>  rooms=roomDao.queryByUserId(4);
        System.out.println("rooms="+rooms);
    }
    @Test
    public void queryByUserIdAndRoomPart() throws Exception{
        List<Room>  rooms=roomDao.queryByUserIdAndRoomPart(7,3);
        System.out.println("rooms="+rooms);
    }
    @Test
    public void queryRoomTypeById() throws Exception{
        List<String> types=roomDao.queryRoomTypeById(7);
        System.out.println(types);
    }

    @Test
    public void queryMacByRoomId() throws Exception {

        String mac=roomDao.queryMacByRoomId("1-1-105");

        System.out.println("mac="+mac);

    }

    @Test
    public void openByRoomMac() throws Exception {

        int a=roomDao.openByRoomMac("0A:00:27:00:00:00");

        System.out.println("a="+a);

    }

    @Test
    public void closeByRoomMac() throws Exception {
        int a=roomDao.closeByRoomMac("0A:00:27:00:00:00");

        System.out.println("a="+a);
    }

    @Test
    public void roomInsert() throws Exception {
        Room room1=new Room("0",413,4,"文渊楼","wangxin","教研室","描述");
        int buildingId=buildingDao.queryIdByBuildingName(room1.getBuildingName());
        String roomId=buildingId+"-"+room1.getRoomPart()+"-"+room1.getRoomIndex();
        room1.setRoomId(roomId);
        int a=roomDao.roomInsert(room1);
        System.out.println(a);
    }
    @Test
    public void roomUserInsert() throws Exception{
        int a =roomDao.roomUserInsert("3-3-31",10);
        System.out.println("a="+a);
    }

    @Test
    public void roomUpdateById() throws Exception {

        int a =roomDao.roomUpdateById(new Room("1-1-109",209,1,"办公室",
                                        "校长办公室"));
        System.out.println("a="+a);
    }
    @Test
    public void roomUserUpdate() throws Exception{
        int a =roomDao.roomUserUpdate("3-3-31",5);
        System.out.println("a="+a);
    }
    @Test
    public void roomDelete() throws Exception{
        int a =roomDao.roomDelete("3-3-21");
        System.out.println(a);
    }
    @Test
    public void roomUserDelete() throws Exception{
        int a=roomDao.roomUserDelete("3-3-21");
        System.out.println(a);
    }
    @Test
    public void insertTest() throws Exception{
        int buildingId=1,roomPart,roomIndex,roomFloor,roomStatus;
        String roomId;
        for(int i=1;i<=4;i++){
            for(int j=1;j<=6;j++){
            for(int k=1;k<=20;k++) {
                roomPart = i;
                roomFloor=j;
                roomIndex=j*100+k;
                roomId=buildingId+"-"+roomPart+"-"+roomIndex;
                { if(k%2==0){

                    roomStatus=0;
                }
                roomStatus=1;
                }
                int a=roomDao.insertTest(roomId,roomIndex,roomPart,roomFloor,buildingId,roomStatus);
                System.out.println(a);
            }
            }
        }

    }
    @Test
    public void updateTest() throws Exception {
    roomDao.updateTest();
    }
    @Test
    public void insertTest1() throws Exception{
        int buildingId=1,roomPart,roomIndex,roomFloor,roomStatus;
        String roomId;
        for(int i=1;i<=4;i++){
            for(int j=1;j<=6;j++){
                for(int k=1;k<=20;k++) {
                    roomPart = i;
                    roomFloor=j;
                    roomIndex=j*100+k;
                    roomId=buildingId+"-"+roomPart+"-"+roomIndex;

                    int a=roomDao.insertTest1(roomId);
                    System.out.println(a);
                }
            }
        }

    }
    @Test
    public void updateTest2() throws Exception{
        int a=roomDao.updateTest2();
    }

}