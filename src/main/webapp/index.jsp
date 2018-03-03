<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet"   type="text/css" href="css/login.css"/>
	<!--  <link rel="stylesheet" type="text/css" href="css/styles.css">-->
	
  </head>
  <body>
  <!--  <div class="loginDiv">
  <table>
     <form action="AllServlet?flag=login" method="post">
               <tr><td>用户名：</td><td><input type="text" name="username"/></td></tr>
               <tr><td>密码：<td><input type="password" name="pwd"/></td></tr>
               <tr><td><input type="submit" value="登录"/></td><td><a href="reginster.jsp">注册</a></td></tr>
     </form>
   </table>
  </div>-->
 <div class="login_div">
<ul class="login_ul">
<form action="AllServlet?flag=login" method="post">
  <input size=17 type="text" name="username"/><br>
  <input size=17  style="margin-top:8px;"type="password" name="pwd"/><br><br>
  <li style="list-style-type:none;margin-top:-47px;margin-left:125px;"><input type="submit" value="           "/></li>
</form>
 <li style="list-style-type:none; font-size:14px;color:red;margin-left:-40px;">${message}</li>
</ul>
</div>
  </body>
</html>
