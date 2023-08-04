package com.dto;

public class AccountDTO{

	int accountId;	// 계좌 id
	String name;		// 이름
	int balance;		// 잔고
	
	public AccountDTO() {}
	public AccountDTO(int accountId, String name, int balance) {
		this.accountId = accountId;
		this.name = name;
		this.balance = balance;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public static String getHeader() {	
		return String.format("%-10s\t %-10s\t %-10s", "accountId", "name", "balance");
	}
	public String getDTO() {
		return String.format("%-10d\t %-10s\t %-10d", accountId, name, balance);
	}
}
