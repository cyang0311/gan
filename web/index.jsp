<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: cy
  Date: 2019/7/6
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#sel").click(function () {
                var selText = document.getElementById("selText").value;
                $.post("sel",{"selName":selText,},function (data) {
                    var result ="";
                    for (var i = 0; i <data.length ; i++) {
                        result+="<tr>";
                        result+="<td>"+data[i].uname+"</td>"
                        result+="<td>"+data[i].phoneNum+"</td>"
                        result+="<td>"+data[i].identity+"</td>"
                        result+="</tr>";
                    }
                    $("#tbody").html(result);
                })
                return false;
            })
        })
    </script>
  </head>
  <body>
  <%  User user =null;
    if(request.getSession().getAttribute("user")!=null){
    user = (User) request.getSession().getAttribute("user");
  }%>
  当前用户：<% if (user!=null){%> <%=user.getUname()%> <% } else {%>
    <a href="login.jsp">点击登录</a><br>
  <%}%>
  <hr>
  员工姓名<input  id="selText" type="text"/>
  <input type="submit" value="查询" id="sel">
  <table border="0.1 #232155"style="align-items: center">
    <tr>
        <td>用户名</td>
        <td>手机号</td>
        <td>部门</td>
    </tr>
    <tbody id="tbody"></tbody>
  </table>
  <hr>
  <a href="download?fileName=aaa.txt">下载</a>
  <hr>
  <form action="upload"  enctype="multipart/form-data"  method="post">
    姓名：<input type="text" name="uname">
    选择文件：<input type="file" name="ufile">
    <input type="submit" value="提交">
  </form>
  </body>
</html>
