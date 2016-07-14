<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录</title>
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
            <h4 class="text-danger showmsg">${error}</h4>
        </div>
        <div class="row animated fadeInUp">
            <div class="col-lg-12">
                <form role="form" style="margin-top: 20px" action="${pageContext.request.contextPath}/login" method="post">
                    <table style="margin: 0 auto;min-width: 20vw" >
                        <tr>
                            <td  class="text-right"><strong>用户名:</strong>&nbsp;&nbsp;</td>
                            <td ><input type="text" class="form-control" placeholder="Username" required name="username" value="${username}"></td>
                        </tr>
                        <tr>
                            <td class="text-right"><strong>密码:</strong>&nbsp;&nbsp;</td>
                            <td><input type="password" class="form-control" placeholder="Password" required name="password"></td>
                        </tr>
                        <tr>
                            <td class="text-right"><strong>验证码:</strong>&nbsp;&nbsp;</td>
                            <td><input type="text" class="form-control" required name="codevalidate" style="width: 40%;display: inline">
                                <img id="codevalidate" src="/code/<%=new Date().getTime()%>" width="90" height="30" style="margin-left: 10px" onclick="changeUrl()">
                            </td>
                        </tr>
                        <tr>
                            <td class="text-right"></td>
                            <td><input type="checkbox" data-toggle="checkbox" value="" id="checkbox3" >
                                Remember me</td>
                        </tr>
                        <tr>
                            <td class="text-right"></td>
                            <td><a href="/register" class="btn btn-primary">注册</a>&nbsp;&nbsp;
                                <button type="submit" class="btn btn-primary">登录</button></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <script src="${path}/static/js/jquery.min.js"></script>
    <script>
        function jumpIndex() {
            var href = window.location.href;
            href = href.substr(0,href.length-6);
            window.location.href = href;
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