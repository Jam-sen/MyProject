<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/10/16
  Time: 10:42 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>第一个springmvc项目</p>
    <a href="test/some.do">发起some.do的请求</a><br>

    <form action="test/other.do" method="post">
        <input type="submit" value="发起other.do的请求"/>
    </form>
</body>
</html>
