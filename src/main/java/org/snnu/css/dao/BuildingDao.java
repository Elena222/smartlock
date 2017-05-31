package org.snnu.css.dao;

import org.apache.ibatis.annotations.Param;
import org.snnu.css.entity.Building;


import java.util.List;

/**
 * Created by Thinkpad on 2017/1/4.
 */
public interface BuildingDao {
    /**
     * 查询所有教学楼的信息
     * @return
     */
    List<Building> buildingQueryAll();

    /**
     * 将没有被分配楼主的教学楼筛选出来
     */
    List<Building> queryBuildingLimitChecked();

    int ChangeStatus(@Param("buildingName") String buildingName,@Param("status") int status);

    /**
     * 根据教学楼名字查询教学楼的段数
     * @param buildingName
     * @return
     */
    int queryPartCountByName(@Param("buildingName") String buildingName);

    /**
     * 根据楼管的id查看管理的房间在哪个楼
     * @param userId
     * @return
     */
    String queryBuildingNameById(@Param("userId") int userId);
    /**
     * 添加一栋教学楼的信息
     * @param building
     * @return
     */
    int buildingInsert(@Param("building") Building building);

    /**
     * 修改教学楼信息
     * @param building
     * @return
     */
    int buildingUpdate(@Param("building") Building building);
    /**
     * 添加教学楼负责人
     * @return
     */
    int buildingAddHigher(@Param("buildingName") String buildingName,@Param("account") String account);
    /**
     * 删除教学楼的信息
     * @param buildingId
     * @return
     */
    int buildingDelete(@Param("buildingId") int buildingId);

    /**
     * 根据教学楼的名字查询教学楼的id
     * @param buildingName
     * @return
     */
    int queryIdByBuildingName(@Param("buildingName") String buildingName);
}

