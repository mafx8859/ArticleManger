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
    
    <title>My JSP 'lookArticle.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
	<link rel="stylesheet" type="text/css" href="css/allCss.css"/>
	<script language='javascript'>
	function back(){history.back(-1);}
	
	</script>

  </head>
  
  <!--  <body>
    <div class="detailCenter">
    <br><ul style="text-align:left;"><a onclick="back()" style="color:red;text-align:left;">《《返回</a></ul><br><br>
    题目： ${art.title}&nbsp;&nbsp;
   发布人：${art.issure}&nbsp;&nbsp;
   发布日期：${art.date}<br>
   文章内容：${art.article}<br>
    </div>
  </body>-->
  <body>
<div class="top_div">
  <ul class="top_ul">
      <li style="margin-top:5px;"><img src="images/logo.png" width="150px" height="40px"></li>
      <li style="font-size:30px; padding-top:0.5%;margin-left:50px;">新闻管理系统</li>
      <li class="li1" style="padding-top:1%;"><form action="mangerArticle.jsp" method="post"><input style="background-color:#45CCE9;" type="submit" value="首页" /></form></li>
      <li class="li1" style="padding-top:1%;"><button style="background-color:#45CCE9;" onclick="back()">返回</button></li>
      <li class="li1" style="padding-top:1%;"><form action="AllServlet?flag=safeout" method="post"><input style="background-color:#45CCE9;" type="submit" value="退出" /></form></li>
      <li class="li1" style="padding-top:1%;"><form action="writeArticle.jsp"><input style="background-color:#45CCE9;" type="submit" value="写文章"/></form></li>
      <li class="li1" style="padding-top:1%;"><form action="index.jsp" method="post"><input style="background-color:#45CCE9;" type="submit" value="个人资料修改" /></form></li>
      <li class="li1" style="padding-top:1%;">
      <%
      if(application.getAttribute("count")==null){
          application.setAttribute("count",new Integer(0));
      }
      int count=Integer.parseInt(application.getAttribute("count").toString());
      count++;
      application.setAttribute("count",count);
       %>
                登录次数:<%=application.getAttribute("count").toString()%>
      </li>
      <li style="padding-top:1%;float:right;margin-right:44px;">
              今天是:<fmt:formatDate value="<%=new Date() %>" pattern="yyyy-MM-dd"/> 
      </li>
  </ul>
</div>
<div class="left_div">
<ul style="height:700px;background-color:#FFF; width:260px;margin-left:20px;position:absolute;margin-top:10px;border-radius:5px;" id="UL">
<ul class="user_ul">当前用户：${sessionScope.user}</ul>
<ul class="manger_ul">管理菜单</ul>
<ul class="news_ul">
<li class="newsCenter_li"><img width="30px" height="30px" src="images/news1.png"/><span style="margin-left:35px;margin-top:-30px;display:block;">新闻中心</span></li>
<li><a href="AllServlet?flag=selectByClass&id=1">全部新闻</a></li>

<li><a href="AllServlet?flag=selectByClass&id=2">通知公告</a></li>

<li><a href="AllServlet?flag=selectByClass&id=3">东师新闻</a></li>

<li><a href="AllServlet?flag=selectByClass&id=4">待办事务</a></li>

<li><a href="AllServlet?flag=selectByClass&id=5">其他新闻</a></li>
</ul>
<ul class="news_ul">
<li class="newsCenter_li"><img width="30px" height="30px" src="images/user1.png"/><span style="margin-left:35px;margin-top:-30px;display:block;">用户管理</span></li>
<li>用户管理</li>
<li>修改密码</li>
<li>修改个人资料</li>

</ul>
</ul>
</div>
<div class="mangerCenter_div">
    <div class="detailCenter">
       文章内容：<br><br>${art.title} <br>
   ${art.article}<br>
      发布人：${art.issure}&nbsp;&nbsp;
      发布日期：${art.date}<br>
    </div>
</div>
</body>
</html>
