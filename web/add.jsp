<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->
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
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script>
        window.onload = function(){
            var id = document.getElementById("return");
            id.onclick = function(){
                location.href = "${pageContext.request.contextPath}/userListServlet";
            }
            document.getElementById("form").onsubmit = function(){
                return checkName() && checkAge() && checkQq() &&checkEmail();
            }
            document.getElementById("name").onblur = checkName;
            document.getElementById("age").onblur = checkAge;
            document.getElementById("qq").onblur = checkQq;
            document.getElementById("email").onblur = checkEmail;
            }
        function checkName(){
            var name = document.getElementById("name").value;
            var reg_name = /^\S{2,12}$/;
            var flag = reg_name.test(name);
            var s_name = document.getElementById("span_name");
            if(flag){
                s_name.innerHTML ="<img height='30px' src='img/gou.png' />";
            }else{
                s_name.innerHTML = "用户名只能为4到12位";
            }
            return flag;
        }
        function checkAge(){
            var age = document.getElementById("age").value;
            var reg_age = /^\d{2}$/;
            var flag = reg_age.test(age);
            var s_age = document.getElementById("span_age");
            if(flag){
                s_age.innerHTML ="<img height='30px' src='img/gou.png' />";
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
                s_qq.innerHTML ="<img height='30px' src='img/gou.png' />";
            }else{
                s_qq.innerHTML = "qq格式不正确";
            }
            return flag;
        }
        function checkEmail(){
            var email = document.getElementById("email").value;
            var reg_email = /^\S{7,30}$/;
            var flag = reg_email.test(email);
            var s_email = document.getElementById("span_email");
            if(flag){
                s_email.innerHTML ="<img height='30px' src='img/gou.png' />";
            }else{
                s_email.innerHTML = "邮箱格式不正确";
            }
            return flag ;
        }
    </script>
</head>
<body>
<div class="container">
    <center><h3>添加联系人页面</h3></center>
    <form action="${pageContext.request.contextPath}/userAddServlet" id="form" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名" >
            <span id="span_name"></span>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="gender" value="男" checked="checked"/>男
            <input type="radio" name="gender" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" placeholder="请输入年龄">
            <span id="span_age"></span>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control" id="jiguan">
                <option value="广东">广东</option>
                <option value="广西">广西</option>
                <option value="湖南">湖南</option>
            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" id="qq" name="qq" placeholder="请输入QQ号码"/>
            <span id="span_qq"></span>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱地址"/>
            <span id="span_email"></span>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"  />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" id="return" value="返回" />
        </div>
    </form>
</div>
</body>
</html>