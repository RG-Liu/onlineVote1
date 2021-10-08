<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title></title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/reg_files/reg.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/admin/reg_files/jquery-1.x.js.下载"></script>
	<script type="text/javascript">
		function checkname() {
			var name=document.getElementById("username").value;
			$.get("AjaxRegisterServlet",{"user_name":name},function(data){
				var msg=document.getElementById("msg");
				msg.innerHTML=data;
				msg.value=data;
			});
		}

		function check() {
			var pmsg=document.getElementById("pmsg");
			if(password.value!=password1.value){
				pmsg.innerHTML="上下密码不一致";
				return false;
			}else if(password.value==""||password.value==null){
				pmsg.innerHTML="密码为空";
				return false;
			}

		}
	</script>
	<style type="text/css">#yddWrapper{min-width:250px;max-width:400px;color:#252525;z-index:10001;text-align:left;font-family:Arial,Helvetica,sans-serif;margin-top:10px}#yddWrapper a{text-decoration:none;color:#50799b;font-family:Arial,Helvetica,sans-serif}#langeasyTip{color:#8a6d3b;background-color:#fcf8e3;border-radius:2px;border:1px solid #faebcc;padding:2px 5px;margin-bottom:4px;display:none}#yddWrapper table,#yddWrapper tr,#yddWrapper td{background:0 0}#yddWrapper table,#yddWrapper td{border:0;padding:2px 0;font-family:Arial,Helvetica,sans-serif}#yddWrapper a:hover{color:#1273c4}#langeasyTitle{margin:0;padding:0;line-height:26px;height:24px;background:#e2f0fb;border-bottom:1px solid #d8e3ec;color:#50799b;font-size:12px;padding:0 10px}#langeasyWord{font-size:18px;padding-left:2px;color:#1273c4}#langeasyDefinition{margin-top:0;font-size:1em;line-height:1.5;padding:10px;position:relative}#langeasyContainer{background:#fff;display:block;position:relative;width:100%;height:100%;box-sizing:border-box;padding-top:0;top:-4px;left:-4px;font-size:12px;border:1px solid #4b7598;box-shadow:2px 2px 5px rgba(0,0,0,.2)}#langeasyTitle a{float:left}#langeasyContainer .icon_logo{width:18px;height:18px;margin-top:3px;background:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo0QTRENTAwQkQ4QjkxMUUzODhEQzg5OUU2MkUyQjI3MyIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo0QTRENTAwQ0Q4QjkxMUUzODhEQzg5OUU2MkUyQjI3MyI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOkVDRTAwNEE0RDhCNzExRTM4OERDODk5RTYyRTJCMjczIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjRBNEQ1MDBBRDhCOTExRTM4OERDODk5RTYyRTJCMjczIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+u07hYgAAC/BJREFUeNq8Wnlw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") no-repeat;background-size:100%;margin-right:6px}.langeasyline{margin:0;padding:0}#langeasyDefinition td{vertical-align:top;text-align:left;font-size:12px;line-height:20px}#langeasyDefinition span{vertical-align:middle;float:left}#langeasyDefinition p{margin:0;padding:0;padding-bottom:5px}#langeasyDefinition .langeasy_icon_audio{background:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABYAAAATCAYAAACUef2IAAABF0lEQVR42u2UMWqFQBRF7cIvkrVkKWlcgaKthboAtbISQQtLQRQLC20VrKxULMXCSnQZN5khBhETEv3FL/JgmDeXx+Gid4ZhHrGCIHj92J4vQVzXxV5LkgS6ruMSlGXZLeDLpWEYyLIMp6FbMNHiOKbnaZrA8zzpb6egO8cvHMdhWRaqmaaJsiy/d72FHK3tbBiG8H2fakVRwLKs+4CHYYCqqlQbxxGKotwHXFXVmyAI2PeXweSnSZKEfX8ZnOc5bNumWl3XILG7An5aZ2VZRtu2FOZ5HtI0/X2WHcc5dEwyrGnaer6RHM/z/LdLsoWvWtd1+HwnmCiKQGZOXekVvtfJpxBFESQVp9+LI1dN06DvezD/9VO9A0IdHPo7H/9ZAAAAAElFTkSuQmCC") no-repeat;width:22px;height:19px}#langeasyAddNeword,#langeasyAlreadyNewWord,#langeasyLogin,#alreadyAdd{display:none;position:absolute;right:10px;font-size:11px}#langeasyAlreadyNewWord{color:#999}#yddWrapper #alreadyAdd{color:red}</style></head>
