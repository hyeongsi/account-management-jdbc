package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dao.AccountDAO;
import com.dto.AccountDTO;

public class AccountService {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "account";
	String pw = "account";
	
	public AccountService() {
		// 접속할 DB의 드라이버를 메모리에 적재
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<AccountDTO> readAll() {
		Connection conn = null;
		AccountDAO dao = null;
		ArrayList<AccountDTO> list = null;
		
		try {
			// DBDriver로 해당 DB 접속
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			list = dao.readAll(conn);
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		
		return list;
	}
	public int transfer(int sendAcntId, int recvAcntId, int amount) {
		Connection conn = null;
		AccountDAO dao = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			
			// 트랜잭션 처리를 위해 자동 커밋 해제
			conn.setAutoCommit(false);
			result += dao.updateBalance(conn, sendAcntId, -amount);	
			result += dao.updateBalance(conn, recvAcntId, amount);	
			
			// 둘 다 성공한 경우만 적용
			if(result == 2) {
				conn.commit();				
			}else {
				conn.rollback();
			}
		} catch (SQLException e) {
			try {
				if(conn != null) conn.rollback();
			} catch (SQLException e1) { e1.printStackTrace(); }
			e.printStackTrace(); 
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}
	public int deposit(int recvAcntId, int amount) {
		Connection conn = null;
		AccountDAO dao = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			
			result = dao.updateBalance(conn, recvAcntId, amount);		
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}
	public int createAccount(String name) {
		Connection conn = null;
		AccountDAO dao = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			
			result = dao.create(conn, name);		
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}
	public int deleteAccount(int accountId) {
		Connection conn = null;
		AccountDAO dao = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			
			result = dao.delete(conn, accountId);		
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}
}
