package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class product_delete {

	Connection con = null;
	
	public product_delete() {
		try {
			this.con = dbconfig.info();			
			
		}catch(Exception e) {
			System.out.println("db접속오류");
		}		
	}
	
	//삭제 모듈
	protected int delete_ok(String idx) throws Exception{
		
		String sql = "delete from product where pidx=?";
		PreparedStatement ps = this.con.prepareStatement(sql);
		ps.setString(1, idx);
		int oksign = ps.executeUpdate();
		
		ps.close();
		this.con.close();
		return oksign;
	}
	
	
}
