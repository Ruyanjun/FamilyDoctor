<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.jsp">家庭医生服务管理系统后台</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li ><a href="order_list.action?pageNumber=1&ostatus=1">签约订单</a></li>
                <li ><a href="user_list.action?pageNumber=1">用户管理</a></li>
                <li ><a href="doctor_list.action?pageNumber=1&rtype=0">医生管理</a></li>
                <li ><a href="type_list.action">专科管理</a></li>
                <li><a href="logout.action">退出</a></li>
            </ul>
        </div>
    </div>
</nav>

