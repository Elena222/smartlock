package org.snnu.css.dao;

import org.apache.ibatis.annotations.Param;

import org.snnu.css.entity.User;
import org.snnu.css.entity.UserReturn;

import java.util.List;

/**
 * Created by Dante on 2017/1/4.
 */
public interface UserDao {
    /**
     *
     * 账户登录
     * @return
     */
    UserReturn queryByAccountAndPassword(@Param("account") String account, @Param("password") String password);

    /**
     * 第一次登录时将mac地址写入数据库
     * @param
     * @return
     */
    int macUpdate(@Param("account") String account,@Param("mac") String mac);

    /**
     * 根据account将mac查询出来
     * @param account
     * @return
     */
    String queryMacByAccount(@Param("account") String account);
    /**
     *
     * 添加用户
     * @return
     */
    int userInsert(@Param("user") User user);

    /**
     *
     * 修改用户u
     * @return
     */
    int userUpdate(@Param("user") User user);

    /**
     * 停用账户
     */
    int userUpdateStatus(@Param("userId") int userId);

    /**
     * 通过id来查询下级账户
     * @param higherId
     * @return
     */
    List<User> queryByHigherId(@Param("higherId") int higherId);

    /**
     * 通过account查询用户id
     * @param account
     * @return
     */
    int queryIdByAccount(@Param("account") String account);

    User queryById(@Param("userId") int userId);

    /**
     * 根据account作为参数来查询此用户的下级用户的类型
     * @param userId
     * @return
     */
    List<String> queryUserTypeById(@Param("userId") int userId);

}
