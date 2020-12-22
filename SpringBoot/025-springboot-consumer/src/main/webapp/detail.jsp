<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>Title</title>
</head>
<body>
    <h6>${student.id}</h6>
    <h6>${student.name}</h6>
    <h6>${student.age}</h6>
    <h6>${count}</h6>

</body>
</html>
