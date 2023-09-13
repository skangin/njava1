package sp1;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dto_product {
	String pidx,pcode,pname,pmoney,pimg,psale,puse;
	
	public ArrayList<String> get_product(){
		
		ArrayList<String> products = new ArrayList<String>();
		products.add(getPidx()); //auto increment
		products.add(getPcode());//상품코드
		products.add(getPname());//상품이름
		products.add(getPmoney());//가격
		products.add(getPimg());//사진경로
		products.add(getPsale());//할인율
		products.add(getPuse());//판매여부
		return products;
	}
}
