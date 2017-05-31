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
    <title>房间管理</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/m-index.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/unlogin.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/panel.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/popAlert.css">

<script src="${pageContext.request.contextPath}/src/bootstrap/js/jquery.min.js"></script>
<body>

<!--此处显示页面内容，right-content是除了上方和左侧导航栏之外的内容区,同时他也是userManageIndex页面中的main-content的孩子-->
<div class="right-content" id="roomContent">
    <c:if test="${userType.type!='院主'}">
        <button class="btn btn-success m-add" id="m-add">添加</button>
        <button class="btn btn-danger m-stop" id="m-stop">停用</button>
        <button class="btn btn-primary m-revise" id="m-revise">修改</button>
    </c:if>
    <button class="btn btn-default-success m-reload" id="m-reload" style="margin-bottom: 10px">刷新</button>

    <input type="text" class="search" style="margin-bottom: 10px">&nbsp;
    <button class="btn btn-group-vertical" id="search" style="margin-bottom: 10px">查找</button>

    <div style="clear: both" class="panel panel-default panel-success">
        <div class="panel-header" style="font-size: 15px;font-family: 微软雅黑;letter-spacing: 1px">
            房间管理：
        </div>
        <div class="panel-body" id="m-table">
            <form action="${pageContext.request.contextPath}/webSmartLock/room/list/delete" method="post">
                <table>
                    <thead>
                    <tr>
                        <th class="first"></th>
                        <th>房间号</th>
                        <th>房间类型</th>
                        <th>所在教学楼</th>
                        <th>所在楼段</th>
                        <th style="display: none">房间描述</th>
                        <%--<th style="display: none">房间ID</th>--%>
                    </tr>
                    </thead>

                    <tbody id="tbody">

                    <c:forEach var = "td" items="${list}">
                        <tr>
                            <td class="first">
                                <input type="checkbox" name="roomId" value="${td.roomId}">
                            </td>
                            <td>${td.roomIndex}</td>
                            <td>${td.roomType}</td>
                            <td>${td.buildingName}</td>
                            <td>${td.roomPart}</td>
                            <td style="display: none">${td.roomDes}</td>
                            <td style="display: none">${td.roomId}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <%--——————————————————————————删除房间————————————————————————————--%>
                <div class="m-popPanel" id="delete">
                    <div class="delete-pop">
                        <div class="pop-title">
                            <p>删除房间？</p>
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

    <%--<div class="page">--%>
        <%--<p>本页共显示10条记录</p>--%>
        <%--<button class="btn btn-default">下一页</button>--%>
        <%--<button class="btn btn-default">上一页</button>--%>
    <%--</div>--%>



    <%--——————————————————————————添加房间————————————————————————————--%>
    <div class="m-popPanel"  id="create">
        <form action="${pageContext.request.contextPath}/../../../webSmartLock/room/list/add" method="post">
            <div class="pop-title">
                <p>房间信息</p>
                <span><img class="cancel" src="${pageContext.request.contextPath}/src/images/x.png" alt=""></span>
            </div>

            <div class="pop-body">
                <div class="input">
                    <span>房间号：</span><input type="text" name="roomIndex" class="roomIndex" placeholder="121">
                </div>

                <%--此处从后端拿数据，获取登陆者管理的房间类型，方法未写--%>
                <div class="input">
                    <span>房间类型：</span>
                    <select name="roomType" class="roomType">
                        <%--<c:forEach var="rl" items="${rList}">--%>
                            <%--<option value="${rl}">${rl}</option>--%>
                        <%--</c:forEach>--%>
                        <option value="教室">教室</option>
                        <option value="教研室">教研室</option>
                    </select>
                </div>

                <div class="input">
                    <span>所在楼：</span>
                    <select name="buildingName" class="buildingName" id="buildings" data-duan="${partCount}">
                        <option value="${User.management}">${User.management}</option>
                    </select>
                    <%--<input type="text" name="buildingName" class="buildingName" value="${User.management}">--%>
                </div>

                <div class="input">
                    <span>所在楼段：</span>
                    <select name="roomPart" class="roomPart" id="duan">
                        <option></option>
                    </select>
                    <%--<input type="text" name="roomPart" class="roomPart" value="${partCount}">--%>
                </div>

                <div class="input">
                    <span>房间描述：</span><textarea name="roomDes" class="roomDes" placeholder="121是普通教室"></textarea>
                </div>
                <%--房间id:设置为隐藏，在前端页面不显示，但是需要传递给后端进行处理--%>
                <%--<div class="input" style="display: none">--%>
                    <%--<span>房间ID：</span><input type="hidden" name="roomId" class="roomId">--%>
                <%--</div>--%>

                <button type="submit" class="button btn btn-primary submit-create">确定</button>

            </div>

        </form>
    </div>



    <%--——————————————————————————修改房间信息————————————————————————————--%>
    <div class="m-popPanel"  id="edit">
        <form action="${pageContext.request.contextPath}/webSmartLock/room/list/edit" method="post">
            <div class="pop-title">
                <p>用户信息</p>
                <span><img class="cancel" src="${pageContext.request.contextPath}/src/images/x.png" alt=""></span>
            </div>

            <div class="pop-body">
                <%--此处从后端拿数据，获取登陆者管理的房间类型，方法未写--%>
                    <div class="input">
                        <span>房间号：</span><input type="text" name="roomIndex" class="roomIndex" readonly>
                    </div>
                <div class="input">
                    <span>房间类型：</span>
                    <select name="roomType" class="roomType">
                        <%--<c:forEach var="rl" items="${rList}">--%>
                            <%--<option value="${rl}">${rl}</option>--%>
                        <%--</c:forEach>--%>
                        <option value="教室">教室</option>
                        <option value="教研室">教研室</option>
                    </select>
                </div>

                <div class="input">
                    <span>所在楼：</span><input type="text" name="buildingName" class="buildingName" readonly>
                </div>

                <div class="input">
                    <span>所在楼段：</span><input type="text" name="roomPart" class="roomPart" readonly>
                </div>

                <div class="input">
                    <span>房间描述：</span><textarea name="roomDes" class="roomDes"></textarea>
                </div>
                <%--房间id:设置为隐藏，在前端页面不显示，但是需要传递给后端进行处理--%>
                <div class="input" style="display: none">
                    <span>房间ID：</span><input type="hidden" name="roomId" class="roomId">
                </div>

                <button type="submit" class="button btn btn-primary submit-edit">确定</button>


            </div>

        </form>
    </div>

</div>

</body>
<script src="${pageContext.request.contextPath}/src/h-ui/js/H-ui.min.js"></script>

<script src="${pageContext.request.contextPath}/src/js/smartLock.js"></script>

</html>