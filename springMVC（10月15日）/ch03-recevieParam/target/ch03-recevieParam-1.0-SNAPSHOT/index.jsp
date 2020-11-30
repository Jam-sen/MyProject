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
    <p>提交参数给Controller</p>
    <form action="test/some.do" method="post">
        姓名：<input type="text" name="name"><br>
        年龄：<input type="text" name="age"><br>
        <input type="submit" value="提交参数"/>
    </form>
    <br>
    <p>请求参数名和处理器方法的形参名不一样</p>
    <form action="test/other.do" method="post">
        姓名：<input type="text" name="rname"><br>
        年龄：<input type="text" name="rage"><br>
        <input type="submit" value="提交参数"/>
    </form>
    <p>使用Java对象接收请求参数</p>
    <form action="test/first.do" method="post">
        姓名：<input type="text" name="name"><br>
        年龄：<input type="text" name="age"><br>
        <input type="submit" value="提交参数"/>
    </form>

</body>
</html>
