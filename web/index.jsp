<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <br>
  <br>
  <br>
  	<a href="http://localhost:8080/web/groups">http://localhost:8080/web/groups  此接口返回infos接口使用的type值，这些值可在客户端写死</a>
  	<br>
    <a href="http://localhost:8080/web/infos?pagesize=20&pageindex=1&type=gndy">http://localhost:8080/web/infos?pagesize=20&pageindex=1&type=gndy</a>
    <br>
    <a href="http://localhost:8080/web/detail?detailid=3000">http://localhost:8080/web/detail?detailid=3000</a>
    <br>
    <a href="http://localhost:8080/web/search?searchKey=love&pagesize=20&pageindex=1">http://localhost:8080/web/search?searchKey=%E6%88%98%E4%BA%89&pagesize=20&pageindex=1</a>
  </body>
</html>
