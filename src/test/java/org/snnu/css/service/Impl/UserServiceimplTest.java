//package org.snnu.css.service.Impl;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.snnu.css.dto.HttpResult;
//import org.snnu.css.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.util.List;
//
///**
// * Created by lhy on 2017/1/5.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
//public class UserServiceimplTest {
//
//    private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//    @Autowired
//    private UserService userservice;
//
////  用户登录
//
//    /**
//     * 用户登录
//     * @throws Exception
//     */
//    @Test
//    public void userLogin() throws Exception {
//        String account = "wang112";
//        String password = "123";
//        String mac="44:04:44:81:99:c2";
//        HttpResult httpResult = userservice.userAppLogin(account,password,mac);
//        logger.info("login={}" + httpResult);
//    }
//
//    @Test
//    public void queryUserTypeById() throws Exception{
//
//        HttpResult httpResult=userservice.queryUserTypeById(9);
//        Object types=httpResult.getResult();
//        logger.info("login={}"+httpResult);
//        System.out.println(types);
//    }
//
//    /**
//     * 查询某个人创建的所有账号
//     *
//     * @throws Exception
//     */
//    @Test
//    public void getByHigherId() throws Exception {
//        int HigherId = 13;
//        HttpResult httpResult = userservice.getByHigherId(HigherId);
//        logger.info("list={}", httpResult);
//    }
//
////  插入一条用户信息
//
//    /**
//     * 插入一条用户信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void insertUser() throws Exception {
//        String account = "liyao";
//        String password = "123";
//        String username = "123";
//        String phone = "11111111";
//        String type = "楼管";
//        int higherId = 1;
//        String management = "文津楼";
//        HttpResult httpResult = userservice.insertUser(account, password, username, phone, type, higherId, management);
//        logger.info("list={}", httpResult);
//    }
//
////  更新用户信息
//
//    /**
//     * 更新用户信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void updateUser() throws Exception {
//        int userId = 2;
//        String account = "1111";
//        String password = "123";
//        String username = "123";
//        String phone = "11111111";
//        String type = "教研负责人";
//        int higherId = 1;
//        String management = "1-3-617";
//        HttpResult httpResult = userservice.updateUser(userId, account, password, username, phone, type, higherId, management);
//        logger.info("list={}", httpResult);
//    }
//
////  停用某个账户
//
//    /**
//     * 停用某个账户
//     *
//     * @throws Exception
//     */
//    @Test
//    public void stopUser() throws Exception {
//        int userId = 5;
//        HttpResult httpResult = userservice.stopUser(userId);
//        logger.info("list={}", httpResult);
//    }
//
////  根据用户ID查找相关的用户信息
//
//    /**
//     * 根据用户ID查找相关的用户信息
//     *
//     * @throws Exception
//     */
//    @Test
//    public void queryById() throws Exception {
//        int userID = 2;
//        HttpResult httpResult = userservice.queryById(userID);
//        logger.info("list={}", httpResult);
//    }
//
//}
