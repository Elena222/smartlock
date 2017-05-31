package org.snnu.css.service.Impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snnu.css.dao.LogDao;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.Log;
import org.snnu.css.enums.SmartLockEnum;
import org.snnu.css.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 日志有关的实现类
 * Created by Administrator on 2017/1/7.
 */
@Service
public class LogServiceImpl implements LogService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LogDao logDao;

    /**
     * 根据heightId查询他所管理的人的所有日志
     *
     * @param heightId
     * @return
     */
    public HttpResult queryAll(int heightId) {
        HttpResult httpResult = new HttpResult();
        List<Log> list = logDao.logQueryAllByUserId(heightId);
        if (list.size() == 0) {
            httpResult.setTranslation(404, "日志为空", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
        httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
        httpResult.setResult(list);
        JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
        System.out.print(jsonObject.toString());
        return httpResult;
    }

    /**
     * 查询某个用户一段时间内的所有日志记录
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    public HttpResult searchLogByTime(int userId, Date startTime, Date endTime) {
        HttpResult httpResult = new HttpResult();
        List<Log> list = logDao.logQueryByUserId(userId, startTime, endTime);
        if (list.size() == 0) {
            httpResult.setTranslation(404, "日志为空", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
        httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
        httpResult.setResult(list);
        JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
        System.out.print(jsonObject.toString());
        return httpResult;
    }

    /**
     * 根据上级用户的id来查询所管理的用户日志记录
     *
     * @param height
     * @param startTime
     * @param endTime
     * @return
     */
    public HttpResult logQueryId(int height, Date startTime, Date endTime) {
        HttpResult httpResult = new HttpResult();
        List<Log> list = logDao.logQueryByHigherId(height, startTime, endTime);
        if (list.size() == 0) {
            httpResult.setTranslation(404, "日志为空", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
        httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
        httpResult.setResult(list);
        JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
        System.out.print(jsonObject.toString());
        return httpResult;
    }

    /**
     * 生成一条日志信息
     *
     * @param logTime
     * @param userId
     * @param roomId
     * @param behavior
     * @return
     */
    public HttpResult insertLog(Date logTime, int userId, String roomId, int behavior) {
        HttpResult httpResult = new HttpResult();
        int insert = logDao.logInsert(logTime, userId, roomId, behavior);
        if (insert <= 0) {
            httpResult.setTranslation(400, "插入失败", SmartLockEnum.INSERT_ERROR);
            return httpResult;
        }
        httpResult.setTranslation(200, "插入成功", SmartLockEnum.INSERT_SUCCESS);
        return httpResult;
    }

}
