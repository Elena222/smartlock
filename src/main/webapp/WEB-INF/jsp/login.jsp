<%--
  Created by IntelliJ IDEA.
  User: xian
  Date: 2017/1/10
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登陆页面</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/css/login.css" />

</head>
<body onload="loadTopWindow()">

<div class="login_frame"></div>
<div class="LoginWindow">
    <div>
        <form method="post" action="${pageContext.request.contextPath}../../webSmartLock/user/login" class="login">
            <p>
                <label >帐号:</label>
                <input type="text" name="account" id="account" value="">
            </p>

            <p>
                <label for="password">密码:</label>
                <input type="password" name="password" id="password" value="">
            </p>

            <p class="login-submit">
                <button type="submit" class="login-button" id="login">Login</button>
            </p>

        </form>
        <p class="registration"><a href="#">忘记密码</a></p>

    </div>
</div>
<script src="${pageContext.request.contextPath}/src/bootstrap/js/jquery.min.js"></script>

<%--主要交互逻辑js文件--%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/smartLock.js"></script>
<script type="text/javascript">
    window.history.forward();
    function loadTopWindow(){
       /* console.log("window.top="+window.top);
        console.log("window.top.document.URL="+window.top.document.URL);
        console.log("document.URL="+document.URL);*/

        var loginUrl = "http://localhost:8080/webSmartLock/user/loginPage";
        if (window.top!=null && window.top.document.URL!=loginUrl){
            window.top.location= loginUrl;
        }
    }

</script>
</body>
</html>