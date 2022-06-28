package com.joha.app.member;

import java.sql.SQLException;

import com.joha.app.common.DAO;

public class MemberDAO extends DAO{
	private static MemberDAO dao = null;
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		if(dao == null) {
			dao = new MemberDAO();
		}
		return dao;
	}
	
	public Member selectOne(Member member) {
		Member loginInfo = null;
		try {
			connect();
			
			String sql = "SELECT * FROM members WHERE member_id = '" + member.getMemberId() + "'";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				if(rs.getString("member_password").equals(member.getMemberPwd())){
					loginInfo = new Member();
					loginInfo.setMemberId(rs.getString("member_id"));
					loginInfo.setMemberPwd(rs.getString("member_password"));
					loginInfo.setMemberRole(rs.getInt("member_role"));
				}else {
					System.out.println(" 비밀번호가 일치하지 않습니다 ");
				}
			}else {
				System.out.println(" 아이디가 존재하지 않습니다 ");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return loginInfo;
	}
	
	
	
}
