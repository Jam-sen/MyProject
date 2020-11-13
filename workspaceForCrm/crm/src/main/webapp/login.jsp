<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>"/>
    <meta charset="UTF-8">
    <link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            $("#loginName").val("");
            $("#loginPwd").val("");
            $("#loginName").focus();
            $("#submit").click(function () {
                login();
            })
            //为当前窗口绑定敲键盘事件
            //event:这个参数可以取得我们敲的是哪个键
            $(window).keydown(function (event) {
                //如果取得键位的码值为13，表示敲的是回车键
                if (event.keyCode === 13) {
                    //用户敲回车键，执行登录验证
                    login();
                }
            })
        })

        //普通的自定义的function方法，一定要写在$(function(){})的外面
        function login() {
            //取得账号密码,判断是否为空
            //将文本中的左右空格去掉，使用$.trim(文本)
            var loginName = $.trim($("#loginName").val());
            var loginPwd = $.trim($("#loginPwd").val());
            if (loginName === "" || loginPwd === "") {
                $("#msg").html("账号密码不能为空");
				//如果为空，则强制终止该方法
				return false;
            }
            //去后台验证登录相关操作
            $.ajax({
				url:"user/login.do",
                data: {
					"loginName":loginName,"loginPwd":loginPwd
				},
				type: "post",
				dataType: "json",
				success: function (result) {
					/*
						result={"success":true/false,"msg":"哪错了"}
					*/
					//如果登录成功
					if (result.flag) {
                        window.location.href = "workbench/index.jsp";
					//如果登录失败
					} else {
                        $("#msg").html(result.msg);
					}
				}
            });
        }
    </script>
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">
        CRM &nbsp;<span style="font-size: 12px;"> </span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input class="form-control" type="text" placeholder="用户名" id="loginName">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input class="form-control" type="password" placeholder="密码" id="loginPwd">
                </div>
                <div class="checkbox" style="position: relative;top: 30px; left: 10px;">

                    <span id="msg" style="color: red"></span>

                </div>
                <button type="button" id="submit" class="btn btn-primary btn-lg btn-block"
                        style="width: 350px; position: relative;top: 45px;">登录
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>