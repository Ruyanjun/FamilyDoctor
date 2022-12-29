<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>家庭医生服务管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
<!--header-->
<jsp:include page="/header.jsp">
    <jsp:param name="flag" value="1"></jsp:param>
</jsp:include>

<!--banner-->

<div class="banner">
    <div class="container">
        <h2 class="hdng"><a href="doctor_detail.action?did=${scrollDoctor.did}">${scrollDoctor.dname}</a><span></span></h2>
        <p>今日医生推荐</p>
        <a class="banner_a" href="javascript:;" onclick="buy(${scrollDoctor.did})">立即签约</a>
        <div class="banner-text">
            <a href="doctor_detail.action?did=${scrollDoctor.did}">
                <img src="${scrollDoctor.dcover}" alt="${scrollDoctor.dname}" width="350" height="350">
            </a>
        </div>
    </div>
</div>

<!--//banner-->

<!--gallery-->
<div class="gallery">
    <div class="container">
        <div class="alert alert-danger">广受好评医生</div>
        <div class="gallery-grids">
            <c:forEach items="${hotList}" var="doctor">
                <div class="col-md-4 gallery-grid glry-two">
                    <a href="doctor_detail.action?did=${doctor.did}">
                        <img src="${doctor.dcover}" class="img-responsive" alt="${doctor.dname}" width="350" height="350"/>
                    </a>
                    <div class="gallery-info galrr-info-two">
                        <p>
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            <a href="doctor_detail.action?did=${doctor.did}">查看详情</a>
                        </p>
                        <a class="shop" href="javascript:;" onclick="buy(${doctor.did})">立即签约</a>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="galy-info">
                        <p>${doctor.dtname} > ${doctor.dname}</p>
                        <div class="galry">
                            <div class="prices">
                                <h5 class="item_price">¥ ${doctor.dprice}</h5>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="clearfix"></div>
        <div class="alert alert-info">新入驻医生</div>
        <div class="gallery-grids">
            <c:forEach items="${newList}" var="doctor">
                <div class="col-md-3 gallery-grid ">
                    <a href="doctor_detail.action?did=${doctor.did}">
                        <img src="${doctor.dcover}" class="img-responsive" alt="${doctor.dname}"/>
                    </a>
                    <div class="gallery-info">
                        <p>
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            <a href="doctor_detail.action?did=${doctor.did}">查看详情</a>
                        </p>
                        <a class="shop" href="javascript:;" onclick="buy(${doctor.did})">立即签约</a>
                        <div class="clearfix"> </div>
                    </div>
                    <div class="galy-info">
                        <p>${doctor.dtname} > ${doctor.dname}</p>
                        <div class="galry">
                            <div class="prices">
                                <h5 class="item_price">¥ ${doctor.dprice}</h5>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<!--subscribe-->
<div class="subscribe"></div>
<!--//subscribe-->


<!--footer-->
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>
