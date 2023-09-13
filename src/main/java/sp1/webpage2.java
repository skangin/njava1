package sp1;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class webpage2 {
	PrintWriter pw = null;
	
	@PostMapping("fileok.do")
	public void upload(MultipartFile mfile, HttpServletRequest req, Model m)throws Exception{
		String filename = mfile.getOriginalFilename();
		long filesize = mfile.getSize();
		String url = req.getServletContext().getRealPath("/fileup/") + filename;
		//파일저장
		File f = new File(url);
		FileCopyUtils.copy(mfile.getBytes(), f);
		System.out.println("업로드 파일 정상적으로 진행 완료!");
		
	}
	//수정완료
	@PostMapping("/product_modifyok.do")
	public String ok_modify(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		String pidx = req.getParameter("pidx");
		String pcode = req.getParameter("pcode");
		String pname = req.getParameter("pname");
		String pmoney = req.getParameter("pmoney");
		String psale = req.getParameter("psale");
		String puse = req.getParameter("puse");
		
		product_ok ok = new product_ok();
		String msg ="";
		int result = ok.modify_sql(pidx, pcode, pname, pmoney, psale, puse);
		if(result == 1) {
			msg="<script>alert('정상적으로 수정 완료 되었습니다.');"
					+"location.href='./product_list.do';"
					+"</script>";
		}else {
			msg="<script>alert('수정 내용이 올바르지 않습니다.');"
					+"history.go(-1);"
					+"</script>";
		}
		try {
		 this.pw = res.getWriter();
		 this.pw.write(msg);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	//상품수정 파트(JSTL)
	@GetMapping("/product_modify.do")
	public String view_product(HttpServletRequest req, Model m) {
		String idx = req.getParameter("idx");
		try {
			product_modify pm = new product_modify();
			ArrayList<String> data = pm.view_ok(idx);
			m.addAttribute("data",data);
		}catch(Exception e) {
			System.out.println("접근오류");
		}
		
		return "/WEB-INF/jsp/product_modify";
	}
	
	//상품 삭제 파트
	@GetMapping("/product_delete.do")
	public void del_product(HttpServletRequest req, HttpServletResponse res) {
		res.setContentType("text/html;charset=utf-8");
		try {
			this.pw = res.getWriter();
			String no = req.getParameter("idx");
			product_delete pd = new product_delete();
			int result = pd.delete_ok(no);
			if(result == 1) {//정상적 sql 작동
				this.pw.write("<script>"
						+ "alert('정상적으로 삭제되었습니다.');"
						+ "location.href='./product_list.do';"
						+ "</script>");
			}else {// sql 문법이 제대로 작동x
				this.pw.write("<script>"
						+ "alert('올바른 데이터 값이 아닙니다.');"
						+ "location.href='./product_list.do';"
						+ "</script>");
			}
			
			
		}catch(Exception e) {
			this.pw.write("<script>"
					+ "alert('잘못된 접근 방식입니다.');"
					+ "history.go(-1);"
					+ "</script>");
			

		}
	}
	
	
	//JSTL로 뷰페이지 출력 파트
	@RequestMapping("/product_list.do")
	public String pd_list(HttpServletRequest req, Model m) {
		
		List<ArrayList<String>> product_data = null;
		try {
				
				product_list pl = new product_list();
				product_data = pl.listdata();
			
			//JSP 형태로 View 출력
			//회원가입 카운팅 전체 숫자값
			
			req.setAttribute("product_data", product_data);
			
		} 
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		return "/WEB-INF/jsp/product_list";
	}
	
	
	//spring1.html에서 넘어온 값을 view를 통해서 핸들링 하는 형태
	@RequestMapping("/spring1ok.do")
	public String product(HttpServletRequest req, HttpServletResponse res, Model m){
		String pdcode = ""; 
		String pdname = "";
		try {
			pdcode = req.getParameter("pdcode").intern();
			pdname = req.getParameter("pdname").intern();
			m.addAttribute("name",pdname);
			m.addAttribute("code",pdcode);
		}		
		catch(Exception e) {
			System.out.println(e);
		}
		
		return "/WEB-INF/jsp/spring1ok";
	}
	
	@PostMapping("/spring2ok.do")
	public String agree(HttpServletRequest req, Model m) {
		String ag = req.getParameter("mail");
		//checkbox 사용시 intern()사용하지 않음(checkbox의 값으론 null이넘어오지만 radio value 값엔 null이 없어서일까?)
		String ad = req.getParameter("ad").intern();
		System.out.println(ad);
		if(ad=="N") {
			System.out.println("동의안함");
		}else {
			System.out.println("동의함");
		}
		
		if(ag==null) {
			ag = "N";
		}
		
		System.out.println(ag);
		
		return null;		
	}
	//Getter와 Setter를 이용해서 값을 로드
	@PostMapping("/spring3ok.do")
	public String user(HttpServletRequest req, Model m) {
		String mid = req.getParameter("mid");
		String mname = req.getParameter("mname");
		
		userdata ud = new userdata(mid, mname);
		System.out.println(ud.getMid());
		System.out.println(ud.getMname());
		
		return null;
	}
	
	@PostMapping("/spring4ok.do")
	public String mails(HttpServletRequest req,Model m) {
		String mname = req.getParameter("mname");//보낸이
		String mail = req.getParameter("mail"); //회신받을 메일
		String mtitle = req.getParameter("mtitle");//제목
		String mcontent = req.getParameter("mcontent");//내용
		//실제 메일 api 서버 정보를 입력
		String host = "smtp.naver.com";
		String user = "fluctuating5493@naver.com";
		String password = "sk85712564!";
		String to_mail = "fluctuating5493@naver.com";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory", 587);
		props.put("mail.smtp.ssl.protocols","TLSv1.2");
		
		//SMTP서버에 로그인을 시키기 위한 작업(암호화)
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getpassPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		try {
			//MimeMessage : okcall 발생함 아이디/패스워드, 포트모두 맞을경우
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(to_mail,mname)); //보낸이
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail)); //받는 주소
			msg.setSubject(mtitle); //제목
			msg.setText(mcontent); //내용
			Transport.send(msg);
		}
		catch(Exception e) {
			System.out.println("메일 서버 통신 오류");
		}
		
		return null;		
	}
	
	//Controller에서 배열을 JSP(view)로 출력하는 형태
	@GetMapping("/spring5ok.do")
	public String datalist(HttpServletRequest req, Model m) {
		dbconfig db = new dbconfig();
		try {
			Connection ct = db.info();
			System.out.println(ct.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
		
		String data[] = {"이순신","홍길동","강감찬","이산","한석봉"};
		ArrayList<String> al = new ArrayList<String>(Arrays.asList(data));

		//jsp방식 (제일 구식)
		//req.setAttribute("person_list", al);
		//return "/WEB-INF/jsp/spring5ok"; //일반JSP View
		
		//표현식 jsp 방식 (그다음 구식)
		m.addAttribute("person_list",al); //표현식방식
		m.addAttribute("person_delete","10");
		
		//표현식 값을 javascript 전달(Front-end) Node 형태로 출력
		
		return "/WEB-INF/jsp/spring5_2ok"; //표현식JSP View
	}
	
	@RequestMapping("/spring6ok.do")
	public String userlist(HttpServletRequest req, Model m) {
		//파라미터값으로 검색어가 적용되는 경우
		String search = req.getParameter("search");
		String part = req.getParameter("part");
		
		List<ArrayList<String>> member_data = null;
		try {
			//검색이 없을경우
			if(search==""||search ==null||search=="null") {
				user_list ul = new user_list();
				member_data = ul.listdata();
			}
			else {//검색 단어가 있을 경우
				user_list ul = new user_list();
				member_data = ul.listdata(search,part);
			}
			//JSP 형태로 View 출력
			//회원가입 카운팅 전체 숫자값
			req.setAttribute("total", new user_list().total_member());
			req.setAttribute("member_data", member_data);
			req.setAttribute("part", part);
		} 
		catch(Exception e) {
			System.out.println(e);
		}
		
		return "/WEB-INF/jsp/member_list";
	}
	

}
