<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Thinker
  Date: 2017/1/7
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" id="m-nav">
<head>
    <meta charset="UTF-8">
    <title>对多媒体教室开关门（大爷）</title>
</head>
<link rel="stylesheet" href="/src/bootstrap/css/bootstrap.min.css">
<script src="/src/bootstrap/js/jquery.min.js"></script>

<link rel="stylesheet" href="/src/css/m-index.css">

<link rel="stylesheet" href="/src/css/panel.css">
<link rel="stylesheet" href="/src/assets/css/amazeui.min.css">
<body>




<div class="right-content">

    <!--此处显示页面内容-->
    <div class="main-content" id="m-classRoomOpen">
        <div class="main">
            <div class="panel panel-default panel-warning">
                <div class="panel-header" style="font-size: 18px;font-family: 微软雅黑;letter-spacing: 1px">
                    <span>${buildingName.management}</span>结构图：
                </div>
                <div class="panel-body">

                  <form action="" method="post" id="room-form">
                        <div  class="date" id="classroom-state">

                               <input type="checkbox" class="choose-all" >
                               <span>选择全部教室</span>

                               <button class="btn btn-danger sub-btn" style="align-content: center"type="submit" data-action="../../../webSmartLock/room/unLock">开锁</button>

                               <button class="btn btn-info sub-btn" style="align-content: center" type="submit" data-action="../../../webSmartLock/room/lock">关锁</button>
                        </div>

                        <div data-am-widget="tabs" class="am-tabs am-tabs-d2" style="margin:0;background-color: rgba(245, 245, 245, 0.33)">
                              <ul class="am-tabs-nav am-cf" style="">
                                  <li class="am-active" id="one"><a href="[data-tab-panel-0]">一段</a></li>
                                  <li class="" id="two"><a href="[data-tab-panel-1]">二段</a></li>
                                  <li class="" id="three"><a href="[data-tab-panel-2]">三段</a></li>
                              </ul>
                              <div class="am-tabs-bd c-content">
                                  <div data-tab-panel-0 class="am-tab-panel am-active">
                                      <ul class="ul">
                                          <c:forEach var="stutas" items="${list}">
                                              <%--段数==1--%>
                                              <c:choose>
                                                  <c:when test="${stutas.roomPart==1}">
                                                      <li data-room-id = "${stutas.roomId}" data-room-type="${stutas.roomType}">
                                                          <p>${stutas.roomIndex}</p>
                                                              <%--房间类型==教研室--%>
                                                          <c:choose>
                                                              <c:when test="${stutas.roomType=='教研室'}">
                                                                  <%--房间状态判断--%>
                                                                  <c:choose>
                                                                      <c:when test="${stutas.roomStatus==1}">
                                                                          <img src="${pageContext.request.contextPath}/src/images/classRoomOpen.png">
                                                                      </c:when>
                                                                      <c:otherwise>
                                                                          <img src="${pageContext.request.contextPath}/src/images/classroomClose.png">
                                                                      </c:otherwise>
                                                                  </c:choose>
                                                              </c:when>
                                                              <%--房间类型==教室--%>
                                                              <c:otherwise>
                                                                  <c:choose>
                                                                      <c:when test="${stutas.roomStatus==1}">
                                                                          <img src="${pageContext.request.contextPath}/src/images/doorOpen.png">
                                                                      </c:when>
                                                                      <c:otherwise>
                                                                          <img src="${pageContext.request.contextPath}/src/images/doorClose.png">
                                                                      </c:otherwise>
                                                                  </c:choose>
                                                              </c:otherwise>
                                                          </c:choose>
                                                      </li>
                                                  </c:when>
                                                  <c:otherwise>
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:forEach>
                                      </ul>


                                  </div>
                                  <div data-tab-panel-1 class="am-tab-panel ">
                                      <ul class="ul">
                                          <c:forEach var="stutas" items="${list}">
                                              <%--段数==2--%>
                                              <c:choose>
                                                  <c:when test="${stutas.roomPart==2}">
                                                      <li data-room-id = "${stutas.roomId}" data-room-type="${stutas.roomType}">
                                                          <p>${stutas.roomIndex}</p>
                                                              <%--房间类型==教研室--%>
                                                          <c:choose>
                                                              <c:when test="${stutas.roomType=='教研室'}">
                                                                  <%--房间状态判断--%>
                                                                  <c:choose>
                                                                      <c:when test="${stutas.roomStatus==1}">
                                                                          <img src="${pageContext.request.contextPath}/src/images/classRoomOpen.png">
                                                                      </c:when>
                                                                      <c:otherwise>
                                                                          <img src="${pageContext.request.contextPath}/src/images/classroomClose.png">
                                                                      </c:otherwise>
                                                                  </c:choose>
                                                              </c:when>
                                                              <%--房间类型==教室--%>
                                                              <c:otherwise>
                                                                  <c:choose>
                                                                      <c:when test="${stutas.roomStatus==1}">
                                                                          <img src="${pageContext.request.contextPath}/src/images/doorOpen.png">
                                                                      </c:when>
                                                                      <c:otherwise>
                                                                          <img src="${pageContext.request.contextPath}/src/images/doorClose.png">
                                                                      </c:otherwise>
                                                                  </c:choose>
                                                              </c:otherwise>
                                                          </c:choose>
                                                      </li>
                                                  </c:when>
                                                  <c:otherwise>
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:forEach>
                                      </ul>
                                  </div>
                                  <div data-tab-panel-2 class="am-tab-panel ">
                                      <ul class="ul">
                                          <c:forEach var="stutas" items="${list}">
                                              <%--段数==3--%>
                                              <c:choose>
                                                  <c:when test="${stutas.roomPart==3}">
                                                      <li data-room-id = "${stutas.roomId}" data-room-type="${stutas.roomType}">
                                                          <p>${stutas.roomIndex}</p>
                                                              <%--房间类型==教研室--%>
                                                          <c:choose>
                                                              <c:when test="${stutas.roomType=='教研室'}">
                                                                  <%--房间状态判断--%>
                                                                  <c:choose>
                                                                      <c:when test="${stutas.roomStatus==1}">
                                                                          <img src="${pageContext.request.contextPath}/src/images/classRoomOpen.png">
                                                                      </c:when>
                                                                      <c:otherwise>
                                                                          <img src="${pageContext.request.contextPath}/src/images/classroomClose.png">
                                                                      </c:otherwise>
                                                                  </c:choose>
                                                              </c:when>
                                                              <%--房间类型==教室--%>
                                                              <c:otherwise>
                                                                  <c:choose>
                                                                      <c:when test="${stutas.roomStatus==1}">
                                                                          <img src="${pageContext.request.contextPath}/src/images/doorOpen.png">
                                                                      </c:when>
                                                                      <c:otherwise>
                                                                          <img src="${pageContext.request.contextPath}/src/images/doorClose.png">
                                                                      </c:otherwise>
                                                                  </c:choose>
                                                              </c:otherwise>
                                                          </c:choose>
                                                      </li>
                                                  </c:when>
                                                  <c:otherwise>
                                                      <p></p>
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:forEach>
                                      </ul>
                                  </div>

                                  <input  type="hidden" class="allRoomId" name="allRoomId"/>

                              </div>
                          </div>
                  </form>
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
<script src="/src/assets/js/jquery.min.js"></script>
<script src="/src/assets/js/amazeui.min.js"></script>

<script src="/src/js/smartLock.js"></script>
</html>