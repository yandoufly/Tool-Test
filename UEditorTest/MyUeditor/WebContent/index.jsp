<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>  
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
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
	<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="js/ueditor/lang/zh-cn/zh-cn.js"></script>
	
	<link type="text/css" rel="stylesheet" href="js/ueditor/third-party/video-js/video-js.css"/>
	<script language="javascript" type="text/javascript" src="js/ueditor/third-party/video-js/video.js"></script>
</head>  
<body>
	<form action="newsServlet" method="post" >
		这里编写新闻内容：<br>
		<script id="container" name="content" type="text/plain">这里写你的初始化内容</script>  
		<script type="text/javascript">  
			var editor = UE.getEditor('container'); 
		</script>
		<br> 
		<input type="submit">
	</form> 
</body>  
</html>