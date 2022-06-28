package com.joha.app.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private String memberId;
	private String memberPwd;
	private int memberRole; //0-관리자, 1-일반
	
	@Override
	public String toString() {
		String info = "";
		if(memberRole == 0) {
			info = "관리자 계정 : " + memberId;
		}else {
			info = "일반 계정 : " + memberId;
		}
		return info;
	}
	
}