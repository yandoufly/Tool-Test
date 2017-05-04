<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>同步分页</title>
</head>
<body>
<center>
	<!-- 输入区 -->
	<form action="${pageContext.request.contextPath}/ArticleServlet" method="post">
		<input type="hidden" name="currPageNO" value="1">
		<table border="2" align="center">
			<tr>
				<td>输入关键字</td>
				<td><input type="text" name="keywords" value="${KEYWORDS}" maxlength="10"></td>
				<td><input id="search" type="button" value="站内搜索"></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		//去空格
		function trim(str){ 
			//"    培训        "
			//先去掉左边空格
			str = str.replace(/^\s*/,"");
			//去掉右边空格
			str = str.replace(/\s*$/,"");
			return str;
		}
		
	
		//定位站内搜索
		document.getElementById("search").onclick = function(){
			//定位表单
			var formElement = document.forms[0];
			//获取关键字
			var keywords = formElement.keywords.value;
			//去空格
			keywords = trim(keywords);
			//判断长度
			if(keywords.length == 0){
				alert("请输入关键字");
			}else{
				//提交表单
				formElement.submit();
			}
		}
		
	</script>
	
	
	<!-- 显示区 -->
	<table border="2" align="center" width="70%">
		<tr>
			<td>编号</td>
			<td>标题</td>
			<td>内容</td>
		</tr>
		<c:forEach var="a" items="${requestScope.page.articleList }">
		<tr>
			<td>${a.id}</td>
			<td>${a.title}</td>
			<td>${a.content}</td>
		</tr>
		</c:forEach>
		<tr >
			<th colspan="3" align="center">
				<a onclick="fenye(1)" style="cursor:hand;color:blue;text-decoration:underline">首页</a> &nbsp;&nbsp;
				
				<c:choose>
					<c:when test="${requestScope.page.currPageNO+1 <= requestScope.page.allPageNO }">
						<a onclick="fenye(${requestScope.page.currPageNO+1})" style="cursor:hand;color:blue;text-decoration:underline">下一页</a> &nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						下一页						
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${requestScope.page.currPageNO-1 >= 1}">
						<a onclick="fenye(${requestScope.page.currPageNO-1})" style="cursor:hand;color:blue;text-decoration:underline">上一页</a> &nbsp;&nbsp;
					</c:when>
					<c:otherwise>
						上一页						
					</c:otherwise>
				</c:choose>
				
				
				<a onclick="fenye(${requestScope.page.allPageNO})" style="cursor:hand;color:blue;text-decoration:underline">末页</a>
			</th>			
		</tr>
	</table>
	<script type="text/javascript">
		function fenye(currPageNO){
			//定位表单
			var formElement = document.forms[0];
			//修改当前页号
			formElement.currPageNO.value = currPageNO;
			
			//提交表单
			formElement.submit();
		}
	</script>
</center>
</body>
</html>