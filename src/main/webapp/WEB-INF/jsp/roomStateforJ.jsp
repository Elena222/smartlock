<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thinker
  Date: 2017/1/7
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" id="m-nav">
<head>
    <meta charset="UTF-8">
    <title>教研室房间状态（教研室负责人）</title>
</head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/m-index.css">


<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/monitor.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/src/css/panel.css">
<link href="${pageContext.request.contextPath}/src/css/userManagerforZ.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/src/bootstrap/js/jquery.min.js"></script>


<body>


<div class="right-content">
    <!--此处显示左边侧栏-->

    <div class="main-content"  id="m-roomState">
        <div class="main">
            <div class="panel panel-default panel-warning">
                <div class="panel-header" style="font-size: 18px;font-family: 微软雅黑;letter-spacing: 1px">房间状态</div>
                <div class="panel-body">
                    <!--此处显示监控画面-->
                    <div class="monitor">
                        <!--<img src="images/classroom1.jpg" alt="">-->
                        <!--<img src="images/classroom02.JPG" alt="">-->
                        <ul id="monitor">
                            <li>
                                <div class="twoDemo">
                                    <img class="transform" src="${pageContext.request.contextPath}/src/images/classroom1.jpg" />
                                    <div class="content">

                                        <h3>写给未来——我的Mr right</h3>
                                        <p>我不知道你会在什么时间 什么地点 什么场合&shy;出现可是 我想你遇到我的时候可以一眼就把我认出来,就像我一直坚信我会在见到你的第一眼就认出你一样。</p>
                                        <a href="#">Mr right</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="twoDemo">
                                    <img class="transform" src="${pageContext.request.contextPath}/src/images/classroom1.jpg" />
                                    <div class="content">

                                        <h3>写给未来——我的Mr right</h3>
                                        <p>我不知道你会在什么时间 什么地点 什么场合&shy;出现可是 我想你遇到我的时候可以一眼就把我认出来,就像我一直坚信我会在见到你的第一眼就认出你一样。</p>
                                        <a href="#">Mr right</a>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="twoDemo">
                                    <img class="transform" src="${pageContext.request.contextPath}/src/images/classroom1.jpg" />
                                    <div class="content">

                                        <h3>写给未来——我的Mr right</h3>
                                        <p>我不知道你会在什么时间 什么地点 什么场合&shy;出现可是 我想你遇到我的时候可以一眼就把我认出来,就像我一直坚信我会在见到你的第一眼就认出你一样。</p>
                                        <a href="#">Mr right</a>
                                    </div>
                                </div>
                            </li>
                        </ul>

                    </div>
                    <!--此处显示房间状态-->
                    <div class="rooms">
                        <%--<form action="/webSmartLock/room/unLock" method="post">--%>
                            <c:forEach var="roomJ" items="${list}">
                            <div class="room">
                                <div class="room-number">
                                    <p>${roomJ.roomIndex}</p>
                                    <div class="line"></div>
                                </div>
                                <div class="room-state">
                                    <p>房间状态:${roomJ.roomStatus}(1表示开，0表示关)</p>
                                </div>
                                <div class="button">
                                    <button class="left-button myInfo btn btn-default"  >房间信息</button>
                                    <!------------------------弹出面板显示教研室房间信息---------------------------->
                                    <div class="modal fade y-roomMassages" >
                                        <div class="modal-dialog">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                    <h3 class="modal-title">房间信息</h3>
                                                </div>
                                                <div class="modal-body">
                                                    <h4><p><label class="font">房间号：</label>${roomJ.roomIndex}</p></h4>
                                                    <h4><p><label class="font">负责人：</label>${roomJ.account}</p></h4>
                                                    <h4><p><label class="font">类&nbsp;型：</label>${roomJ.roomType}</p></h4>
                                                    <h4><p><label class="font">房间介绍:</label></p></h4>
                                                    <h4><p>
                                                        &nbsp;&nbsp;&nbsp;&nbsp;${roomJ.roomDes}
                                                    </p></h4>
                                                    <input type="hidden" value="${roomJ.roomId}" name="roomId">

                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-primary" data-dismiss="modal">确定</button>
                                                    <button type="button" class="btn btn-default" data-dismiss="modal" aria-label="Close">取消</button>
                                                </div>
                                            </div><!-- /.modal-content -->
                                        </div><!-- /.modal-dialog -->
                                    </div>


                                    <button class="right-button btn btn-default">查看监控</button>
                                    <form action="${pageContext.request.contextPath}/webSmartLock/room/unLock" method="post">
                                        <input type="hidden" value="${roomJ.roomId}" name="roomId">
                                        <button type="submit" class="bottom-button btn btn-default" style="float: left;width: 125px">开锁</button>
                                    </form>
                                    <form action="${pageContext.request.contextPath}/webSmartLock/room/lock" method="post">
                                        <input type="hidden" value="${roomJ.roomId}" name="roomId">
                                        <button type="submit" class="bottom-button btn btn-default" style="float: right;width: 125px;right:0">关锁</button>
                                    </form>
                                </div>
                            </div>
                        </c:forEach>
                        <%--</form>--%>
                    </div>
                </div>
            </div>

        </div>

    </div>

</div>
</body>
<script src="/src/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script>
    $(".leftNav").on("click",".leval",function(){
        $(this).addClass("current")
            .siblings().removeClass("current");

    });
</script>
<%--查看房间信息--%>
<script type="text/javascript">
    $(function(){
        // dom加载完毕

//        var $modal = $('.y-roomMassages');

        $('.myInfo').on("click",this,function(){

            $(this).next().modal({backdrop: 'static'});

        });


        // 测试 bootstrap 居中
        var $modal = $('.y-roomMessages');
        $modal.on('shown.bs.modal', function(){
            var $this = $(this);
            var $modal_dialog = $this.find('.modal-dialog');
            var m_top = ( $(document).height() - $modal_dialog.height() )/2;
            $modal_dialog.css({'margin': m_top + 'px auto'});
        });
    });
</script>
<%--开锁、关锁--%>
<script>

</script>

</html>