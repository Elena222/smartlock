package org.snnu.css.service;

import org.snnu.css.dto.HttpResult;

import java.util.Date;

/**
 * Created by Administrator on 2017/1/7.
 */
public interface LogService {

    /**
     * 根据上级用户的id来查询所管理的用户日志记录
     *
     * @param heightId
     * @param startTime
     * @param endTime
     * @return
     */
    HttpResult logQueryId(int heightId, Date startTime, Date endTime);

    /**
     * 查询某个用户一段时间内的所有日志记录
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    HttpResult searchLogByTime(int userId, Date startTime, Date endTime);

    /**
     * 根据heightId查询他所管理的人的所有日志
     *
     * @param heightId
     * @return
     */
    HttpResult queryAll(int heightId);

    /**
     * 生成一条日志信息
     *
     * @param logTime
     * @param userId
     * @param roomId
     * @param behavior
     * @return
     */
    HttpResult insertLog(Date logTime, int userId, String roomId, int behavior);

}
