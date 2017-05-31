package org.snnu.css.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snnu.css.entity.Building;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Thinkpad on 2017/1/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BuildingDaoTest {
    @Resource
    private BuildingDao buildingDao;
    @Test
    public void buildingQueryAll() throws Exception {

        List<Building> buildings=buildingDao.buildingQueryAll();

        System.out.println(buildings);
    }

    @Test
    public void queryRoomPartByName() throws Exception{
        int parts=buildingDao.queryPartCountByName("文津楼");
        System.out.println(parts);
    }
    @Test
    public void buildingInsert() throws Exception {

        int a=buildingDao.buildingInsert(new Building("图书馆",1,2,3,"计科院的教室"));
        System.out.println(a);
    }

    @Test
    public void buildingUpdate() throws Exception {
        int a=buildingDao.buildingUpdate(new Building(14,"文艺楼",1,2,3,"这是计科院的教室"));
        System.out.println(a);
    }

    @Test
    public void buildingDelete() throws Exception {

        int a =buildingDao.buildingDelete(12);
        System.out.println("a="+a);
    }
    @Test
    public void queryIdByBuildingName() throws Exception{

        int a=buildingDao.queryIdByBuildingName("文汇楼");
        System.out.println(a);
    }

}