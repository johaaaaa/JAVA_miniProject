package com.joha.app.selectInfo;

import java.util.List;
import java.util.Scanner;

import com.joha.app.suit.Suit;
import com.joha.app.suit.SuitDAO;

public class SelectInfo {
	private SuitDAO dao = SuitDAO.getInstance();
	private Scanner sc = new Scanner(System.in);
	
	public void run() {
		while(true) {
			menuPrint();
			int menuNo = selectMenu();
			
			if(menuNo == 1) {
				//전체 목록 조회
				selectAll();
			}else if(menuNo == 2) {
			//사이즈별 조회
				selectSize();
			}else if(menuNo == 3) {
			//카테고리별 조회
				selectCategory();
			}else if(menuNo == 4) {
			//대여가능한 옷 조회
				selectPossible();
			}else if(menuNo == 5) {
			//대여중인 옷 조회
				selectImpossible();
			}else if (menuNo == 6) {
			//회원별 조회
				selectMember();
			}else if (menuNo == 0) {
			//뒤로가기
				back();
				break;
			}else {
				inputError();
			}
		}
	}
	
	private void menuPrint() {
		System.out.println("┌─────────────────────────────조회메뉴───────────────────────────────┐");
		System.out.println("| 1.전체 | 2.사이즈 | 3.카테고리 | 4.대여가능 | 5.대여중 | 6.회원별 | 0.뒤로가기 |");
		System.out.println("|                  메 뉴 를 숫 자 로 입 력 해 주 세 요 ^0^                |");
		System.out.println("└─────────────────────────────────────────────────────────────────┘");
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
	
	private void selectAll() {
		List<Suit> list = dao.selectAll();
		for(Suit suit : list) {
			System.out.println(suit);
		}
	}


	private void selectSize() {
		String suitSize = inputSuitSize();
		Suit suit = dao.selectSize(suitSize);
		if(suit != null) {
			System.out.println(suit);
		}else {
			System.out.println("해당 제품이 존재하지 않습니다.");
		}
	}


	private String inputSuitSize() {
		// TODO Auto-generated method stub
		return null;
	}

	private void selectCategory() {
		// TODO Auto-generated method stub
		
	}


	private void selectPossible() {
		// TODO Auto-generated method stub
		
	}


	private void selectImpossible() {
		// TODO Auto-generated method stub
		
	}


	private void selectMember() {
		// TODO Auto-generated method stub
		
	}


	
	private void back() {
		System.out.println(" 메인 메뉴로 돌아갑니다 ");
	}
	
	private void inputError() {
		System.out.println(" 알맞은 메뉴를 입력해주세요 ! ");
	}
	
}