<body langeasy="105">
<div class="main">
	<div class="top">
		<img src="./reg_files/logo.gif">
	</div>
	<div class="line1"></div>
	<div class="second"></div>
	<div class="third">
		<div class="head">新用户注册</div>
		<form action="${pageContext.request.contextPath}/registerServlet" method="post" onsubmit="return check();">
			<table>
				<tbody><tr>
					<td>用户名：</td>
					<td><input type="text" id="username" name="username" class="aa" onblur="checkname()"></td>
					<td><span id="msg"></span></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="password" name="password" class="aa" id="password"></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input type="password" name="password1" class="aa" id="password1"></td>
					<td align="left"><span id="pmsg"></span></td>
				</tr>
				<tr>
					<td></td>
					<td align="left">
						<input type="submit" value="立即注册" style="width: 180px;height: 32px;background-color:dodgerblue ;border: none;color: white;padding: 5px 10px;text-align: center;display: inline-block;font-size: 16px;border-radius: 5px;cursor: pointer;" />
					</td>
				</tr>
				</tbody></table>
		</form>
	</div>
	<div class="line2"></div>
	<div class="end">
		青软实训 © 版权所有
	</div>
</div>


</body></html>










<%--
<%@ page contentType="text/html;charset=UTF-8" language="java"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- saved from url=(0035)http://localhost/vote/admin/reg.jsp -->
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	&lt;%&ndash;<%
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	%>


	<base href="<%=path%>/">&ndash;%&gt;

	<base href="<%=request.getContextPath()%>/">
	<title>注册页面</title>
		<link rel="stylesheet" href="./reg_files/reg.css">
		<script type="text/javascript" src="./reg_files/jquery-1.x.js.下载"></script>
		<script type="text/javascript">
			function checkname() {
				var name=document.getElementById("username").value;
			 	$.get("AjaxRegisterServlet",{"user_name":name},function(data){
			 		var msg=document.getElementById("msg");
			 		msg.innerHTML=data;
			 		msg.value=data;
			 	});

			}

			function check() {
				var pmsg=document.getElementById("pmsg");
				if(password.value!=password1.value){
					pmsg.innerHTML="密码不一致";
					return false;
				}else if(password.value==""||password.value==null){
					pmsg.innerHTML="密码为空";
					return false;
				}else if(msg.value!="用户名可以使用"){
					return false;
				}
			}
		</script>
	<style type="text/css">#yddWrapper{min-width:250px;max-width:400px;color:#252525;z-index:10001;text-align:left;font-family:Arial,Helvetica,sans-serif;margin-top:10px}#yddWrapper a{text-decoration:none;color:#50799b;font-family:Arial,Helvetica,sans-serif}#langeasyTip{color:#8a6d3b;background-color:#fcf8e3;border-radius:2px;border:1px solid #faebcc;padding:2px 5px;margin-bottom:4px;display:none}#yddWrapper table,#yddWrapper tr,#yddWrapper td{background:0 0}#yddWrapper table,#yddWrapper td{border:0;padding:2px 0;font-family:Arial,Helvetica,sans-serif}#yddWrapper a:hover{color:#1273c4}#langeasyTitle{margin:0;padding:0;line-height:26px;height:24px;background:#e2f0fb;border-bottom:1px solid #d8e3ec;color:#50799b;font-size:12px;padding:0 10px}#langeasyWord{font-size:18px;padding-left:2px;color:#1273c4}#langeasyDefinition{margin-top:0;font-size:1em;line-height:1.5;padding:10px;position:relative}#langeasyContainer{background:#fff;display:block;position:relative;width:100%;height:100%;box-sizing:border-box;padding-top:0;top:-4px;left:-4px;font-size:12px;border:1px solid #4b7598;box-shadow:2px 2px 5px rgba(0,0,0,.2)}#langeasyTitle a{float:left}#langeasyContainer .icon_logo{width:18px;height:18px;margin-top:3px;background:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo0QTRENTAwQkQ4QjkxMUUzODhEQzg5OUU2MkUyQjI3MyIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDo0QTRENTAwQ0Q4QjkxMUUzODhEQzg5OUU2MkUyQjI3MyI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOkVDRTAwNEE0RDhCNzExRTM4OERDODk5RTYyRTJCMjczIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjRBNEQ1MDBBRDhCOTExRTM4OERDODk5RTYyRTJCMjczIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+u07hYgAAC/BJREFUeNq8Wnlw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") no-repeat;background-size:100%;margin-right:6px}.langeasyline{margin:0;padding:0}#langeasyDefinition td{vertical-align:top;text-align:left;font-size:12px;line-height:20px}#langeasyDefinition span{vertical-align:middle;float:left}#langeasyDefinition p{margin:0;padding:0;padding-bottom:5px}#langeasyDefinition .langeasy_icon_audio{background:url("data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABYAAAATCAYAAACUef2IAAABF0lEQVR42u2UMWqFQBRF7cIvkrVkKWlcgaKthboAtbISQQtLQRQLC20VrKxULMXCSnQZN5khBhETEv3FL/JgmDeXx+Gid4ZhHrGCIHj92J4vQVzXxV5LkgS6ruMSlGXZLeDLpWEYyLIMp6FbMNHiOKbnaZrA8zzpb6egO8cvHMdhWRaqmaaJsiy/d72FHK3tbBiG8H2fakVRwLKs+4CHYYCqqlQbxxGKotwHXFXVmyAI2PeXweSnSZKEfX8ZnOc5bNumWl3XILG7An5aZ2VZRtu2FOZ5HtI0/X2WHcc5dEwyrGnaer6RHM/z/LdLsoWvWtd1+HwnmCiKQGZOXekVvtfJpxBFESQVp9+LI1dN06DvezD/9VO9A0IdHPo7H/9ZAAAAAElFTkSuQmCC") no-repeat;width:22px;height:19px}#langeasyAddNeword,#langeasyAlreadyNewWord,#langeasyLogin,#alreadyAdd{display:none;position:absolute;right:10px;font-size:11px}#langeasyAlreadyNewWord{color:#999}#yddWrapper #alreadyAdd{color:red}</style></head>
	<body langeasy="105">
		<div class="main">
			<div class="top">
				<img src="./reg_files/logo.gif">
			</div>
			<div class="line1"></div>
			<div class="second"></div>
			<div class="third">
				<div class="head">新用户注册</div>
				<form action="${pageContext.request.contextPath}/registerServlet" method="post" onsubmit="return check();">
					<table>
						<tbody>
							<tr>
								<td>用户名：</td>
								<td><input type="text" id="username" name="username" class="aa" onblur="checkname()"></td>
								<td><span id="msg"></span></td>
							</tr>
							<tr>
								<td>密码：</td>
								<td><input type="password" name="password" class="aa" id="password"></td>
							</tr>
							<tr>
								<td>确认密码：</td>
								<td><input type="password" name="password1" class="aa" id="password1"></td>
								<td align="left"><span id="pmsg"></span></td>
							</tr>
							<tr>
								<td></td>
								<td align="left"><input type="submit" value="立即注册" style="width:120px; height:30px;border:0;"></td>
							</tr>
						</tbody>
					</table>
				</form>
			</div>
			<div class="line2"></div>
			<div class="end">
				青软实训 © 版权所有
			</div>
		</div>


</body></html>

--%>
