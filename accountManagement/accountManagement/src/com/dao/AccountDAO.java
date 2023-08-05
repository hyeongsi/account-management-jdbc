package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.AccountDTO;

public class AccountDAO {
	public ArrayList<AccountDTO> readAll(Connection conn){
		ArrayList<AccountDTO> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT accountId, name, balance ")
		  .append("FROM tb_account");
		
		try {
			String query = sb.toString();
			pstmt = conn.prepareStatement(query);
			
			// 쿼리 질의 후 ResultSet으로 값을 받음
			// DQL은 executeQuery()로 질의
			rs = pstmt.executeQuery();
			
			// 받은 질의 결과값을 DTO에 담아 list로 생성해서 반환
			while(rs.next()) {
				int accountId = rs.getInt(1);
				String name = rs.getString(2);
				int balance = rs.getInt(3);
				
				AccountDTO dto = new AccountDTO(accountId, name, balance);
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public int updateBalance(Connection conn, int accountId, int amount) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE tb_account ")
		  .append("SET balance=(balance+?) ")
		  .append("WHERE accountId=? ");
		
		try {
			String query = sb.toString();
			pstmt = conn.prepareStatement(query);
			
			// query의 ? 부분을 해당 값으로 치환
			pstmt.setInt(1, amount);	
			pstmt.setInt(2, accountId);
			
			// DML은 executeUpdate()로 질의
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		
		return result;
	}
	public int deposit(Connection conn, int accountId, int amount) {
		int result = 0;
		
		return result;
	}
	public int create(Connection conn, AccountDTO dto) {
		int result = 0;
		
		return result;
	}
	public int delete(Connection conn, int accountId) {
		int result = 0;
		
		return result;
	}
}
