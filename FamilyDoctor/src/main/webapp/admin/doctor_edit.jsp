<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>医生信息修改</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container-fluid">

    <jsp:include page="/admin/header.jsp"></jsp:include>

    <br><br>
    <form class="form-horizontal" action="doctor_update.action" method="post" enctype="multipart/form-data">
        <input type="hidden" name="did" value="${g.did }"/>
        <input type="hidden" name="dcover" value="${g.dcover }"/>
        <input type="hidden" name="dimage1" value="${g.dimage1 }"/>
        <input type="hidden" name="dimage2" value="${g.dimage2 }"/>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">姓名</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="dname" value="${g.dname}" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">电话</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="dphone"  value="${g.dphone}" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">资历</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="dqua"  value="${g.dqua}" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">所在医院</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="dlocal"  value="${g.dlocal}" required="required">
            </div>
        </div>
        <div class="form-group">
            <label for="input_file" class="col-sm-1 control-label">照片</label>
            <div class="col-sm-6"><img src="../${g.dcover }" width="100" height="100"/>
                <input type="file" name="dcover"  id="input_file" required="required">推荐尺寸: 600 * 600
            </div>
        </div>
        <div class="form-group">
            <label for="input_file" class="col-sm-1 control-label">介绍图片1</label>
            <div class="col-sm-6"><img src="../${g.dimage1 }" width="100" height="100"/>
                <input type="file" name="dimage1"  id="input_file" required="required">推荐尺寸: 600 * 600
            </div>
        </div>
        <div class="form-group">
            <label for="input_file" class="col-sm-1 control-label">介绍图片2</label>
            <div class="col-sm-6"><img src="../${g.dimage2 }" width="100" height="100"/>
                <input type="file" name="dimage2"  id="input_file" required="required">推荐尺寸: 600 * 600
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">收费</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="dprice" value="${g.dprice}">
            </div>
        </div>
        <div class="form-group">
            <label for="select_topic" class="col-sm-1 control-label">专科</label>
            <div class="col-sm-6">
                <select class="form-control" id="select_topic" name="dtid">

                    <c:forEach items="${doctorTypes }" var="t">
                        <option <c:if test="${t.dtid==g.dtid }">selected="selected"</c:if> value="${t.dtid }">${t.dtname }</option>
                    </c:forEach>

                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">允许签约的家庭数</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="dstock" value="${g.dstock}">
            </div>
        </div>
        <div class="form-group">
            <label for="input_name" class="col-sm-1 control-label">介绍</label>
            <div class="col-sm-6">
                <input type="text" class="form-control" id="input_name" name="dmark" value="${g.dmark}">
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-1 col-sm-10">
                <button type="submit" class="btn btn-success">提交保存</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
