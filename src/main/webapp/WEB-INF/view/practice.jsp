<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>练习</title>
    <link rel="shortcut icon" href="${path}/static/images/favicon.ico">
    <link href="${path}/static/css/bootstrap.min.css" rel="stylesheet">
    <link href="${path}/static/css/flat-ui.min.css" rel="stylesheet">
    <link href="${path}/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="${path}/static/css/bootstrap-table.min.css" rel="stylesheet">
    <link href="${path}/static/css/animate.min.css" rel="stylesheet">
    <link href="${path}/static/css/app.css" rel="stylesheet">
</head>
<body>
<!--头部开始-->
<header>
    <!-- Static navbar -->
    <div class="navbar navbar-lg navbar-default" role="navigation" id="nav">
        <%@include file="../common/menu.jsp"%>
    </div>
</header>
<!--头部结束-->
<!--通知栏开始,主要用户发布一些通知-->
<div class="tips">
    <%@include file="../common/notify.jsp"%>
</div>
<!--通知栏结束-->
<!--主体开始-->
<div class="practicepage common-page">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
           <div class="col-md-9">
               <!--主面板-->
               <section class="animated fadeInLeft">
                   <strong>&nbsp;&nbsp;须知:</strong><br/>
                   <p class="page-text" style="text-indent: 2em;">
                       起步主要考察基本算法,大多都是课本上的例题,每一道题涉及的考点都会在题目列表中提及
                       起步主要考察基本算法,大多都是课本上的例题,每一道题涉及的考点都会在题目列表中提及
                       起步主要考察基本算法,大多都是课本上的例题,每一道题涉及的考点都会在题目列表中提及
                       起步主要考察基本算法,大多都是课本上的例题,每一道题涉及的考点都会在题目列表中提及
                       起步主要考察基本算法,大多都是课本上的例题,每一道题涉及的考点都会在题目列表中提及
                   </p>
                   <h4 class="text-center">题目列表</h4>
                <table class="table-bordered" width="100%" data-toggle="table" id="practice-table">
                    <thead>
                        <tr>
                            <th class="col-xs-1" data-sortable="true" data-formatter="problemid" data-field="problem_id" data-align="center">ID</th>
                            <th class="col-xs-5" data-formatter="problemtitle" data-field="title">题目标题</th>
                            <th class="col-xs-3" data-field="tag">涉及知识点</th>
                            <th class="col-xs-1" data-formatter="problemRatio" data-field="ratio" data-align="center">Ratio</th>
                            <th class="col-xs-2" data-formatter="problemacsubmit" data-field="acsubmit" data-align="center">AC/Submit</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
               </section>
           </div>
            <div class="col-md-3 widget animated fadeInRight">
                <!--侧边栏,这样便于调整顺序-->
                <%@include file="../common/aside_person.jsp"%>
                <%@include file="../common/aside_cate.jsp"%>
                <%@include file="../common/aside_tags.jsp"%>
                <%@include file="../common/aside_article.jsp"%>
            </div>
        </div>
    </div>
</div>
<!--主体结束-->

<footer>
    <%@include file="../common/footer.jsp"%>
</footer>

<!--script引入-->
<script src="${path}/static/js/jquery.min.js"></script>
<script src="${path}/static/js/flat-ui.min.js"></script>
<script src="${path}/static/js/bootstrap-table.min.js"></script>
<script src="${path}/static/js/bootstrap-table-zh-CN.min.js"></script>
<script src="${path}/static/js/app.js"></script>
<script src="${path}/static/js/table-demo.js"></script>
<script>
    <%--判断用户是否已经完成该题,思路先用一个js数组,把用户完成题目存储起来,然后再判断--%>
    var problem = new Array();
    <c:forEach items="${sessionScope.userAC}" var="id">
    problem.push(${id});
    </c:forEach>
    function contain(value) {
        var i = problem.length;
        while (i--){
            if (problem[i] == value){
                return true;
            }
        }
        return false;
    }
    //更改题目ID
    function problemid(value, row, index) {

        if (contain(value)){
            return [
                '<div class="text-center">',
                '<span class="text-primary"><i class="fa fa-thumbs-up"></i>' + value + '</span>',
                '</div>'
            ].join('');
        }else {
            return [
                '<div class="text-center">',
                '<span >' + value + '</span>',
                '</div>'
            ].join('');
        }
    }
</script>
</body>
</html>