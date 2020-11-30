<%--
  Created by IntelliJ IDEA.
  User: ys
  Date: 2020/10/7
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        if (1 == (Integer) (request.getAttribute ("result"))){
    %>
        学生信息注册成功
    <%
        }else{
            System.out.println ("+++++"+request.getAttribute ("result"));
    %>
        注册失败
    <%
        }
    %>
</head>
<body>
</body>
</html>
