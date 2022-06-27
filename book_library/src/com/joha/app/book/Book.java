package com.joha.app.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
	protected int isbn;
	protected String bookTitle;
	protected String bookWriter;
	protected String bookCategory;
	protected int bookRental;
	
	@Override
	public String toString() {
		if(bookRental>0) {
			return " [ISBN] " + isbn + " [제목] " + bookTitle 
					+ " [저자] " + bookWriter + " [분류] "
					+ bookCategory + " [대출가능여부] 대출중";	
		}else {
		return " [ISBN] " + isbn + " [제목] " + bookTitle 
				+ " [저자] " + bookWriter + " [분류] "
				+ bookCategory + " [대출가능여부] 대출가능";
		}
	}
}


