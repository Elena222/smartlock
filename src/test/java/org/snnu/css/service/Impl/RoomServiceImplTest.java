//package org.snnu.css.service.Impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.LoggerFactory;
//import org.snnu.css.dto.HttpResult;
//import org.snnu.css.service.RoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * Created by lhy on 2017/1/6.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
//public class RoomServiceImplTest {
//    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private RoomService roomService;
//
//    //  根据用户id查询房间信息
//
//    /**
//     * 根据用户id查询房间信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void queryById() throws Exception {
//        int userId = 17;
//        HttpResult httpResult = roomService.queryById(userId);
//        System.out.println(httpResult);
//        logger.info("room={}", httpResult);
//    }
//
//    @Test
//    public void queryRoomTypeById() throws Exception{
//        HttpResult httpResult=roomService.queryRoomTypeById(13);
//        System.out.println(httpResult.getResult());
//        System.out.println(httpResult);
//
//    }
//
//    /**
//     * 插入一条房间信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void insertRoom() throws Exception {
//        int roomIndex = 411;
//        int roomPart = 5;
//        String buildingName = "文苑楼";
//        String account = "liyao";
//        String roomType = "2";
//        String roomDes = "dfsgggg";
//        HttpResult httpResult = roomService.insertRoom(roomIndex, roomPart, buildingName, roomType, roomDes);
//        System.out.println(httpResult);
//        logger.info("room={}", httpResult);
//    }
//
//    //  更新房间信息
//
//    /**
//     * 更新房间信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void updateRoom() throws Exception {
//        String roomId = "1-1-109";
//        int roomIndex = 666;
//        int roomPart = 3;
//        String buildingName = "文苑楼";
//        String roomType = "hfdskjfdh";
//        String roomDes = "dfsgggg";
//        HttpResult httpResult = roomService.updateRoom(roomId, roomIndex, roomPart, buildingName, roomType, roomDes);
//        System.out.println(httpResult);
//        logger.info("room={}", httpResult);
//    }
//
//    //  根据房间ID来删除房间
//
//    /**
//     * 根据房间ID来删除房间
//     *
//     * @throws Exception
//     */
//    @Test
//    public void roomDeleteById() throws Exception {
//        String roomId = "2";
//        HttpResult httpResult = roomService.roomDeleteById(roomId);
//        System.out.println(httpResult);
//        logger.info("room={}", httpResult);
//    }
//
//    //  根据房间ID来传入相应的MAC地址值
//
//    /**
//     * 根据房间ID来传入相应的MAC地址值
//     *
//     * @throws Exception
//     */
//    @Test
//    public void openByRoomId() throws Exception {
//        //String data = "CMD:0A:00:27:00:00:00:051902110A0F";
//        String roomId = "1-1-102";
//        HttpResult httpResult = roomService.openByRoomId(1,roomId);
//        System.out.println(httpResult);
//        logger.info("room={}", httpResult);
//    }
//
////  根据MAC地址值来进行开锁操作
//
//    /**
//     * 根据MAC地址值来进行开锁操作
//     *
//     * @throws Exception
//     */
//    @Test
//    public void openByMac() throws Exception {
//        String mac = "bc:e6:3f:77:5f:52";
//        HttpResult httpResult = roomService.openByMac(1,mac,"1-1-102");
//        System.out.println(httpResult);
//        logger.info("room={}", httpResult);
//    }
//    @Test
//    public void closeByRoomId() throws Exception{
//        HttpResult httpResult=roomService.closeByRoomId(1,"1-1-102");
//
//        System.out.println(httpResult);
//    }
//    /**
//     * 根据MAC地址值来进行关锁操作
//     *
//     * @throws Exception
//     */
//    @Test
//    public void closeByMac() throws Exception {
//        String mac = "0A:00:27:00:00:00";
//        HttpResult httpResult = roomService.closeByMac(1,mac,"1-1-102");
//        System.out.println(httpResult);
//        logger.info("room={}", httpResult);
//    }
//
////
////    /**
////     * 开指定的门
////     * @throws Exception
////     */
////    @Test
////    public void openAll() throws Exception {
////        String roomId="2";
////        HttpResult httpResult = roomServce.roomDeleteById(roomId);
////        System.out.println(httpResult);
////        logger.info("room={}",httpResult);
////    }
////
////    /**
////     * 关指定的门
////     * @throws Exception
////     */
////    @Test
////    public void closeAll() throws Exception {
////        String roomId="2";
////        HttpResult httpResult = roomServce.roomDeleteById(roomId);
////        System.out.println(httpResult);
////        logger.info("room={}",httpResult);
////    }
//
//
//}