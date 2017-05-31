package org.snnu.css.service;

import org.apache.ibatis.annotations.Param;
import org.snnu.css.dto.HttpResult;

/**
 * Created by lhy on 2017/1/6.
 */
public interface BuildingService {

    /**
     * 查询所有的教学楼信息
     *
     * @return
     */
    HttpResult queryAllById();

    /**
     * 将没有分配楼主的教学楼查询出来
     * @return
     */
    HttpResult queryBuildingLimitChecked();


    /**
     * 根据教学楼的名字来查询段数
     * @param buildingName
     * @return
     */
    HttpResult queryPartCountByName(String buildingName);

    /**
     * 根据楼管的id来查询楼管的管理房间所在教学楼的名字
     * @param userId
     * @return
     */
    HttpResult queryBuildingNameById(@Param("userId") int userId);
    /**
     * 插入教学楼信息
     *
     * @param buildingName
     * @param roomcount
     * @param floorcount
     * @param partcount
     * @param buildingDes
     * @return
     */
    HttpResult insertBuilding(String buildingName,int roomcount, int floorcount, int partcount, String buildingDes);

    /**
     * 根据楼Id删除教学楼
     *
     * @param buildingId
     * @return
     */
    HttpResult deleteById(int buildingId);


    /**
     * 修改教学楼信息
     *
     * @param buildingId
     * @param buildingName
     * @param roomcount
     * @param floorcount
     * @param partcount
     * @param buildingDes
     * @return
     */
    HttpResult buildingUpdate(int buildingId, String buildingName, int roomcount, int floorcount,
                              int partcount, String buildingDes);

}
