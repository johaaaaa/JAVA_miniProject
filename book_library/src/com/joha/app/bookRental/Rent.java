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
}
