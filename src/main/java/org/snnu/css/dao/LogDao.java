package org.snnu.css.dao;

import org.apache.ibatis.annotations.Param;
import org.snnu.css.entity.Log;

import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2017/1/4.
 */
public interface LogDao {
    /**
     * 根据用户的id来查询日志记录
     * @param userId
     * @return
     */
    List<Log>  logQueryAllByUserId(@Param("userId") int userId);
    /**
     * 生成一条日志记录
     * @return
     */
    int logInsert(@Param("logTime") Date logTime, @Param("userId") int userId,
                  @Param("roomId") String roomId, @Param("behavior") int behavior);

    /**
     * 查看某一段时间内管理范围内的所有下级用户的日志记录
     * @return
     */
    List<Log> logQueryByHigherId(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    /**
     * 查看某一段时间内某个用户的日志记录
     * @return
     */
    List<Log> logQueryByUserId(@Param("userId") int userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

}

