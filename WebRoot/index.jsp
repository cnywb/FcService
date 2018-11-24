<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>FC数据处理系统</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script>
	function sub(){
		var passwd=document.getElementById("passwd");
		if(passwd.value!="" && passwd.value.length>0 ){
			document.getElementById("form1").submit();
		}else{
			alert("请输入校验密码！");
			return false;
		}
	}
	</script>
	</head>

	<body>
		<center>
			<b>FC数据处理系统</b>
		</center>
		<br />
		<form action="dowLOG.do" id="form1"  method="post">
			<table style="font-size: 11pt; border-collapse: collapse;"
				align="center" border="1">
				<tr>
					<td style="text-algin: left" colspan="2">
						<b>[系统登录校验]</b>
					</td>
				</tr>
				<tr>
					<td>
						<input type="password" id="passwd" name="passwd" maxlength="15" />
					</td>
					<td>
						<input type="button" onclick="sub()" value="校验" />
					</td>
				</tr>
				<%  String messge=request.getParameter("message"); 
				    if(messge!=null && !"".equals(messge)){
				 %>
				<tr>
					<td colspan="2" ><font color="red">密码错误！</font></td>
				</tr>
				<%   }
				%>
			</table>
		</form>
	</body>
</html>
