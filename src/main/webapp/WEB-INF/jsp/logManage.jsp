<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" id="m-nav">
<head>
    <meta charset="UTF-8">
    <title>教研室负责人日志管理</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/src/bootstrap/js/jquery.min.js"></script>
<!--表格-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/panel.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/table.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/assets/css/amazeui.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/m-index.css">


<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/logManage1.css">

<!--表格插件-->
<link href="${pageContext.request.contextPath}/src/lock-plug/table/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/src/lock-plug/table/css/style-metro.css" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/src/lock-plug/table/css/style.css" rel="stylesheet" type="text/css"/>

<link href="${pageContext.request.contextPath}/src/lock-plug/table/css/uniform.default.css" rel="stylesheet" type="text/css"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/src/lock-plug/table/css/select2_metro.css"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/src/lock-plug/table/css/DT_bootstrap.css"/>

<link rel="stylesheet" href="${pageContext.request.contextPath}/src/lock-plug/table/css/table_myself.css"/>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/src/lock-plug/table/image/favicon.ico"/>
<style type="text/css">
    .back-color {
        background-color: #3cc051;
        background-image: none !important;
        text-shadow: none !important;
        font-weight: 300;
        display: inline-block;
        padding: 2px 4px;
        font-size: 11.844px;
        white-space: nowrap;
        vertical-align: baseline;
        border-radius: 0 !important;
        text-align: left;

    }
</style>

<body>


<div class="right-content">
    <div class="main row c-table">
        <!--在此处修改面板的主色调-->
        <div class="portlet box blue ">

            <div class="portlet-title">

                <div class="caption"><i class="icon-globe"></i>用户信息展示</div>

                <div class="actions">

                    <div class="btn-group">

                        <a class="btn" href="#" data-toggle="dropdown">

                            选择列

                            <i class="icon-angle-down"></i>

                        </a>

                        <div id="sample_2_column_toggler" class="dropdown-menu hold-on-click dropdown-checkboxes pull-right">

                            <label><input type="checkbox" checked data-column="0">选择</label>

                            <label><input type="checkbox" checked data-column="1">登录名</label>

                            <label><input type="checkbox" checked data-column="2">姓名</label>

                            <label><input type="checkbox" checked data-column="3">操作对象</label>

                            <label><input type="checkbox" checked data-column="4">操作时间</label>

                            <label><input type="checkbox" checked data-column="5">操作</label>

                        </div>

                    </div>

                </div>

            </div>

            <div class="portlet-body">

                <table class="table table-striped table-bordered table-hover table-full-width c-container" id="sample_2">

                    <thead>

                    <tr>

                        <th class="hidden-480">选择</th>

                        <th class="hidden-480">登录名</th>

                        <th class="hidden-480">姓名</th>

                        <th class="hidden-480">操作对象</th>

                        <th class="hidden-480">操作时间</th>

                        <th class="hidden-480">操作</th>

                    </tr>

                    </thead>

                    <tfoot>

                    </tfoot>

                    <tbody>

                    <c:forEach var="log" items="${list}">
                        <tr>

                            <td class="hidden-480">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" class="blankCheckbox" value="option1"
                                               style="height: 20px;width: 20px;background-color:#FFF;">
                                    </label>
                                </div>
                            </td>

                            <td class="hidden-480">${log.account}</td>

                            <td class="hidden-480">${log.username}</td>

                            <td class="hidden-480">${log.buildingName}${log.roomPart}段${log.roomIndex}</td>

                            <td class="hidden-480 ">
                                    <%-- <%

                                         Date d1=new Date();
                                         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                         String sdfomatter=sdf.format(d1);
                                     %>--%>
                                    <%--<fmt:formatDate value="${log.logTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
                                    <%--<%=sdfomatter%>--%>
                                    ${log.logTime}
                            </td>

                            <td class="hidden-480 ">${log.behavior}</td>



                        </tr>
                    </c:forEach>

                    </tbody>

                </table>

            </div>

        </div>
    </div>
</div>


</body>
<%--表格插件--%>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/bootstrap.min.js" type="text/javascript"></script>

<!--[if lt IE 9]>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/excanvas.min.js"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/respond.min.js"></script>
<![endif]-->

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery.slimscroll.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery.blockui.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery.cookie.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery.uniform.min.js" type="text/javascript"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/src/lock-plug/table/js/select2.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/src/lock-plug/table/js/jquery.dataTables.min.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/src/lock-plug/table/js/DT_bootstrap.js"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/app.js"></script>

<script src="${pageContext.request.contextPath}/src/lock-plug/table/js/table-advanced.js"></script>


<script type="text/javascript">
    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-37564768-1']);
    _gaq.push(['_setDomainName', 'keenthemes.com']);
    _gaq.push(['_setAllowLinker', true]);
    _gaq.push(['_trackPageview']);
    (function () {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();

    $(".leftNav").on("click", ".leval", function () {
        $(this).addClass("current")
            .siblings().removeClass("current");

    });

    jQuery(document).ready(function () {

        App.init();//初始化变量

        TableAdvanced.init();

    });

    $(function () {
        $(".datepicker").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: "yy-mm-dd"
        });

    });

</script>

</html>