<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0132)http://localhost/vote/changeServlet?title=%E4%BD%A0%E8%A7%89%E5%BE%97%E6%9C%80%E5%A5%BD%E7%9A%84%E4%B8%8B%E8%BD%BD%E5%B7%A5%E5%85%B7 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!--<base href="/vote/">--><base href=".">
<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/change_files/reg.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/addNewtp_files/dcalendar.picker.css" />

		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/change_files/jquery-1.x.js.下载"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/change_files/changeH.js.下载"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/addNewtp_files/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/admin/addNewtp_files/dcalendar.picker.js"></script>
	</head>
	<body>
		<div class="third">
				<div class="head">修改投票</div>
				<form action="${pageContext.request.contextPath}/changeSuccessServlet" method="post" onsubmit="return check()">
					<table style="width: 480px">
						<tbody>
						<tr>
							<td>投票内容：</td>
							<td><input type="text" name="title" class="bb" value="${title}" readonly="readonly"/></td>
						</tr>

						<tr>

							<td>结束时间:</td>
							<td>
								<input type="text" name="end_time" id="mydatepicker1" placeholder="时间/日期" value="${endTime}">
							</td>

						</tr>


						<tr>
							<td>投票类型：</td>
							<td align="left">
								<input type="radio" name="type" value="dan" checked="checked">单选
								<input type="radio" name="type" value="duo">多选
							</td>
						</tr>
						<tbody id="addTr">
						<tr>
							<td>投票选项：</td>
							<td><input type="text" name="option" class="bb" value="${list[0]}"/></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="text" name="option" class="bb" value="${list[1]}"/></td>
						</tr>
						<c:forEach items="${ylist}"  var="y">
							<tr>
								<td></td>
								<td><input type="text" class="bb" name="option" value="${y}"/></td>
								<td onClick="getDel(this)"><a>删除</a></td>
							</tr>
						</c:forEach>

						</tbody>
						<tr>
							<td></td>
							<td align="left">
								<input type="image" src="${pageContext.request.contextPath}/admin/change_files/button_submit.gif" style="vertical-align:middle" />
								&nbsp;&nbsp;&nbsp;
								<a  id="getStr">增加选项</a>&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="admin/index_files/tpList.jsp">取消操作</a>
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