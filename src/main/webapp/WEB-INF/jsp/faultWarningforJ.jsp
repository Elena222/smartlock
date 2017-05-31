<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: Thinker
  Date: 2017/1/9
  Time: 16:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html  id="m-nav">

<head>
    <meta charset="UTF-8">
    <title>故障警示（教研室负责人）</title>

    <link rel="stylesheet" href="/src/bootstrap/css/bootstrap.min.css">
    <script src="/src/bootstrap/js/jquery.min.js"></script>
    <link rel="stylesheet" href="/src/lock-plug/date/css/jquery-ui-1.9.2.custom.css" type="text/css">
    <!--表格-->
    <link rel="stylesheet" href="/src/css/panel.css">
    <link rel="stylesheet" href="/src/css/table.css">
    <link rel="stylesheet" href="/src/assets/css/amazeui.min.css">
    <link rel="stylesheet" href="/src/css/m-index.css">

    <link rel="stylesheet" href="/src/css/faultWarning.css"/>
    <!--时间-->
    <link rel="stylesheet" href="/src/jquery-ui-1.11.4.custom/jquery-ui.css">

    <script>
        $(function() {
            $( ".datepicker" ).datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat:"yy-mm-dd"
            });

        });

    </script>


</head>
<body>

<!--此处显示上方导航栏-->
<div class="topNav">
    <ul>
        <li class="logo"><p>smart lock</p></li>
        <li class="login"><a href="#"><p>登录</p></a></li>
        <li class="login"><a href="#"><p>注册</p></a></li>
    </ul>
</div>

<div class="content">
    <!--此处显示左边侧栏-->
    <div class="leftNav">
        <ul>
            <li >
                ui
            </li>
            <li class="leval">
                <a href="roomStateforJ.html"><img src="src/images/lock.png" alt=""><p>查看房间状态</p></a>
            </li>

            <li class="leval">
                <a href="#"><img src="src/images/lock.png" alt=""><p>用户管理</p></a>
            </li>

            <li class="leval">
                <a href="#"><img src="src/images/lock.png" alt=""><p>通告管理</p></a>
            </li>

            <li class="leval">
                <a href="#"><img src="src/images/lock.png" alt=""><p>日志管理</p></a>
            </li>

            <li class="leval">
                <a href="#"><img src="src/images/lock.png" alt=""><p>故障警示</p></a>
            </li>

        </ul>
    </div>

    <!--此处显示页面内容-->
    <div class="main-content" id="c-faultWarning" >
        <div class="main">
            <div class="date">
                <table class="table c-twotime">
                    <thead>
                    <tr>
                        <th style="border-right: 2px solid #ddd">起始时间:
                            <input type="text" class="datepicker" data-format="yyyy-MM-dd ">
                        </th>
                        <th>结束时间:
                            <input type="text" class="datepicker">
                        </th>
                    </tr>
                    </thead>
                </table>
            </div>

            <!--在此处修改面板的主色调-->
            <div class="panel panel-default panel-warning now-table">
                <div class="panel-header" style="font-size: 18px;letter-spacing: 1px">
                    异常信息
                </div>
                <div class="panel-body" id="m-table">
                    <table>
                        <thead>

                        <th>故障描述</th>
                        <th>房间号</th>
                        <th>发生时间</th>
                        <th>是否处理</th>
                        <th>处理时间</th>
                        </thead>

                        <tbody>
                            <c:forEach var="sl" items="${list}">
                                <tr>
                                    <td>${s1.errorDescription}</td>
                                    <td>${sl.roomNumber}</td>
                                    <td>${sl.happenTime}</td>
                                    <td>${s1.operateOrNot}</td>
                                    <td>
                                        <label class="radio-inline">
                                            <input type="radio" name="inlineRadioOptions" class="inlineRadio1" value="option1"> 是
                                        </label>
                                        <label class="radio-inline">
                                            <input type="radio" name="inlineRadioOptions" class="inlineRadio2" value="option2"> 否
                                        </label>
                                    </td>
                                    <td>${s1.solveTime}</td>
                                </tr>
                            </c:forEach>

                        </tbody>

                    </table>
                </div>
            </div>
        </div>

    </div>

</div>
</body>

<script>
    $(".leftNav").on("click",".leval",function(){
        $(this).addClass("current")
                .siblings().removeClass("current");

    });

</script>

<script src="src/assets/js/jquery.min.js"></script>
<script src="src/assets/js/amazeui.min.js"></script>
<script src="src/jquery-ui-1.11.4.custom/jquery-ui.js"></script>


</html>