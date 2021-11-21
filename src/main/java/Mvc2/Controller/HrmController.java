package Mvc2.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Mvc2.Model.AccountBean;
import Mvc2.Model.DBBean;
import Mvc2.Model.DataBean;
import Mvc2.Model.PageObject;


/**
 * Servlet implementation class controller
 */
@WebServlet("/hrmCtler")
public class HrmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HrmController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		Object obj = null;
		
		if (type == null)
			type = "login";
		
		switch (type) {
		
		case "login": {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
		case "loginPro": {
			String user_id=request.getParameter("id");
			String user_pw=request.getParameter("pw");
			System.out.println(user_id);
			System.out.println(user_pw);
			
			DBBean instance=DBBean.getInstance();
			int check=0;
			int u_level;
			
			try {
				check=instance.userCheck(user_id, user_pw);
				System.out.println(check);
				if(check==0) {
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('아이디와 비밀번호가 일치하지 않습니다.');");
					out.println("history.go(-1);");
					out.println("</script>");
					
				}else {
					u_level=instance.getUserLevel(user_id, user_pw);
					System.out.println(u_level);
					// 로그인 성공시 세션 생성
					session.setAttribute("user_id", user_id);
					session.setAttribute("u_level", u_level);
					RequestDispatcher dispatcher = request.getRequestDispatcher("hrmCtler?type=list");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		case "logout":{
	        session.invalidate();

	        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	        dispatcher.forward(request, response);
	        break;
	    }
		
		case "join":{
			RequestDispatcher dispatcher = request.getRequestDispatcher("join.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
		case "joinPro":{
			String id=request.getParameter("id");
			String pw=request.getParameter("pw");
			int u_level=Integer.parseInt(request.getParameter("u_level"));
			try {
				AccountBean account=new AccountBean();	
				DBBean instance = DBBean.getInstance();
				
				int chk=instance.idCheck(id);
				if(chk==1) {
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('이미 사용중인 아이디입니다.');");
					out.println("history.go(-1)");
					out.println("</script> ");
				}else {	
					account.setId(id);
					account.setPw(pw);
					account.setU_level(u_level);
					instance.join(account);
					
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('회원가입이 완료되었습니다.');");
					out.println("location.href='hrmCtler?type=login';");
					out.println("</script> ");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		case "list": {
			DBBean instance = DBBean.getInstance();
			PageObject pgData=new PageObject();
			List<DataBean> list = null;
			
			int pageSize=10;
			String pageNum=request.getParameter("pageNum");
			if(pageNum==null) {
				pageNum="1";
			}
			int currentPage=Integer.parseInt(pageNum);
			int startRow=(currentPage-1)*pageSize+1;
			int endRow=currentPage * pageSize;
			int count=0;
			int number=0;
			
			try {
				count=instance.getListCnt();
				number=count-(currentPage-1)*pageSize;
				
				pgData.setPageSize(pageSize);
				pgData.setCurrentPage(currentPage);
				pgData.setStartRow(startRow);
				pgData.setEndRow(endRow);
				pgData.setTotalPage(number);
				request.setAttribute("pgData", pgData);
				
				if(count>0) {
					list=instance.getList(startRow, endRow);
				} else {
					list=instance.getList(0, 0);
				}
				
				obj=list;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			request.setAttribute("list", obj);
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
	
			break;
		}
		
		case "findMember": {
			String member=request.getParameter("search");
			System.out.println(member);
			
			try {
				DBBean instance = DBBean.getInstance();
				List<DataBean> list = instance.getMember(member);
				if(list!=null) {			
					obj=list;
					request.setAttribute("list", obj);
					RequestDispatcher dispatcher = request.getRequestDispatcher("find.jsp");
					dispatcher.forward(request, response);
				}else {
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('해당 사원이 존재하지 않습니다.');");
					out.println("history.go(-1)");
					out.println("</script>");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		}
		
		case "insert": {
			RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
		case "insertPro": {
			DataBean data=new DataBean();
			DBBean instance = DBBean.getInstance();

			try {
				String name=request.getParameter("name");
				String dept=request.getParameter("dept");
				String rank=request.getParameter("rank");
				String birthday=request.getParameter("birthday");
				String regDate=request.getParameter("regDate");
				String phone=request.getParameter("phone");
				String email=request.getParameter("email");
				String status=request.getParameter("status");
				String inputSal=request.getParameter("sal");
				System.out.println(inputSal);
				int sal=Integer.parseInt(inputSal);
				
				data.setName(name);
				data.setDept(dept);
				data.setRank(rank);
				data.setBirthday(birthday);
				data.setRegDate(regDate);
				data.setPhone(phone);
				data.setEmail(email);
				data.setStatus(status);
				data.setSal(sal);
				instance.insertFc(data);
			
				out.println("<script type=\"text/javascript\"> ");
				out.println("alert('사원 등록이 완료되었습니다.');");
				out.println("window.close();");
				out.println("</script>");
			} catch (Exception e) {
				e.printStackTrace();
				out.println("<script type=\"text/javascript\"> ");
				out.println("alert('사원 등록을 실패하였습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
			}
			
			break;	
		}
		
		case "update": {
			String[] chk=request.getParameterValues("Chk");
			Object e_num=null;
			DBBean instance = DBBean.getInstance();
			DataBean data=null;
			
			try {
				if(chk==null) {
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('수정할 데이터를 체크해주세요.');");
					out.println("history.go(-1);");
					out.println("</script>");
				}
				if(chk.length==1) {
					for(int i=0;i<chk.length;i++) {
						e_num=chk[i];
					}
					System.out.println("obj: "+e_num);
					int num=Integer.parseInt(String.valueOf(e_num));
					data=instance.getEmployee(num);
					obj=data;
					request.setAttribute("data", obj);
					RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
					dispatcher.forward(request, response);
				}else {
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('수정은 한 데이터만 가능합니다.');");
					out.println("history.go(-1);");
					out.println("</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			break;
		}
		case "updatePro": {
			DBBean instance = DBBean.getInstance();
			DataBean data=new DataBean();
			
			try {
				int e_num=Integer.parseInt(request.getParameter("num"));
				String dept=request.getParameter("dept");
				String rank=request.getParameter("rank");
				String email=request.getParameter("email");
				String phone=request.getParameter("phone");
				String status=request.getParameter("status");
				int sal=Integer.parseInt(request.getParameter("sal")); 
				
				data.setDept(dept);
				data.setRank(rank);
				data.setEmail(email);
				data.setPhone(phone);			
				data.setStatus(status);
				data.setSal(sal);
				instance.updateFc(data, e_num);
				
				out.println("<script type=\"text/javascript\"> ");
				out.println("alert('사원 수정이 완료되었습니다.');");
				out.println("location.href='hrmCtler?type=list'");
				out.println("</script>");
			} catch (Exception e) {
				e.printStackTrace();
				out.println("<script type=\"text/javascript\"> ");
				out.println("alert('사원 수정을 실패하였습니다.');");
				out.println("history.go(-1);");
				out.println("</script>");
			}
			
			break;
		}
		
		case "delete": {
			String[] chk=request.getParameterValues("Chk");
			DBBean instance = DBBean.getInstance();
			try {
				if(chk!=null) {
					instance.deleteFc(chk);
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('삭제가 완료되었습니다.');");
					out.println("location.href='hrmCtler?type=list';");
					out.println("</script>");
				}else {
					out.println("<script type=\"text/javascript\"> ");
					out.println("alert('삭제할 데이터를 체크해주세요');");
					out.println("history.go(-1)");
					out.println("</script>");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	
		}
	}
	
}
