<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/yesVote_files/index.css">
	</head>
	<body>
			<table class="table3">
				<tbody><tr>
					<td class="d1" colspan="2"><img src="${pageContext.request.contextPath}/admin/yesVote_files/title_ico.gif" style="vertical-align:middle">&nbsp;&nbsp;参与投票</td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="line3"></div>
						<p class="p11"><img src="${pageContext.request.contextPath}/admin/yesVote_files/vote_icon.gif"> ${title }</p>
						<span class="p2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有 ${optionNum} 个选项，已有 ${voteNum} 个网友参与了投票。</span>
					</td>	
				</tr>

				<%! int count=1; %>

				<c:forEach var="oplist" items="${list}">
					<tr>
						<td width="40px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${count}</td>
						<td><label>${oplist}</label>
							<c:forEach var="inte" items="${sinte }">
								<c:if test="${oplist==inte}">
									<label>&nbsp;&nbsp;&nbsp;&nbsp;</label>
									<img src="${pageContext.request.contextPath}/admin/yesVote_files/yes.png" style="height: 15px;width: 15px;margin-top: 3px">
								</c:if>
							</c:forEach>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td>
						<a href="lookVoteServlet?title=${title}&optionNum=${optionNum}&voteNum=${voteNum}" target="mainframe"><input type="image" src="${pageContext.request.contextPath}/admin/yesVote_files/button_view.gif"></a>
					</td>
				</tr>
		
			</tbody></table>
	
</body></html>