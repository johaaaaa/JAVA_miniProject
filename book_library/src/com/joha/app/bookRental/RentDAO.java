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
		//대출	
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
			public void insertRent(Book book, int phoneNum) {
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
					String sql = "SELECT * FROM rental WHERE phone_num =" + phoneNum;
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
			
			//-2. 책 제목 입력받
			//-3. 대출중->대출가능으로 update(rental,books테이블 둘 다)
			//-4. rental테이블에서 삭제
			public Rent returnBook(int isbn) {
				Rent rent = null;
				try {
					connect();
					String sql = "DELETE FROM rental WHERE isbn=" + isbn;
					stmt = conn.createStatement();
					int result = stmt.executeUpdate(sql);
						
					if(result >0) {
						
						System.out.println(" [ 정상적으로 반납되었습니다 ] ");
					}else {
						System.out.println(" [ 반납되지 않았습니다 ] ");
					}
					
				}catch(SQLException e){
					e.printStackTrace();
					
				}finally {
					disconnect();
				}
				return rent;
			}
			//연체
			public String lateFee(int isbn) {
				String lateFee = "";
				try {
					connect();
					String sql = "SELECT return_date, "
							+ "CASE WHEN return_date<sysdate THEN TRUNC(sysdate-return_date) WHEN return_date>= sysdate THEN 0 END late FROM rental WHERE isbn="+isbn;
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					
					if(rs.next()) {
						int late = rs.getInt("late");
						if(late>0) {
							
							lateFee += "\n <( ‵□′)>── [ "+ late + " ] 일 연체되어 연체료 [ "+late*500+" ] 원이 발생했습니다 ─Ｃε(┬﹏┬)3  \n";
						}
					}else {
						
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
				return lateFee;
			}
			
			//연장(returndate수정)
			//회원번호 확인 - 대출목록 출력 - 연장할 isbn 입력 - returndate update 쿼리
			public void updateReturn(Book book) {
				try {
					connect();
					String sql = "UPDATE rental SET return_date = return_date +1 WHERE isbn = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, book.getIsbn());
					
					pstmt.executeUpdate();
					
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
			}
			
}
