<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>注册</title>
    <link rel="shortcut icon" href="${path}/static/images/favicon.ico">
    <link href="${path}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${path}/static/css/flat-ui.min.css" rel="stylesheet">
    <link href="${path}/static/css/animate.min.css" rel="stylesheet">
    <link href="${path}/static/css/app.css" rel="stylesheet">
    <style>
        td{
            padding: 10px 5px;
        }
    </style>
</head>
<body  style="overflow-x: hidden">
    <div class="bg-one" style="height: 100vh">
        <div class="container-fluid text-center animated fadeInDown" style="margin-top: 5%">
            <h1 class="logo-name">AUST</h1>
            <h3>欢迎使用 Aust Code</h3>
        </div>
        <div class="row animated fadeInUp">
            <div class="col-lg-12 text-center">
                <form role="form" style="margin-top: 20px" action="/register" method="post">
                    <table style="margin: 20px auto;min-width: 33vw">
                        <tr>
                            <td width="25%" class="text-right"><strong>用户名:</strong>&nbsp;&nbsp;</td>
                            <td width="50%"><input type="text" class="form-control" placeholder="用户名" required minlength="3" maxlength="30" name="username" value="${username}"></td>
                            <td width="20%" class="text-danger text-left">*${username}</td>
                        </tr>
                        <tr>
                            <td width="25%" class="text-right"><strong>邮箱:</strong>&nbsp;&nbsp;</td>
                            <c:if test="${signEmail != null}">
                                <td width="50%"><input type="email" class="form-control" placeholder="example@XX.com" required name="email" value="${signEmail}"></td>
                            </c:if>
                            <c:if test="${signEmail == null}">
                                <td width="50%"><input type="email" class="form-control" placeholder="example@XX.com" required name="email" value="${email}"></td>
                            </c:if>
                            <td width="25%" class="text-danger text-left">*${email}</td>
                        </tr>
                        <tr>
                            <td width="25%" class="text-right"><strong>密码:</strong>&nbsp;&nbsp;</td>
                            <td width="50%"><input type="password"  class="form-control" placeholder="Password" minlength="6" maxlength="30" required name="password" id="password"></td>
                            <td width="20%" class="text-danger text-left">*${password}</td>
                        </tr>
                        <tr>
                            <td width="25%" class="text-right"><strong>确认密码:</strong>&nbsp;&nbsp;</td>
                            <td width="50%"><input type="password" class="form-control" placeholder="Password" minlength="6" maxlength="30" required name="password2" id="password2" onblur="check(this)"></td>
                            <td width="20%" class="text-danger text-left passowrd2">*${password2}</td>
                        </tr>
                        <tr>
                            <td width="25%" class="text-right"><strong>验证码:</strong>&nbsp;&nbsp;</td>
                            <td width="50%" class="text-left">
                                <input type="text" class="form-control" required name="codevalidate" style="width: 40%;display: inline">
                                <img id="codevalidate" src="/code/<%=new Date().getTime()%>" width="90" height="30" style="margin-left: 10px" onclick="changeUrl()">
                            </td>
                            <td width="20%" class="text-danger text-left passowrd2">*${codeerror}</td>
                        </tr>
                        <tr>
                            <td class="text-right"></td>
                            <td class="text-left">
                                <a href="/login" class="btn btn-primary">登&nbsp;&nbsp;录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <button type="submit" class="btn btn-primary">注&nbsp;&nbsp;册</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <script src="${path}/static/js/jquery.min.js"></script>
<script>
    function check(input){
        var password = $("#password").val();
        if (password != input.value){
            $(".passowrd2").html('两次输入密码不一致');
            $("button[type='submit']").addClass('disabled');
        }else {
            $(".passowrd2").html(' ');
            $("button[type='submit']").removeClass('disabled');
        }
    }
    function changeUrl() {
        var url = $("#codevalidate").prop('src');
        url = url.substr(0,url.lastIndexOf('/')+1);
        url = url + (new Date()).valueOf();
        $("#codevalidate").prop('src',url);
    }
</script>
</body>
</html>