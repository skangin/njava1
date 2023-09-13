package sp1;

import java.util.ArrayList;
import java.util.List;

//DAO,DTO 형태의 구성
public class array_link4 {
 //DAO 파트
	public static void main(String[] args) {
		new box().datacall();
	}
}
class box{
	
	dto dt = new dto();//setter, getter
 	
	public void datacall() {
		List<ArrayList<String>> select = this.list();
		System.out.println(select);
	}
	
	//Database연결 및 데이터를 배열화 하는 메소드 필요
	public List<ArrayList<String>> list(){
		//return 받는 메소드 형태가 2차원 배열이므로 2차원형식으로 선언
		List<ArrayList<String>> al  = new ArrayList<ArrayList<String>>();
		
		
		String data[][] = new String[][] {
			{"hong","hong@nate.com"},
			{"lee","leesu@gmail.com"}
		};
		
		//반복문을 이용해서 setter에 값 입력
		int w =0;
		while(w<data.length) {
			this.dt.setUserid(data[w][0]);
			this.dt.setEmail(data[w][1]);
			//dto의 1차원 배열(as) 메소드를 호출하여 return 값을 받아서 2차원 배열(al)에 삽입
			al.add(this.dt.database());
			w++;			
		}
		return al; //2차원 배열 반환
	}
}