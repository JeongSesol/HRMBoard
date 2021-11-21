<%@page import="Mvc2.Model.PageObject"%>
<%@page import="Mvc2.Model.DBBean"%>
<%@page import="Mvc2.Model.DataBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원관리프로그램</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	List<DataBean> list=(List<DataBean>)request.getAttribute("list");
	DBBean instance = DBBean.getInstance();
	int u_level=(int)session.getAttribute("u_level");
	PageObject pg=(PageObject)request.getAttribute("pgData");
	%>
	
	<%@ include file="header.jsp"%>
	<div class="main-wrapper">
		<section class="cta-section theme-bg-light py-5">
			<div class="container text-center">
				<h2 class="heading">사원현황/관리</h2>
				<form action="hrmCtler?type=findMember" method="post" class="signup-form form-inline justify-content-center pt-3">
					<div class="form-group"> 
						<input type="text" name="search"
							class="form-control mr-md-1 semail" placeholder="사원 이름을 입력하세요" required>
					</div>
					<button type="submit" class="btn btn-primary">검색</button>
				</form>
			</div>
			<!--//container-->
		</section>
		<section class="blog-list px-3 py-5 p-md-5">
			<div class="content-wrapper">
				<div>
					<table id="count">
						<tr>
							<td class="blue-td">재직</td>
							<td><%=instance.getFullTimeEmployee() %></td>
							<td class="blue-td">퇴사</td>
							<td><%=instance.getquittedEmployee() %></td>
						</tr>
					</table>
				</div>
				<form action="hrmCtler?type=delete" method="post" id="frm">
				<table class="list-table">
					<tr>
						<th>선택</th>
						<th>사원번호</th>
						<th>이름</th>
						<th>부서</th>
						<th>직위</th>
						<th>생년월일</th>
						<th>입사일</th>
						<th>번호</th>
						<th>이메일</th>
						<th>상태</th>
						<th>급여</th>
						<th>사원카드</th>
					</tr>
			<%
			for (int i = 0; i < list.size(); i++) {
				DataBean dataBean = list.get(i);
			%>
					<tr> 
						<td><input type="checkbox" name="Chk" value="<%=dataBean.getNumber() %>"></td> 
						<td><%=dataBean.getNumber() %></td> 
						<td><%=dataBean.getName() %></td> 
						<td><%=dataBean.getDept() %></td> 
						<td><%=dataBean.getRank() %></td> 
						<td><%=dataBean.getBirthday() %></td> 
						<td><%=dataBean.getRegDate() %></td> 
						<td><%=dataBean.getPhone() %></td> 
						<td><%=dataBean.getEmail() %></td> 
						<td><%=dataBean.getStatus() %></td> 
						<td><%=dataBean.getSal() %></td> 
						<td><%=dataBean.getResume() %></td> 
					</tr>
			<%
			}
			%>
				</table>
				
				
			<div style="text-align:center;">
				<%
                  if (pg.getTotalPage() > 0) {
                     int pageCount = pg.getTotalPage() / pg.getPageSize() + (pg.getTotalPage() % pg.getPageSize() == 0 ? 0 : 1);
                     int startPage = 1;

                     if (pg.getCurrentPage() % 10 != 0)
                        startPage = (int) (pg.getCurrentPage() / 10) * 10 + 1;
                     else
                        startPage = ((int) (pg.getCurrentPage() / 10) - 1) * 10 + 1;

                     int pageBlock = 10;
                     int endPage = startPage + pageBlock - 1;
                     if (endPage > pageCount)
                        endPage = pageCount;

                     if (startPage > 10) {
                  %>
                  <a href="hrmCtler?type=list&pageNum=<%=startPage - 10%>"
                     class="">[이전]</a>
                  <%
                  }

                  for (int i = startPage; i <= endPage; i++) {
                  %>
                  <a href="hrmCtler?type=list&pageNum=<%=i%>" class="a_tag_ver2">[<%=i%>]
                  </a>
                  <%
                  }

                  if (endPage < pageCount) {
                  %>
                  <a href="hrmCtler?type=list&pageNum=<%=startPage + 10%>"
                     class="">[다음]</a>
                  <%
                  }
                  }
                  %>
               </div>
				
				
				<div class="btn-wrapper">
			<%if(u_level==2) {%>
					<input type="button" class="btn btn-primary" value="신규사원등록" onclick="window.open('hrmCtler?type=insert','사원등록','width=700,height=1100,location=no,status=no,scrollbars=yes');">
					<input type="button" class="btn btn-primary" value="사원정보수정" onclick="update();">
			<%}if(u_level==1) { %>
					<input type="button" class="btn btn-primary" value="신규사원등록" onclick="window.open('hrmCtler?type=insert','사원등록','width=700,height=1100,location=no,status=no,scrollbars=yes');">
					<input type="button" class="btn btn-primary" value="사원정보수정" onclick="update();">
					<input type="submit" class="btn btn-primary" value="선택 삭제">
			<%} %>
				</div>
				</form>
			</div>
		</section>
		<%@ include file="footer.jsp"%>
	</div>
</body>
<script type="text/javascript">
	function update(){
		 var frm = document.getElementById("frm"); 
		  frm.action="hrmCtler?type=update"; 
		  frm.submit(); 
	}
</script>
</html>