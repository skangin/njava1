package sp1;

import java.util.ArrayList;
import java.util.Scanner;

//2차원 배열에 대한 응용편
/*
 데이터 : 김경민,배유미,김승균,이철의,장진호,홍사라,박병준,전정호,이경선,최현제,서강인,염무원
 결과
 [[A조,김경민,배유미,김승균][B조,이철의,장진호,홍사라]...]
 
 
 
 */
public class array_link3 {
	
	public static void main(String[] args) {
		//코드 및 결과 출력
		ArrayList<ArrayList<String>> group = new ArrayList<ArrayList<String>>();
		
		Scanner sc = new Scanner(System.in);
		String temps = "";
		for(int i =1;i>0;i++) {
			System.out.println(i+"번째 조 입력을 시작하시려면 아무키나 입력해주세요. 입력을 마치시려면 종료를 입력해주세요");
			temps = sc.nextLine().intern();
			if(temps=="종료") {
				System.out.println("조 구성원 출력");
				System.out.println(group);
				break;
			}
			else {				
				ArrayList<String> temp = new ArrayList<String>();
				for(int j = 1; j>0;j++) {	
					System.out.println(i+"번째 조 "+j+ "번째 원소를 입력해주세요. 배열을 끝내시려면 종료라고 입력해주세요");
					temps = sc.nextLine().intern();
					if(temps=="종료") {						
						group.add(temp);
						break;
					}
					else
						temp.add(temps);
				}			
			}
		}
		
	}

}
