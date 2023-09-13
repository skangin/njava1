//Thread 활용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class server3 {

	public static void main(String[] args) {
		server3 sv = new server3();
		sv.start();

	}
	//소켓을 오픈하는 역할(접속 환경)
	public void start() {
		//port는 하나이므로 접속자가 여러명일 때 가상 port를 이용하여 계속적으로 추가할 수있도록 하는 class
		ServerSocket serverSocket = null;
		Socket socket = null;
				
		try {
			serverSocket = new ServerSocket(8000);
			System.out.println("채팅 서버 오픈");
			while(true) {
				socket = serverSocket.accept();
				System.out.println(socket);
				//client 접속(client마다 새로운 스레드생성)
				chatroom chatroom = new chatroom(socket);
				chatroom.start();
			}
			
		}catch(Exception e) {
			System.out.println("Thread 오류 발생");
		}
		finally { //소켓 통신 오류발생 후 처리하는 방식
			if(serverSocket != null) {
				try {
					serverSocket.close();
					System.out.println("서버를 종료합니다.");
				}catch(Exception e) {
					System.out.println("서버 소켓이 정지하였음 강제종료합니다.");
					System.exit(0);
				}
			}
		}
	}
}

//접속자 현황을 셋팅해서 Thread로 관리
class chatroom extends Thread{
	InputStream is = null;
	OutputStream os = null;
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	//서버메모리에 사용자리스트를 저장하는 공간
	static List<PrintWriter> list = new ArrayList<PrintWriter>();
	
	//즉시실행
	public chatroom(Socket socket) {
		this.socket = socket;
		try {
			this.out = new PrintWriter(this.socket.getOutputStream());
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.list.add(out);
			
			System.out.println(this.socket.getInetAddress()); //접속자 ip주소
			System.out.println(this.socket.getKeepAlive()); //소켓이 유/무 메소드 true/false(상대 기준이기에 server 연 당사자 외엔 false)
			
			//System.out.println(this.socket.getLocalSocketAddress()); //서버 ip주소와 포트
			//System.out.println(this.socket.getLocalAddress()); //서버 ip주소
			
			
		}catch(Exception e) {
			System.out.println("소켓통신오류");
		}
	}
	
	//Thread 실행(.start())시 실행됨
	@Override
	public void run() {
		String name = "";
		try {
			name = this.in.readLine();
			System.out.println("["+name+"]");
			sendAll("["+name+"] welcome");
			while(this.in != null) {
				String inputmsg = this.in.readLine().intern();
				if(inputmsg=="exit") {
					break;
				}else {
					sendAll("["+name+"] :" + inputmsg);
				}
			}			
		}catch(Exception io) {
			System.out.println("["+name+" Error!]");
		}
		finally {
			sendAll("["+name+"] Chatting out!");
			this.list.remove(this.out);
			try {
				socket.close();
			}catch(Exception e) {
				System.out.println("채팅서버종료");
			}
		}
		System.out.println("["+name+"] EXIT!");
	}
	
	//사용자 정보가 있는 배열을 이용하여 메시지 출력
	private void sendAll(String s) {
		for(PrintWriter out: this.list) {
			out.println(s);
			out.flush();
		}
	}
	
	
}
