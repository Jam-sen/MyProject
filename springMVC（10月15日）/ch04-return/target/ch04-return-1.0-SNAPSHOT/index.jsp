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
<p>处理器方法返回String 表示视图名称</p>
<form action="test/returnString-View.do" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    <input type="submit" value="提交参数"/>
</form>

<p>处理器方法返回String，如果需要在View中加入处理器方法中的数据，也可以手动添加数据到request作用域</p>
<form action="test/returnString-View2.do" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    <input type="submit" value="提交参数"/>
</form>

<p>处理器方法返回String，表示完整视图路径，此时不能配置视图解析器</p>
<form action="test/returnString-View3.do" method="post">
    姓名：<input type="text" name="name"><br>
    年龄：<input type="text" name="age"><br>
    <input type="submit" value="提交参数"/>
</form>
<br>
<button id="btn1">发起ajax请求</button>
</body>
</html>
