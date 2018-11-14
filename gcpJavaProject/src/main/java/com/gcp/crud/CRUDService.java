package com.gcp.crud;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;


@Service("crudService")
@Transactional
public class CRUDService {
	
	//select
	public void select(Map<String,Object> param) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. 연결하기  "jdbc:mysql://IP주소/사용할db이름" getConnection(url,"사용자","비밀번호")
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. 쿼리 수행을 위한 Statement 객체 생성.
			stmt = conn.createStatement();
			 
			// 4. SQL 쿼리 작성
			// 1) JDBC에서 쿼리를 작성할 때는 세미콜론(;)을 빼고 작성한다.
			String sql = "SELECT no, employee_name, employee_age FROM crud.employee";
			 
			// 5. 쿼리 수행
			// 레코드들은 ResultSet 객체에 추가된다.
			rs = stmt.executeQuery(sql);
			 
			// 6. 실행결과 출력하기
			Logger out = Logger.getLogger("SystemOut");
			out.warn("-------------");
			out.warn("번호" + "  " + "이름" + "  " + "나이");
			out.warn("-------------");
			while(rs.next()){
				// 레코드의 칼럼은 배열과 달리 0부터 시작하지 않고 1부터 시작한다.
				// 데이터베이스에서 가져오는 데이터의 타입에 맞게 getString 또는 getInt 등을 호출한다.
				String no = rs.getString(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				 
				out.warn(no + " " + name + " " + age);
			}
			out.warn("-------------");
			
		}catch( ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
		}catch( SQLException e){
			System.out.println("에러 " + e);
		}
		finally{
			//연결 해제  
			try{
				if( conn != null && !conn.isClosed()){
					conn.close();
				}
				if( stmt != null && !stmt.isClosed()){
					stmt.close();
				}
				if( rs != null && !rs.isClosed()){
					rs.close();
				}
			}catch( SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	//insert
	public void insert(Map<String,Object> param) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. 연결하기
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. 쿼리 수행을 위한 prepareStatement 객체 생성
			String sql = "INSERT INTO crud.employee VALUES (?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			// 4. SQL 쿼리 준비
			pstmt.setString(1, param.get("no").toString());
		    pstmt.setString(2, param.get("name").toString());
		    pstmt.setString(3, param.get("age").toString());
			 
			// 5. 쿼리 수행
		    int count = pstmt.executeUpdate();
		    if( count == 0 ){
		        System.out.println("데이터 입력 실패");
		    }else{
		    	System.out.println("데이터 입력 성공");
		    }
		}catch( ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
		}catch( SQLException e){
			System.out.println("에러 " + e);
		}
		finally{
			//연결 해제  
			try{
				if( conn != null && !conn.isClosed()){
					conn.close();
				}
			}catch( SQLException e){
				e.printStackTrace();
			}
		}
	}	
	
	
	//update
	public void update(Map<String,Object> param) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. 연결하기
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. 쿼리 수행을 위한 객체 생성
			String sql = "UPDATE crud.employee SET employee_age=? WHERE no=? ";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			// 4. SQL 쿼리 준비
			pstmt.setString(1, param.get("age").toString());
		    pstmt.setString(2, param.get("no").toString());
			 
			// 5. 쿼리 수행
		    int count = pstmt.executeUpdate();
		    if( count == 0 ){
		        System.out.println("데이터 변경 실패");
		    }else{
		    	System.out.println("데이터 변경 성공");
		    }
		}catch( ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
		}catch( SQLException e){
			System.out.println("에러 " + e);
		}
		finally{
			//연결 해제  
			try{
				if( conn != null && !conn.isClosed()){
					conn.close();
				}
			}catch( SQLException e){
				e.printStackTrace();
			}
		}
	}	
	
	//delete
	public void delete(Map<String,Object> param) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			// 1. 드라이버 로딩
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. 연결하기  "jdbc:mysql://IP주소/사용할db이름"
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. 쿼리 수행을 위한 객체 생성
			String sql = "DELETE FROM crud.employee WHERE no=? ";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			// 4. SQL 쿼리 준비
		    pstmt.setString(1, param.get("no").toString());
			 
			// 5. 쿼리 수행
		    int count = pstmt.executeUpdate();
		    if( count == 0 ){
		        System.out.println("데이터 삭제 실패");
		    }else{
		    	System.out.println("데이터 삭제 성공");
		    }
		}catch( ClassNotFoundException e){
			System.out.println("드라이버 로딩 실패");
		}catch( SQLException e){
			System.out.println("에러 " + e);
		}
		finally{
			//연결 해제  
			try{
				if( conn != null && !conn.isClosed()){
					conn.close();
				}
			}catch( SQLException e){
				e.printStackTrace();
			}
		}
	}	
	
	
	
	
}
