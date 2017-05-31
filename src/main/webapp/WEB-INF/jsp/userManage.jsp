<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thinker
  Date: 2017/1/7
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN" id="m-nav">
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/m-index.css">
<%--<link rel="stylesheet" href="/src/h-ui/css/H-ui.min.css">--%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/popAlert.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/panel.css">
<script src="${pageContext.request.contextPath}/src/bootstrap/js/jquery.min.js"></script>
<body>

<!--此处显示页面内容，right-content是除了上方和左侧导航栏之外的内容区,同时他也是userManageIndex页面中的main-content的孩子-->
    <div class="right-content" id="userContent">
        <input type="hidden" value="${cList[0].buildingName}" id="buildingsExitJudge">
        <button class="btn btn-success m-add" id="m-add" data-type="${userType.type}">添加</button>
        <button class="btn btn-danger m-stop" id="m-stop">停用</button>
        <button class="btn btn-default-success m-reload" id="m-reload">刷新</button>


        <input type="text" class="search">&nbsp;
        <button class="btn btn-group-vertical" id="search">查找</button>

    <div style="clear: both" class="panel panel-default panel-warning">
        <div class="panel-header" style="font-size: 15px;font-family: 微软雅黑;letter-spacing: 1px">
            教研室成员表：
        </div>
        <div class="panel-body" id="m-table">
            <form action="${pageContext.request.contextPath}/webSmartLock/user/list/delet" method="post">
                <table>
                    <thead>
                    <tr>
                        <th class="first"></th>
                        <th>登录名</th>
                        <th>姓名</th>
                        <th style="display: none">密码</th>
                        <th>电话</th>
                        <th>角色</th>
                        <th>管理范围</th>
                    </tr>
                    </thead>

                    <tbody id="tbody">

                    <c:forEach var = "td" items="${list}">
                        <tr class="">
                            <td class="first">
                                <input type="checkbox" name="userId" value="${td.userId}">
                            </td>
                            <td>${td.account}</td>    <%--登录名--%>
                            <td>${td.username}</td>   <%--姓名--%>
                            <td style="display: none">${td.password}</td>      <%--密码--%>
                            <td>${td.phone}</td>      <%--电话--%>
                            <td>${td.type}</td>       <%--角色--%>
                            <td>${td.management}</td> <%--管理范围--%>

                            <%--用户id:设置为隐藏，在前端页面不显示，但是需要传递给后端进行处理--%>
                            <td style="display: none">${td.userId}</td>

                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <%--——————————————————————————删除用户————————————————————————————--%>
                <div class="m-popPanel" id="delete">
                    <div class="delete-pop">
                        <div class="pop-title">
                            <p>删除用户？</p>
                        </div>

                        <div class="pop-body">
                            <button type="submit" class="button btn btn-danger">确定</button>
                            <button type="button" class="button btn btn-primary cancel">取消</button>
                        </div>
                    </div>
                </div>

            </form>
        </div>
    </div>

    <%--——————————————————————————创建新用户————————————————————————————--%>
    <div class="m-popPanel"  id="create">
        <form action="${pageContext.request.contextPath}/webSmartLock/user/list/create" method="post">
            <div class="pop-title">
                <p>用户信息</p>
                <span><img class="cancel" src="${pageContext.request.contextPath}/src/images/x.png" alt=""></span>
            </div>

            <div class="pop-body">
                <div class="input">
                    <span>登录名：</span><input type="text" name="account" class="account" placeholder="工号:201416497">
                </div>
                <div class="input">
                    <span>姓名：</span><input type="text" name="username" class="username" placeholder="孟丽媛">
                </div>
                <div class="input">
                    <span>密码：</span><input type="password" name="password" class="password" placeholder="7890789yu..">
                </div>

                <div class="input">
                    <span>电话：</span><input type="text" name="phone" class="phone" placeholder="13168800987">
                </div>
                <div class="input">
                    <span>角色：</span>
                    <select name="type" class="type" id="l-y-type">
                        <c:forEach var="tl" items="${tList}">
                            <option value="${tl}">${tl}</option>
                        </c:forEach>
                    </select>


                </div>

                <c:if test="${userType.type=='总负责人'||userType.type=='楼主'}">

                    <div class="input" id="l-management">

                        <span>管理范围：</span>
                        <c:if test="${userType.type=='楼主'}">
                            <select name="" class="management">
                                <option value="${userType.management}">${userType.management}</option>
                            </select>
                        </c:if>

                        <c:if test="${userType.type=='总负责人'}">
                            <select name="" class="management">
                                <c:forEach var="bl" items="${cList}">
                                    <option value="${bl.buildingName}">${bl.buildingName}</option>
                                </c:forEach>
                            </select>
                        </c:if>

                        <input type="text" name="" id="roomIdArray-lou" style="display: none">
                    </div>

                    <%--<div class="input" id="y-management" style="display: none">--%>
                        <%--<span>管理范围：</span>--%>
                        <%--<select name="" class="management">--%>
                            <%--<option value="文学院">文学院</option>--%>
                            <%--<option value="计科院">计科院</option>--%>
                            <%--<option value="数学学院">数学学院</option>--%>
                            <%--<option value="美术学院">美术学院</option>--%>
                            <%--<option value="历史学院">历史学院</option>--%>
                            <%--<option value="化学学院">化学学院</option>--%>
                            <%--<option value="体育学院">体育学院</option>--%>
                            <%--<option value="音乐学院">音乐学院</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>

                    <div class="input" id="staff-distribution" style="display: none">
                        <span data-type="院主" class="staffrooms">教研室分配：</span>
                        <select class="management"></select>
                        <div class="staff-distr" style="display: none">
                            <c:forEach var="room" items="${staffRooms}">
                                <li><input type="checkbox" value="${room.roomId}" data-id="${room.roomId}"><span>${room.roomId}</span></li>
                            </c:forEach>
                        </div>

                        <input type="text" name="" id="roomIdArray" style="display: none">

                    </div>

                </c:if>

                <c:if test="${userType.type=='院主'||userType.type=='教研负责人'}">
                    <div class="input" id="staff-distribution">
                        <span data-type="教研负责人" class="staffrooms">教研室分配：</span>
                        <input type="hidden" name="management" class="management" id="yuan-management">

                        <select class="management"></select>
                        <div class="staff-distr" style="display: none">
                            <c:forEach var="room" items="${rList}">
                                <li><input type="checkbox" value="${room.roomId}" data-id="${room.roomId}"><span>${room.roomId}</span></li>
                            </c:forEach>
                        </div>

                        <input type="text" name="roomIds" id="roomIdArray-yuan" style="display: none">
                    </div>
                </c:if>
                <%--用户id:设置为隐藏，在前端页面不显示，但是需要传递给后端进行处理--%>
                <div class="input" style="display: none">
                    <span>用户id：</span><input type="hidden" name="userId" class="userId">
                </div>

                <button type="submit" class="button btn btn-primary submit-create">确定</button>

            </div>

        </form>
    </div>

</div>

</body>

<script src="${pageContext.request.contextPath}/src/h-ui/js/H-ui.min.js"></script>

<script src="${pageContext.request.contextPath}/src/js/smartLock.js"></script>
</html>