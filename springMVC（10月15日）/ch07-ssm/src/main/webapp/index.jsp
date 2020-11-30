<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="js/jquery-3.4.1.js"></script>
    <script type="application/javascript">
        $(function () {
            loadStudentData();
            $("#btn1").click(function () {
               loadStudentData();
            })
            function loadStudentData() {
                $.ajax({
                    url:"student/showStudents.do",
                    dataType:"json",
                    success:function (result) {
                        //清除旧数据
                        $("#table1").empty()
                        //增加新数据
                        $.each(result,function(index,element) {
                            $("#table1").append("<tr><td>"+element.id+"</td><td>"+element.name+"</td><td>"+element.age+"</td></tr>")
                        })
                    }
                })
            }
        })
    </script>
</head>
<body>
<h2>Hello World!</h2>
<form action="student/register.do" method="post">
    name:<input type="text" name="name">
    age:<input type="text" name="age"/>
    <input type="submit" value="submit"><br>
</form>
<br>
<button id="btn1">查看所有学生</button>
<table id="table1" border="1px"></table>

</body>
</html>
