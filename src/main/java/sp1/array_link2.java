package sp1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

//List : ArrayList, LinkedList, Vector, Stack
public class array_link2 {

	public static void main(String[] args) {
		String a[] = {"A","B","C"};
		List<String> z = Arrays.asList(a);
		//System.out.println(z);
		
		List<String> zz = new ArrayList<String>(Arrays.asList(a));
		zz.add("D");
		zz.add(4, "J"); //추가
		zz.set(4, "H"); // 해당 데이터 변경
		//System.out.println(zz);
		
		//Vector : 쓰레드를 활용하여 배열에 대한 안정성이 확보됨 but LinkedList보다 빠르고 ArrayList보다 느림
		List<String> zzz = new Vector<String>(Arrays.asList(a));
		zzz.add("10");
		zzz.set(0, "AAA");
		//System.out.println(zzz);

		//자료형이 없는 경우
		List k = new ArrayList<>(Arrays.asList(a));
		//k.add(110);
		//k.add("홍길동");
		//System.out.println(k);

		//저항력이 없는 경우(Object)
		ArrayList<?> al = new ArrayList<Object>(Arrays.asList(a));
		//System.out.println(al);
		
		new datalist().abc();
	}

}
class datalist{
	public void abc() {
		String member[][] = {
				{"홍길동","SKT","45"},
				{"이순신","LGT","55"},
				{"강감찬","KT","25"}
		};
		//String[], Integer[] : List 모든 배열을 2차 배열형태로 구성함
		ArrayList<String[]> al = new ArrayList<String[]>(Arrays.asList(member));
		//System.out.println(al);
		int w = 0;
		while(w<al.size()) {
			//System.out.println(al.get(w)[0]);
			w++;
		}
		ArrayList<Integer>[] ai = new ArrayList[20];		
		int ww = 0;
		while(ww<10) {
			ai[ww] = new ArrayList<Integer>();
			ww++;
		}
		//System.out.println(Arrays.toString(ai));
		ai[0].add(10);
		ai[1].add(20);
		ai[2].add(30);
		ai[3].add(40);
		
		//System.out.println(Arrays.toString(ai));
		//System.out.println(ai[0].get(0));
		
		//2차원 배열 ArrayList
		ArrayList<ArrayList<String>> k = new ArrayList<ArrayList<String>>();
		ArrayList<String> kk = new ArrayList<String>();
		kk.add("홍길동");
		kk.add("25");
		kk.add("hong@nate.com");
		k.add(kk);
		ArrayList<String> kk2 = new ArrayList<String>();
		kk2.add("이순신");
		kk2.add("36");
		kk2.add("lee@nate.com");
		k.add(kk2);
		System.out.println(k);
		//System.out.println(k.get(0).get(2));//2차원 배열중 그룹,index
		k.get(1).remove(2); 
		System.out.println(k);
		k.get(1).add("lee2@nate.com");
		System.out.println(k);
	}
}