package org.snnu.css.service;

import org.apache.ibatis.annotations.Param;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.User;

/**
 * Created by lhy on 2017/1/5.
 */
public interface UserService {


    /**
     * App用户登录
     *
     * @param account
     * @param password
     * @return
     */
    HttpResult userAppLogin(String account, String password,String mac);

    /**
     * PC端用户登录
     * @param account
     * @param password
     * @return
     */
    HttpResult userPCLogin(String account,String password);

    /***
     * 上级给下级分配账号
     * @param account
     * @param password
     * @param username
     * @param phone
     * @param type
     * @param higherId
     * @param management
     * @param roomIds
     * @return
     */
    HttpResult insertUser(String account, String password, String username, String phone, String type,
                          int higherId, String management,String roomIds[]);

    /***
     * 上级给下级分配账号App
     * @param account
     * @param password
     * @param username
     * @param phone
     * @param type
     * @param higherId
     * @param management
     * @return
     */
    HttpResult insertUserApp(String account, String password, String username, String phone, String type, int higherId, String management);

    /**
     * 停用某个账户
     *
     * @param userId
     * @return
     */
    HttpResult stopUser(int userId);


    /**
     * 修改用户信息
     *
     * @param userId
     * @param account
     * @param password
     * @param username
     * @param phone
     * @param type
     * @param higherId
     * @param management
     * @return
     */
    HttpResult updateUser(int userId, String account, String password, String username, String phone, String type, int higherId, String management,String roomIds[]);

    /**
     * 查询某个人创建的所有账号
     *
     * @param HigherId
     * @return
     */
    HttpResult getByHigherId(int HigherId);

    /**
     * 根据account来查询此用户的下级用户的类型
     * @param userId
     * @return
     */
    HttpResult queryUserTypeById(@Param("userId") int userId);


    /**
     * 根据用户ID来查询相关用户
     *
     * @param userId
     * @return
     */
    HttpResult queryById(int userId);

    /**app端修改用户信息
     * app
     * @param user
     * @return
     */
    HttpResult updateUserApp(User user);

//    HttpResult deleteUser(int userId);
//    int checkproxy();

}
