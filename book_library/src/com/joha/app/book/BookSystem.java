package com.joha.app.book;

import java.util.Scanner;

import com.joha.app.selectInfo.SelectSystem;

public class BookSystem {
	private BookDAO bdao = BookDAO.getInstance();
	private Scanner sc = new Scanner(System.in);
	
	public BookSystem() {
		while(true) {
			//메뉴 출력
			menuPrint();
			//메뉴 입력
			int menuNo = selectMenu();
			//기능별 실행
			if(menuNo == 1) {
				//제품 등록
				inputBook();
			}else if (menuNo ==2) {
				//삭제
				deleteBook();
			}else if (menuNo ==3){
				//조회(서브메뉴 실행)
				new SelectSystem().run();
			}else if (menuNo ==4){
				//대여
				rentalBook();
			}else if (menuNo ==5){
				//반납
				returnBook();
			}else if (menuNo ==0) {
				//종료
				exit();
				break;
			}else {
				inputError();
			}
			
		}
	}
	
	private void returnBook() {
		int isbn = inputIsbn();
		bdao.returnBook(isbn);		
	}

	private void rentalBook() {
		Book book = inputIsbn();
		bdao.rentBook(book);
	}

	private void menuPrint() {
		System.out.println("┌─────────────────────────────────────────────┐");
		System.out.println("| 1.등록 | 2.삭제 | 3.조회 | 4.대출 | 5.반납 | 0.종료 |");
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
	
	private void inputBook() {
		Book book = inputAll();
		bdao.insert(book);
	}
	
	private Book inputAll() {
		Book book = new Book();
		System.out.print(" 제목 > ");
		book.setBookTitle(sc.nextLine());
		System.out.print(" 저자 > ");
		book.setBookWriter(sc.nextLine());
		System.out.print(" 카테고리 > ");
		book.setBookCategory(sc.nextLine());
		System.out.print(" 재고 > ");
		book.setBookStock(Integer.parseInt(sc.nextLine()));
		
		return book;
	}
	
	private void deleteBook() {
		int isbn = inputIsbn();
		bdao.delete(isbn);
	}

	private int inputIsbn() {
		System.out.print(" ISBN > ");
		return Integer.parseInt(sc.nextLine());
	}
	
	private void exit() {
		System.out.println(" ~ 프로그램을 종료합니다 ~ ");
	}
	
	private void inputError() {
		System.out.println(" 알맞은 메뉴를 입력해주세요 ! ");
	}
	
	
	
	
	
	
	
	
}
