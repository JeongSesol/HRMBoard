<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link id="theme-style" rel="stylesheet" href="assets/css/theme-3.css">
</head>
<body>
<div class="login-wrapper">
	<form action="hrmCtler?type=loginPro" method="post">
		<input type="text" name="id" class="form-control mr-md-1 semail" placeholder="ID" required>
		<input type="password" name="pw" class="form-control mr-md-1 semail" placeholder="PW" required>
		<input type="submit" value="로그인" class="btn btn-primary">
		<input type="button" value="회원가입" class="btn btn-primary" onclick="location.href='hrmCtler?type=join'">
	</form>
</div>
</body>
</html>