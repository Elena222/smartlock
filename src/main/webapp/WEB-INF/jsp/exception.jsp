<%@page import="java.net.URLEncoder"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/include/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

</head>
<body>
${exception.message }<br/>
错误详情:

<div style="color: red;" id="errors">
    <c:forEach items="${exception.stackTrace }" var="e">
        ${e }<br />
    </c:forEach>
</div>

</body>
</html>