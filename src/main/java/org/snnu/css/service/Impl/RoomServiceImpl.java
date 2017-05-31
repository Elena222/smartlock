package org.snnu.css.service.Impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snnu.css.dao.BuildingDao;
import org.snnu.css.dao.LogDao;
import org.snnu.css.dao.RoomDao;
import org.snnu.css.dao.UserDao;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.Room;
import org.snnu.css.entity.User;
import org.snnu.css.enums.SmartLockEnum;
import org.snnu.css.nettyclient.NettyClient;
import org.snnu.css.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 房间有关的实现类（1是门开着。2是门关着 ）
 * Created by lhy on 2017/1/6.
 */
@Service
public class RoomServiceImpl implements RoomService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BuildingDao buildingDao;
    @Autowired
    private LogDao logDao;

    /**
     * 根据负责人ID查找房间
     *
     * @param userId
     * @return
     */
    public HttpResult queryById(int userId) {
        User user = userDao.queryById(userId);
        List<Room> list;
        if (user.getType().equals("楼主") || user.getType().equals("楼管")) {
            list = roomDao.queryByUserId(userId);

        } else if (user.getType().equals("教研人员")) {
            int higherId = user.getHigherId();
            User use = userDao.queryById(higherId);
            String account = use.getAccount();
            list = roomDao.queryByUserIdRoom(userId);
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setAccount(account);
            }
        } else if (user.getType().equals("教研负责人")) {
            list = roomDao.queryByUserIdRoom(userId);
            for (Room room : list) {
                room.setAccount(user.getAccount());
            }
        } else {
            list = roomDao.queryByUserIdRoom(userId);
        }
        HttpResult httpResult = new HttpResult();
        try {
            if (list.size() == 0) {
                httpResult.setTranslation(404, "没有找到任何房间信息", SmartLockEnum.CHECK_ERROR);
                return httpResult;
            } else {
                httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
                httpResult.setResult(list);
                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
                System.out.print(jsonObject.toString());
                return httpResult;
            }
        } catch (Exception e) {
            httpResult.setTranslation(404, "操作出错", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

    /**
     * 根据教学楼的名字把所有房间查询出来然后放到数组里
     *
     * @param BuildingName
     * @return
     */
    public HttpResult queryRoomByBuildingName(String BuildingName) {
        String[] rooms = roomDao.queryRoomByBuildingName(BuildingName);
        HttpResult httpResult = new HttpResult();
        try {
            if (rooms.length != 0) {
                httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
                httpResult.setResult(rooms);
                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
                System.out.print(jsonObject.toString());
            } else {
                httpResult.setTranslation(404, "查询失败", SmartLockEnum.CHECK_ERROR);
            }
        } catch (Exception e) {
            httpResult.setTranslation(404, "查询失败", SmartLockEnum.CHECK_ERROR);
        }
        return httpResult;
    }

    /**
     * 楼主的特用方法
     * 根据楼主的id来查询所管理教学楼的所有教研室
     *
     * @param userId
     * @return
     */
    public HttpResult querySpecialRoomById(int userId) {
        HttpResult httpResult = new HttpResult();
        try {
            List<Room> rooms = roomDao.querySpecialRoomById(userId);
            if (rooms.size() != 0) {
                httpResult.setResult(rooms);
                httpResult.setTranslation(400, "查询成功", SmartLockEnum.CHECK_SUCCESS);
            } else {
                httpResult.setTranslation(304, "查询失败", SmartLockEnum.CHECK_ERROR);
            }
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(304, "查询失败", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }

    }

    public HttpResult queryRoomTypeById(int userId) {
        HttpResult httpResult = new HttpResult();
        try {
            List<String> types = roomDao.queryRoomTypeById(userId);
            if (types.size() != 0) {
                httpResult.setResult(types);
                httpResult.setTranslation(400, "查询成功", SmartLockEnum.CHECK_SUCCESS);
            } else {
                httpResult.setTranslation(304, "查询失败", SmartLockEnum.CHECK_ERROR);
            }
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(304, "查询失败", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

    /**
     * 插入一条房间信息
     *
     * @param roomIndex
     * @param roomPart
     * @param buildingName
     * @param roomType
     * @param roomDes
     * @return
     */
    public HttpResult insertRoom(int roomIndex, int roomPart, String buildingName,
                                 String roomType, String roomDes) {
        HttpResult httpResult = new HttpResult();
        int building_id = buildingDao.queryIdByBuildingName(buildingName);
        String room_id = building_id + "-" + roomPart + "-" + roomIndex;
        Room room = new Room(room_id, roomIndex, roomPart, roomType, roomDes, buildingName);
        try {
            int insert1 = roomDao.roomInsert(room);
            if (insert1 <= 0) {
                httpResult.setTranslation(400, "插入失败", SmartLockEnum.INSERT_ERROR);
            } else {
                httpResult.setTranslation(200, "插入成功", SmartLockEnum.INSERT_SUCCESS);
            }

        } catch (Exception e) {
            httpResult.setTranslation(404, "操作出错", SmartLockEnum.INSERT_ERROR);
        }
        return httpResult;
    }

    /**
     * 更新房间信息
     *
     * @param roomId
     * @param roomIndex
     * @param roomPart
     * @param buildingName
     * @param roomType
     * @param roomDes
     * @return
     */
    public HttpResult updateRoom(String roomId, int roomIndex, int roomPart, String buildingName
            , String roomType, String roomDes) {
        HttpResult httpResult = new HttpResult();
        Room room = new Room(roomId, roomIndex, roomPart, roomType, roomDes, buildingName);
        try {
            int update1 = roomDao.roomUpdateById(room);
            if (update1 <= 0) {
                httpResult.setTranslation(400, "更新失败", SmartLockEnum.UPDATE_ERROR);
                return httpResult;
            } else {
                httpResult.setTranslation(200, "更新成功", SmartLockEnum.UPDATE_SUCCESS);
                return httpResult;
            }

        } catch (Exception e) {
            httpResult.setTranslation(404, "操作出错", SmartLockEnum.UPDATE_ERROR);
            return httpResult;
        }
    }

    /**
     * 通过房间id来删除房间
     *
     * @param roomId
     * @return
     */
    public HttpResult roomDeleteById(String roomId) {
        HttpResult httpResult = new HttpResult();
        try {
            int delete1 = roomDao.roomDelete(roomId);
            if (delete1 <= 0) {
                httpResult.setTranslation(400, "删除失败", SmartLockEnum.DETELE_ERROR);
                return httpResult;
            } else {
                int delete2 = roomDao.roomUserDelete(roomId);
                if (delete2 <= 0) {
                    httpResult.setTranslation(400, "删除失败", SmartLockEnum.DETELE_ERROR);
                    return httpResult;
                } else {
                    httpResult.setTranslation(200, "删除成功", SmartLockEnum.DETELE_SUCCESS);
                    return httpResult;
                }
            }
        } catch (Exception e) {
            httpResult.setTranslation(404, "操作出错", SmartLockEnum.DETELE_SUCCESS);
            return httpResult;
        }
    }

    /**
     * 根据ID和段查询房间
     *
     * @param userId
     * @param part
     * @return
     */
    public HttpResult roomQueryByRoomIdAndPart(int userId, int part) {
        HttpResult httpResult = new HttpResult();
        List<Room> room = roomDao.queryByUserIdAndRoomPart(userId, part);
        try {
            if (room.size() == 0) {
                httpResult.setTranslation(400, "查找失败", SmartLockEnum.CHECK_ERROR);
                return httpResult;
            } else {
                httpResult.setTranslation(200, "查询成功了耶", SmartLockEnum.CHECK_SUCCESS);
                httpResult.setResult(room);
                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
                System.out.print(jsonObject.toString());
                return httpResult;
            }
        } catch (Exception e) {
            httpResult.setTranslation(404, "操作出错", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

    /**
     * 根据房间ID来返回一个MAC地址值
     *
     * @param roomIds
     * @return
     */
    public HttpResult openByRoomId(int userId, String roomIds[]) {
        HttpResult httpResult = new HttpResult();
        try {
            ArrayList list = new ArrayList();
                for (int i = 0; i < roomIds.length; i++) {
                    String mac = roomDao.queryMacByRoomId(roomIds[i]);
                    httpResult = this.openByMac(userId, mac, roomIds[i]);
                    if (httpResult.getError_code()==200){
                        list.add("开锁成功");
                    }
                    else{
                        list.add("开锁失败");
                    }
            }
            httpResult.setResult(list);
            System.out.println(list);
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(304, "开锁操作失败", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

    /**
     * 根据MAC地址值来进行开锁操作
     *
     * @param mac
     * @return
     */
    public HttpResult openByMac(int userId, String mac, String roomId) {
        HttpResult httpResult = new HttpResult();
        String data = "CMD:" + mac + ":051902110A0F";
        try {
            new NettyClient(data, "10.10.10.173", 8618).start();
            System.out.println(data);
            roomDao.openByRoomMac(mac);
            logDao.logInsert(new Date(), userId, roomId, 1);
            httpResult.setTranslation(200, "开锁成功", SmartLockEnum.CHECK_SUCCESS);
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(304, "开锁失败", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

    /**
     * roomId查询到房间小主机的mac地址，然后通过mac地址来关门
     *
     * @param roomIds
     * @return
     */
    public HttpResult closeByRoomId(int userId, String roomIds[]) {

        HttpResult httpResult = new HttpResult();
        try {
            ArrayList list = new ArrayList();
            for (int i = 0; i < roomIds.length; i++) {
                String mac = roomDao.queryMacByRoomId(roomIds[i]);
                httpResult = this.closeByMac(userId, mac, roomIds[i]);
                if (httpResult.getError_code()==200){
                    list.add("关锁成功");
                }
                else{
                    list.add("关锁失败");
                }
            }
            httpResult.setResult(list);
            System.out.println(list);
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(304, "操作失败", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }



    /**
     * 根据MAC地址值来进行关锁操作
     *
     * @param mac
     * @return
     */
    public HttpResult closeByMac(int userId, String mac, String roomId) {
        HttpResult httpResult = new HttpResult();
        String data = "CMD:" + mac + ":05190212090F";
        try {
            new NettyClient(data, "10.10.10.173", 8618).start();
            roomDao.closeByRoomMac(mac);
            logDao.logInsert(new Date(), userId, roomId, 0);
            System.out.println(data);
            httpResult.setTranslation(200, "关锁成功", SmartLockEnum.CHECK_SUCCESS);
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(304, "关锁失败", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

//    public HttpResult closeByMac(String mac) {
//        HttpResult httpResult = new HttpResult();
//        //List<Room> room = roomDao.queryByUserIdAndRoomPart(userId,part); //使用逻辑判断关锁
//        try {
//            if (room.size() <= 0) {
//                httpResult.setTranslation(400, "关锁失败", SmartLockEnum.CHECK_ERROR);
//                return httpResult;
//            } else {
//                httpResult.setTranslation(200, "关锁成功", SmartLockEnum.CHECK_SUCCESS);
//                httpResult.setResult(room);
//                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
//                System.out.print(jsonObject.toString());
//                return httpResult;
//            }
//        } catch (Exception e) {
//            httpResult.setTranslation(404, "操作出错", SmartLockEnum.CHECK_ERROR);
//            return httpResult;
//        }
//    }
//
//    /**
//     * 根据指定的房间开门
//     * @return
//     */
//    public HttpResult openAll() {
//        HttpResult httpResult = new HttpResult();
//        //List<Room> room = roomDao.queryByUserIdAndRoomPart(userId,part); //使用逻辑判断关锁
//        try {
//            if (room.size() <= 0) {
//                httpResult.setTranslation(400, "指定房间开锁失败", SmartLockEnum.CHECK_ERROR);
//                return httpResult;
//            } else {
//                httpResult.setTranslation(200, "指定房间开锁成功", SmartLockEnum.CHECK_SUCCESS);
//                httpResult.setResult(room);
//                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
//                System.out.print(jsonObject.toString());
//                return httpResult;
//            }
//        } catch (Exception e) {
//            httpResult.setTranslation(404, "操作出错", SmartLockEnum.CHECK_ERROR);
//            return httpResult;
//        }
//    }
//
//    /**
//     * 根据指定的房间关门
//     * @return
//     */
//    public HttpResult closeAll() {
//        HttpResult httpResult = new HttpResult();
//        //List<Room> room = roomDao.queryByUserIdAndRoomPart(userId,part); //使用逻辑判断关锁
//        try {
//            if (room.size() <= 0) {
//                httpResult.setTranslation(400, "指定房间关锁失败", SmartLockEnum.CHECK_ERROR);
//                return httpResult;
//            } else {
//                httpResult.setTranslation(200, "指定房间关锁成功", SmartLockEnum.CHECK_SUCCESS);
//                httpResult.setResult(room);
//                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
//                System.out.print(jsonObject.toString());
//                return httpResult;
//            }
//        } catch (Exception e) {
//            httpResult.setTranslation(404, "操作出错", SmartLockEnum.CHECK_ERROR);
//            return httpResult;
//        }
//    }

}
