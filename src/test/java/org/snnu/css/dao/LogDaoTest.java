package org.snnu.css.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.snnu.css.entity.Log;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Dante on 2017/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class LogDaoTest {
    @Resource
    private LogDao logDao;
    @Test
    public void logQueryAllByUserId() throws Exception {
        int userId=3;
        List<Log> log = logDao.logQueryAllByUserId(userId);
        System.out.println(log);
    }
    @Test
//生成一条日志记录
    public void logInsert() throws Exception {
        int userId=6,behavior=1;
            String  roomId="1-3-617";
        Date logTime=new Date();
        int a = logDao.logInsert(logTime,userId,roomId,behavior);
        System.out.println(a);
    }
    @Test
//查看某一段时间内管理范围内的所有下级用户的日志记录
    public void logQueryByHigherId() throws Exception {
        int userId=4;
//        String startTime1="2016-12-21 10:00:00";
//        String endTime1="2017-01-30 11:00:00";
//        SimpleDateFormat   formatter   =
//                new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//        Date startTime=formatter.parse(startTime1),
//                endTime=formatter.parse(endTime1);
        Date startTime=null;
        Date endTime=null;
        List<Log> log = logDao.logQueryByHigherId(userId,startTime,endTime);
        System.out.println(log);
    }
    @Test
//查看某一段时间内某个用户的日志记录
    public void logQueryByUserId() throws Exception {
        int userId=1;
//        String startTime1="2014-1-15 9:00:00";
//        String endTime1="2017-1-01 11:00:00";
//        SimpleDateFormat   formatter   =
//                new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
//        Date startTime=formatter.parse(startTime1),
//                endTime=formatter.parse(endTime1);
        Date startTime=null;
        Date endTime=null;
        List<Log> log = logDao.logQueryByUserId(userId,startTime,endTime);
        System.out.println(log);
    }
}