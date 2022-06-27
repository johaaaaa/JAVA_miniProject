package com.joha.app.bookRental;

import java.sql.Date;

import com.joha.app.book.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rent extends Book{
	private int phoneNum;
	private Date rentDate;
	private Date returnDate;
	
	@Override
	public String toString() {
		return " [ISBN] " + isbn + " [제목] " + bookTitle + " [저자] " + bookWriter 
				+ " | [대여일] " + rentDate + " [대여마감일] " + returnDate;
	}
	
	
}
