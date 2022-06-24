package com.joha.app.selectInfo;

import java.util.List;
import java.util.Scanner;

import com.joha.app.book.Book;
import com.joha.app.book.BookDAO;

public class SelectSystem {
	private SelectDAO sdao = SelectDAO.getInstance();
	private Scanner sc = new Scanner(System.in);
	
	public void run() {
		while(true) {
			menuPrint();
			int menuNo = selectMenu();
			
			if(menuNo == 1) {
				//전체 목록 조회
				selectAll();
			}else if(menuNo == 2) {
				//제목별 조회
				selectBookTitle();
			}else if(menuNo == 3) {
				//저자별 조회
				selectBookWriter();
			}else if(menuNo == 4) { 
				//카테고리별 조회
				selectBookCategory();
			}else if(menuNo == 5) {
				//대여가능한 책 조회
				selectPossible();
			}else if(menuNo == 6) {
				//대여중인 책 조회
				selectImpossible();
			}else if (menuNo == 7) {
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
		System.out.println("┌──────────────────────────────────────────────────────────────────────┐");
		System.out.println("| 1.전체 | 2.제목 | 3.저자 | 4.카테고리 | 5.대출가능 | 6.대출중 | 7.회원 | 0.뒤로가기 |");
		System.out.println("|                     메 뉴 를 숫 자 로 입 력 해 주 세 요 ^0^                  |");
		System.out.println("└──────────────────────────────────────────────────────────────────────┘");
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
		List<Book> list = sdao.selectAll();
		for(Book book : list) {
			System.out.println(book);
		}
	}

	//제목검색
	private void selectBookTitle() {
		String bookTitle = inputBookTitle();
		Book book = sdao.selectBookTitle(bookTitle);
		if(book != null) {
			System.out.println(book);
		}else {
			System.out.println("해당 책이 존재하지 않습니다.");
		}
	}
	
	private String inputBookTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	//저자검색
	private void selectBookWriter() {
		String bookTitle = inputBookWriter();
		Book book = sdao.selectBookTitle(bookTitle);
		if(book != null) {
			System.out.println(book);
		}else {
			System.out.println("해당 책이 존재하지 않습니다.");
		}		
	}

	private String inputBookWriter() {
		// TODO Auto-generated method stub
		return null;
	}



	private void selectBookCategory() {
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
