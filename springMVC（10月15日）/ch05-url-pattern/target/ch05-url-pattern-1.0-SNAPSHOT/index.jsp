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
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#btn1").click(function () {
                $.ajax({
                    // url: "test/returnStudentJson.do",
                    // url: "test/returnStudentListJson.do",
                    url: "test/returnStringData.do",
                    data: {name: "zhangsan", age: 20},
                    type: "post",
                    // dataType: "json",
                    success: function (result) {
                    //从服务器端反回的是json格式的字符串，Jquery会把字符串转为json对象，赋值给result形参。
                        alert("返回的是文本数据"+result);
                    }
                })
            })
        })
    </script>
</head>
<body>
<form action="test/some.do" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    <input type="submit" value="提交参数"/>
</form>
<br>
<button id="btn1">发起ajax请求</button>
<br>
<img src="images/timg.jpg" alt="我是一个静态资源，不能显示"/>
</body>
</html>
