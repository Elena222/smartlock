package org.snnu.css.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/1/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class LogServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogService logService;

//  根据heightId查询他所管理的人的所有日志

    /**
     * 根据heightId查询他所管理的人的所有日志
     *
     * @throws Exception
     */
    @Test
    public void queryAll() throws Exception {
        int userId = 66;
        HttpResult httpResult = logService.queryAll(userId);
        System.out.println(httpResult);
        logger.info("log={}" + httpResult);
    }

//  查询某个用户一段时间内的所有日志记录

    /**
     * 查询某个用户一段时间内的所有日志记录
     *
     * @throws Exception
     */
    @Test
    public void searchLogByTime() throws Exception {
        int userId = 1;
        Date startTime = new Date();
        Date endTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.add(calendar.YEAR, 1);
        endTime = calendar.getTime();
        HttpResult httpResult = logService.searchLogByTime(userId, startTime, endTime);
        System.out.println(httpResult);
        logger.info("log={}" + httpResult);
    }

//  根据上级用户的id来查询所管理的用户日志记录

    /**
     * 根据上级用户的id来查询所管理的用户日志记录
     *
     * @throws Exception
     */
    @Test
    public void logQueryId() throws Exception {
        int userId = 4;
        Date startTime = new Date();
        Date endTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endTime);
        calendar.add(calendar.YEAR, 1);
        endTime = calendar.getTime();
        HttpResult httpResult = logService.logQueryId(userId, startTime, endTime);
        System.out.println(httpResult);
        logger.info("log={}" + httpResult);
    }

//    生成一条日志信息(如何判断重复查询)

    /**
     * 生成一条日志信息(如何判断重复查询)
     *
     * @throws Exception
     */
    @Test
    public void insertLog() throws Exception {
        HttpResult httpResult = logService.insertLog(new Date(), 5, "1-3-617", 2);
        System.out.println(httpResult);
        logger.info("log={}" + httpResult);
    }

}