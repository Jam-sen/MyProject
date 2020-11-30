
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>但处理器方法返回ModelAdnView实现Forward</p>
    <form action="doForward.do" method="post">
        姓名：<input type="text" name="name"/><br>
        年龄：<input type="text" name="age"/><br>
        <input type="submit" value="提交">
    </form>
    <br>
    <p>但处理器方法返回ModelAdnView实现redirect（重定向）</p>
    <form action="doRedirect.do" method="post">
        姓名：<input type="text" name="name"/><br>
        年龄：<input type="text" name="age"/><br>
        <input type="submit" value="提交">
    </form>
</body>
</html>