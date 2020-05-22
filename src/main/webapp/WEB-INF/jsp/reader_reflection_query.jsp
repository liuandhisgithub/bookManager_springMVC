<%@ page import="com.book.domain.Reflection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>读后感信息</title>
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


<div style="padding: 30px 550px 10px">
    <form   method="post" action="reader_queryreflection_do.html" class="form-inline"  id="searchform">
        <div class="input-group">
            <input type="text" placeholder="输入读后感标题或书名" class="form-control" id="search" name="searchWord" class="form-control">
            <span class="input-group-btn">
                            <input type="submit" value="搜索" class="btn btn-default">
            </span>
        </div>
    </form>
    <script>
        function mySubmit(flag){
            return flag;
        }
        $("#searchform").submit(function () {
            var val=$("#search").val();
            if(val==''){
                alert("请输入关键字");
                return mySubmit(false);
            }
        })
    </script>
</div>
<div style="position: relative;top: 10%">
    <c:if test="${!empty succ}">
        <div class="alert alert-success alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${succ}
        </div>
    </c:if>
    <c:if test="${!empty error}">
        <div class="alert alert-danger alert-dismissable">
            <button type="button" class="close" data-dismiss="alert"
                    aria-hidden="true">
                &times;
            </button>
                ${error}
        </div>
    </c:if>
</div>
<c:if test="${!empty reflections}">
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                查询结果：
            </h3>
        </div>
        <div class="panel-body">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>标题</th>
                    <th>书名</th>
                    <th>作者id</th>
                    <th>收藏</th>
                    <th>详情</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${reflections}" var="reflection">
                    <tr>
                        <td><c:out value="${reflection.name}"></c:out></td>
                        <td><c:out value="${reflection.bookName}"></c:out></td>
                        <td><c:out value="${reflection.readerId}"></c:out></td>
                        <td><c:out value="${reflection.likeNum}"></c:out></td>
                        <td><a href="readerreflectiondetail.html?reflectionId=<c:out value="${reflection.reflectionId}"></c:out>"><button type="button" class="btn btn-success btn-xs">详情</button></a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>


</body>
</html>
