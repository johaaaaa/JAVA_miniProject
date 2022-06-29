package com.joha.app.selectInfo;

import java.util.List;
import java.util.Scanner;

import com.joha.app.book.Book;
import com.joha.app.bookRental.Rent;
import com.joha.app.bookRental.RentDAO;

public class SelectSystem {
	private SelectDAO sdao = SelectDAO.getInstance();
	private Scanner sc = new Scanner(System.in);
	int page = 1;
	
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
				//대여중인 책 조회
				selectRented();
			}else if (menuNo == 6) {
				//회원별 조회
				selectPhoneNum();
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
		System.out.println("");
		System.out.println("　   ∧   ∧");
		System.out.println("　  ( 0 v 0)  <  검색 방식을 숫자로 선택해주세요 ~ ! ");
		System.out.println("┌─────〇─〇────────────────────────────────────────＊");
		System.out.println("| 1.전체 | 2.제목 | 3.저자 | 4.카테고리 | 5.대출중 | 6.회원 |");
		System.out.println("|-------------------------------------------------|");
		System.out.println("|                   0.뒤로 돌아가기                   |");
		System.out.println("└─────────────────────────────────────────────────┘");
	}
	
	private int selectMenu() {
		int menu = 0;
		while(true) {
		try {
			System.out.print(" 메뉴 선택 > ");
			menu = Integer.parseInt(sc.nextLine());
			break;
		}catch(NumberFormatException e) {
			System.out.println(" 메뉴를 숫자로 입력해주세요 ");
		}
		}
		return menu;
	}
	
	//전체검색 - 한번더돌리면 안나옴 !! 
	private void selectAll() {
		
		List<Book> list = sdao.selectList(page);
		System.out.println("\n - 전체 도서 목록 -");
		for(Book book : list) {
			System.out.println(book);
		}
		System.out.println(" \n                    - 현재  " + page 
				+ " 페이지  / 전체  " + sdao.printLastPage() + " 페이지 -");
		
		while(true) {
			page = insertPage();
			if(page > 0) {
		list = sdao.selectList(page);
		System.out.println("\n - 전체 도서 목록 -");
		for(Book book : list) {
			System.out.println(book);
			}
		System.out.println(" \n                    - 현재  " + page 
				+ " 페이지 / 전체 " + sdao.printLastPage() + " 페이지 -");
			}else if(page > sdao.printLastPage()) {
				System.out.println(" 페이지가 더이상 존재하지 않습니다. ");
			}else {
				break;
			}
		}
	}

		//이동할 페이지 입력
		private int insertPage() {
			System.out.print("\n 이동할 페이지 (뒤로가기 0) > ");
			return Integer.parseInt(sc.nextLine());
		}
	
	//제목검색
	private void selectBookTitle() {
		String bookTitle = inputBookTitle();
		System.out.println("\n - " + bookTitle + " 이/가 제목에 포함된 책 목록 -");
		List<Book> book = sdao.selectBookTitle(bookTitle);
		if(book.size()==0) {
			System.out.println(" 해당 책이 존재하지 않습니다.");
		}
		for(Book bk : book) {
			System.out.println(bk);
		}
	}
	
	//제목 입력
	private String inputBookTitle() {
		System.out.print(" 제목 > ");
		return sc.nextLine();
	}
			
	//저자검색
	private void selectBookWriter() {
		String bookWriter = inputBookWriter();
		System.out.println("\n - " + bookWriter + " 작가의 책 목록 -" );
		List <Book> book = sdao.selectBookWriter(bookWriter);
		if(book.size()==0) {
			System.out.println(" 해당 저자의 책이 존재하지 않습니다.");
		}
		for(Book bk : book) {
			System.out.println(bk);
		}
	}	

	private String inputBookWriter() {
		System.out.print(" 저자 > ");
		return sc.nextLine();
	}

	//카테고리검색
	private void selectBookCategory() {
		String bookCategory = inputBookCategory();
		System.out.println("\n - " + bookCategory + " 분류의 책 목록 - ");
		List <Book> book = sdao.selectBookCategory(bookCategory);
		if(book.size()==0) {
			System.out.println("\n 해당 카테고리에 책이 없습니다 T.T 이런 카테고리는 어떠세요? ");
			printCate();
		}
		
		for(Book bk : book) {
			System.out.println(bk);
		}
	}
	
	private String inputBookCategory() {
		System.out.print(" 카테고리 > ");
		return sc.nextLine();
	}

	private void printCate() {
		List<Book> list = sdao.printCategory();
		String print = "";
		for(Book book : list) {
			print += (book.getBookCategory()+" · ");
			
		}
		print = print.substring(0, print.length()-2);
		System.out.println("[ " + print + "]");
	}
	
	//대출중
	private void selectRented() {
		List<Rent> rent = sdao.selectBookRented(0);
		if(rent.size()==0) {
			System.out.println(" 대출중인 책이 없습니다.");
		}
		System.out.println("\n - 대출상태가 대출중인 책 목록 - ");
		for(Rent rt : rent) {
			System.out.println(rt);
		}
	}

	//회원별조회
	private void selectPhoneNum() {
		int phoneNum = inputPhoneNum();
		System.out.printf("\n - " + phoneNum + "님이 대출한 책 목록 - \n");
		List<Rent> rent = sdao.selectPhoneNum(phoneNum);
		if(rent.size()==0) {
			System.out.println(" 대출중인 책이 없습니다. ");
		}
		for(Rent rt : rent) {
			System.out.println(rt);
		}
		System.out.println("");
	}

	private int inputPhoneNum() {
		System.out.print(" 전화번호 뒷자리 8자리 > ");
		return Integer.parseInt(sc.nextLine());
	}

	
	private void back() {
		System.out.println(" 메인 메뉴로 돌아갑니다 ");
	}
	
	private void inputError() {
		System.out.println(" 알맞은 메뉴를 입력해주세요 ! ");
	}
	
}
