package org.snnu.css.dao;

import org.apache.ibatis.annotations.Param;
import org.snnu.css.entity.Room;

import java.util.List;


/**
 * Created by Dante on 2017/1/4.
 */
public interface RoomDao {


    /**
     *
     * 根据用户ID查找房间
     */
    List<Room> queryByUserId(@Param("userId") int userId);

    List<Room> queryByUserIdRoom(@Param("userId") int userId);

    String[] queryRoomByBuildingName(@Param("BuildingName") String BuildingName);
    /**
     * 根据楼管的id来查询出所管理教学楼的所有教研室
     */
    List<Room> querySpecialRoomById(@Param("userId") int userId);
    List<String> queryRoomTypeById(@Param("userId") int userId);
    /**
     * 根据userid和段数来查询房间
     * @param userId
     * @param roomPart
     * @return
     */
    List<Room> queryByUserIdAndRoomPart(@Param("userId") int userId,@Param("roomPart") int roomPart);
    /**
     *
     * 根据房间Id查找mac
     */
    String queryMacByRoomId(@Param("roomId") String roomId);

    /**
     * 根据roomId来开锁
     * @param roomMac
     * @return
     */
     int openByRoomMac(@Param("roomMac") String roomMac);

    /**
     * 根据roomMac来关锁
     * @param roomMac
     * @return
     */
     int closeByRoomMac(@Param("roomMac") String roomMac);

    /**
     *
     * 添加房间
     */
    int roomInsert(@Param("room1") Room room1);
//    //通过账户来查询userId
//    int queryUserIdByAccount(@Param("room") Room room);
    //插入房间和用户的一条对应关系
    int roomUserInsert(@Param("roomId") String roomId, @Param("userId") int userId);

    /**
     *
     * 根据房间ID修改房间
     */
    int roomUpdateById(@Param("room") Room room);

    int roomUserUpdate(@Param("roomId") String roomId, @Param("userId") int userId);

    /**
     * 删除房间
     */
    int roomDelete(@Param("roomId") String roomId);
    int roomDeleteByBuildingId(@Param("buildingId") int buildingId);
    /**
     * 删除房间和人的对应关系
     * @param roomId
     * @return
     */
    int roomUserDelete(String roomId);





    int insertTest(@Param("roomId") String roomId,@Param("roomIndex") int roomIndex,@Param("roomPart") int roomPart,@Param("roomFloor") int roomFloor,@Param("buildingId") int buildingId,@Param("roomStatus") int roomStatus);
    int updateTest();
    int insertTest1(@Param("roomId") String roomId);
    int updateTest2();
}
