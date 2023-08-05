package com.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.dto.AccountDTO;
import com.service.AccountService;

public class accountView {
	static AccountService service = new AccountService();
	
	static final int READ_ALL = 1;
	static final int TRANSFER = 2;
	static final int DEPOSIT = 3;
	static final int CREATE = 4;
	static final int DELETE = 5;

	private accountView() {};
	
	public static void renderMenu() {
		System.out.println("**************************");
		System.out.println("       [계좌 관리 메뉴]       ");
		System.out.println("**************************");
		System.out.println("1. 전체 계좌 확인");
		System.out.println("2. 송금");
		System.out.println("3. 입금");
		System.out.println("4. 계좌 추가");
		System.out.println("5. 계좌 삭제");
		System.out.println("0. 종료");
		System.out.println("**************************");
		System.out.print("메뉴 입력 => ");
	}
	
	public static boolean run(Scanner scan) {
		int chooseMenu = scan.nextInt();
		
		switch (chooseMenu) {
		case READ_ALL:	// 조회
			readAll();
			break;
		case TRANSFER:	// 송금
			transfer(scan);
			break;
		case DEPOSIT:	// 입금
			deposit(scan);
			break;
		case CREATE:		// 생성
			createAccount(scan);
			break;
		case DELETE:		// 삭제
			deleteAccount(scan);
			break;
		default:
			System.out.println("프로그램이 종료되었습니다.");
			return false;
		} 
		
		return true;
	}
	
	public static void readAll() {
		ArrayList<AccountDTO> list = service.readAll();
		renderDTO(list);
	}
	public static void transfer(Scanner scan) {
		System.out.println("송신자의 계정 id를 입력해주세요.");
		int sendAcntId = scan.nextInt();
		System.out.println("보낼 금액을 입력해주세요.");
		int amount = scan.nextInt();
		System.out.println("수신자의 계정 id를 입력해주세요.");
		int recvAcntId = scan.nextInt();
		
		service.transfer(sendAcntId, recvAcntId, amount);
		// 송수신 처리 여부에 따라 예외처리
	}
	public static void deposit(Scanner scan) {
		System.out.println("입금할 계정의 id를 입력해주세요.");
		int recvAcntId = scan.nextInt();
		System.out.println("입금할 금액을 입력해주세요.");
		int amount = scan.nextInt();
		
		service.deposit(recvAcntId, amount);
		// 송수신 처리 여부에 따라 예외처리
	}
	public static void createAccount(Scanner scan) {
		System.out.println("계정을 생성하는분의 이름을 입력해주세요.");
		String name = scan.next();
		
		service.createAccount(name);
	}
	public static void deleteAccount(Scanner scan) {
		System.out.println("삭제할 계정의 id를 입력해주세요.");
		int id = scan.nextInt();
		
		service.deleteAccount(id);
	}
	
	public static void renderDTO(ArrayList<AccountDTO> list) {
		StringBuilder sb = new StringBuilder();
		
		sb.append('\n');
		sb.append(AccountDTO.getHeader()).append('\n');
		
		for(AccountDTO dto : list) {
			sb.append(dto.getDTO()).append('\n');
		}
		
		System.out.println(sb);
	}
}
