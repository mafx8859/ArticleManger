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
    
    <title>文章管理</title>
    
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
  
  <!-- <body>
      <div class="mainDiv">
      <ul style="text-align:left;"><a onclick="back()" style="color:red;text-align:left;">《《返回</a></ul>
        <table id="mainTab">
           <tr><th>序号</th><th>题目</th><th>作者</th><th>查看</th><th>编辑</th><th>删除</th></tr>
           <c:forEach items="${art}" var="article">
           <tr><td>${article.id}</td><td>${article.title}</td><td>${article.issure}</td><td><a href="AllServlet?flag=lookArticle&id=${article.id}">查看</a></td><td><a href="AllServlet?flag=editorArticle&id=${article.id}">编辑</a></td><td><a href="AllServlet?flag=delArticle&id=${article.id}">删除</a></td></tr>
           </c:forEach>
        </table> 
        <p>
           <font>共查询到：${messageCount}条信息</font>
           <font><a href="AllServlet?flag=selectMessage&pageNow=${param.pageNow-1}">上一页</a>&nbsp;&nbsp;<a href="AllServlet?flag=selectMessage&pageNow=${param.pageNow+1}">下一页</a></font>
           </p>
      </div>
      <form action="writeArticle.jsp"><input type="submit" value="写文章"/></form>
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
                页面访问次数:<%=application.getAttribute("count").toString()%>
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
<c:forEach var="artclass" items="${articleClass}">
<li><a href="AllServlet?flag=selectByClass&id=${artclass.id}">${artclass.articleClass}</a></li>
</c:forEach>
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
    <div class="mainDiv">
    <table id="mainTab">
           <tr><th>序号</th><th>题目</th><th>作者</th><th>查看</th><th>编辑</th><th>删除</th><th>排序</th></tr>
           <c:if test="${empty art }">
               <tr><td><font style="color:red;">未查找到该类新闻</font></td></tr>
           </c:if>
           <c:forEach items="${art}" var="article">
           <tr><td align="center">${article.id}</td><td>${article.title}</td><td>${article.issure}</td>
           <td align="center"><a href="AllServlet?flag=lookArticle&id=${article.id}"><img width="20px" height="20px" src="images/look.PNG">&nbsp;&nbsp;查看</a></td>
           <td align="center"><a href="AllServlet?flag=editorArticle&id=${article.id}"><img width="20px" height="20px" src="images/editor.PNG">&nbsp;&nbsp;编辑</a></td>
           <td align="center"><a href="AllServlet?flag=delArticle&id=${article.id}"><img width="20px" height="20px" src="images/del.PNG">&nbsp;&nbsp;删除</a></td>
           <td align="center">
           <a href="AllServlet?what=up&flag=sort&id=${article.id}"><img width="15px" height="20px" src="images/sortUp.PNG"/></a>
           <a href="AllServlet?what=down&flag=sort&id=${article.id}"><img width="15px" height="20px" src="images/sortDown.PNG"/></a>
           </td></tr>
           </c:forEach>
        </table> 
        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
           <font>共查询到：${messageCount}条信息</font>
           <font><a href="AllServlet?flag=selectMessage&pageNow=${param.pageNow-1}">上一页</a>&nbsp;&nbsp;<a href="AllServlet?flag=selectMessage&pageNow=${param.pageNow+1}">下一页</a></font>
           </p>
</div>
</div>
</body>
</html>
