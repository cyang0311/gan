<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <%--<script src="https://libs.baidu.com/jquery/1.7.2/jquery.min.js"></script>--%>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#getcode").click(function () {
                var ucode=document.getElementById("ucode").value;
                var phoneNum=document.getElementById("phoneNum").value;
                if (phoneNum!=""){
                    $.post("getcode",{"phoneNum":phoneNum});
                }
            })
        })
    </script>
</head>
<body>
<a href="getcode">gan</a>
    <form action="reg" method="post"><br/>
        姓名<input type="text" name="uname" id="uname"><br/>
        密码<input type="text" name="upwd"><br/>
        手机号<input type="text" name="phoneNum" id="phoneNum"><br/>
        验证码<input type="text"  name="ucode" placeholder="输入手机号获取验证码" id="ucode">
        <input type="button" id="getcode" value="获取验证码" /><br/>
        <input type="submit" name="submit">
    </form>
    <a href="login.jsp">返回登录</a>
</body>
</html>
