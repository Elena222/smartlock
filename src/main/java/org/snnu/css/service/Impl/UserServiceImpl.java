package org.snnu.css.service.Impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snnu.css.dao.BuildingDao;
import org.snnu.css.dao.RoomDao;
import org.snnu.css.dao.UserDao;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.User;
import org.snnu.css.entity.UserReturn;
import org.snnu.css.enums.SmartLockEnum;
import org.snnu.css.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 和用户有关的实现类
 * Created by lhy on 2017/1/5.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BuildingDao buildingDao;
    @Autowired
    private RoomDao roomDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * App用户登录
     *
     * @param account
     * @param password
     * @return
     */
    public HttpResult userAppLogin(String account, String password, String mac) {


        UserReturn user = userDao.queryByAccountAndPassword(account, password);
        HttpResult httpResult = new HttpResult();
        if (user == null) {
            httpResult.setTranslation(404, "用户名或密码错误", SmartLockEnum.LOAD_ERROR);
            httpResult.setResult(user);

        } else {
            //查询当前账户是否第一次登录，如果是则将当前账户所使用的设备的mac地址写入
            String queryMac = userDao.queryMacByAccount(account);

            if (queryMac == null) {

                userDao.macUpdate(account, mac);
                httpResult.setTranslation(200, "登录成功", SmartLockEnum.LOAD_SUCCESS);
            } else if (queryMac.equals(mac)) {
                httpResult.setTranslation(200, "登录成功", SmartLockEnum.LOAD_SUCCESS);
            } else if (queryMac != mac) {
                httpResult.setTranslation(500, "请使用原始设备登录", SmartLockEnum.LOAD_ERROR);
            }
        }
        httpResult.setResult(user);
        JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
        System.out.print(jsonObject.toString());
        return httpResult;
    }

    /**
     * PC端登录
     *
     * @param account
     * @param password
     * @return
     */
    public HttpResult userPCLogin(String account, String password) {
        UserReturn userReturn = userDao.queryByAccountAndPassword(account, password);

        HttpResult httpResult = new HttpResult();

        if (userReturn == null) {
            httpResult.setTranslation(404, "用户名或密码错误", SmartLockEnum.LOAD_ERROR);
        } else {
            //判断用户的状态，被停用的账户不能再登录

            User user=userDao.queryById(userReturn.getUserId());
            int status=user.getUserStatus();
            if(status==0){
                httpResult.setTranslation(404,"该账户已经被停用",SmartLockEnum.LOAD_ERROR);
            }
            else{
                httpResult.setTranslation(200, "登录成功", SmartLockEnum.LOAD_SUCCESS);
            }
        }
        httpResult.setResult(userReturn);
        JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
        System.out.print(jsonObject.toString());
        return httpResult;
    }

    /**
     * 查询某个人创建的所有账号
     *
     * @param HigherId
     * @return
     */
    public HttpResult getByHigherId(int HigherId) {
        HttpResult httpResult = new HttpResult();
        try {
            List<User> list = userDao.queryByHigherId(HigherId);
            if (list.size() == 0) {
                httpResult.setTranslation(404, "该用户未创建账户", SmartLockEnum.CHECK_ERROR);
                httpResult.setResult(list);
                return httpResult;
            } else {
                httpResult.setTranslation(200, "查询成功了", SmartLockEnum.CHECK_SUCCESS);
                httpResult.setResult(list);
                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
                System.out.print(jsonObject.toString());
                return httpResult;
            }
        } catch (Exception e) {
            httpResult.setTranslation(400, "操作失误", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

//    public HttpResult queryUserTypeByAccount(String account){
//
//        String userType=userDao.queryUserTypeByAccount("liyao");
//        HttpResult httpResult=;
//        return httpResult;
//    }

    /**
     * 插入一条用户信息
     *
     * @param account
     * @param password
     * @param username
     * @param phone
     * @param type
     * @param higherId
     * @param management
     * @return
     */
    public HttpResult insertUser(String account, String password, String username, String phone,
                                 String type, int higherId, String management, String roomIds[]) {
        HttpResult httpResult = new HttpResult();

            int userStatus = 1;
        try {
            User user = new User(account, password, username, phone, userStatus, type, higherId, management);

                if(type.equals("楼主"))
                    {
                        buildingDao.ChangeStatus(management,1);
                    }

                int insert = userDao.userInsert(user);

                if (insert <= 0) {
                    httpResult.setTranslation(404, "插入失败", SmartLockEnum.INSERT_ERROR);
                } else {

                    httpResult.setTranslation(200, "插入成功", SmartLockEnum.INSERT_SUCCESS);
                   //添加用户时，如果添加的是楼主，数组为空，如果是院主，则不为空
                    if(roomIds.length==0){
                        roomIds = roomDao.queryRoomByBuildingName(management);
                        //将选中的教学楼的状态改为已经备选
                    }else{

                    }
                    for (int i = 0; i <roomIds.length; i++) {
                        int userId = userDao.queryIdByAccount(account);
                        String roomId = roomIds[i];

                        roomDao.roomUserInsert(roomId, userId);
                    }
//                  buildingDao.buildingAddHigher(management, account);
                }

            } catch (Exception e) {
                httpResult.setTranslation(400, "插入未成功", SmartLockEnum.INSERT_ERROR);
            }
        return httpResult;
    }

    /**
     * 插入一条用户信息(App模块)
     *
     * @param account
     * @param password
     * @param username
     * @param phone
     * @param type
     * @param higherId
     * @param management
     * @return
     */
    public HttpResult insertUserApp(String account, String password, String username, String phone,
                                 String type, int higherId, String management) {
        HttpResult httpResult = new HttpResult();
        {

            User user = new User(account, password, username, phone, type, higherId, management);

            try {

                int insert = userDao.userInsert(user);
                if (insert <= 0) {
                    httpResult.setTranslation(404, "插入失败", SmartLockEnum.INSERT_ERROR);
                } else {
                    httpResult.setTranslation(200, "插入成功", SmartLockEnum.INSERT_SUCCESS);
                    int userId = userDao.queryIdByAccount(account);
                    roomDao.roomUserInsert(management, userId);
//                    buildingDao.buildingAddHigher(management, account);
                }
                return httpResult;
            } catch (Exception e) {
                httpResult.setTranslation(400, "此账户已经被创建", SmartLockEnum.INSERT_ERROR);
                return httpResult;
            }
        }
    }

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
    public HttpResult updateUser(int userId, String account, String password, String username, String phone, String type, int higherId, String management, String roomIds[]) {
        HttpResult httpResult = new HttpResult();
        int userStatus=1;

        User user = new User(userId, account, password, username, phone, userStatus,type, higherId, management);

        try {
            int a = userDao.userUpdate(user);
            if (a <= 0) {
                httpResult.setTranslation(304, "更新失败", SmartLockEnum.UPDATE_ERROR);
            } else {
                httpResult.setTranslation(200, "更新成功", SmartLockEnum.UPDATE_SUCCESS);
                for (int i = 0; i <= roomIds.length; i++) {
                    roomDao.roomUserInsert(roomIds[i], userId);
                }
            }
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(400, "该用户名已被注册", SmartLockEnum.UPDATE_ERROR);
            return httpResult;
        }
    }

    /**
     * 停用某个账户
     *
     * @param userId
     * @return
     */
    public HttpResult stopUser(int userId) {
        HttpResult httpResult = new HttpResult();
        int stop = userDao.userUpdateStatus(userId);
        User user=userDao.queryById(userId);
        if (stop <= 0) {
            httpResult.setTranslation(304, "修改失败", SmartLockEnum.UPDATE_ERROR);
        }
        else{
            if(user.getType().equals("楼主")){
                buildingDao.ChangeStatus(user.getManagement(),0);
            }
            httpResult.setTranslation(200, "修改成功", SmartLockEnum.UPDATE_SUCCESS);
        }

        return httpResult;

    }

    /**
     * 根据传入的账户来查询此用户的下级用户的所有类型
     */
    public HttpResult queryUserTypeById(int userId) {
        HttpResult httpResult = new HttpResult();
        try {
            List<String> types = userDao.queryUserTypeById(userId);
            httpResult.setResult(types);
            httpResult.setTranslation(200, "查询成功", SmartLockEnum.CHECK_SUCCESS);
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(304, "查询出错了", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }
    }

    /**
     * 根据用户ID来查询相关用户
     *
     * @param userId
     * @return
     */
    public HttpResult queryById(int userId) {
        HttpResult httpResult = new HttpResult();
        User user = userDao.queryById(userId);
        try {
            if (user == null) {
                httpResult.setTranslation(404, "查找失败", SmartLockEnum.CHECK_ERROR);
                return httpResult;
            } else {
                httpResult.setTranslation(200, "查找成功", SmartLockEnum.CHECK_SUCCESS);
                httpResult.setResult(user);
                JsonObject jsonObject = new Gson().toJsonTree(httpResult).getAsJsonObject();
                System.out.print(jsonObject.toString());
                return httpResult;
            }
        } catch (Exception e) {
            httpResult.setTranslation(400, "操作失败", SmartLockEnum.CHECK_ERROR);
            return httpResult;
        }

    }

    /**
     * app端修改用户信息（自己修改自己的）
     * @param user
     * @return
     */
    public HttpResult updateUserApp(User user) {
        HttpResult httpResult = new HttpResult();
       // User user1 = userDao.queryById(user.getUserId());
        //int higherId = user1.getHigherId();
//        User user2 = new User(user.getUserId(), user.getAccount(), user.getPassword(), user.getUsername(), user.getPhone(),
//                1, user.getType(), user.getHigherId(), user.getManagement());
        System.out.println(user);
        try {
            int a = userDao.userUpdate(user);
            if (a <= 0) {
                httpResult.setTranslation(304, "更新失败", SmartLockEnum.UPDATE_ERROR);
            } else {
                httpResult.setTranslation(200, "更新成功", SmartLockEnum.UPDATE_SUCCESS);
            }
            System.out.println(httpResult);
            return httpResult;
        } catch (Exception e) {
            httpResult.setTranslation(400, "该用户名已被注册", SmartLockEnum.UPDATE_ERROR);
            return httpResult;
        }
    }

}




