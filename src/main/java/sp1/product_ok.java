package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class product_ok {
	Connection con = null;
	private void dbcon() {
		try {
			this.con = dbconfig.info();			
			
		}catch(Exception e) {
			System.out.println("db접속오류");
		}	
	}
	
	protected int modify_sql(String pidx,String pcode, String pname, String pmoney, String psale, String puse) {
		int sign = 0;
		try {
			this.dbcon();
			String sql = "update product set pname=?,pmoney=?,psale=?,puse=? where pidx=? and pcode=?";
			PreparedStatement ps = this.con.prepareStatement(sql);
			ps.setString(1, pname);
			ps.setString(2, pmoney);
			ps.setString(3, psale);
			ps.setString(4, puse);
			ps.setString(5, pidx);
			ps.setString(6, pcode);
			sign = ps.executeUpdate();
			
			ps.close();
			this.con.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}	
		
		return sign;
	}
}
