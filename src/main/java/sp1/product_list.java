package sp1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class product_list {
	Connection conn = null;
	PreparedStatement ps = null;
	public product_list() {
		try {
			this.conn = dbconfig.info();
			
		}catch(Exception e){
			System.out.println(e);
		}
	}
	public List<ArrayList<String>> listdata(){
		List<ArrayList<String>> product = new ArrayList<ArrayList<String>>();
		try {
			String sql = "select * from product order by pidx desc";
			this.ps = this.conn.prepareStatement(sql);
			ResultSet rs = this.ps.executeQuery();
			dto_product dto = new dto_product(); 
			while(rs.next()) {
				dto.setPidx(rs.getString("pidx"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPname(rs.getString("pname"));
				dto.setPmoney(rs.getString("pmoney"));
				dto.setPimg(rs.getString("pimg"));
				dto.setPsale(rs.getString("psale"));
				dto.setPuse(rs.getString("puse"));
				product.add(dto.get_product());
			}
			
			this.conn.close();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		return product;
	}
}
