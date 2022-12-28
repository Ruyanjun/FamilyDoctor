<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>医生列表</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="header.jsp"></jsp:include>

    <div class="text-right"><a class="btn btn-warning" href="doctor_add.jsp">添加医生</a></div>

    <br>

    <ul role="tablist" class="nav nav-tabs">
        <li <c:if test="${rtype==0 }">class="active"</c:if> role="presentation"><a href="doctor_list.action?pageNumber=1&rtype=0">全部医生</a></li>
        <li <c:if test="${rtype==1 }">class="active"</c:if> role="presentation"><a href="doctor_list.action?pageNumber=1&rtype=1">条幅推荐</a></li>
        <li <c:if test="${rtype==2 }">class="active"</c:if> role="presentation"><a href="doctor_list.action?pageNumber=1&rtype=2">口碑推荐</a></li>
        <li <c:if test="${rtype==3 }">class="active"</c:if> role="presentation"><a href="doctor_list.action?pageNumber=1&rtype=3">新人推荐</a></li>
    </ul>


    <br>

    <table class="table table-bordered table-hover">

        <tr>
            <th width="5%">ID</th>
            <th width="10%">照片</th>
            <th width="10%">姓名</th>
            <th width="20%">介绍</th>
            <th width="10%">收费</th>
            <th width="10%">专科</th>
            <th width="25%">操作</th>
        </tr>

        <c:forEach items="${p.list }" var="g">
            <tr>
                <td><p>${g.did }</p></td>
                <td><p><a href="#" target="_blank"><img src="../${g.dcover}" width="100px" height="100px"></a></p></td>
                <td><p><a href="#" target="_blank">${g.dname}</a></p></td>
                <td><p>${g.dmark}</p></td>
                <td><p>${g.dprice}</p></td>
                <td><p>${g.dtname}</p></td>
                <td>
                    <p>
                        <c:choose>
                            <c:when test="${g.isScroll }">
                                <a class="btn btn-info" href="doctor_change.action?did=${g.did}&rtype=1&method=remove&page=${rtype}">移出条幅</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary" href="doctor_change.action?did=${g.did}&rtype=1&method=add&page=${rtype}">加入条幅</a>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${g.isHot }">
                                <a class="btn btn-info" href="doctor_change.action?did=${g.did}&rtype=2&method=remove&page=${rtype}">移出口碑</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary" href="doctor_change.action?did=${g.did}&rtype=2&method=add&page=${rtype}">加入口碑</a>
                            </c:otherwise>
                        </c:choose>
                        <c:choose>
                            <c:when test="${g.isNew }">
                                <a class="btn btn-info" href="doctor_change.action?did=${g.did}&rtype=3&method=remove&page=${rtype}">移出新人</a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-primary" href="doctor_change.action?did=${g.did}&rtype=3&method=add&page=${rtype}">加入新人</a>
                            </c:otherwise>
                        </c:choose>

                    </p>
                    <a class="btn btn-success" href="doctor_edit_show.action?did=${g.did }">修改</a>
                    <a class="btn btn-danger" href="doctor_delete.action?did=${g.did}&rtype=${rtype}">删除</a>
                </td>
            </tr>
        </c:forEach>


    </table>

    <br>
    <jsp:include page="/page.jsp">
        <jsp:param value="doctor_list.action" name="url"/>
        <jsp:param value="&rtype=${rtype }" name="param"/>
    </jsp:include>
    <br>
</div>
</body>
</html>
