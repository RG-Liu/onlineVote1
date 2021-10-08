<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0131)http://localhost/vote/attendVoteServlet?title=%E8%B0%81%E6%98%AF%E6%9C%80%E6%9C%89%E9%92%B1%E7%9A%84%E4%BA%BA&optionNum=3&voteNum=0 -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<base href="/vote/">--><base href=".">

<title></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/cytp_files/index.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/cytp_files/jquery-1.x.js.下载"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/admin/cytp_files/changeH.js.下载"></script>

</head>
<body>
	<form action="${pageContext.request.contextPath}/attendVoteServlet?type=${type}&title=${requestScope.title}	" method="post" onsubmit="return checknull();">
		<table class="table3">
			<tbody><tr>
				<td class="d1" colspan="2"><img src="${pageContext.request.contextPath}/admin/cytp_files/title_ico.gif" style="vertical-align: middle">&nbsp;&nbsp;参与投票</td>
			</tr>
			<tr>
				<td colspan="2">
					<div class="line3"></div>
					<p class="p11">
						<img src="${pageContext.request.contextPath}/admin/cytp_files/vote_icon.gif" /> ${requestScope.title}
					</p> <span class="p2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有
						${requestScope.optionNum} 个选项，已有 ${requestScope.voteNum} 个网友参与了投票。</span>
				</td>
			</tr>
			<c:forEach var="option" items="${optionList}">
				<%!int count = 1;%>
				<tr>
					<td width="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${count}</td>
					<td><c:choose>
						<c:when test="${type==1 }">
							<input type="checkbox" name="chbox" class="bb" value="${option}" />
						</c:when>
						<c:otherwise>
							<input type="radio" name="radio" class="bb" value="${option}" />
						</c:otherwise>
					</c:choose> <label>${option}</label>
					</td>
				</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td><input type="image" src="${pageContext.request.contextPath}/admin/cytp_files/button_vote.gif">
					</td>
			</tr>
		</tbody></table>
	</form>


</body></html>