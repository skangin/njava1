import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client2 {

	public static void main(String[] args) {
		new client_chat().chat();

	}

}

class client_chat{
	private final String ip = "192.168.110.213";
	final int port = 8009; //서버포트
	Scanner sc = null; //메시지 입력
	Socket sk = null; // 소켓통신
	InputStream is = null; // 서버메시지를 받는 역할
	OutputStream os = null;//서버로 보내는 메시지
	String msg = null; //scanner로부터 입력 받은 메시지내용 
	String mid = null; // 접속자 id
	String smsg = null;//서버측 메시지를 담는 변수
	String check = null;//exit 활용하기위함
	
	public void chat() {
		try {
			this.sc = new Scanner(System.in);
			System.out.println("아이디를 생성해주세요: ");
			this.mid = this.sc.nextLine();
			int ck = 0;
			while(true) { // 접속유지
				this.sk = new Socket(this.ip,this.port);
				this.is = this.sk.getInputStream();
				this.os = this.sk.getOutputStream();
				if(ck==0) { //최초 접속시 출력
					this.msg = "["+this.mid+"] : 님 입장하엿습니다.";
				}
				else { // 사용자가 입력하는 메시지
					this.sc = new Scanner(System.in);
					System.out.println("메시지 내용을 입력해주세요 : ");
					this.check = this.sc.nextLine().intern();
					this.msg = "["+this.mid+"] :" + this.check;
				}
				if(this.check=="exit") {//메시지 exit 입력시 접속종료
					this.msg = "["+this.mid+"] : 님 퇴장하엿습니다.";
					this.os.write(this.msg.getBytes());
					this.os.flush();
					this.sc.close();
					this.os.close();
					this.is.close();
					this.sk.close();
				}else {
					this.os.write(this.msg.getBytes());//서버로 발송
					this.os.flush();//메모리를비워둠
				}
				//서버에서 발송한 메시지 출력
					byte data[] = new byte[1024*2];
					int n = this.is.read(data);
					this.smsg = new String(data,0,n);
					System.out.println(this.smsg);
					ck++;
					
				}
			
			
		}catch(Exception e) {
			System.out.println("서버 접속 장애 발생, 강제종료됩니다.");
		}
	}
}
