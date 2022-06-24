package com.joha.app.bookRental;

import java.sql.SQLException;

import com.joha.app.book.Book;
import com.joha.app.common.DAO;

public class RentDAO extends DAO {
	private static RentDAO rentDAO = null;
	private RentDAO() {}
	public static RentDAO getInstance() {
		if(rentDAO == null) {
			rentDAO = new RentDAO();
		}
		return rentDAO;
	}
	
	//대출 - 재고 -1(stock테이블만들어야함)XX, 대출여부는->대출중으로(두 테이블 다) / 데이터를 rental 테이블에 insert
	
			//-0.책 제목 입력
			
		
			//-1.books 테이블에서 대출가능 -> 대출중으로 변경(수정)
			public void rentStatus(Book book) {
				try {
					connect();
					String sql = "UPDATE books SET book_rental = 1 WHERE ISBN=?";
				
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, book.getIsbn());
					pstmt.executeUpdate(); 
				
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
			}
			
			//-2.rental 테이블에 값 복사			
			public void insertRent(Book book) {
				try {
					connect();
					String sql = "INSERT INTO rental (isbn,book_title,book_writer,book_category,book_stock,book_rental) "
							+ "SELECT isbn,book_title,book_writer,book_category,book_stock,book_rental FROM books WHERE ISBN=?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, book.getIsbn());
					pstmt.executeUpdate();

				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
			}		
					
			//-3.rental 테이블의 (전화번호, 대여일, 대여마감일)컬럼에 값 추가  
			public void insertPhoneNum(Rent rent) {
				try {
					connect();
					String sql = "INSERT INTO rental(phone_num, rent_date, return_date) "
							+ "VALUES(?,?, SELECT  FROM  WHERE ))";

					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, rent.getPhoneNum());
					pstmt.setDate(2, rent.getRentDate());
					pstmt.setDate(3, rent.getReturnDate());
					
					pstmt.executeUpdate();
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
			}
			//-4.대여 완

		
		//반납 - 대출여부 ->대출중으로 수정(books,rental 테이블 둘 다 변경) / 
			//-1. 회원정보 입력받기 
			
			//-2. 책 제목 입력받기
			
			//-3. 대출중->대출가능으로 update(rental,books테이블 둘 다)
			
			//-4. rental테이블에서 삭제

	
	
	
}
