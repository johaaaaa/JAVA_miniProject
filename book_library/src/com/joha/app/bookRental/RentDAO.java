package com.joha.app.bookRental;

import java.sql.SQLException;

import com.joha.app.book.Rent;
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
	
	
	//crud

	} //isbn기준
	
	//
	
	
	//대출 - 재고 -1(stock테이블만들어야함)XX, 대출여부는->대출중으로(두 테이블 다) / 데이터를 rental 테이블에 insert
	
			//-0.책 제목 입력
			
		
			//-1.books 테이블에서 대출가능 -> 대출중으로 변경(수정)
			public void rentStatus(Rent book) {
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
			public void insertRent(Rent book, int phoneNum) {
				try {
					connect();
					String sql = "INSERT INTO rental VALUES(?, ?, ?, ?, ?,default,default,?)";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, book.getIsbn());
					pstmt.setString(2, book.getBookTitle());
					pstmt.setString(3, book.getBookWriter());
					pstmt.setString(4, book.getBookCategory());
					pstmt.setInt(5, book.getBookRental());
					pstmt.setInt(6, phoneNum);
					
					int result = pstmt.executeUpdate();
					
					if(result>0) {
						System.out.println("완료");
					}else {
						System.out.println("실패");
					}
					
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
			//회원정보 조회
			public Rent selectPhoneNum(int phoneNum) {
				Rent rent = null;
				try {
					connect();
					String sql = "SELECT * FROM rental WHERE phoneNum =" + phoneNum;
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						rent = new Rent();
						rent.setIsbn(rs.getInt("isbn"));
						rent.setBookTitle(rs.getString("book_title"));
						rent.setBookWriter(rs.getString("book_writer"));
						rent.setBookCategory(rs.getString("book_category"));
						rent.setBookRental(rs.getInt("book_rental"));
						rent.setRentDate(rs.getDate("rent_date"));
						rent.setReturnDate(rs.getDate("return_date"));
						rent.setPhoneNum(rs.getInt("phone_num"));
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
				return rent;
			} 
			
			//-2. 책 제목 입력받기
			
			//-3. 대출중->대출가능으로 update(rental,books테이블 둘 다)
			
			//-4. rental테이블에서 삭제

	
	
	
}
