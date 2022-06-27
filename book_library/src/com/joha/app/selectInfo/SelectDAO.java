package com.joha.app.selectInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.joha.app.book.Book;
import com.joha.app.bookRental.Rent;
import com.joha.app.common.DAO;

public class SelectDAO extends DAO {
	private static SelectDAO selectDAO = null;
	private SelectDAO() {}
	public static SelectDAO getInstance() {
		if(selectDAO == null) {
			selectDAO = new SelectDAO();
		}
		return selectDAO;
	}
	
	//조회(따로클래스만들어서 메인메뉴-조회-조회메뉴)
			//전체조회
			public List<Book> selectAll(){
				List<Book> list = new ArrayList<>();
				try {
					connect();
					String sql = "SELECT * FROM books ORDER BY isbn";
					
					stmt = conn.createStatement();
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						Book book = new Book();
						book.setIsbn(rs.getInt("isbn"));
						book.setBookTitle(rs.getString("book_title"));
						book.setBookWriter(rs.getString("book_writer"));
						book.setBookCategory(rs.getString("book_category"));
						book.setBookRental(rs.getInt("book_rental"));
						
						list.add(book);
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					disconnect();
				}
				return list;
			}
		
			//단건조회 
				//제목 조회
				public List<Book> selectBookTitle(String bookTitle) {
					List<Book> list = new ArrayList<>();
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_title LIKE '%'||?||'%'";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, bookTitle);
						rs = pstmt.executeQuery();

						while(rs.next()) {
							Book book = new Book();
							book.setIsbn(rs.getInt("isbn"));
							book.setBookTitle(rs.getString("book_title"));
							book.setBookWriter(rs.getString("book_writer"));
							book.setBookCategory(rs.getString("book_category"));
							book.setBookRental(rs.getInt("book_rental"));
							list.add(book);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return list;
				}
				
				//저자 조회
				public List<Book> selectBookWriter(String bookWriter) {
					List<Book> list = new ArrayList<>();
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_writer ='"+bookWriter+"'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							Book book = new Book();
							book.setIsbn(rs.getInt("isbn"));
							book.setBookTitle(rs.getString("book_title"));
							book.setBookWriter(rs.getString("book_writer"));
							book.setBookCategory(rs.getString("book_category"));
							book.setBookRental(rs.getInt("book_rental"));
							list.add(book);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return list;
				}
				
				//카테고리(분류) 조회
				public List<Book> selectBookCategory(String bookCategory) {
					List<Book> list = new ArrayList<>();
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_category=UPPER(?)";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, bookCategory);
						rs = pstmt.executeQuery();
						
						while(rs.next()) {
							
							Book book = new Book();
							book.setIsbn(rs.getInt("isbn"));
							book.setBookTitle(rs.getString("book_title"));
							book.setBookWriter(rs.getString("book_writer"));
							book.setBookCategory(rs.getString("book_category"));
							book.setBookRental(rs.getInt("book_rental"));
							list.add(book);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return list;
				}
			
				//대여중 조회 (rental table을 select)
				public List<Book> selectBookRented(int bookRental) {
					List<Book> list = new ArrayList<>();
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_rental=1";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						
						while(rs.next()) {
							Book book = new Book();
							book.setIsbn(rs.getInt("isbn"));
							book.setBookTitle(rs.getString("book_title"));
							book.setBookWriter(rs.getString("book_writer"));
							book.setBookCategory(rs.getString("book_category"));
							book.setBookRental(rs.getInt("book_rental"));
							list.add(book);
						}
						
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return list;
				}
				
				
				//회원별 대여목록 조회
				public List<Rent> selectPhoneNum(int phoneNum) {
					List <Rent> list = new ArrayList<>();
					try {
						connect();
						String sql = "SELECT * FROM rental WHERE phone_num ="+phoneNum;
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							Rent rent = new Rent(); 
							rent.setIsbn(rs.getInt("isbn"));
							rent.setBookTitle(rs.getString("book_title"));
							rent.setBookWriter(rs.getString("book_writer"));
							rent.setBookCategory(rs.getString("book_category"));
							rent.setBookRental(rs.getInt("book_rental"));
							rent.setRentDate(rs.getDate("rent_date"));
							rent.setReturnDate(rs.getDate("return_date"));
							rent.setPhoneNum(rs.getInt("phone_num"));
							list.add(rent);
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return list;
				}
}
