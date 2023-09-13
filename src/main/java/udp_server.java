import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class udp_server {

	public static void main(String[] args) {
		server_chat sc = new server_chat();
		sc.udp();

	}

}

class server_chat{
	private String ip = null; //서버 ip
	private int port = 0; //서버 포트 
	public DatagramSocket ds = null; //udp socket
	public DatagramPacket dp = null;
	public InetAddress ia = null;	
	public InputStream is = null;
	public OutputStream os = null;
	public BufferedReader br = null;
	String msg = ""; //메시지
	
	public server_chat() {
		this.ip = "192.168.110.213";
		this.port = 7000;
		
	}
	
	public void udp() {
		try {
			this.ia = InetAddress.getByName(this.ip); //서버 ip를 확인
			System.out.println("udp server 오픈");
			this.ds = new DatagramSocket(this.port); //udp 포트 오픈
			
			while(true) {
				byte[] by = new byte[200]; //메시지 크기
				this.dp = new DatagramPacket(by, by.length); //client에서 오는 패킷 크기를 정함
				System.out.println("채팅시작");				
				this.ds.receive(this.dp); // client에서 보낸 메시지를 서버에서 받는 역할
				this.msg = new String(this.dp.getData());
				System.out.println(this.msg);
				
				
				//클라이언트로 메시지를 서버에서 전송하는 부분
				System.out.println("메시지를 입력하세요 : ");
				//클라이언트 ip udp port정보를 가져옴
				InetAddress ia2 = this.dp.getAddress();
				int port2 = this.dp.getPort();
				
				
				this.br = new BufferedReader(new InputStreamReader(System.in));
				this.msg = this.br.readLine();
				byte by2[] = this.msg.getBytes();
				
				this.dp = new DatagramPacket(by2,by2.length,ia2,port2);
				this.ds.send(this.dp);
			}
			
		}
		catch(Exception e) {
			System.out.println("upd 서버 오픈 오류");
			e.printStackTrace();
		}
	}
}