<%@page import="Mvc2.Model.DataBean"%>
<%@page import="Mvc2.Model.DBBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 수정</title>
<link id="theme-style" rel="stylesheet" href="assets/css/theme-3.css">
</head>
<body>
<%
	DataBean data=(DataBean)request.getAttribute("data");
%>
<div class="insert-wrapper">
	<form action="hrmCtler?type=updatePro" method="post">
		<ul class="insert-list">
			<li>
				<label>사원번호</label>
				<input type="text" name="num" class="form-control mr-md-1 semail" value="<%=data.getNumber() %>" readonly>
			</li>
			<li>
				<label>이름</label>
				<input type="text" name="name" class="form-control mr-md-1 semail" value="<%=data.getName() %>" readonly>
			</li>
			<li>
				<label>부서</label>
				<input type="text" name="dept" class="form-control mr-md-1 semail" value="<%=data.getDept() %>">
			</li>
			<li>
				<label>직위</label> 
				<input type="text" name="rank" class="form-control mr-md-1 semail" value="<%=data.getRank() %>">
			</li>
			<li>
				<label>생년월일</label> 
				<input type="text" name="birthday" placeholder="YYYY-MM-DD" class="form-control mr-md-1 semail" value="<%=data.getBirthday() %>" readonly>
			</li>
			<li>
				<label>입사일</label>
				<input type="text" name="regDate" placeholder="YYYY-MM-DD" class="form-control mr-md-1 semail" value="<%=data.getRegDate() %>" readonly>
			</li>
			<li>
				<label>번호</label>
				<input type="text" name="phone" placeholder="하이픈(-) 포함" class="form-control mr-md-1 semail" value="<%=data.getPhone() %>">
			</li>
			<li>
				<label>이메일</label> 
				<input type="text" name="email" class="form-control mr-md-1 semail" value="<%=data.getEmail() %>">
			</li>
			<li>
				<label>상태</label>
				<select name="status" class="form-control mr-md-1 semail">
					<option value="재직">재직</option>
					<option value="퇴사">퇴사</option>
				</select> 
			</li>
			<li>
				<label>급여</label> 
				<input type="text" name="sal" placeholder="단위:만원" class="form-control mr-md-1 semail" value="<%=data.getSal() %>">
			</li>
			<li>
				<label>사원카드</label> 
				<input type="file" name="resume">
			</li>
		</ul>
		<div class="btn-wrapper2">
		<input type="submit" class="btn btn-primary" value="등록" >
		</div>
	</form>
</div>
</body>
</html>