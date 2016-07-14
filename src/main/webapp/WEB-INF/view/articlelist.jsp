<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>文章列表</title>
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
<!--主体开始,对于article使用problem的css-->
<div class="articlempage common-page">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
           <div class="col-md-9">
               <!--主面板-->
               <section class="problemsection animated fadeInUp">
                    <h5 style="border-bottom: 2px dotted gray;padding-bottom: 5px">当前搜索:
                    <c:if test="${search != null}">${search.substring(1,search.length()-1)}</c:if>
                    <c:if test="${search == null}">全部</c:if>
                    </h5>
                   <div class="row">
                    <div class="col-md-6">
                   <c:forEach items="${pageinfo.list}" var="article" varStatus="status">
                           <c:if test="${status.index % 2 == 0}">
                               <div class="article-list">
                                   <dl class="article-time text-center">
                                       <dd>${article.start_time.year + 1900}</dd>
                                       <dd>${article.start_time.month}-${article.start_time.date}</dd>
                                   </dl>
                                   <dl class="article-detail">
                                       <c:if test="${article.totop}">
                                           <dt><a href="/article/${article.id}" target="_blank"><span class="text-danger">[置顶]</span>${article.title}</a></dt>
                                       </c:if>
                                       <c:if test="${!article.totop}">
                                           <dt><a href="/article/${article.id}" target="_blank">${article.title}</a></dt>
                                       </c:if>
                                       <dd><i class="glyphicon glyphicon-user"></i><a href="/user/${article.user_id}">${article.nickname}</a></dd>
                                   </dl>
                                   <p class="article-mark">${article.summary}</p>
                                   <div class="article-footer">
                                       <i class="fa fa-bookmark"></i>&nbsp;
                                       <c:forTokens items="${article.tags}" delims="," var="tag" begin="0" end="4">
                                           <a href="/articlelist?search=${tag}">${tag}</a>
                                       </c:forTokens>
                                       <a href="/article/${article.id}" class="rigth" target="_blank">More</a>
                                   </div>
                               </div>
                           </c:if>
                   </c:forEach>
                       </div>
                       <div class="col-md-6">
                           <c:forEach items="${pageinfo.list}" var="article" varStatus="status">
                           <c:if test="${status.index % 2 != 0}">
                               <div class="article-list">
                                   <dl class="article-time text-center">
                                       <dd>${article.start_time.year + 1900}</dd>
                                       <dd>${article.start_time.month}-${article.start_time.date}</dd>
                                   </dl>
                                   <dl class="article-detail">
                                       <c:if test="${article.totop}">
                                           <dt><a href="/article/${article.id}" target="_blank"><span class="text-danger">[置顶]</span>${article.title}</a></dt>
                                       </c:if>
                                       <c:if test="${!article.totop}">
                                           <dt><a href="/article/${article.id}" target="_blank">${article.title}</a></dt>
                                       </c:if>
                                       <dd><i class="glyphicon glyphicon-user"></i><a href="/user/${article.user_id}">${article.nickname}</a></dd>
                                   </dl>
                                   <p class="article-mark">${article.summary}</p>
                                   <div class="article-footer">
                                       <i class="fa fa-bookmark"></i>&nbsp;
                                       <c:forTokens items="${article.tags}" delims="," var="tag" begin="0" end="4">
                                           <a href="#">${tag}</a>
                                       </c:forTokens>
                                       <a href="/article/${article.id}" class="rigth" target="_blank">More</a>
                                   </div>
                               </div>
                           </c:if>
                           </c:forEach>
                       </div>
                   </div>

                   <ul class="pagination-plain">
                       <c:if test="${pageinfo.hasPreviousPage}">
                           <li class="previous"><a href="/articlelist?offset=${pageinfo.prePage}">← Previous</a></li>
                       </c:if>
                       <c:if test="${pageinfo.hasNextPage}">
                           <li class="next"><a href="/articlelist?offset=${pageinfo.nextPage}">Newer →</a></li>
                       </c:if>
                       <c:if test="${!pageinfo.hasPreviousPage && !pageinfo.hasNextPage}">
                           <li class="next"><span class="text-danger">已经到底了</span></li>
                       </c:if>
                   </ul>
               </section>
           </div>
            <div class="col-md-3 widget animated fadeInRight">
                <!--侧边栏-->
                <aside class="widget">
                    <h5><span>搜索</span></h5>
                    <p>
                    <form class="form-inline" action="/articlelist">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="search" class="form-control" size="100%" value="${search.substring(1,search.length()-1)}" name="search">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                </span>
                            </div><!-- /input-group -->
                        </div><!-- /.form-group -->
                    </form>
                    </p>
                </aside>
                <%@include file="../common/aside_person.jsp"%>
                <%@include file="../common/aside_article.jsp"%>
                <%@include file="../common/aside_tags.jsp"%>
                <%@include file="../common/aside_cate.jsp"%>
            </div>
        </div>
    </div>
</div>
<!--主体结束-->
<footer>
    <%@include file="../common/footer.jsp"%>
</footer>
<!--模态框-->
<!--具体提交的模态框-->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">提交代码</h4>
            </div>
            <div class="modal-body">
                <form id="pro_form">
                    <div class="form-group">
                        <label class="radio-inline">选择编译器:</label>
                        <label class="radio-inline">
                            <input type="radio" name="checkcode"  value="1"> C++
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="checkcode" checked value="2"> Java
                        </label>
                    </div>
                    <div class="form-group">
                        <textarea class="form-control" rows="20" cols="20"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Commit</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!--具体提交的模态框结束-->
<!--模态框结束-->
<!--script引入-->
<script src="${path}/static/js/jquery.min.js"></script>
<script src="${path}/static/js/flat-ui.min.js"></script>
<script src="${path}/static/js/app.js"></script>
</body>
</html>