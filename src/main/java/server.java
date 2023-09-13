import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class server {

	public static void main(String[] args) {
		int port = 8009; //TCP, UDP 포트번호
		ServerSocket ss = null; //TCP 서버통신(소켓사용)
		Socket sc = null;  // 소켓통신으로 접속허용
		Scanner sn = null; 
		try {
			ss = new ServerSocket(port); // 해당 포트를 오픈 
			
			System.out.println("채팅시작");
			while(true) {//반복문으로 접속 유지
				sc = ss.accept(); // 접속허용
				InputStream is = sc.getInputStream(); //값을 받아서 처리(client->server)
				OutputStream os = sc.getOutputStream();//값을 보내는 처리(server->client)
				
				//byte 형태로 받음
				byte[] data = new byte[1024];
				int n = is.read(data); //client 메시지를 읽어들임
				String message = new String(data,0,n); //바이트를 문자로 변환
				System.out.println(message); //해당 서버에 출력
				
							
				sn = new Scanner(System.in);
				System.out.println("관리자메시지 : "); //서버에서 클라이언트로 발송
				String msg ="관리자: " + sn.nextLine();
				os.write(msg.getBytes()); //바이트단위로 전송
				os.flush();//메모리를 비워줌
			}
			
		}catch(Exception e) {
			System.out.println("server error");
		}
	}

}
