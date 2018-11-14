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
			// 1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. �����ϱ�  "jdbc:mysql://IP�ּ�/�����db�̸�" getConnection(url,"�����","��й�ȣ")
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. ���� ������ ���� Statement ��ü ����.
			stmt = conn.createStatement();
			 
			// 4. SQL ���� �ۼ�
			// 1) JDBC���� ������ �ۼ��� ���� �����ݷ�(;)�� ���� �ۼ��Ѵ�.
			String sql = "SELECT no, employee_name, employee_age FROM crud.employee";
			 
			// 5. ���� ����
			// ���ڵ���� ResultSet ��ü�� �߰��ȴ�.
			rs = stmt.executeQuery(sql);
			 
			// 6. ������ ����ϱ�
			Logger out = Logger.getLogger("SystemOut");
			out.warn("-------------");
			out.warn("��ȣ" + "  " + "�̸�" + "  " + "����");
			out.warn("-------------");
			while(rs.next()){
				// ���ڵ��� Į���� �迭�� �޸� 0���� �������� �ʰ� 1���� �����Ѵ�.
				// �����ͺ��̽����� �������� �������� Ÿ�Կ� �°� getString �Ǵ� getInt ���� ȣ���Ѵ�.
				String no = rs.getString(1);
				String name = rs.getString(2);
				String age = rs.getString(3);
				 
				out.warn(no + " " + name + " " + age);
			}
			out.warn("-------------");
			
		}catch( ClassNotFoundException e){
			System.out.println("����̹� �ε� ����");
		}catch( SQLException e){
			System.out.println("���� " + e);
		}
		finally{
			//���� ����  
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
			// 1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. �����ϱ�
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. ���� ������ ���� prepareStatement ��ü ����
			String sql = "INSERT INTO crud.employee VALUES (?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			// 4. SQL ���� �غ�
			pstmt.setString(1, param.get("no").toString());
		    pstmt.setString(2, param.get("name").toString());
		    pstmt.setString(3, param.get("age").toString());
			 
			// 5. ���� ����
		    int count = pstmt.executeUpdate();
		    if( count == 0 ){
		        System.out.println("������ �Է� ����");
		    }else{
		    	System.out.println("������ �Է� ����");
		    }
		}catch( ClassNotFoundException e){
			System.out.println("����̹� �ε� ����");
		}catch( SQLException e){
			System.out.println("���� " + e);
		}
		finally{
			//���� ����  
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
			// 1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. �����ϱ�
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. ���� ������ ���� ��ü ����
			String sql = "UPDATE crud.employee SET employee_age=? WHERE no=? ";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			// 4. SQL ���� �غ�
			pstmt.setString(1, param.get("age").toString());
		    pstmt.setString(2, param.get("no").toString());
			 
			// 5. ���� ����
		    int count = pstmt.executeUpdate();
		    if( count == 0 ){
		        System.out.println("������ ���� ����");
		    }else{
		    	System.out.println("������ ���� ����");
		    }
		}catch( ClassNotFoundException e){
			System.out.println("����̹� �ε� ����");
		}catch( SQLException e){
			System.out.println("���� " + e);
		}
		finally{
			//���� ����  
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
			// 1. ����̹� �ε�
			Class.forName("com.mysql.jdbc.Driver");
			 
			// 2. �����ϱ�  "jdbc:mysql://IP�ּ�/�����db�̸�"
			String url = "jdbc:mysql://35.201.200.252/crud";
			conn = DriverManager.getConnection(url, "root", "levware!1234");
			 
			// 3. ���� ������ ���� ��ü ����
			String sql = "DELETE FROM crud.employee WHERE no=? ";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			 
			// 4. SQL ���� �غ�
		    pstmt.setString(1, param.get("no").toString());
			 
			// 5. ���� ����
		    int count = pstmt.executeUpdate();
		    if( count == 0 ){
		        System.out.println("������ ���� ����");
		    }else{
		    	System.out.println("������ ���� ����");
		    }
		}catch( ClassNotFoundException e){
			System.out.println("����̹� �ε� ����");
		}catch( SQLException e){
			System.out.println("���� " + e);
		}
		finally{
			//���� ����  
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
