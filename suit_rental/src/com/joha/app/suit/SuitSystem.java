package com.joha.app.suit;

import java.util.Scanner;

import com.joha.app.selectInfo.SelectInfo;

public class SuitSystem {
	private SuitDAO dao = SuitDAO.getInstance();
	private Scanner sc = new Scanner(System.in);
	
	public SuitSystem() {
		while(true) {
			//메뉴 출력
			menuPrint();
			//메뉴 입력
			int menuNo = selectMenu();
			//기능별 실행
			if(menuNo == 1) {
				//제품 등록
				inputSuit();
			}else if (menuNo ==2) {
				//삭제
				deleteSuit();
			}else if (menuNo ==3){
				//조회(서브메뉴 실행)
				new SelectInfo().run();
			}else if (menuNo ==4){
				//대여
				//rentalSuit();
			}else if (menuNo ==5){
				//반납
				//returnSuit();
			}else if (menuNo ==0) {
				//종료
				exit();
				break;
			}else {
				inputError();
			}
			
		}
	}
	
	private void menuPrint() {
		System.out.println("┌─────────────────────────────────────────────┐");
		System.out.println("| 1.등록 | 2.삭제 | 3.조회 | 4.대여 | 5.반납 | 0.종료 |");
		System.out.println("|       메 뉴 를 숫 자 로 입 력 해 주 세 요 ^0^       |");
		System.out.println("└─────────────────────────────────────────────┘");
		System.out.print(" 메뉴 선택 > ");
	}
	
	private int selectMenu() {
		int menu = 0;
		try {
			menu = Integer.parseInt(sc.nextLine());
		}catch(NumberFormatException e) {
			System.out.println(" 메뉴를 숫자로 입력해주세요 ! ");
		}
		return menu;
	}
	
	private void inputSuit() {
		Suit suit = inputAll();
		dao.insert(suit);
	}
	
	private Suit inputAll() {
		Suit suit = new Suit();
		System.out.print(" 사이즈 > ");
		suit.setSuitSize(sc.nextLine());
		System.out.print(" 카테고리 > ");
		suit.setCategory(sc.nextLine());
		System.out.print(" 재고 > ");
		suit.setStock(Integer.parseInt(sc.nextLine()));
		
		return suit;
	}
	
	private void deleteSuit() {
		int suitNo = inputSuitNo();
		dao.delete(suitNo);
	}

	private int inputSuitNo() {
		System.out.print(" 제품번호 > ");
		return Integer.parseInt(sc.nextLine());
	}
	
	private void exit() {
		System.out.println(" ~ 프로그램을 종료합니다 ~ ");
	}
	
	private void inputError() {
		System.out.println(" 알맞은 메뉴를 입력해주세요 ! ");
	}
	
	
	
	
	
	
	
	
}
