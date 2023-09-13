package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class user_list {
	Connection conn = null;
	PreparedStatement ps = null;
	
	public user_list() {
		try {
			this.conn = dbconfig.info();
			//System.out.println(this.conn);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public int total_member() throws Exception{
		//전체 가입자수 카운팅
		String sql2 = "";
		sql2 = "select count(*) as cnt from member2";
		this.ps = this.conn.prepareStatement(sql2);
		ResultSet rs2 = this.ps.executeQuery();
		rs2.next();
		int total = Integer.parseInt(rs2.getString("cnt"));
		return total;
	}
	
	/*
	 1.검색어를 입력시 데이터베이스에 값과 동일한 경우
	 2.검색어를 입력했는데 데이터베이스 값과 동일하지 않을경우
	 */
	public List<ArrayList<String>> listdata(String sh, String part){//검색어가 있을경우
		List<ArrayList<String>> member2 = new ArrayList<ArrayList<String>>();
		try {
			String sql = "";
			if(part.intern() == "id") {
				sql = "select * from member2 where mid like ? order by mno desc";
			}else if(part.intern() == "tel"){
				sql = "select * from member2 where mtel like ? order by mno desc";
			}
			System.out.println(sql);
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, "%"+sh+"%");
			ResultSet rs = this.ps.executeQuery();
			System.out.println(ps);
			dto_user dto = new dto_user(); // dto로 setter,getter,Array
			//해당 검색단어가 없을 경우 처리
			
			while(rs.next()) {
				dto.setMno(rs.getString("mno"));
				dto.setMid(rs.getString("mid"));
				dto.setMpass(rs.getString("mpass"));
				dto.setMemail(rs.getString("memail"));
				dto.setMtel(rs.getString("mtel"));
				dto.setMarea(rs.getString("marea"));
				dto.setMinter(rs.getString("minter"));
				dto.setMage(rs.getString("mage"));
				dto.setMdate(rs.getString("mdate"));
				member2.add(dto.db_data());
			}
			if(member2.size()==0) {
				dto.setMno(null);
				dto.setMid(null);
				dto.setMpass(null);
				dto.setMemail(null);
				dto.setMtel(null);
				dto.setMarea(null);
				dto.setMinter(null);
				dto.setMage(null);
				dto.setMdate(null);
				member2.add(dto.db_data());
			}
				
			this.conn.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		return member2;
	}
	
	public List<ArrayList<String>> listdata(String sh){//검색어가 있을경우
		List<ArrayList<String>> member2 = new ArrayList<ArrayList<String>>();
		try {
			String sql = "select * from member2 where mid = ? order by mno desc";
			this.ps = this.conn.prepareStatement(sql);
			this.ps.setString(1, sh);
			ResultSet rs = this.ps.executeQuery();
			dto_user dto = new dto_user(); // dto로 setter,getter,Array
			//해당 검색단어가 없을 경우 처리
			while(rs.next()) {
				dto.setMno(rs.getString("mno"));
				dto.setMid(rs.getString("mid"));
				dto.setMpass(rs.getString("mpass"));
				dto.setMemail(rs.getString("memail"));
				dto.setMtel(rs.getString("mtel"));
				dto.setMarea(rs.getString("marea"));
				dto.setMinter(rs.getString("minter"));
				dto.setMage(rs.getString("mage"));
				dto.setMdate(rs.getString("mdate"));
				member2.add(dto.db_data());
			}
			if(member2.size()==0) {
				dto.setMno(null);
				dto.setMid(null);
				dto.setMpass(null);
				dto.setMemail(null);
				dto.setMtel(null);
				dto.setMarea(null);
				dto.setMinter(null);
				dto.setMage(null);
				dto.setMdate(null);
				member2.add(dto.db_data());
			}
				
			this.conn.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		return member2;
	}
	
	//sql 문법 및 데이터를 가져옴
	public List<ArrayList<String>> listdata(){//검색어가 없을경우
		List<ArrayList<String>> member2 = new ArrayList<ArrayList<String>>();
		try {
			String sql = "select * from member2 order by mno desc";
			this.ps = this.conn.prepareStatement(sql);
			ResultSet rs = this.ps.executeQuery();
			dto_user dto = new dto_user(); // dto로 setter,getter,Array
			while(rs.next()) {
				dto.setMno(rs.getString("mno"));
				dto.setMid(rs.getString("mid"));
				dto.setMpass(rs.getString("mpass"));
				dto.setMemail(rs.getString("memail"));
				dto.setMtel(rs.getString("mtel"));
				dto.setMarea(rs.getString("marea"));
				dto.setMinter(rs.getString("minter"));
				dto.setMage(rs.getString("mage"));
				dto.setMdate(rs.getString("mdate"));
				member2.add(dto.db_data());
			}
			
			this.conn.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return member2;
	}
}
