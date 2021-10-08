<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0040)http://localhost/vote/admin/addNewtp.jsp -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--<base href="/vote/">--><base href=".">
<title>添加投票界面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/addNewtp_files/reg.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/addNewtp_files/dcalendar.picker.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/addNewtp_files/jquery-1.x.js.下载"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/addNewtp_files/changeH.js.下载"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/addNewtp_files/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/addNewtp_files/dcalendar.picker.js"></script>



	</head>
	<body>
		<div class="third">
				<div class="head">添加新投票</div>
				<form action="${pageContext.request.contextPath}/articleListServlet?flag=add" method="post" onsubmit="return check()">
					<table style="width: 480px">
						<tbody>
						<tr>
							<td>投票内容：</td>
							<td><input type="text" name="title" class="bb"></td>
						</tr>
						<tr>
							<td>结束时间:</td>
							<td>
								<input type="text" name="end_time" id="mydatepicker1" placeholder="请选择结束日期">
							</td>
						</tr>
						<tr>
							<td>投票类型：</td>
							<td align="left">
								<input type="radio" name="type" value="dan" checked="checked">单选
								<input type="radio" name="type" value="duo">多选
							</td>
						</tr>
						</tbody>
						<tbody id="addTr">
						<tr>
							<td>投票选项：</td>
							<td><input type="text" name="option" class="bb"></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" name="option" class="bb"></td>
						</tr>
						</tbody>
						<tbody>
						<tr>
							<td></td>
							<td align="left">
								<input type="image" src="${pageContext.request.contextPath}/admin/addNewtp_files/button_submit.gif" style="vertical-align:middle">
								&nbsp;&nbsp;&nbsp;
								<a id="getStr">增加选项</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="${pageContext.request.contextPath}/admin/addNewtp_files/addNewtp.jsp">取消操作</a>
							</td>
						</tr>
					</tbody>

					</table>
				</form>
			</div>
			<div class="bottom" style="margin-top: 170px"></div>
	
</body>
<script type="text/javascript" >

	$('#mydatepicker1').dcalendarpicker({
		format:'yyyy-MM-dd'
	});

	$('#mydatepicker1').dcalendar();
</script>


</html>