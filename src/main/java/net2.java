//네트워크 기초 외부 이미지 및 동영상 크롤링

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class net2 {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		System.out.println("웹에서 가져올 이미지 주소를 입력하세요;");
		String url = sc.nextLine();
		
		URL u = new URL(url); // URL 클래스 : 네트워크 경로를 말함
		URLConnection con = u.openConnection(); // 해당 경로를 연결시켜줌
		
		//indexOf 기능과 비슷함 (-1일 경우 못 가져옴)
		int imgsize = con.getContentLength();
		String imgtype = con.getContentType(); // 파일 타입을 확인
		long date = con.getDate(); // 파일 업로드 날짜
		//날짜 변환
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String day = sdf.format(date);
		//해당 데이터를 Stream으로 가져옴
		InputStream is = u.openStream();
		BufferedInputStream bi = new BufferedInputStream(is);
		//파일로 저장
		byte[] data = new byte[bi.available()];
		FileOutputStream fo = new FileOutputStream("nc.mp4");
		int imgdata = 0;
		while((imgdata=bi.read(data))!=-1) {
			fo.write(data,0,imgdata);
		}
		fo.flush();
		fo.close();
		bi.close();
		is.close();
		System.out.println("해당 정보를 다운로드 완료하였습니다.");
	}

}
