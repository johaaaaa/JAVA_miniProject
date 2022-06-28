package com.joha.app.common;

import java.util.Scanner;

import com.joha.app.book.BookSystem;
import com.joha.app.member.Member;
import com.joha.app.member.MemberDAO;

public class LoginControl {
	Scanner sc = new Scanner(System.in);
	private static Member loginInfo = null;

	public static Member getLoginInfo() {
		return loginInfo;
	}

	public LoginControl() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();

			if (menuNo == 1) {
				login();
			} else if (menuNo == 2) {
				exit();
				break;
			} else {
				showInputError();
			}
		}
	}

	private void menuPrint() {
		System.out.println("   ∩ ,,,∩");
		System.out.println("  ( 0 v 0)");
		System.out.println("┌─∪─∪─┐");
		System.out.println("| 1. 로그인  |");
		System.out.println("| 2. 나가기  |");
		System.out.println("└──────────┘");
		System.out.print(" 메뉴 선택 > ");
	}
	
	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println(" 메뉴를 숫자로 입력해주세요 ");
		}
		return menuNo;
	}
	
	private void exit() {
		System.out.println(" ~ 프로그램을 종료합니다 ~ ");
	}
	
	private void showInputError() {
		System.out.println(" 알맞은 메뉴를 입력해주세요 ");
	}

	private void login() {
		Member inputInfo = inputMember();
		loginInfo = MemberDAO.getInstance().selectOne(inputInfo);
		
		if(loginInfo == null) return;
		
		new BookSystem().run();
	}
	
	private Member inputMember() {
		Member info = new Member();
		System.out.print(" 아이디 > ");
		info.setMemberId(sc.nextLine());
		System.out.print(" 비밀번호 > ");
		info.setMemberPwd(sc.nextLine());
		return info;
	}
	
}
