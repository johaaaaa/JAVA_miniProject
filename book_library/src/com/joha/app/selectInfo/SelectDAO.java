package com.joha.app.selectInfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
						book.setBookStock(rs.getInt("book_stock"));
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
				public Book selectBookTitle(String bookTitle) {
					Book book = null;
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_title ='" + bookTitle +"'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							book = new Book();
							book.setBookTitle(rs.getString("book_title"));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return book;
				}
				
				//저자 조회
				public Book selectBookWriter(String bookWriter) {
					Book book = null;
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_writer ='"+bookWriter+"'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							book = new Book();
							book.setBookWriter(rs.getString("book_writer"));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return book;
				}
				
				//카테고리(분류) 조회
				public Book selectBookCategory(String bookCategory) {
					Book book = null;
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_category='"+bookCategory+"'";
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							book = new Book();
							book.setBookCategory(rs.getString("book_category"));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return book;
				}
			
				//대여가능 조회
				public Book selectBookRental(int bookRental) {
					Book book = null;
					try {
						connect();
						String sql = "SELECT * FROM books WHERE book_rental=1";
						stmt = conn.createStatement();
						stmt.executeQuery(sql);
						
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return book;
				}
			
				
				//대여중 조회 (rental table을 select)
				public Rent selectBookRented(int rental) {
					Rent rent = null;
					try {
						connect();
						String sql = "SELECT * FROM rental";
						stmt = conn.createStatement();
						stmt.executeQuery(sql);
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return rent;
				}
				
				//회원별 대여목록 조회
				public Rent selectMember(int phoneNum) {
					Rent rent = null;
					try {
						connect();
						String sql = "SELECT * FROM rental WHERE phone_num ="+phoneNum;
						stmt = conn.createStatement();
						rs = stmt.executeQuery(sql);
						while(rs.next()) {
							rent = new Rent();
							rent.setPhoneNum(rs.getInt("phone_num"));
						}
					}catch(SQLException e) {
						e.printStackTrace();
					}finally {
						disconnect();
					}return rent;
				}
}
