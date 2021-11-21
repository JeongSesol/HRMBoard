package Mvc2.Model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBBean {
	private static DBBean instance = new DBBean();
    public static DBBean getInstance() {
        return instance;
    }
    
    private DBBean() {}
    
    private Connection getConnection() throws Exception {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        DataSource ds = (DataSource)envCtx.lookup("jdbc/basicjsp");
        return ds.getConnection();
    }
    
    // 사원 목록 가져오기
	public List<DataBean> getList(int startRow, int end) {
		List<DataBean> list=new ArrayList<DataBean>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select * from hrTb limit ?,?";
		ResultSet rs=null;
		try {
		conn=getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setInt(1, startRow);
		pstmt.setInt(2, end);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			DataBean dataBean=new DataBean();
			int num=rs.getInt("num");
			String name=rs.getString("name");
			String dept=rs.getString("dept");
			String rank=rs.getString("rank");
			String birthday=rs.getString("birthday");
			String regDate=rs.getString("reg_date");
			String phone=rs.getString("phone");
			String email=rs.getString("email");
			String status=rs.getString("status");
			int sal=rs.getInt("sal");
			Blob resume=rs.getBlob("resume");
			
			dataBean.setNumber(num);
			dataBean.setName(name);
			dataBean.setDept(dept);
			dataBean.setRank(rank);
			dataBean.setBirthday(birthday);
			dataBean.setRegDate(regDate);
			dataBean.setPhone(phone);
			dataBean.setEmail(email);
			dataBean.setStatus(status);
			dataBean.setSal(sal);
			dataBean.setResume(resume);
			
			list.add(dataBean);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
			if(conn!=null)try {rs.close();}catch(Exception e) {}
		}
		return list;
	}
	
	// 게시판 페이징
	public int getListCnt() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from hrTb";
		int cnt=0;
		
		try {
			conn=getConnection();
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				cnt=rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
			if(conn!=null)try {rs.close();}catch(Exception e) {}
		}
		
		return cnt;
		
	}
	
	
	// 사원 검색
	public List<DataBean> getMember(String member) {
		List<DataBean> list=new ArrayList<DataBean>();
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="select * from hrTb where name=?";
		ResultSet rs=null;
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, member);
			rs=pstmt.executeQuery();
		if(rs.next()) {
			DataBean dataBean=new DataBean();
			int num=rs.getInt("num");
			String name=rs.getString("name");
			String dept=rs.getString("dept");
			String rank=rs.getString("rank");
			String birthday=rs.getString("birthday");
			String regDate=rs.getString("reg_date");
			String phone=rs.getString("phone");
			String email=rs.getString("email");
			String status=rs.getString("status");
			int sal=rs.getInt("sal");
			Blob resume=rs.getBlob("resume");
			
			dataBean.setNumber(num);
			dataBean.setName(name);
			dataBean.setDept(dept);
			dataBean.setRank(rank);
			dataBean.setBirthday(birthday);
			dataBean.setRegDate(regDate);
			dataBean.setPhone(phone);
			dataBean.setEmail(email);
			dataBean.setStatus(status);
			dataBean.setSal(sal);
			dataBean.setResume(resume);
			
			list.add(dataBean);
			return list;
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
			if(conn!=null)try {rs.close();}catch(Exception e) {}
		}
		return null;
	}
	
	// 사원 등록
	public void insertFc(DataBean data) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into hrTb values(NULL,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, data.getName());
			pstmt.setString(2, data.getDept());
			pstmt.setString(3, data.getRank());
			pstmt.setString(4, data.getBirthday());
			pstmt.setString(5, data.getRegDate());
			pstmt.setString(6, data.getPhone());
			pstmt.setString(7, data.getEmail());
			pstmt.setString(8, data.getStatus());
			pstmt.setInt(9, data.getSal());
			pstmt.setBlob(10, data.getResume());
			pstmt.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
		}
		
	}
	
	// 사원 보기
	public DataBean getEmployee(int e_num) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        DataBean data=new DataBean();
        String sql="select * from hrTb where num=?";
		
        try {
        	conn = getConnection();
        	pstmt = conn.prepareStatement(sql);
        	pstmt.setInt(1, e_num);
        	rs = pstmt.executeQuery();
        	
        	if(rs.next()) {
        		data.setNumber(rs.getInt("num"));
        		data.setName(rs.getString("name"));
        		data.setDept(rs.getString("dept"));
        		data.setRank(rs.getString("rank"));
        		data.setBirthday(rs.getString("birthday"));
        		data.setRegDate(rs.getString("reg_date"));
        		data.setPhone(rs.getString("phone"));
        		data.setEmail(rs.getString("email"));
        		data.setStatus(rs.getString("status"));
        		data.setSal(rs.getInt("sal"));
        	}
        	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
			if(conn!=null)try {rs.close();}catch(Exception e) {}
		}
  
		return data;		
	}
	
	
	// 사원 수정
	public void updateFc(DataBean data, int e_num) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update hrTb set dept=?, rank=?, phone=?, email=?, status=?, sal=? where num=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, data.getDept());
			pstmt.setString(2, data.getRank());
			pstmt.setString(3, data.getPhone());
			pstmt.setString(4, data.getEmail());
			pstmt.setString(5, data.getStatus());
			pstmt.setInt(6, data.getSal());
			pstmt.setInt(7, e_num);
			pstmt.executeUpdate();			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
		}
		
	}
	
	
	// 사원 삭제(복수 데이터 삭제)
	public void deleteFc(String[] deleteChk) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from hrTb where num=?";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			for(int i=0; i<deleteChk.length;i++) {
				pstmt.setString(1, deleteChk[i]);
				pstmt.executeUpdate();
			}		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
		}
	}
	
	// 재직 사원 집계
	public int getFullTimeEmployee() {
		int cnt=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from hrTb where status='재직'";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cnt=rs.getInt(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
		}
		
		return cnt;
		
	}
	
	// 퇴사 사원 집계
	public int getquittedEmployee() {
		int cnt=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="select count(*) from hrTb where status='퇴사'";
		
		try {
			conn=getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				cnt=rs.getInt(1);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(conn!=null)try {conn.close();}catch(Exception e) {}
			if(pstmt!=null)try {pstmt.clearParameters();}catch(Exception e) {}
		}
		
		return cnt;
		
	}
	
	// ID와 PW 대조하여 로그인 기능 처리
	public int userCheck(String user_id, String user_password) {
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String sql = "select id,pw from accountTb where id=? and pw=?";
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, user_id);
    		pstmt.setString(2, user_password);
    		rs = pstmt.executeQuery();
    		    		
    		if(rs.next()) {
    			if(rs.getString("id").equals(user_id)) {
    				if(rs.getString("pw").equals(user_password)) {
    					return 1;
    				}
    			}
    			
    		}
    		return 0;
    		
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.clearParameters(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return 0;
    }
	
	// 권한 가져오기(권한별 기능 수행 위한 메소드) 
	public int getUserLevel(String user_id, String user_password) {
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	int u_level;
    	String sql = "select * from accountTb where id=? and pw=?";
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, user_id);
    		pstmt.setString(2, user_password);
    		rs = pstmt.executeQuery();
    		    		
    		if(rs.next()) {
    			u_level=rs.getInt("u_level");
				return u_level;    			
    		}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.clearParameters(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return 0;	
	}
	
	// 회원가입
	public void join(AccountBean account) {
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	String sql="insert into accountTb values(?,?,?)";
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account.getId());
			pstmt.setString(2, account.getPw());
			pstmt.setInt(3, account.getU_level());
    		pstmt.executeUpdate();
    		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) try { pstmt.clearParameters(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		
	}
	
	// 아이디 중복체크
	public int idCheck(String user_id) {
		Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	String sql = "select id from accountTb where id=?";
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1, user_id);
    		rs = pstmt.executeQuery();
    		    		
    		if(rs.next()) {
				return 1;    			
    		}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.clearParameters(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return 0;		
	}
	
}
