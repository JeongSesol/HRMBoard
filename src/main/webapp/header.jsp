<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>사원관리프로그램</title>
    
    <!-- Meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Blog Template">
    <meta name="author" content="Xiaoying Riley at 3rd Wave Media">    
    
    <!-- FontAwesome JS-->
    <script defer src="https://use.fontawesome.com/releases/v5.7.1/js/all.js" integrity="sha384-eVEQC9zshBn0rFj4+TU78eNA19HMNigMviK/PU/FFjLXqa/GKPgX58rvt5Z8PLs7" crossorigin="anonymous"></script>
    
    <!-- Theme CSS -->  
    <link id="theme-style" rel="stylesheet" href="assets/css/theme-3.css">

</head> 
<body>
	<header class="header text-center">	    
	    <h1 class="blog-name pt-lg-4 mb-0"><a href="hrmCtler?type=list">사원관리프로그램</a></h1>
        
	    <nav class="navbar navbar-expand-lg navbar-dark" >
           
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
			</button>

			<div id="navigation" class="collapse navbar-collapse flex-column" >
				<div class="profile-section pt-3 pt-lg-0">
				    <img class="profile-image mb-3 rounded-circle mx-auto" src="assets/images/profile.png" alt="image" >			
					
					<div class="bio mb-3"><%=session.getAttribute("user_id") %>님 환영합니다.<br><a href="hrmCtler?type=logout">Logout</a></div><!--//bio-->
			        <hr> 
				</div><!--//profile-section-->
			</div>
		</nav>
    </header>
</body>
</html>