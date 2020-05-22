<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>《 ${detail.name}》</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <style>
        body{
            background-color: rgb(240,242,245);
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default" role="navigation" style="background-color:#fff">
    <div class="container-fluid">
        <div class="navbar-header" style="margin-left: 8%;margin-right: 1%">
            <a class="navbar-brand " href="reader_main.html"><p class="text-primary">我的图书馆</p></a>
        </div>
        <div class="collapse navbar-collapse" id="example-navbar-collapse">
            <ul class="nav navbar-nav navbar-left">
                <li >
                    <a href="reader_querybook.html" >
                        图书查询
                    </a>
                </li>
                <li>
                    <a href="reader_info.html" >
                        个人信息
                    </a>
                </li>
                <li class="active">
                    <a href="reader_queryreflection.html" >
                        读后感查询
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        我的收藏
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="likeBook.html">收藏图书</a></li>
                        <li class="divider"></li>
                        <li><a href="likeReflection.html">收藏读后感</a></li>
                    </ul>
                </li>
                <li >
                    <a href="reader_repasswd.html" >
                        密码修改
                    </a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="reader_info.html"><span class="glyphicon glyphicon-user"></span>&nbsp;${readercard.name}，已登录</a></li>
                <li><a href="login.html"><span class="glyphicon glyphicon-log-in"></span>&nbsp;退出</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="col-xs-6 col-md-offset-3" style="position: relative;top: 3%">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">《 ${detail.name}》</h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <tr>
                    <th width="15%">标题</th>
                    <td>${detail.name}</td>
                </tr>
                <tr>
                    <th>书名</th>
                    <td>${detail.bookName}</td>
                </tr>
                <tr>
                    <th>作者id</th>
                    <td>${detail.readerId}</td>
                </tr>
                <tr>
                	<th>类型</th>
                	<td>${detail.classId}</td>
                </tr>
                <tr>
                    <th>收藏</th>
                    <td>${detail.likeNum}</td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td>${detail.introduction}</td>
                </tr>
                </tbody>
            </table>
	        <c:if test="${empty noReader}">
	        	<!-- 显示收藏 或者 取消收藏 -->
		        <c:if test="${!empty notlike}">
					<a href="reader_like_reflection.html?reflectionId=<c:out value="${detail.reflectionId}"></c:out>&classId=<c:out value="${detail.classId}"></c:out>"><button type="button" class="btn btn-success btn-xs">收藏</button></a>
				</c:if>
				<c:if test="${!empty like}">
					<a href="reader_not_like_reflection.html?reflectionId=<c:out value="${detail.reflectionId}"></c:out>"><button type="button" class="btn btn-danger btn-xs">取消收藏</button></a>
				</c:if>
				<!-- 显示写读后感 -->
			</c:if>
        </div>
    </div>

</div>

</body>
</html>
