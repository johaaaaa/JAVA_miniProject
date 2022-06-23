package com.joha.app.suit;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Suit {
	private int suitNo;
	private String suitSize;
	private String category;
	private int stock;
	private int rental;
	@Override
	public String toString() {
		return "제품코드" + suitNo + "| 사이즈 " + suitSize 
				+ "| category " + category + "| stock " + stock + "| rental "
 				+ rental;
	}
}
