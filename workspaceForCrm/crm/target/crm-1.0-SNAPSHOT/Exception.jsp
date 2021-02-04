<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/11/22
  Time: 5:49 下午
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
    <title>发生错误</title>
</head>
<body>
    <center>
        放生错误：${requestScope.msg}
    </center>
</body>
</html>
