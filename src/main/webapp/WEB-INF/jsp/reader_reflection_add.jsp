<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编写读后感</title>
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
                <li class="active">
                    <a href="reader_querybook.html" >
                        图书查询
                    </a>
                </li>
                <li>
                    <a href="reader_info.html" >
                        个人信息
                    </a>
                </li>
                <li>
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

<div style="position: relative;top: 10%;width: 80%;margin-left: 10%">
            <form action="reflection_add_do.html" method="post" id="addbook" >
                <div class="form-group">
                    <label for="name">标题</label>
                    <input type="text" class="form-control" name="name" id="name" placeholder="请输入标题">
                </div>
                <div class="form-group">
                    <label for="author">书名</label>
                    <input type="text" class="form-control" name="bookName" id="bookName"  value="${bookName}" readonly="readonly">
                </div>
                <div class="form-group">
                    <label for="publish">作者id</label>
                    <input type="text" class="form-control"  name="readerId" id="readerId"  value="${readerId}" readonly="readonly">
                </div>
                <div class="form-group">
                    <label for="publish">类型</label>
                    <input type="text" class="form-control"  name="classId" id="classId"  value="${classId}" readonly="readonly">
                </div>
                <div class="form-group">
                    <label for="introduction">内容</label>
                    <textarea class="form-control" rows="30"  name="introduction" id="introduction" placeholder="请输入内容"></textarea>
                </div>


                <input type="submit" value="发布" class="btn btn-success btn-sm" class="text-left">
                <script>
                    function mySubmit(flag){
                        return flag;
                    }
                    $("#addbook").submit(function () {
                        if($("#name").val()==''||$("#bookName").val()==''||$("#readerId").val()==''||$("#introduction").val()==''){
                            alert("请填入完整读后感信息！");
                            return mySubmit(false);
                        }
                    })
                </script>
            </form>

</div>



</body>
</html>
