package org.snnu.css.web;

import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.User;
import org.snnu.css.enums.SmartLockEnum;
import org.snnu.css.service.LogService;
import org.snnu.css.service.RoomService;
import org.snnu.css.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Thinker on 2017/1/10.
 */
@Controller
@RequestMapping("/appSmartLock")
public class SmartLockAppController extends BaseController {


    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private LogService logService;


    /**
     * 登录操作
     * @author yefan
     * @param
     * @throws IOException
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult login(@RequestParam("UserAccount") String userAccount,@RequestParam("Password")String passWord,@RequestParam("mac")String mac) throws IOException {

        System.out.println("userAccount:"+userAccount + "PassWord:"+passWord);

        HttpResult result = userService.userAppLogin(userAccount, passWord,mac);

        return result;

    }


    /**
     * 房间管理
     * @author yefan
     * @param userId
     */
    @RequestMapping(value = "/room/list", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult roomManage(@RequestParam("userId") int userId) {

        HttpResult result = roomService.queryById(userId);

        return result;

    }


    /**
     * 查询所有历史记录
     * @param userId
     * @author yefan
     */
    @RequestMapping(value = "/log/list", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult logManage(@RequestParam("userId") int userId) {

        HttpResult result = logService.queryAll(userId);

        return result;

    }


    /**
     * 用户管理界面
     *
     * @param userId
     * @throws IOException
     * @author yefan
     */
    @RequestMapping(value = "/user/{userId}/list", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult userManageforJ(@PathVariable("userId") int userId) throws IOException {

        HttpResult result = userService.getByHigherId(userId);

        return result;
    }

    /**
     * 添加用户
     *
     * @param user
     * @author yefan
     */
    @RequestMapping(value = "/user/list/add", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult userManage(@RequestBody User user) {
        HttpResult result = userService.insertUserApp(user.getAccount(), user.getPassword(), user.getUsername(),
                user.getPhone(), user.getType(), user.getHigherId(),user.getManagement());

        if (SmartLockEnum.INSERT_SUCCESS.getState() == result.getState()) {

            HttpResult httpResult = userService.getByHigherId(user.getUserId());

            return httpResult;
        }

        return result;

    }

    /**
     * 修改信息
     *
     * @param user
     * @throws IOException
     * @author yefan
     */
    @RequestMapping(value = "/user/list/edit", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult editUser(@RequestBody User user) throws IOException {

        String[] arr = {user.getManagement()};
        HttpResult result = userService.updateUserApp(user);

//        if (SmartLockEnum.UPDATE_SUCCESS.getState() == result.getState()) {
//
//
//            HttpResult httpResult = userService.getByHigherId(user.getUserId());
//
//            return httpResult;
//        }
        return result;


    }

    /**
     * 删除学生信息
     *
     * @param userId
     * @author yefan
     */
    @RequestMapping(value = "/user/list/stop", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult stopUser(@RequestParam("higherId") int higherId, @RequestParam("userId") int userId) {


        HttpResult result = userService.stopUser(userId);

        return result;

    }

    /**
     * 用户个人设置
     *
     * @param userId
     * @author yefan
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult checkUserInfo(@RequestParam("userId") int userId) throws IOException{

        HttpResult  result = userService.queryById(userId);

        return result;

    }


    /**
     * 查询个人历史记录
     * @param userId
     * @param startTime
     * @param endTime
     * @author yefan
     */
    @RequestMapping(value = "/log/personalList", method = RequestMethod.GET)
    @ResponseBody
    public HttpResult personalLogManage(@RequestParam("userId") int userId, @RequestParam("startTime") String startTime, @RequestParam("endTime") String endTime) {

        System.out.println("personalLogManage ");
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date start = null;
//        Date end = null;
//        try {
//            start = sdf.parse(startTime);
//            end = sdf.parse(endTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println("startTime:" + startTime + "endTime:" + endTime);

        HttpResult result = logService.searchLogByTime(userId,null,null);

        return result;

    }


    /**
     * 锁门(一个或批量)
     * @author yefan
     * @param roomId
     */

    @RequestMapping(value = "/room/lock", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult lock(@RequestParam("userId") int userId,@RequestParam("roomId") String roomId) {
        String [] stringArr= roomId.split(",");

        HttpResult httpResult = roomService.closeByRoomId(userId,stringArr);

        return httpResult;
    }


    /**
     * 开锁(一个或批量)
     * @author yefan
     * @param roomId
     */
    @RequestMapping(value = "/room/unlock", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult unLock(@RequestParam("userId") int userId,@RequestParam( "roomId") String roomId) {

        String [] stringArr= roomId.split(",");

        HttpResult httpResult = roomService.openByRoomId(userId,stringArr);

        return httpResult;
    }

}









