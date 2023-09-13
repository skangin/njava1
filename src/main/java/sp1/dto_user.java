package sp1;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dto_user {
	String mno,mid,mpass,memail,mtel,marea,minter,mage,mdate;
	
	public ArrayList<String> db_data(){
		
		ArrayList<String> lists = new ArrayList<String>();
		lists.add(getMno());    // 인덱스
		lists.add(getMid());	//아이디
		lists.add(getMemail()); // 이메일
		lists.add(getMtel());	//연락처
		lists.add(getMage());	//나이
		lists.add(getMdate());	//가입일자	
		
		return lists;
	}
}
