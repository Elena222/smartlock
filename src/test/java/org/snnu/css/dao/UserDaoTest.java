//package org.snnu.css.dao;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.snnu.css.entity.User;
//import org.snnu.css.entity.UserReturn;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Dante on 2017/1/4.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration({"classpath:spring/spring-dao.xml"})
//public class UserDaoTest {
//    @Resource
//    private UserDao userDao;
//    @Test
////用户登录
//    public void queryByAccountAndPassword() throws Exception {
//        String account = "qichao", password = "qichao";
//        UserReturn user = userDao.queryByAccountAndPassword(account, password);
//        System.out.println(user);
//    }
//    @Test
////添加用户
//    public void userInsert() throws Exception {
//        User user=new User("wujingyu","wujingyu","吴景彧","13785445690","教研人员",4,"619");
//        int a = userDao.userInsert(user);
//        System.out.println(a);
//    }
//    @Test
////修改用户
//    public void userUpdate() throws Exception {
//        User user=new User(28,"xiaoming","123","王鑫15","137854","总负责人",6,"607");
//        int a = userDao.userUpdate(user);
//        System.out.println(a);
//    }
//    @Test
////停用用户
//    public void userUpdateStatus() throws Exception {
//        int userId=28;
//        int a = userDao.userUpdateStatus(userId);
//        System.out.println(a);
//    }
//    @Test
//    //通过id来查询下级账户
//    public void queryByHigherId() throws Exception {
//        int higherId=9;
//        List<User> list = userDao.queryByHigherId(higherId);
//        System.out.println(list);
//    }
//    @Test
//    //通过account来查询id
//    public void queryIdByAccount() throws Exception{
//        int id=userDao.queryIdByAccount("liyao");
//        System.out.println(id);
//    }
//    @Test
//    public void queryUserTypeByAccount() throws Exception{
//        List<String> userType=userDao.queryUserTypeById(4);
//        System.out.println(userType);
//    }
//}