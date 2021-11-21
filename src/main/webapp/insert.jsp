<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록</title>
<link id="theme-style" rel="stylesheet" href="assets/css/theme-3.css">
</head>
<body>
<div class="insert-wrapper">
	<form action="hrmCtler?type=insertPro" method="post">
		<ul class="insert-list">
			<li>
				<label>이름</label>
				<input type="text" name="name" class="form-control mr-md-1 semail" required>
			</li>
			<li>
				<label>부서</label>
				<input type="text" name="dept" class="form-control mr-md-1 semail" required>
			</li>
			<li>
				<label>직위</label> 
				<input type="text" name="rank" class="form-control mr-md-1 semail" required>
			</li>
			<li>
				<label>생년월일</label> 
				<input type="text" name="birthday" placeholder="YYYY-MM-DD" class="form-control mr-md-1 semail" required>
			</li>
			<li>
				<label>입사일</label>
				<input type="text" name="regDate" placeholder="YYYY-MM-DD" class="form-control mr-md-1 semail" required>
			</li>
			<li>
				<label>번호</label>
				<input type="text" name="phone" placeholder="하이픈(-) 포함" class="form-control mr-md-1 semail" required>
			</li>
			<li>
				<label>이메일</label> 
				<input type="text" name="email" class="form-control mr-md-1 semail" required>
			</li>
			<li>
				<label>상태</label> 
				<input type="text" name="status" class="form-control mr-md-1 semail" value="재직" readonly>
			</li>
			<li>
				<label>급여</label> 
				<input type="text" name="sal" placeholder="단위:만원" class="form-control mr-md-1 semail" required>
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