package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.security.auth.login.AccountException;

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
	public int transfer(int sendAcntId, int recvAcntId, int amount) throws AccountException{
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
				throw new AccountException("송금을 실패했습니다.");
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
	public int deposit(int recvAcntId, int amount) throws AccountException {
		Connection conn = null;
		AccountDAO dao = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			
			result = dao.updateBalance(conn, recvAcntId, amount);	
			if(result == 0)
				throw new AccountException("송금을 실패했습니다.");
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
	public int deleteAccount(int accountId) throws AccountException{
		Connection conn = null;
		AccountDAO dao = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			
			result = dao.delete(conn, accountId);	
			if(result == 0)
				throw new AccountException("계정 삭제에 실패했습니다. ");
		} catch (SQLException e) {
			e.printStackTrace(); 
		} finally {
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}
	
	public boolean isPosibleSend(int accountId, int balance) {
		Connection conn = null;
		AccountDAO dao = null;
		boolean result = false;
		
		try {
			conn = DriverManager.getConnection(url, id, pw);
			dao = new AccountDAO();
			
			result = dao.isPosibleSend(conn, accountId, balance);	
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
