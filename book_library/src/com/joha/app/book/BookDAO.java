package com.joha.app.book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joha.app.common.DAO;

public class BookDAO extends DAO{
	private static BookDAO bookDAO = null;
	private BookDAO() {}
	public static BookDAO getInstance() {
		if(bookDAO == null) {
			bookDAO = new BookDAO();
		}
		return bookDAO; 
	}
	
	//crud
	//등록
	public void insert(Book book) {
		
		try {
			connect(); //try-catch문밖에있어도O?
			String sql = "INSERT INTO books VALUES(book_isbn_seq.nextval,?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, book.getBookTitle());
			pstmt.setString(2, book.getBookWriter());
			pstmt.setString(3, book.getBookCategory());
			pstmt.setInt(4, book.getBookStock());
			pstmt.setInt(5, book.getBookRental());
			
			int result = pstmt.executeUpdate(); //실행! !
			
			if(result>0) {
				System.out.println(" [ 등록되었습니다 ] ");
			}else {
				System.out.println("등록 실패했습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//삭제 - 있는 책인지 확인하고 삭제 
	public void delete(int isbn) {
		try {
			connect();
			String sql = "DELETE FROM books WHERE isbn="+ isbn;
			
			stmt = conn.createStatement();
			int result  = stmt.executeUpdate(sql);
		
			if(result>0) {
				System.out.println(" [ 삭제되었습니다 ] "); 
			}else {
				System.out.println(" [ 존재하지 않는 책입니다 ] ");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	//조회 - selectDAO 클래스에서
	//대출 - rentDAO 클래스
	//반납 - rentDAO 클래스 

	
	
	
	
	
	
}
