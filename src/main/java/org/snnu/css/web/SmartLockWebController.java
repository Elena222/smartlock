package org.snnu.css.web;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.snnu.css.dto.HttpResult;
import org.snnu.css.entity.*;
import org.snnu.css.service.BuildingService;
import org.snnu.css.service.LogService;
import org.snnu.css.service.RoomService;
import org.snnu.css.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thinker on 2017/1/9.
 */

@Controller
@RequestMapping("/webSmartLock")
public class SmartLockWebController extends  BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private LogService logService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private RoomService roomService;

    /**
     * 登陆
     *
     * @return
     * @auther liyuan
     */
    @RequestMapping(value = "/user/loginPage", method = RequestMethod.GET)
    public String loginIndex() {
        return "login";
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String login(User user, HttpSession httpSession, Model model) {
        HttpResult result = userService.userPCLogin(user.getAccount(), user.getPassword());

        if (result.getResult() == null) {

            return "login";

        } else {
                if(result.getError_code()==404){
                    return "login";
                }
                else{
                    UserReturn use = (UserReturn) result.getResult();

                    httpSession.setAttribute("user", use);

                    model.addAttribute("userType", use.getType());

                    return "lockIndex";
                }
        }
    }

    /**
     * 注销
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/user/logout", method = RequestMethod.POST)
    public String logout(HttpSession httpSession) {
        httpSession.setAttribute("user",null);
        return "redirect:/webSmartLock/user/loginPage";
    }


    /**
     * 用户管理
     *
     * @param model
     * @param httpSession
     * @return
     * @author liyuan
     */
    @RequestMapping(value = "/user/id/list", method = RequestMethod.GET)

    public String userManage(Model model, HttpSession httpSession) {

        UserReturn user = (UserReturn) httpSession.getAttribute("user");

        Map<String,List> map = new HashMap<String, List>();

        HttpResult result = userService.getByHigherId(user.getUserId());

        List<User> userList = (List<User>) result.getResult();//获得管理人员列表use in all

        HttpResult result1 = roomService.queryById(user.getUserId());

        List<Room> roomList = (List<Room>) result1.getResult();//根据用户id获得管理房间范围列表use in 院主

        HttpResult result2 = userService.queryUserTypeById(user.getUserId());

        List<String> tList = (List<String>)result2.getResult();//获得管理下级用户类型列表use

        HttpResult result3 = buildingService.queryAllById();//获得所有楼名use

        List<Building> builingList = (List<Building>) result3.getResult();

        HttpResult result6=buildingService.queryBuildingLimitChecked();

        List<Building> buildings= (List<Building>) result6.getResult();//获得未被分配楼主的教学楼

        HttpResult result4 = userService.queryById(user.getUserId());

        User userType = (User) result4.getResult();//登录者的类型use

        model.addAttribute("userType",userType);

        HttpResult result5 = roomService.querySpecialRoomById(user.getUserId());//根据楼主id获得这个楼的所有教研室

        List<Room> staffRoomsList = (List<Room>) result5.getResult();

        map.put("tList",tList);
        map.put("list",userList);
        map.put("rList",roomList);
        map.put("bList",builingList);
        map.put("staffRooms",staffRoomsList);
        map.put("cList",buildings);

        model.addAllAttributes(map);

        return "userManage";

    }

    /**
     * 创建用户
     *
     * @param account
     * @param password
     * @param username
     * @param phone
     * @param type
     * @param management
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/user/list/create", method = RequestMethod.POST)

    public String createUser(@RequestParam("account") String account,
                             @RequestParam("password") String password,
                             @RequestParam("username") String username,
                             @RequestParam("phone") String phone,
                             @RequestParam("type") String type,
                             @RequestParam("management") String management,
                             @RequestParam("roomIds") String roomIds,//数组--房间id
                             HttpSession httpSession) {

        String[] roomIdsArr=new String[]{};
        if(roomIds.length()==0){

        }else {
            roomIdsArr = roomIds.split(",");
        }
        UserReturn user = (UserReturn) httpSession.getAttribute("user");

        userService.insertUser(account, password, username, phone, type, user.getUserId(), management,roomIdsArr);

        return "redirect:/webSmartLock/user/id/list";

    }

    /**
     * 停用用户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/user/list/delet", method = RequestMethod.POST)

    public String stopUser(@RequestParam("userId") int userId) {

        userService.stopUser(userId);

        return "redirect:/webSmartLock/user/id/list";
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
     * @param httpSession
     * @param management
     * @return
     */
    @RequestMapping(value = "/user/list/edit", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String editUser(@RequestParam("userId") int userId,
                           @RequestParam("account") String account,
                           @RequestParam("password") String password,
                           @RequestParam("username") String username,
                           @RequestParam("phone") String phone,
                           @RequestParam("type") String type,
                           @RequestParam("roomIds") String roomIds,//数组--房间id
                           HttpSession httpSession,
                           @RequestParam("management") String management) {// HttpSession httpSession, HttpServletRequest request){

//        try {
//            request.setCharacterEncoding("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        String[] roomIdsArr = roomIds.split(",");

        UserReturn user = (UserReturn) httpSession.getAttribute("user");

        userService.updateUser(userId, account, password, username, phone, type,
                user.getUserId(), management,roomIdsArr);

        return "redirect:/webSmartLock/user/id/list";
    }


    /**
     * 房间管理
     *
     * @param model
     * @param httpSession
     * @return
     * @author liyuan
     */
    @RequestMapping(value = "/room/list", method = RequestMethod.GET)
    public String roomManage(Model model, HttpSession httpSession) {

        UserReturn user = (UserReturn) httpSession.getAttribute("user");

        Map<String,List> map = new HashMap<String, List>();

        HttpResult result = roomService.queryById(user.getUserId());

        List<Room> roomList = (List<Room>) result.getResult();

        map.put("list",roomList);

        HttpResult result1 = buildingService.queryAllById();

        List<Building> buildingList = (List<Building>) result1.getResult();

        map.put("bList",buildingList);

        HttpResult result2 = roomService.queryRoomTypeById(user.getUserId());

        List rList = (List)result2.getResult();

        map.put("rList",rList);

        HttpResult result3 = userService.queryById(user.getUserId());

        User userType = (User) result3.getResult();//登录者的类型use

        model.addAttribute("userType",userType);

        HttpResult result4 = userService.queryById(user.getUserId());

        User u = (User)result4.getResult();//通过登录者id获得用户信息（包括管理范围）

        model.addAttribute("User",u);

        String type = user.getType();

        if(type.equals("楼主")){

            HttpResult result5 = buildingService.queryPartCountByName(u.getManagement());//通过管理范围（楼名）获得段数

            int partCount = (Integer) result5.getResult();

            model.addAttribute("partCount",partCount);
        }

        model.addAllAttributes(map);

        return "roomManage";

    }

    /**
     * 添加房间
     *
     * @param roomIndex
     * @param roomPart
     * @param buildingName
     * @param roomType
     * @param roomDes
     * @return
     */
    @RequestMapping(value = "/room/list/add", method = RequestMethod.POST)
    public String addRoom(@RequestParam("roomIndex") int roomIndex,
                          @RequestParam("roomPart") int roomPart,
                          @RequestParam("buildingName") String buildingName,
                          @RequestParam("roomType") String roomType,
                          @RequestParam("roomDes") String roomDes) {

        roomService.insertRoom(roomIndex, roomPart, buildingName,roomType, roomDes);

        return "redirect:/webSmartLock/room/list";
    }

    /**
     * 修改房间信息
     *
     * @param roomId
     * @param roomIndex
     * @param roomPart
     * @param buildingName
     * @param roomType
     * @param roomDes
     * @return
     */
    @RequestMapping(value = "/room/list/edit", method = RequestMethod.POST)
    public String editRoom(@RequestParam("roomId") String roomId,
                           @RequestParam("roomIndex") int roomIndex,
                           @RequestParam("roomPart") int roomPart,
                           @RequestParam("buildingName") String buildingName,
                           @RequestParam("roomType") String roomType,
                           @RequestParam("roomDes") String roomDes) {

        roomService.updateRoom(roomId, roomIndex, roomPart,
                buildingName,roomType, roomDes);

        return "redirect:/webSmartLock/room/list";
    }

    /**
     * 删除房间
     *
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/room/list/delete", method = RequestMethod.POST)
    public String deleteRoom(@RequestParam("roomId") String roomId) {

        roomService.roomDeleteById(roomId);

        return "redirect:/webSmartLock/room/list";
    }

    /**
     * 教学楼管理
     *
     * @param model
     * @return
     * @author liyuan
     */
    @RequestMapping(value = "/building/list", method = RequestMethod.GET)
    public String buildingManage(Model model) {

        HttpResult result = buildingService.queryAllById();

        List<Building> buildingList = (List<Building>) result.getResult();

        model.addAttribute("list", buildingList);

        return "buildingManage";
    }

    /**
     * 添加教学楼
     * @param buildingName
     * @param roomcount
     * @param floorcount
     * @param partcount
     * @param buildingDes
     * @return
     */
    @RequestMapping(value = "/building/list/add", method = RequestMethod.POST)

    public String addBuilding(@RequestParam("buildingName") String buildingName,
                              @RequestParam("roomcount") int roomcount,
                              @RequestParam("floorcount") int floorcount,
                              @RequestParam("partcount") int partcount,
                              @RequestParam("buildingDes") String buildingDes) {
        buildingService.insertBuilding(buildingName, roomcount,
                floorcount, partcount, buildingDes);

        return "redirect:/webSmartLock/building/list";
    }

    /**
     * 修改教学楼信息
     *
     * @param buildingId
     * @param buildingName
     * @param roomcount
     * @param floorcount
     * @param partcount
     * @param buildingDes
     * @return
     */
    @RequestMapping(value = "/building/list/edit", method = RequestMethod.POST)

    public String editBuilding(@RequestParam("buildingId") int buildingId,
                               @RequestParam("buildingName") String buildingName,
                               @RequestParam("roomcount") int roomcount,
                               @RequestParam("floorcount") int floorcount,
                               @RequestParam("partcount") int partcount,
                               @RequestParam("buildingDes") String buildingDes) {

        buildingService.buildingUpdate(buildingId, buildingName,
                roomcount, floorcount, partcount, buildingDes);

        return "redirect:/webSmartLock/building/list";
    }

    /**
     * 删除教学楼
     * @param buildingId
     * @return
     */
    @RequestMapping(value = "/building/list/delete", method = RequestMethod.POST)

    public String deleteBuilding(@RequestParam("buildingId") int buildingId) {

        buildingService.deleteById(buildingId);

        return "redirect:/webSmartLock/building/list";
    }

    /**
     * 日志管理
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/log/list", method = RequestMethod.GET)

    public String logManage(Model model, HttpSession httpSession) {

        UserReturn user = (UserReturn) httpSession.getAttribute("user");
        //查询所管理人的全部日志
        HttpResult result = logService.queryAll(user.getUserId());

        List<Log> logmassage = (List<Log>) result.getResult();

        model.addAttribute("list", logmassage);

        return "logManage";
    }

    /**
     * 房间状态（大爷、教研负责人）
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/room/state", method = RequestMethod.GET)
    public String checkAllRoomState(Model model, HttpSession httpSession) {

        UserReturn user = (UserReturn) httpSession.getAttribute("user");

        HttpResult result = roomService.queryById(user.getUserId());

        List<Room> roomList = (List<Room>) result.getResult();

        model.addAttribute("list", roomList);

        HttpResult result1 = userService.queryById(user.getUserId());

        User buildingName = (User) result1.getResult();

        model.addAttribute("buildingName",buildingName);

        String type = user.getType();

        if(type.equals("教研负责人")){

            return "roomStateforJ";
        }

        else
            return "classroomOpen";

    }


    /**
     * 开锁（批量）
     * @param allRoomId
     * @return
     */
    @RequestMapping(value = "/room/unLock", method = RequestMethod.POST)
    public String unLock(@RequestParam("allRoomId") String allRoomId,
                         HttpSession httpSession) {

        UserReturn user = (UserReturn)httpSession.getAttribute("user");

        String [] stringArr= allRoomId.split(",");

        roomService.openByRoomId(user.getUserId(),stringArr);

        return "redirect:/webSmartLock/room/state";
    }


    /**
     * 关锁（批量）
     * @param allRoomId
     * @return
     */
    @RequestMapping(value="/room/lock",method=RequestMethod.POST)
    public String lock(@RequestParam("allRoomId") String allRoomId
                        ,HttpSession httpSession){

        UserReturn user = (UserReturn)httpSession.getAttribute("user");

        String [] stringArr= allRoomId.split(",");

        roomService.closeByRoomId(user.getUserId(),stringArr);
        return "redirect:/webSmartLock/room/state";
    }

    /**
     * 设置时间提醒
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/room/remind",method = RequestMethod.POST)
    public String TimeRemind(@RequestParam("roomId") String roomId,@RequestParam("Date") Date date){

        //TODO

        return "redirect:/webSmartLock/room/state";
    }

}

