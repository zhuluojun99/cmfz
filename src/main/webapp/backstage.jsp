<%@page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script type="text/javascript" src="${path}/jqGrid/js/jquery.min.js"></script>
    <!-- 引入css样式 -->
    <link rel="stylesheet" href="${path}/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/jqGrid/css/trirand/ui.jqgrid-bootstrap.css">
    <!-- 引入js样式 -->
    <script type="text/javascript" src="${path}/boot/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${path}/jqGrid/js/trirand/2/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${path}/jqGrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/boot/js/ajaxfileupload.js"></script>
    <title>欢迎访问持明法州后台管理页面</title>
    <style>
        #bxx{
            position:relative;
            left:800px;
            bottom:20px;
            top:-40px;
            width:1px;
            height: 1px;
        }
        #myCarousel  .carousel-inner > .item > img {
            display: block;
            width:1200px;
            height:400px;
        }
        #kio{
            position:relative;
            left:55px;
            bottom:20px;
            top:12px;
        }
    </style>
    <script>
        $(function () {
            <c:if test="${queryAll1==null}">
                location.href="${path}/Banner/QueryBanner"
            </c:if>
            setInterval(function(){
                document.getElementById("time").innerHTML = new Date().toLocaleString();
            },1000);
            $('.carousel').carousel({
                interval: 5000
            })
        });
    </script>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${path}/backstage.jsp">持明法州管理系统</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:  ${sessionScope.admin.username}</a></li>
                <li><a href="${path}/Admin/logout">退出登录  <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <div class="panel-group" id="panel-469724">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-469724" href="#panel-element-984020"><center>用户管理</center></a>
                    </div>
                    <div id="panel-element-984020" class="panel-collapse collapse">
                        <div class="panel-body">
                            <center><button type="button" class="btn btn-danger" href="${pageContext.request.contextPath}/User/QueryAll"  onclick="init()" id="button" data-toggle="tab">用户管理</button></center>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-469724" href="#panel-element-688647"><center>上师管理</center></a>
                    </div>
                    <div id="panel-element-688647" class="panel-collapse collapse">
                        <div class="panel-body">
                            <center><button type="button" class="btn btn-danger">上师管理</button></center>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-469724" href="#panel-element-688649"><center>文章管理</center></a>
                    </div>
                    <div id="panel-element-688649" class="panel-collapse collapse">
                        <div class="panel-body">
                            <center>
                                <button class="btn btn-danger">
                                    <a href="javascript:$('#divss').load('${path}/article.jsp')">文章管理</a>
                                </button>
                            </center>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-469724" href="#panel-element-688640"><center>专辑管理</center></a>
                    </div>
                    <div id="panel-element-688640" class="panel-collapse collapse">
                        <div class="panel-body">
                            <center>
                            <button class="btn btn-danger">
                                <a href="javascript:$('#divss').load('${path}/album.jsp')">专辑列表</a>
                            </button>
                            </center>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a class="panel-title" data-toggle="collapse" data-parent="#panel-469724" href="#panel-element-6886400"><center>轮播图管理</center></a>
                    </div>
                    <div id="panel-element-6886400" class="panel-collapse collapse">
                        <div class="panel-body">
                        <center>
                            <button class="btn btn-danger">
                                <a href="javascript:$('#divss').load('${path}/banner.jsp')">轮播图管理</a>
                            </button>
                        </center>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-sm-10" id="divss">
            <div id="huany" class="container">
                <div class="jumbotron">
                    <h2 ><big>欢迎来到持明法州后台管理系统<small id="time"></small></big></h2>
                    <div id="bxx"><iframe id="tianqi"  scrolling="no" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&a=getcode&id=34&h=25&w=280"></iframe></div>
                </div>

                <!--轮播区域-->
                <div  class="carousel slide" style="width: 1140px" id="myCarousel" data-ride="carousel" data-interval="1500">
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner" style="height: 400px;">
                                <div class="item active">
                                    <img src="${pageContext.request.contextPath}/images/${queryAll1.img}">
                                    <div class="carousel-caption">${queryAll1.title}</div>
                                </div>
                                <div class="item">
                                    <img src="${pageContext.request.contextPath}/images/${queryAll2.img}">
                                    <div class="carousel-caption">${queryAll2.title}</div>
                                </div>
                                <div class="item">
                                    <img src="${pageContext.request.contextPath}/images/${queryAll3.img}">
                                    <div class="carousel-caption">${queryAll3.title}</div>
                                </div>
                    </div>
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right"></span>
                    </a>
                </div>
        </div>
    </div>

</div>


    <nav class="navbar navbar-default navbar-fixed-bottom">
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${path}/Admin/adminOut"><button class="btn btn-primary">导出管理员信息</button></a>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${path}/Banner/bannerOut"><button class="btn btn-primary">导出轮播图</button></a>
        <div id="kio"  class="container">
           <center>百知教育baizhi@zparkhr.com.cn</center>
        </div>
    </nav>

</body>
</html>