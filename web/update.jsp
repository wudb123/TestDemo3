<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>修改用户</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <script src="js/jquery-2.1.0.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script>
            window.onload = function(){
                var id = document.getElementById("return");
                id.onclick = function(){
                    location.href = "${pageContext.request.contextPath}/userListServlet";
                }
                document.getElementById("form").onsubmit = function(){
                    return checkAge() && checkQq() &&checkEmail();
                }
                document.getElementById("age").onblur = checkAge;
                document.getElementById("qq").onblur = checkQq;
                document.getElementById("email").onblur = checkEmail;
            }
            function checkAge(){
                var age = document.getElementById("age").value;
                var reg_age = /^\d{2}$/;
                var flag = reg_age.test(age);
                var s_age = document.getElementById("span_age");
                if(flag){
                    s_age.innerHTML ="";
                }else{
                    s_age.innerHTML = "年龄只能为两位数";
                }
                return flag;
            }
            function checkQq(){
                var qq = document.getElementById("qq").value;
                var reg_qq = /^\d{6,12}$/;
                var flag = reg_qq.test(qq);
                var s_qq = document.getElementById("span_qq");
                if(flag){
                    s_qq.innerHTML ="";
                }else{
                    s_qq.innerHTML = "qq格式不正确";
                }
                return flag;
            }
            function checkEmail(){
                var email = document.getElementById("email").value;
                var reg_email = /^\S{11,30}$/;
                var flag = reg_email.test(email);
                var s_email = document.getElementById("span_email");
                if(flag){
                    s_email.innerHTML ="";
                }else{
                    s_email.innerHTML = "邮箱格式不正确";
                }
                return flag ;
            }
        </script>
    </head>
    <body>
        <div class="container" style="width: 400px;">
        <h3 style="text-align: center;">修改联系人</h3>
        <form action="${pageContext.request.contextPath}/userUpdateServlet?id=${user.id}&" id="form" method="post">
          <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${user.name}" readonly="readonly" placeholder="请输入姓名" />
          </div>

          <div class="form-group">
            <label>性别：</label>
              <c:if test="${user.gender == '男'}">
                  <input type="radio" name="gender" value="男" checked />男
                  <input type="radio" name="gender" value="女"  />女
              </c:if>
              <c:if test="${user.gender == '女'}">
                  <input type="radio" name="gender" value="男"  />男
                  <input type="radio" name="gender" value="女" checked />女
              </c:if>

          </div>

          <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age"  name="age" value="${user.age}" placeholder="请输入年龄" />
              <span id="span_age"></span>
          </div>

          <div class="form-group">
            <label for="address">籍贯：</label>
             <select name="address" class="form-control" >
                 <c:if test="${user.address == '广东'}">
                     <option value="广东" selected>广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南">湖南</option>
                 </c:if>

                 <c:if test="${user.address == '广西'}">
                     <option value="广东" >广东</option>
                     <option value="广西" selected>广西</option>
                     <option value="湖南">湖南</option>
                 </c:if>

                 <c:if test="${user.address == '湖南'}">
                     <option value="广东" >广东</option>
                     <option value="广西">广西</option>
                     <option value="湖南" selected>湖南</option>
                 </c:if>
            </select>
          </div>

          <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" id="qq" name="qq" value="${user.qq}" placeholder="请输入QQ号码"/>
              <span id="span_qq"></span>
          </div>

          <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" value="${user.email}" placeholder="请输入邮箱地址"/>
              <span id="span_email"></span>
          </div>

             <div class="form-group" style="text-align: center">
                <input class="btn btn-primary" type="submit" value="提交" />
                <input class="btn btn-default" type="reset" value="重置" />
                <input class="btn btn-default" type="button" id="return" value="返回"/>
             </div>
        </form>
        </div>
    </body>
</html>