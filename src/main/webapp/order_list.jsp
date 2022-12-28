
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的签约</title>
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
<jsp:include page="header.jsp">
    <jsp:param name="flag" value="5"></jsp:param>
</jsp:include>
<!--//header-->


<!--cart-items-->
<div class="cart-items">
    <div class="container">



        <h2>我的签约</h2>

        <table class="table table-bordered table-hover">

            <tr>
                <th width="10%">ID</th>
                <th width="10%">费用</th>
                <th width="10%">签约医生</th>
                <th width="30%">客户信息</th>
                <th width="10%">签约状态</th>
                <th width="10%">支付方式</th>
                <th width="10%">签约时间</th>
                <th width="10%">合同结束时间</th>
            </tr>

            <c:forEach items="${orderList }" var="order">

                <tr>
                    <td><p>${order.oid }</p></td>
                    <td><p>${order.ototal }</p></td>
                    <td>
                        <c:forEach items="${order.itemList }" var="item">
                            <p>${item.doctor.dname }</p>
                        </c:forEach>

                    </td>
                    <td>
                        <p>${order.orealname }</p>
                        <p>${order.ophone }</p>
                        <p>${order.oaddress }</p>
                    </td>
                    <td>
                        <p>
                            <c:if test="${order.ostatus==2 }"><span style="color:red;">已付款</span></c:if>
                            <c:if test="${order.ostatus==3 }"><span style="color:green;">签约中</span></c:if>
                            <c:if test="${order.ostatus==4 }"><span style="color:black;">已完成</span></c:if>


                        </p>
                    </td>
                    <td>
                        <p>

                            <c:if test="${order.opaytype==1 }">微信</c:if>
                            <c:if test="${order.opaytype==2 }">支付宝</c:if>
                            <c:if test="${order.opaytype==3 }">银行卡</c:if>

                        </p>
                    </td>
                    <td><p>${order.odatetime }</p></td>
                    <td><p>2023-12-15</p></td>
                </tr>

            </c:forEach>
        </table>

        <br>
        <jsp:include page="/page.jsp">
            <jsp:param name="url" value="/admin/order_list.action"/>
            <jsp:param name="param" value="&ostatus=${ostatus}"/>
            
            
        </jsp:include>



    </div>
</div>
<!--//cart-items-->

<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>
<!--//footer-->


</body>
</html>
