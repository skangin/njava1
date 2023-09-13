package sp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

//ArrayList : 중복허용 및 순서를 유지하는 배열형태 - 출력위주

//LinkedList : 순방향, 역순 검색(양방향) - 검색할 때 유리함
public class array_link1 {

	public static void main(String[] args) {
		//LinkedList : 중간 데이터를 사용할 경우, 중간 데이터를 삭제할 경우 유리
		Integer a[] = {1,3,6,9,10};
		LinkedList<Integer> list = new LinkedList<>(Arrays.asList(a));
		//System.out.println(list);
		list.add(3,55);
		//System.out.println(list);
		//ArrayList : 순차적으로 데이터 사용 및 삭제시 유리
		Integer b[] = {1,3,6,9,10};
		ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(a));
		//System.out.println(list2);
		list2.add(3,55);
		//System.out.println(list2);
		
		//반복데이터
		LinkedList<Integer> data1 = new LinkedList();
		ArrayList<Integer> data2 = new ArrayList();
		//시작시간
		long start = System.currentTimeMillis();
		Integer w = 0;
		//데이터 순차적 삽입
		do {
			data1.add(w);
			w++;
		}while(w <= 10000000);
		
		Integer ww = 0;
		int no = 2;
		//값을 2단위로 중간에 데이터를 삽입
		do {
			data1.add(no,ww);
			no+=2;
			ww++;
		}while(ww<=500);
		//작업 완료 시간
		long end = System.currentTimeMillis();
		long timer = end - start;//걸린시간
		System.out.println(timer);
	}
}
