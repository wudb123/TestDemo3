<%--
  Created by IntelliJ IDEA.
  User: WDB
  Date: 2019/4/10
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
        #span{
            font-size: 25px;
            margin-left: 5px;
        }
        #div{
            float: right;
            margin:5px;
        }
        #div1{
            float: left;
        }
    </style>
    <script>
        window.onload = function(){
            var id = document.getElementById("del");
            id.onclick = function(){
                if(confirm("您确定要删除选中吗？")){
                    var flag = false;
                    var cbs = document.getElementsByName("select");
                    for (var i = 0;i < cbs.length;i++){
                        if(cbs[i].checked){
                            flag = true;
                            break;
                        }
                    }
                    if(flag){
                        document.getElementById("form").submit();
                    }
                }
            }
            var first = document.getElementById("first");
            first.onclick = function(){
                var cbs = document.getElementsByName("select");
                for (var i = 0;i < cbs.length;i++){
                    cbs[i].checked = first.checked;
                }
            }
        }
        function deleteUser(id){
            if(confirm("您确定要删除吗？")){
                location.href = "${pageContext.request.contextPath}/userDelServlet?id="+id;
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div id="div1">
        <form class="form-inline" action="${pageContext.request.contextPath}/userFindByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name"  value="${information.name[0]}" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName1">籍贯</label>
                <input type="text" name="address" value="${information.address[0]}" class="form-control" id="exampleInputName1" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" name="email" value="${information.email[0]}" class="form-control" id="exampleInputEmail2" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div id="div">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0)" id="del">删除选中</a>
    </div>
    <form action="${pageContext.request.contextPath}/userDelSelectedServlet" method="post" id="form">
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th><input type="checkbox" id="first"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${pb.list}" var="user" varStatus="s">
            <tr>
                <td><input type="checkbox" name="select" value="${user.id}"></td>
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/userFindServlet?id=${user.id}">修改
                    </a>&nbsp;<a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${pb.currentPage==1}">
                <li class="disabled">
            </c:if>
            <c:if test="${pb.currentPage!=1}">
                <li>
            </c:if>
                <a href="${pageContext.request.contextPath}/userFindByPageServlet?currentPage=${pb.currentPage-1}&raw=7&name=${information.name[0]}&address=${information.address[0]}&email=${information.email[0]}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach begin="1" end="${pb.totalPage}" var="i">
                <c:if test="${pb.currentPage == i}">
                <li class="active"><a href="${pageContext.request.contextPath}/userFindByPageServlet?currentPage=${i}&raw=7&name=${information.name[0]}&address=${information.address[0]}&email=${information.email[0]}">${i}</a></li>
                </c:if>
                <c:if test="${pb.currentPage != i}">
                    <li><a href="${pageContext.request.contextPath}/userFindByPageServlet?currentPage=${i}&raw=7&name=${information.name[0]}&address=${information.address[0]}&email=${information.email[0]}">${i}</a></li>
                </c:if>
            </c:forEach>

            <c:if test="${pb.currentPage==pb.totalPage}">
                <li class="disabled">
            </c:if>
            <c:if test="${pb.currentPage!=pb.totalPage}">
                <li>
            </c:if>
                <a href="${pageContext.request.contextPath}/userFindByPageServlet?currentPage=${pb.currentPage+1}&raw=7&name=${information.name[0]}&address=${information.address[0]}&email=${information.email[0]}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <span id="span">
            共${pb.totalCount}条记录，共${pb.totalPage}页
        </span>
        </ul>
    </nav>
</div>
</body>
</html>

