<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="js/ueditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="js/ueditor/lang/zh-cn/zh-cn.js"></script>
	<!-- 添加video支持 -->
	<link type="text/css" rel="stylesheet" href="js/ueditor/third-party/video-js/video-js.css"/>
	<script language="javascript" type="text/javascript" src="js/ueditor/third-party/video-js/video.js"></script>
	<!-- 在页面的head部分加入如下脚本，非支持 audio/video标签浏览器使用 -->
	<script type="text/javascript" src="js/html5media.min.js"></script>	
</head>
<body>
${content}
</body>
</html>