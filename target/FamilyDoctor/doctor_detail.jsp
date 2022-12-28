<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>医生详情信息</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <link type="text/css" rel="stylesheet" href="css/flexslider.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/jquery.flexslider.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
    <script>
        $(function() {
            $('.flexslider').flexslider({
                animation: "slide",
                controlNav: "thumbnails"
            });
        });
    </script>
</head>
<body>







<!--header-->
<jsp:include page="/header.jsp"></jsp:include>
<!--//header-->


<!--//single-page-->
<div class="single">
    <div class="container">
        <div class="single-grids">
            <div class="col-md-4 single-grid">
                <div class="flexslider">

                    <ul class="slides">
                        <li data-thumb="${doctor.dcover}">
                            <div class="thumb-image"> <img src="${doctor.dcover}" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="${doctor.dimage1}">
                            <div class="thumb-image"> <img src="${doctor.dimage1}" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                        <li data-thumb="${doctor.dimage2}">
                            <div class="thumb-image"> <img src="${doctor.dimage2}" data-imagezoom="true" class="img-responsive"> </div>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 single-grid simpleCart_shelfItem">
                <h3>${doctor.dname}</h3>
                <div class="tag">
                    <p>专科 : <a href="goods.action?typeid=5">${doctor.dtname}</a></p>
                </div>
                <div class="tag">
                    <p>资历 : <a href="goods.action?typeid=5">${doctor.dqua}</a></p>
                </div>

                <div class="tag">
                    <p>所在医院 : <a href="goods.action?typeid=5">${doctor.dlocal}</a></p>
                </div>
                <p>${doctor.dmark}</p>
                <div class="galry">
                    <div class="prices">
                        <h5 class="item_price">¥ ${doctor.dprice}</h5>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="btn_form">
                    <a href="javascript:;" class="add-cart item_add" onclick="buy(${doctor.did})">加入意向</a>
                </div>
            </div>
            <div class="col-md-4 single-grid1">
                <ul>
                    <li><a  href="doctortypes_list.action?pageNumber=1&dtid=-1">全部专科</a></li>

                    <c:forEach items="${doctorTypes}" var="t">
                        <li><a href="doctortypes_list.action?pageNumber=1&dtid=${t.dtid}">${t.dtname}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="clearfix"> </div>
        </div>
    </div>
</div>





<!--footer-->
<jsp:include page="/footer.jsp"></jsp:include>
<!--//footer-->


</body>
</html>
