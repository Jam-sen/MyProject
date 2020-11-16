<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2020/11/12
  Time: 4:40 下午
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

    $.ajax({
        url: "",
        data: {

        },
        type: "",
        dataType: "",
        success:function () {

        }
    })
    //获取当前用户名
    ((User) request.getSession().getAttribute("user")).getName()
</head>
<body>

</body>
</html>
