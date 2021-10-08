<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/cktp_files/index.css">
	</head>
	<body>
			<table class="table5">
				<tbody><tr>
					<td class="d1" colspan="4"><img src="${pageContext.request.contextPath}/admin/cktp_files/title_ico.gif" style="vertical-align:middle">&nbsp;&nbsp;查看投票</td>
				</tr>
				<tr>
					<td colspan="4">
						<div class="line3"></div>
						<p class="p11"><img src="${pageContext.request.contextPath}/admin/cktp_files/vote_icon.gif" /> ${title}</p>
						<span class="p2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有 ${optionNum}个选项，已有${voteNum} 个网友参与了投票。</span>
					</td>	
				</tr>
			</tbody></table>



				<table class="table4">
					<c:forEach var="op" items="${options}">
						<tr>
							<td width="40px">${op.option}</td>
							<td width="610px">
								<div class="b1">
									<c:choose>
										<c:when test="${titleNum!=0}">
											<div class="b11" style="width: ${op.num*1.0/titleNum*600}px"></div>
										</c:when>
										<c:otherwise>
											<div class="b11" style="width: 0px"></div>
										</c:otherwise>
									</c:choose>

								</div>
							</td>
							<td width="30px">${op.num}票</td>
							<td width="165px">
								<c:choose>
									<c:when test="${titleNum!=0}">
										<span>(</span>
										<fmt:formatNumber type="percent" value="${op.num*1.0/titleNum}"/>
										<span>)</span>
									</c:when>
									<c:otherwise>
										(0%)
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>

					<tr >
						<td colspan="4" style="margin-top: 120px;">
							<img src="${pageContext.request.contextPath}/admin/cktp_files/goback.gif" style="vertical-align:middle"> &nbsp;&nbsp;<a href="${pageContext.request.contextPath}/admin/index_files/tpList.jsp" target="mainframe">返回投票列表</a>
						</td>
					</tr></table>
	
</body></html>