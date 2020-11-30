<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/10/30
  Time: 9:17 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>模拟登出</title>
</head>
<body>
    推出系统，从session中删除数据
<%
    session.removeAttribute("name");
%>
</body>
</html>
