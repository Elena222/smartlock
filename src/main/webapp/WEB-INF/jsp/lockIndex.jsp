<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thinker
  Date: 2017/2/18
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html id="m-nav">
<head>
    <title>用户管理首页</title>
</head>

<link rel="stylesheet" href="${pageContext.request.contextPath}/src/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/m-index.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/unlogin.css">

<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/panel.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/unlogin.css">
<script src="${pageContext.request.contextPath}/src/bootstrap/js/jquery.min.js"></script>

<body>
<c:choose>
    <c:when test="${userType!='教研人员'||userType!='教研负责人'}">
        <!--此处显示上方导航栏-->
        <div class="topNav">
            <ul>
                <li class="logo"><p>smart lock</p></li>
                <!--<li class="login"><a href="#"><p>登陆</p></a></li>-->
                <li class="login">
                    <a onclick="return confirm('您确定要退出系统吗？');" style="float: right">
                        <form action=${pageContext.request.contextPath}+"../../webSmartLock/user/logout" method="post">
                            <input type="submit" value="注销">
                        </form>
                    </a>
                    <p class="currentUser">
                        当前用户：${user.account} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </p>

                </li>
            </ul>
        </div>

        <%--content是处理上方导航栏之外的部分--%>
        <div class="content">
            <!--此处显示左边侧栏-->
            <div class="leftNav">
                <ul>
                    <li>
                        <br/>
                    </li>

                    <c:if test="${userType=='总负责人'}">
                    <li class="leval" data-src="${pageContext.request.contextPath}../building/list">
                        <img src="${pageContext.request.contextPath}/src/images/buildingadmin.png" alt=""><p>教学楼管理</p>
                    </li>
                    <li class="active leval" data-src="${pageContext.request.contextPath}../user/id/list">
                        <img src="${pageContext.request.contextPath}/src/images/user.png" alt=""><p>用户管理</p>
                    </li>

                    <li class="leval" data-src="${pageContext.request.contextPath}../log/list">
                        <img src="${pageContext.request.contextPath}/src/images/logger.png" alt=""><p>日志管理</p>
                        </c:if>

                        <c:if test="${userType=='楼主'}">
                    <li class="leval" data-src="${pageContext.request.contextPath}../room/list">
                        <img src="${pageContext.request.contextPath}/src/images/buildingadmin.png" alt=""><p>房间管理</p>
                    </li>
                    <li class="active leval" data-src="${pageContext.request.contextPath}../user/id/list">
                        <img src="${pageContext.request.contextPath}/src/images/user.png" alt=""><p>用户管理</p>
                    </li>

                    <li class="leval" data-src="${pageContext.request.contextPath}../log/list">
                        <img src="${pageContext.request.contextPath}/src/images/logger.png" alt=""><p>日志管理</p>
                    </li>

                    </c:if>

                    <c:if test="${userType=='院主'}">
                        <li class="leval" data-src="${pageContext.request.contextPath}../room/list">
                            <img src="${pageContext.request.contextPath}/src/images/buildingadmin.png" alt=""><p>查看房间</p>
                        </li>
                        <li class="active leval" data-src="${pageContext.request.contextPath}../user/id/list">
                            <img src="${pageContext.request.contextPath}/src/images/user.png" alt=""><p>用户管理</p>
                        </li>

                        <li class="leval" data-src="${pageContext.request.contextPath}../log/list">
                            <img src="${pageContext.request.contextPath}/src/images/logger.png" alt=""><p>日志管理</p>
                        </li>
                    </c:if>

                    <c:if test="${userType=='楼管'}">
                        <li class="active leval" data-src="${pageContext.request.contextPath}../room/state" id="room-state">
                            <img src="${pageContext.request.contextPath}/src/images/roomstate.png" alt=""><p>查看房间状态</p>
                        </li>

                        <li class="leval leval0" id="set-time">
                            <img src="${pageContext.request.contextPath}/src/images/time.png" alt=""><p>设置时间提醒</p>
                        </li>
                    </c:if>


                    <c:if test="${userType=='教研负责人'}">
                        <li class="leval" data-src="${pageContext.request.contextPath}../room/state">
                            <img src="${pageContext.request.contextPath}/src/images/roomManage.png" alt=""><p>查看房间状态</p>
                        </li>

                        <li class="active leval" data-src="${pageContext.request.contextPath}../user/id/list">
                            <img src="${pageContext.request.contextPath}/src/images/user.png" alt=""><p>用户管理</p>
                        </li>

                        <li class="leval" data-src="${pageContext.request.contextPath}../log/list">
                            <img src="${pageContext.request.contextPath}/src/images/logger.png" alt=""><p>日志管理</p>
                        </li>
                    </c:if>



                </ul>
            </div>

            <!--此处显示页面内容，main-content是除了上方和左侧导航栏之外的内容区-->
            <div class="main-content">
                <c:if test="${userType=='总负责人'||userType=='楼主'||userType=='院主'}">

                    <iframe id = "main-frame" src="${pageContext.request.contextPath}../user/id/list" frameborder="0" style="width:100%;height:100%"></iframe>

                </c:if>

                <%--<c:if test="${userType=='楼管'||userType=='教研负责人'}">--%>

                    <%--<iframe id = "main-frame" src="../room/state" frameborder="0" style="width:100%;height:100%"></iframe>--%>

                <%--</c:if>--%>
                <c:if test="${userType=='楼管'}">

                    <iframe id = "main-frame" src="${pageContext.request.contextPath}../room/state" frameborder="0" style="width:100%;height:100%"></iframe>

                </c:if>


            </div>

        </div>
    </c:when>

    <c:otherwise>
        <iframe src="${pageContext.request.contextPath}../user/loginPage" frameborder="0" style="width:100%;height:100%"></iframe>
    </c:otherwise>
</c:choose>

</body>
<script>
    //实现异步加载
    $("li[class!='leval leval0']").on("click",this,function(){
        $("iframe").attr("src",this.getAttribute("data-src"));
    });

    $("li").on("click",this,function(){

        $(this).addClass("active")
            .siblings().removeClass("active");
    })

</script>
<script src="${pageContext.request.contextPath}/src/js/smartLock.js"></script>
<script type="text/javascript">
    window.history.forward();
</script>

</html>
