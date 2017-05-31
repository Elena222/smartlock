package org.snnu.css.service.Impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.snnu.css.dao.BuildingDao;
import org.snnu.css.dao.RoomDao;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.Building;
import org.snnu.css.enums.SmartLockEnum;
import org.snnu.css.service.BuildingService;
import org.snnu.css.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.spi.http.HttpHandler;
import java.util.List;

/**
 * 建筑物有关的实现类
 * Created by lhy on 2017/1/6.
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingDao buildingDao;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomDao roomDao;
    /**
     * 查询所有的教学楼信息
     *
     * @return
     */
    public HttpResult queryAllById() {
        HttpResult httpResult = new HttpResult();
        List<Building> building = buildingDao.buildingQueryAll();
        if (building.size() == 0) {
            httpResult.setTranslation(404, "不存在任何楼", SmartLockEnum.CHECK_ERROR);
        } else
            httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
        httpResult.setResult(building);
        JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
        System.out.print(jsonObject.toString());
        return httpResult;
    }
    //将没有分配楼主的教学楼查询出来
    public HttpResult queryBuildingLimitChecked(){
        HttpResult httpResult=new HttpResult();
        List<Building> building=buildingDao.queryBuildingLimitChecked();

        if(building.size()==0){
            httpResult.setTranslation(200,"查询成功",SmartLockEnum.CHECK_SUCCESS);
        }
        else {
            httpResult.setTranslation(404,"没有教学楼可供分配",SmartLockEnum.CHECK_ERROR);
        }
        httpResult.setResult(building);
        JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
        System.out.print(jsonObject.toString());
        return httpResult;
    }


    /**
     * 根据教学楼的名字来查询楼的段数
     * @param buildingName
     * @return
     */
    public HttpResult queryPartCountByName(String buildingName){
        HttpResult httpResult=new HttpResult();
        try {
            int parts = buildingDao.queryPartCountByName(buildingName);
            httpResult.setResult(parts);
            httpResult.setTranslation(200,"查询成功",SmartLockEnum.CHECK_SUCCESS);
            JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
            return  httpResult;
        }catch (Exception e){
            httpResult.setTranslation(304,"查询错误",SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

    /**
     * 根据楼管的id来查询楼管的管理房间所在教学楼的名字
     * @param userId
     * @return
     */
    public HttpResult queryBuildingNameById(int userId){
        HttpResult httpResult=new HttpResult();
        try {
            String name=buildingDao.queryBuildingNameById(userId);
            if(name!=null) {
                httpResult.setResult(name);
                httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
            }else{
                httpResult.setTranslation(304, "查询失败", SmartLockEnum.CHECK_SUCCESS);
            }
            return httpResult;
        }catch (Exception e){
            httpResult.setTranslation(304,"查询失败",SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }

    }
    /**
     * 删除教学楼
     *
     * @param buildingId
     * @return
     */
    public HttpResult deleteById(int buildingId) {
        HttpResult httpResult = new HttpResult();
        try {
            int detele = buildingDao.buildingDelete(buildingId);
            if (detele <= 0) {
                httpResult.setTranslation(404, "该楼不存在", SmartLockEnum.DETELE_ERROR);
                return httpResult;
            } else {
                httpResult.setTranslation(200, "删除成功", SmartLockEnum.DETELE_SUCCESS);
                roomDao.roomDeleteByBuildingId(buildingId);
                return httpResult;
            }
        } catch (Exception e) {
            httpResult.setTranslation(400, "操作失败", SmartLockEnum.DETELE_ERROR);
            return httpResult;
        }
    }

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
    public HttpResult insertBuilding(String buildingName, int roomcount, int floorcount,
                                     int partcount, String buildingDes) {
        Building building = new Building(buildingName, roomcount, floorcount, partcount,buildingDes);
        HttpResult httpResult = new HttpResult();
        try {
            int insert = buildingDao.buildingInsert(building);
            if (insert <= 0) {
                httpResult.setTranslation(400, "插入失败", SmartLockEnum.INSERT_ERROR);
                return httpResult;
            } else {
                httpResult.setTranslation(200, "插入成功", SmartLockEnum.INSERT_SUCCESS);
                {
                    for(int i=1;i<=partcount;i++){
                        for(int j=1;j<=floorcount;j++){
                            for(int k=1;k<=roomcount;k++){
                                int index=j*100+k;
                                roomService.insertRoom(index,i,buildingName,"教室"," ");
                            }
                        }
                    }

                }
                return httpResult;
            }
        } catch (Exception e) {
            httpResult.setTranslation(400, "已存在该楼信息", SmartLockEnum.INSERT_ERROR);
            return httpResult;
        }
    }

    /**
     * 修改教学楼的信息
     *
     * @param buildingId
     * @param buildingName
     * @param roomcount
     * @param floorcount
     * @param partcount
     * @param buildingDes
     * @return
     */
    public HttpResult buildingUpdate(int buildingId, String buildingName,  int roomcount,
                                     int floorcount, int partcount, String buildingDes) {
        Building building = new Building(buildingId, buildingName, roomcount, floorcount, partcount,buildingDes);
        HttpResult httpResult = new HttpResult();
        try {
            int update = buildingDao.buildingUpdate(building);
            if (update <= 0) {
                httpResult.setTranslation(404, "更新未实现", SmartLockEnum.UPDATE_ERROR);
                return httpResult;
            }
            httpResult.setTranslation(200, "更新成功", SmartLockEnum.UPDATE_SUCCESS);
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(400, "楼名已存在", SmartLockEnum.UPDATE_ERROR);
            return httpResult;
        }
    }

}
