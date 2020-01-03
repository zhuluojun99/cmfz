<%@page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>login UI</title>
    <link rel="stylesheet" href="css/Login.css">
    <script type="text/javascript" src="${path}/jqGrid/js/jquery.min.js"></script>
</head>
<style>
    #dxx{

        position:relative;
        left:190px;
        bottom:20px;
        top:-80px;
        width:1px;
        height: 1px;
    }
    #bxx{

        position:relative;
        left:680px;
        bottom:20px;
        top:40px;
        width:1px;
        height: 1px;
    }
</style>
<script>
    function sub2(){
        var user = $("#user").val();
        var pass = $("#pass").val();
        var yzm = $("#yzm").val();
        var data =  $("#f").serialize();
        if(user!=''&&pass!=''&&yzm!=''){
            $.ajax({
                url: "${path}/Admin/login",
                type: "POST",
                data: "username="+user+"&password="+pass+"&yzm="+yzm,
                success: function (result) {
                    console.log(result)
                    if(result == "ok"){
                        location.href = "${path}/backstage.jsp"
                    }else{
                        $("#tip").empty();
                        $("#tip").append(result);
                    }
                }
            });
        }

    }



</script>
<body>
<div id="bxx"><iframe id="tianqi"  scrolling="no" frameborder="0" allowtransparency="true" src="http://i.tianqi.com/index.php?c=code&a=getcode&id=34&h=25&w=280"></iframe></div>

<div class="box">
    <h2>持明法州后台管理系统</h2>
    <br>
    <h5 style="color: red" id="tip"></h5>
    <form onsubmit="return false;">
        <div class="inputBox">
            <input type="text" maxlength="20" name="email" id="user" required="">
            <label>用户名</label>
        </div>
        <div class="inputBox">
            <input type="password" maxlength="20" id="pass" name="pass" required="">
            <label>密码</label>
        </div>
        <div class="inputBox">
            <input maxlength="4" type="text" name="yzm" id="yzm" required="">
            <label>验证码</label>
        </div>
        <div id="dxx">
            <img src="${path}/code/getGifCode" id="code" onclick="document.getElementById('code').src='${path}/code/getGifCode?time='+new Date().getTime()">
        </div>
        <input type="submit" name="" onclick="sub2()" value="登录">
    </form>
</div>
</body>
</html>