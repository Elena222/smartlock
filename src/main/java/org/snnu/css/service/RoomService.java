package org.snnu.css.service;

import org.apache.ibatis.annotations.Param;
import org.snnu.css.dto.HttpResult;

/**
 * Created by lhy on 2017/1/6.
 */
public interface RoomService {


    /**
     * 根据负责人ID来查找房间
     *
     * @param userId
     * @return
     */
    HttpResult queryById(int userId);


    HttpResult queryRoomByBuildingName(String BuilduingName);
    /**
     * 插入一条房间信息
     *
     * @param roomIndex
     * @param roomPart
     * @param buildingName
     * @param account
     * @param roomType
     * @param roomDes
     * @return
     */
    HttpResult insertRoom(int roomIndex, int roomPart, String buildingName, String roomType, String roomDes);

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
    HttpResult updateRoom(String roomId, int roomIndex, int roomPart, String buildingName,
                          String roomType, String roomDes);

    /**
     * 通过房间ID来删除房间
     *
     * @param roomId
     * @return
     */
    HttpResult roomDeleteById(String roomId);

    /**
     * 根据负责人ID和段数来查询房间
     *
     * @param userId
     * @param part
     * @return
     */
    HttpResult roomQueryByRoomIdAndPart(int userId, int part);

    /**
     * 根据用户id来查询此用户管理房间的类型
     * @param userId
     * @return
     */
    HttpResult queryRoomTypeById(@Param("userId") int userId);

    HttpResult querySpecialRoomById(@Param("userId") int userId);
    /**
     * 根据房间ID来返回一个MAC地址值
     *
     * @param roomId
     * @return //
     */
    HttpResult openByRoomId(@Param("userId") int userId,@Param("roomId") String roomIds[]);

    /**
     * 通过MAC地址值来进行开锁操作
     *
     * @param mac
     * @return
     */
    HttpResult openByMac(@Param("userId") int userId,@Param("mac") String mac,
                         @Param("roomId") String roomId);

    /**
     * 根据roomId来查询到该房间小主机的mac地址，根据mac地址来关门
     * @param roomId
     * @return
     */
    HttpResult closeByRoomId(@Param("userId") int userId,@Param("roomId") String roomIds[]);
    /**
     * 通过MAC地址值来进行关锁操作
     *
     * @param mac
     * @return
     */

    HttpResult closeByMac(@Param("userId") int userId,@Param("mac") String mac,
                          @Param("roomId") String roomId);

//    /***
//     * 开数组中所有门
//     */
//    HttpResult openAll();
//
//    /***
//     * 关数组中所有门
//     */
//    HttpResult closeAll();

//
//    /***
//     * 通过ip进行房间查找
//     * @param ip
//     * @return
//     */
//     HttpResult QueryByIp(String ip);
}
