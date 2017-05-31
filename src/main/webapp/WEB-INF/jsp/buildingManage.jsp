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

<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/popAlert.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/panel.css">
<script src="${pageContext.request.contextPath}/src/bootstrap/js/jquery.min.js"></script>
<body>

<!--此处显示页面内容，right-content是除了上方和左侧导航栏之外的内容区,同时他也是userManageIndex页面中的main-content的孩子-->
<div class="right-content" id="buildingContent">
    <button class="btn btn-success m-add" id="m-add">添加</button>
    <button class="btn btn-danger m-stop" id="m-stop">停用</button>
    <%--<button class="btn btn-primary m-revise" id="m-revise">修改</button>--%>
    <button class="btn btn-default-success m-reload" id="m-reload">刷新</button>

    <input type="text" class="search">&nbsp;
    <button class="btn btn-group-vertical" id="search">查找</button>

    <div style="clear: both" class="panel panel-default panel-success">
        <div class="panel-header" style="font-size: 15px;font-family: 微软雅黑;letter-spacing: 1px">
            教学楼管理：
        </div>
        <div class="panel-body" id="m-table">
            <form action="${pageContext.request.contextPath}/webSmartLock/building/list/delete" method="post">
                <table>
                    <thead>
                    <tr>
                        <th class="first"></th>
                        <th>教学楼名</th>
                        <th>段数</th>
                        <th>层数</th>
                        <th>房间数</th>
                        <%--<th>负责人</th>--%>
                        <%--<th style="display: none">教学楼描述</th>--%>
                        <%--<th style="display: none">教学楼ID</th>--%>
                    </tr>
                    </thead>

                    <tbody id="tbody">

                    <c:forEach var = "td" items="${list}">
                        <tr>
                            <td class="first">
                                <input type="checkbox" name="buildingId"  value="${td.buildingId}" class="check_building">
                            </td>
                            <td>${td.buildingName}</td>
                            <td>${td.partcount}</td>
                            <td>${td.floorcount}</td>
                            <td>${td.roomcount}</td>
                            <td style="display: none">${td.buildingDes}</td>
                            <td style="display: none">${td.buildingId}</td>
                        </tr>
                    </c:forEach>

                    </tbody>
                </table>

                <%--——————————————————————————删除教学楼————————————————————————————--%>
                <div class="m-popPanel" id="delete">
                    <div class="delete-pop">
                        <div class="pop-title">
                            <p>删除教学楼？</p>
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



    <%--——————————————————————————添加教学楼————————————————————————————--%>
    <div class="m-popPanel"  id="create">
        <form action="${pageContext.request.contextPath}/webSmartLock/building/list/add" method="post">
            <div class="pop-title">
                <p>教学楼信息</p>
                <span><img class="cancel" src="${pageContext.request.contextPath}/src/images/x.png" alt=""></span>
            </div>

            <div class="pop-body">
                <div class="input">
                    <span>楼名：</span><input type="text" name="buildingName" class="buildingName" placeholder="文津楼">
                </div>

                <div class="input">
                    <span>段数：</span>
                    <%--<input type="text" name="partcount" class="partcount">--%>
                    <select name="partcount" class="partcount">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>

                <div class="input">
                    <span>层数：</span>
                    <select name="floorcount" class="floorcount">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                    <%--<input type="text" name="floorcount" class="floorcount">--%>
                </div>

                <div class="input">
                    <span>房间数：</span><input type="text" name="roomcount" class="roomcount" placeholder="20">
                </div>

                <div class="input">
                    <span>教学楼描述：</span><textarea name="buildingDes" class="buildingDes" placeholder="文津楼是......"></textarea>
                </div>

                <div class="input" >
                    <input type="hidden" name="buildingId" class="buildingId">
                </div>

                <div class="input" >
                    <p style="color:blue;">*房间数是指每段每层的房间数</p>
                </div>

                <button type="submit" class="button btn btn-primary submit-create">确定</button>

            </div>

        </form>
    </div>



    <%--——————————————————————————修改教学楼信息————————————————————————————--%>
    <div class="m-popPanel"  id="edit">
        <form action="${pageContext.request.contextPath}/webSmartLock/building/list/edit" method="post">
            <div class="pop-title">
                <p>用户信息</p>
                <span><img class="cancel" src="${pageContext.request.contextPath}/src/images/x.png" alt=""></span>
            </div>

            <div class="pop-body">
                <div class="input">
                    <span>楼名：</span><input type="text" name="buildingName" class="buildingName">
                </div>

                <div class="input">
                    <span>段数：</span><input type="text" name="partcount" class="partcount">
                </div>

                <div class="input">
                    <span>层数：</span><input type="text" name="floorcount" class="floorcount">
                </div>

                <div class="input">
                    <span>房间数：</span><input type="text" name="roomcount" class="roomcount">
                </div>

                <div class="input">
                    <span>教学楼描述：</span><textarea name="buildingDes" class="buildingDes"></textarea>
                </div>

                <div class="input" >
                    <input type="hidden" name="buildingId" class="buildingId">
                </div>

                <div class="input" >
                    <p style="color:blue;">*房间数是指每段每层的房间数</p>
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