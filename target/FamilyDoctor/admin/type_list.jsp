<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>专科列表</title>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

    <jsp:include page="header.jsp"></jsp:include>

    <br>

    <div>
        <form class="form-inline" method="post" action="type_add.action">
            <input type="text" class="form-control" id="input_name" name="dtname" placeholder="输入专科名称" required="required" style="width: 500px">
            <input type="submit" class="btn btn-warning" value="添加专科"/>
        </form>
    </div>
    <br/>
    <c:if test="${!empty msg }">
        <div class="alert alert-success">${msg }</div>
    </c:if>
    <c:if test="${!empty failMsg }">
        <div class="alert alert-danger">${failMsg }</div>
    </c:if>
    <br>

    <table class="table table-bordered table-hover">

        <tr>
            <th width="5%">ID</th>
            <th width="10%">名称</th>
            <th width="10%">操作</th>
        </tr>

        <c:forEach items="${list }" var="t">
            <tr>
                <td><p>${t.dtid }</p></td>
                <td><p>${t.dtname }</p></td>
                <td>
                    <a class="btn btn-primary" href="type_edit.jsp?dtid=${t.dtid}&dtname=${t.dtname}">修改</a>
                    <a class="btn btn-danger" href="type_delete.action?dtid=${t.dtid}">删除</a>
                </td>
            </tr>
        </c:forEach>


    </table>

</div>
</body>
</html>
