package org.snnu.css.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.Building;
import org.snnu.css.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lhy on 2017/1/6.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class BuildingServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BuildingService buildingService;

    //  查看所有的教学楼信息

    /**
     * 查看所有的教学楼信息
     *
     * @throws Exception
     */
    @Test
    public void queryAllById() throws Exception {
        HttpResult httpResult = buildingService.queryAllById();
        System.out.println(httpResult);
        logger.info("log={}" + httpResult);
    }

    @Test
    public void queryRoomPartByName() throws Exception{
        HttpResult httpResult=buildingService.queryPartCountByName("文津楼");
        Object parts=httpResult.getResult();
        System.out.println("parts="+parts);
        System.out.println(httpResult);
        logger.info("log={}"+httpResult);
    }
    @Test
    public  void queryBuildingNameById() throws Exception{
        HttpResult httpResult=buildingService.queryBuildingNameById(14);
        Object name=httpResult.getResult();
        System.out.println(name);
        System.out.println(httpResult);
    }
    /**
     * 删除教学楼信息
     *
     * @throws Exception
     */
    @Test
    public void deleteById() throws Exception {
        int buildingId = 2;
        HttpResult httpResult = buildingService.deleteById(buildingId);
        logger.info("building={}", httpResult);
    }

//  插入教学楼信息

    /**
     * 插入教学楼信息
     *
     * @throws Exception
     */
    @Test
    public void insertBuilding() throws Exception {
        String buildingName = "图书馆1";
        int roomcount = 2;
        int floorcount = 3;
        int partcount = 4;
        String buildingDes = "dasfsaf";
        HttpResult httpResult = buildingService.insertBuilding(buildingName,roomcount, floorcount, partcount, buildingDes);
        logger.info("building={}", httpResult);
    }

//  修改教学楼信息

    /**
     * 修改教学楼信息
     *
     * @throws Exception
     */
    @Test
    public void buildingUpdate() throws Exception {
        int buildId = 15;
        String buildingName = "11111111";
        int roomcount = 2;
        int floorcount = 3;
        int partcount = 4;
        String buildingDes = "dasfsaf";
        HttpResult httpResult = buildingService.buildingUpdate(buildId, buildingName,roomcount, floorcount, partcount, buildingDes);
        logger.info("building={}", httpResult);
    }

}