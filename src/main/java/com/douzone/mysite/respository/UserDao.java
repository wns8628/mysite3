package com.douzone.mysite.respository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.douzone.mysite.exception.UserDaoException;
import com.douzone.mysite.vo.UserVo;
@Repository
public class UserDao {
	
	public UserVo get(String email) {

		UserVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			 conn = getConnection();	
			 
			String sql="select no,name" + 
						"  from user  \r\n" + 
						" where email =?";
								
			pstmt = conn.prepareCall(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {

				long no = rs.getLong(1);
				String name = rs.getString(2);

				result = new UserVo();
				
				result.setNo(no);
				result.setName(name);	
			}		
			 
		}  catch (SQLException e) {
			System.out.println( "error: "+e );
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public UserVo get(long userNo) {

		UserVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			 conn = getConnection();	
			 
			String sql="select no,name,email,password,gender,join_date,role from user " + 
					"    where no=?";
								
			pstmt = conn.prepareCall(sql);
			pstmt.setLong(1, userNo);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {

				long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				String gender = rs.getString(5);
				String joinDate = rs.getString(6);
				String role = rs.getString(7);
				
				
				result = new UserVo();
				
				result.setNo(no);
				result.setName(name);	
				result.setEmail(email);
				result.setPassword(password);
				result.setGender(gender);
				result.setJoinDate(joinDate);
				result.setRole(role);
				
			}		
			 
		}  catch (SQLException e) {
			System.out.println( "error: "+e );
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	public UserVo get(String email, String password) {

		UserVo result = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		
		try {
			 conn = getConnection();	
			 
			String sql="select no,name,role from user\r\n" + 
					"    where email=? and password=?";
										//이름,패스워드,글,날짜
			pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			
			//한개가나옴 인증되면 당연하지 한명이니깐
			if(rs.next()) {
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String role = rs.getString(3);
				
				result = new UserVo();
				result.setNo(no);
				result.setName(name);	
				result.setRole(role);
			}		
			 
		}  catch (SQLException e) {
			System.out.println( "error: "+e );
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean insert(UserVo vo) throws UserDaoException {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			 conn = getConnection();	
			 
			String sql="insert into user\r\n" + 
					"      values(null, ?,?,?,?,now(), 'USER' )";
										//이름,패스워드,글,날짜
			pstmt = conn.prepareCall(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			int count = pstmt.executeUpdate();
			result = count ==1;			
			 
		}  catch (SQLException e) {
			throw new UserDaoException("회원정보 저장 실패");
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public boolean update(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
				
		try {
			 conn = getConnection();	
			 
			 String sql = null; 
			if( vo.getPassword().length() == 0 ) { 
				sql="update user set name=?, gender=? where no=?";
				
				pstmt = conn.prepareCall(sql);
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
			}else {
				sql="update user set name=?, password=?, gender=? where no=?";		
				
				pstmt = conn.prepareCall(sql);				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getPassword());
				pstmt.setString(3, vo.getGender());
				pstmt.setLong(4, vo.getNo());
			}
			
			int count = pstmt.executeUpdate();
			result = count ==1;			
			 
		}  catch (SQLException e) {
			System.out.println( "error: "+e );
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	   // 커넥트함수
	   private Connection getConnection() throws SQLException {
	      Connection conn = null;

	      try {
	         Class.forName("com.mysql.jdbc.Driver"); // 패키지 이름

	         String url = "jdbc:mysql://localhost:3306/webdb"; // DB 종류마다 url이 다르다
	         conn = DriverManager.getConnection(url, "webdb", "webdb"); // interface
	      } catch (ClassNotFoundException e) {
	         System.out.println("드라이버 로딩 실패" + e);
	      }
	      return conn;
	   }
}

