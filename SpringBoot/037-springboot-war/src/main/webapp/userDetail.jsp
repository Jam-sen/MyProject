<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/12/20
  Time: 1:55 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>userDetail</title>
</head>
<body>
<h3>用户编号:${id}</h3>
<h3>用户姓名:${name}</h3>
</body>
</html>