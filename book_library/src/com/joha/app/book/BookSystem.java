package com.joha.app.book;

import java.util.List;
import java.util.Scanner;

import com.joha.app.bookRental.Rent;
import com.joha.app.bookRental.RentDAO;
import com.joha.app.selectInfo.SelectDAO;
import com.joha.app.selectInfo.SelectSystem;

public class BookSystem {
	private BookDAO bdao = BookDAO.getInstance();
	private RentDAO rdao = RentDAO.getInstance();
	private SelectDAO sdao = SelectDAO.getInstance();
	private Scanner sc = new Scanner(System.in);
	
	
	public BookSystem() {
		//int bkk  = bk;
		System.out.println("");
		System.out.println("                          ∧,,,∧");
		System.out.println("                        ∩(＾ 0 ＾)∩");
		System.out.println("           ∘✧₊⁺⁺₊✧∘ 영남대학교 도서관 대출시스템입니다 ∘✧₊⁺⁺₊✧∘ ");
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
			}else if (menuNo ==4){
				//조회(서브메뉴 실행)
				new SelectSystem().run();
			}else if (menuNo ==5){
				//대여
				rentalBook();
			}else if (menuNo ==6){
				//반납
				returnBook();
			}else if (menuNo ==7) {
				//연장
				extendReturnDate();
			}else if(menuNo == 3) {
				//수정
				updateBook();
			}else if (menuNo ==0) {
				//종료
				exit();
				break;
			}else {
				inputError();
			}
			
		}
	}
	
	//메뉴 출력
	private void menuPrint() {
		System.out.println("");
		System.out.println("           ~ 메 뉴 를 숫 자 로 입 력 해 주 세 요 ^o^ ~             ");
		System.out.println("┌─────────────────────────────────────────────────────────┐");
		System.out.println("|   1.책등록 | 2.삭제 | 3.수정 | 4.검색 | 5.대출 | 6.반납 | 7.연장  |");
		System.out.println("|---------------------------------------------------------|");
		System.out.println("|                         0.나가기                          |");
		System.out.println("└─────────────────────────────────────────────────────────┘");
	}
	
	//메뉴 선택
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
	
	//책 등록
	private void inputBook() {
		Book book = inputAll();
		bdao.insert(book);
	}
	
	private Book inputAll() {
		Book book = new Book();
		System.out.println("\n ＊ 등록할 책의 정보를 입력하세요 ＊");
		System.out.print(" 제목 > ");
		book.setBookTitle(sc.nextLine());
		System.out.print(" 저자 > ");
		book.setBookWriter(sc.nextLine());
		System.out.print(" 카테고리 > ");
		book.setBookCategory(sc.nextLine());

		
		return book;
	}
	
	//책 수정
	private void updateBook() {
		//isbn 입력 - 존재하는 책인지 확인 / 대출중이면 수정 불가
		System.out.print(" 수정할 책의");
		int isbn = inputIsbn();
		Book book = bdao.selectBookIsbn(isbn);
		if(book != null) {
			//존재하면 출력 
			System.out.println("\n - 기존 정보 - ");
			System.out.println(book);
		}else if(book == null) {	
			//안되면 경고
			System.out.println(" [ 등록되지 않은 책입니다 ] ");
			return;
		}
		book = inputUpdateInfo(book);
		bdao.updateBookInfo(book);
	}
		
		//수정정보 입력
		private Book inputUpdateInfo(Book book) {
			System.out.println("\n ＊ 수정할 정보를 입력하세요 (수정하지 않으려면 0 입력) ＊ ");
			System.out.print(" 제목 > ");
			String title = sc.nextLine();
			if(!title.equals("0")) {
				book.setBookTitle(title);
			}
			System.out.print(" 작가 > ");
			String writer = sc.nextLine();
			if(!title.equals("0")) {
				book.setBookWriter(writer);
			}
			System.out.print(" 카테고리 > ");
			String category = sc.nextLine();
			if(!category.equals("0")) {
				book.setBookCategory(category);
			}
			return book;
		}

	//책 삭제
	private void deleteBook() {
		int isbn = inputIsbn();
		bdao.delete(isbn);
	}

	//대출
		private void rentalBook() {
			System.out.println("\n   * * 대출기한은 하루입니다 * * ");
			System.out.println(" ＊ 대출할 책의 ISBN을 입력하세요 ＊");
			int isbn = inputIsbn();
			
			//책존재확인하기
			Book book = bdao.selectBookIsbn(isbn);
			if(book == null) {
				//안되면 경고
				System.out.println(" [ 등록되지 않은 책입니다 ] ");
				return;
			}
			//확인되면 대출
			//대여가능여부 확인
			if(book.getBookRental() ==1 ) {
				System.out.println("[ 대출중인 책입니다 ] ");
				return;
			}
			//전화번호 입력받아서 대여기록에 추가 
			System.out.print(" 전화번호(뒷자리 8자리)를 입력하세요 > ");
			int phoneNum = Integer.parseInt(sc.nextLine());
			
			//대여가능여부 업데이트
			book.setBookRental(1);
			rdao.insertRent(book, phoneNum);
			bdao.updateRentalStatus(book);
			
			
			System.out.println(" [ " + isbn + " 대출 완료 ] ");
		}

	//반납
		private void returnBook() {
			int phoneNum = inputPhoneNum();
			//회원확인
			System.out.printf("\n - " + phoneNum + " 님이 대출한 책 목록 - \n");
			List<Rent> rent = sdao.selectPhoneNum(phoneNum);
			if(rent.size()==0) {
				System.out.println(" " + phoneNum + "님이 대출중인 책이 없습니다. ");
				return;
			}
			for(Rent rt : rent) {
				System.out.println(rt);
			}
			//확인되면 isbn 입력 / 확인 
			System.out.println("\n ＊ 반납할 책의 ISBN을 입력하세요 ＊");
			int isbn = inputIsbn();
			Book book = bdao.selectBookIsbn(isbn);
			
			if(book == null) {
				System.out.println(" 대여 목록에 해당 책이 없습니다.");
				return;
			}
			
			//대여가능여부 업데이트
			book.setBookRental(0);
			bdao.updateRentalStatus(book);
			//연체료 문구
			System.out.println(rdao.lateFee(isbn));
			//대여기록에서 삭제 
			rdao.returnBook(isbn);
			}
	
	//연장
	private void extendReturnDate() {
		int phoneNum = inputPhoneNum();
		//회원확인
		System.out.printf("\n - " + phoneNum + " 님이 대출한 책 목록 - \n");
		List<Rent> rent = sdao.selectPhoneNum(phoneNum);
		if(rent.size()==0) {
			System.out.println(" " + phoneNum + "님이 대출중인 책이 없습니다. ");
			return;
		}
		for(Rent rt : rent) {
			System.out.println(rt);
		}
		//확인되면 isbn 입력 / 확인 
		System.out.println("\n ＊ 연장할 책의 ISBN을 입력하세요 ＊");
		int isbn = inputIsbn();
		Book book = bdao.selectBookIsbn(isbn);
		
		if(book == null) {
			System.out.println(" 대여 목록에 해당 책이 없습니다.");
			return;
		}
		//반납일 업데이트
		//book.setBookRental(0);
		rdao.updateReturn(book);
		
		System.out.println("\n [ 대여마감일에서 1일 연장되었습니다 ] ");
	}
	
	
		private int inputIsbn() {
			int isbn = 0;
			System.out.print(" ISBN > ");
			try {
				isbn = Integer.parseInt(sc.nextLine());
			}catch (NumberFormatException e) {
				System.out.println(" ISBN은 4자리 숫자입니다. ");
			}
			return isbn;	
		}
		
		private int inputPhoneNum() {
			System.out.print(" 전화번호 > ");
			return Integer.parseInt(sc.nextLine());
		}
		
		private void exit() {
			System.out.println(" ~ 프로그램을 종료합니다 ~ ");
		}
		
		private void inputError() {
			System.out.println(" 알맞은 메뉴를 입력해주세요 ");
		}
		
	
	
	
	
	
	
	
}
