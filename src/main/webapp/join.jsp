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
<div class="join-wrapper">
	<h2>회원 가입</h2>
	<form action="hrmCtler?type=joinPro" method="post" id="joinFrm">
		<input type="text" name="id" class="form-control" id="id" placeholder="아이디" required>
		<input type="password" name="pw" class="form-control mr-md-1 semail" id="pw1" placeholder="비밀번호" required>
		<input type="password" name="pwCheck" class="form-control mr-md-1 semail" id="pw2" placeholder="비밀번호 확인" required>	
		<input type="hidden" name="u_level" value="2">
		<input type="button" value="회원가입" class="btn btn-primary" onclick="check();">
		<input type="button" value="취소" class="btn btn-primary" onclick="location.href='hrmCtler?type=login'">
	</form>
</div>
</body>
<script type="text/javascript">
	function check(){
		var pw1=document.getElementById("pw1").value;
		var pw2=document.getElementById("pw2").value;
		var frm=document.getElementById("joinFrm");
		
		if(pw1!=pw2){
			alert("비밀번호 확인이 일치하지 않습니다.");
			return false;
		}
		else{
			frm.action="hrmCtler?type=joinPro";
			frm.submit();
		}
	}
</script>
</html>