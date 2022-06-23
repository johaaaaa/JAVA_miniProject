package com.joha.app.suit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.joha.app.common.DAO;

public class SuitDAO extends DAO{
	private static SuitDAO suitDAO = null;
	private SuitDAO() {}
	public static SuitDAO getInstance() {
		if(suitDAO == null) {
			suitDAO = new SuitDAO();
		}
		return suitDAO; 
	}
	
	//crud
	//등록
	public void insert(Suit suit) {
		
		try {
			connect(); //try-catch문밖에있어도O?
			String sql = "INSERT INTO suit VALUES(suit_no_seq.nextval,?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, suit.getSuitSize());
			pstmt.setString(2, suit.getCategory());
			pstmt.setInt(3, suit.getStock());
			pstmt.setInt(4, suit.getRental());
			
			int result = pstmt.executeUpdate(); //실행! !
			
			if(result>0) {
				System.out.println(" [ 등록되었습니다 ] ");
			}else {
				System.out.println("등록 실패했습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//삭제 - 있는 제품인지 확인하고 삭제 
	public void delete(int suitNo) {
		try {
			connect();
			String sql = "DELETE FROM suit WHERE suit_no="+ suitNo;
			
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result>0) {
				System.out.println(" [ 삭제되었습니다 ] ");
			}else {
				System.out.println("[ 삭제되지 않았습니다. ] ");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	
	//조회(따로클래스만들어서 메인메뉴-조회-조회메뉴)
		//전체조회
		public List<Suit> selectAll(){
			List<Suit> list = new ArrayList<>();
			try {
				connect();
				String sql = "SELECT * FROM suit ORDER BY suit_no";
				
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					Suit suit = new Suit();
					suit.setSuitNo(rs.getInt("suit_no"));
					suit.setSuitSize(rs.getString("suit_size"));
					suit.setCategory(rs.getString("category"));
					suit.setStock(rs.getInt("stock"));
					suit.setRental(rs.getInt("rental"));
					
					list.add(suit);
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
			return list;
		}
	
		//단건조회 
			//사이즈 조회
		public Suit selectSize(String suitSize) {
			Suit suit = null;
			try {
				connect();
				String sql = "SELECT * FROM suit WHERE suit_size =?";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while(rs.next()) {
					suit = new suit();
					suit.setSuitNo(rs.getString("suit_no"));
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}return suit;
		}
		
	
			//카테고리 조회
	
			//대여가능 조회
		
			//대여중 조회
		
			//회원별 대여목록 조회
	
	//대여
	
	//반납
	
	
	
	
	
	
	
	
	
}
