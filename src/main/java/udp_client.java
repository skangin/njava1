import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udp_client {

	public static void main(String[] args) {
		client_udp cu = new client_udp();
		cu.cudp();

	}

}

class client_udp{
	private String ip = null;
	private int port = 0;
	private int myport = 0;
	public DatagramSocket ds = null; //udp socket
	public DatagramPacket dp = null;
	public InetAddress ia = null;
	public BufferedReader br = null;
	public String msg = null; //메시지
	public String mid = null; //메시지
	public String mpw = null; //메시지
	
	
	/*
	 udp는 포트가 서버포트 별도, 자신이 접속하는 포트 별도
	 중복 발생시 접속 차단
	 */
	
	public client_udp() {
		this.ip = "192.168.110.213";
		this.port = 7000;
		//this.myport = 7001;
		//랜덤을 이용한 다중 접속
		this.myport = (int)(Math.random()*1000+10000);
	}
	
	public void cudp() {
		try {
			//자신의 port에 대한 소캣을 오픈
			this.ia = InetAddress.getByName(this.ip); // 서버 ip를 가져옴
			
			this.ds = new DatagramSocket(this.myport); 
			System.out.println("아이디를 입력하세요 : ");
			this.br = new BufferedReader(new InputStreamReader(System.in));
			this.mid = this.br.readLine().intern();	
			System.out.println("패스워드를 입력하세요 : ");
			this.br = new BufferedReader(new InputStreamReader(System.in));
			this.mpw = this.br.readLine().intern();
			
			if(this.mid=="hong"&&this.mpw=="a1234") {
				while(true) {
					System.out.println("메시지를 입력해주세요:");
					this.br = new BufferedReader(new InputStreamReader(System.in));
					this.msg = this.br.readLine();
					byte by[] = this.msg.getBytes();
					// 서버로 해당 메시지를 패킷을 이용해서 전송
					//DatagramPacket(byte배열,배열크기,서버ip,서버포트)
					this.dp = new DatagramPacket(by,by.length,this.ia,this.port);
					this.ds.send(dp);// 전송구문			
					//System.out.println("메시지 전송 완료");
					
					//서버에서 전송된 메시지를 수신
					byte[] by2 = new byte[200];
					this.dp = new DatagramPacket(by2, by2.length);
					this.ds.receive(this.dp);
					System.out.println(new String(this.dp.getData()));
					
					
				}
			}
			else {
				System.out.println("아이디와 비밀번호를 확인해주세요");
				System.out.println("프로세스를 종료합니다.");
				System.exit(0);
			}
			
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}