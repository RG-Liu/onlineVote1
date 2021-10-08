<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>投票页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/index_files/index.css">
	</head>
	<body>
		<div class="main">
			<table class="table2">
				<tbody>
				<tr>
					<td class="d1" colspan="2"><img src="${pageContext.request.contextPath}/admin/index_files/title_vote.gif" style="vertical-align:middle">&nbsp;&nbsp;投票列表</td>
				</tr>
				<c:if test="${list==null&&flag==null }">
					<script type="text/javascript">
						function load(){
							window.location.href="${pageContext.request.contextPath}/articleListServlet";
						}
						window.onload=load;//页面一加载就立即运行
					</script>
				</c:if>


				<c:forEach var="listing" items="${list}">
				<tr>
					<td width="810px">
						<span class="p1"><img src="${pageContext.request.contextPath}/admin/index_files/list.gif" /> <a href="lookVoteServlet?title=${listing.title}&optionNum=${listing.optionNum}&voteNum=${listing.voteNum}" id="h1">${listing.title }</a></span><br>
						<span class="p2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共有 ${listing.optionNum}个选项，已有${listing.voteNum }个网友参与了投票。</span>
					</td>
					<td width="105px">

						<c:choose>
							<%--管理员具有删除和修改功能--%>
							<c:when test="${del=='d'}">
								<c:choose>
									<c:when test="${listing.isEnd!=true}">
										<img src="${pageContext.request.contextPath}/admin/index_files/delete.gif" />
										<a href="delServlet?title=${listing.title}" onclick="return confirm('是否确认删除?')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<img src="${pageContext.request.contextPath}/admin/index_files/update.gif" />
										<a href="changeServlet?title=${listing.title}">修改</a>
									</c:when>
									<c:otherwise>
										<img src="${pageContext.request.contextPath}/admin/index_files/delete.gif" />
										<a href="delServlet?title=${listing.title}" onclick="return confirm('是否确认删除?')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<img src="${pageContext.request.contextPath}/admin/index_files/overtime.png" style="width: 12px;height: 12px" />
										<a style=" text-decoration:none;color: red">无法修改</a>

									</c:otherwise>
								</c:choose>
								<%--<img src="${pageContext.request.contextPath}/admin/index_files/delete.gif" />
								<a href="delServlet?title=${listing.title}" onclick="return confirm('是否确认删除?')">删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<img src="${pageContext.request.contextPath}/admin/index_files/update.gif" />

								<a href="changeServlet?title=${listing.title}">修改</a>--%>
							</c:when>
							<c:when test="${listing.isVote==true}">
                                <img src="${pageContext.request.contextPath}/admin/index_files/okvote.gif" />
								<a href="whetherVoteServlet?title=${listing.title}&optionNum=${listing.optionNum}&voteNum=${listing.voteNum}" class="p3" target="mainframe">已参与投票</a>

								<c:if test="${listing.isEnd==true}">
									<img src="${pageContext.request.contextPath}/admin/index_files/endtime.png" style="width: 15px;height: 15px" />
									<a style=" text-decoration:none;color: red">投票已过期</a>
								</c:if>
							</c:when>


							<c:otherwise>
								<c:choose>
									<c:when test="${listing.isEnd!=true}">
                                		<img src="${pageContext.request.contextPath}/admin/index_files/novote.gif" />
										<a href="attendVoteServlet?title=${listing.title}&optionNum=${listing.optionNum}&voteNum=${listing.voteNum}" class="p3" target="mainframe">立即投票</a>
									</c:when>
									<c:otherwise>
										<img src="${pageContext.request.contextPath}/admin/index_files/endtime.png" style="width: 15px;height: 15px" />
										<a style=" text-decoration:none;color: red">投票已过期</a>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>

					</td>
				</tr>

				</c:forEach>
				<tr>
					<td style="padding-left: 400px"><a href="articleListServlet?goPage=${goPage-1 }&del=${del}">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="articleListServlet?goPage=${goPage+1 }&del=${del}">下一页</a></td>
				<tr>
			</table>
		</div>
	

</body>
</html>