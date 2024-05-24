package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.KicMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicMember;

@WebServlet("/member/*")
public class MemberController extends MskimRequestMapping{
	HttpSession session;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		System.out.println("service");
		super.service(request, response);
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav", "index");
		return "/view/index.jsp";
	} // end of index()
	
	@RequestMapping("join")
	public String join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav", "join");
		return "/view/member/join.jsp";
	} // end of join()
	
	@RequestMapping("joinPro")
	public String joinPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture");
		
		KicMemberDAO dao = new KicMemberDAO();
		KicMember kic = new KicMember(); // DTO bean
		kic.setId(id);
		kic.setPass(pass);
		kic.setName(name);
		kic.setGender(gender);
		kic.setTel(tel);
		kic.setEmail(email);
		kic.setPicture(picture);
		
		int num = dao.insertMember(kic);
		
		String msg = "";
		String url = "join";
		
		if(num > 0) {
			msg = name + "님의 회원가입이 완료되었습니다.";
			url = "login";
		} else {
			msg = "회원가입이 실패 하였습니다.";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of joinPro()
	
	@RequestMapping("joinInfo")
	public String joinInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = (String) session.getAttribute("id");
		KicMemberDAO dao = new KicMemberDAO();
		KicMember mem = dao.getMember(id);
		
		request.setAttribute("mem", mem);
		request.setAttribute("nav", "joininfo");
		return "/view/member/joinInfo.jsp";
	} // end of join()
	
	@RequestMapping("memberUpdateForm")
	public String memberUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = (String) session.getAttribute("id");
		KicMemberDAO dao = new KicMemberDAO();
		KicMember mem = dao.getMember(id);
		request.setAttribute("mem", mem);

		return "/view/member/memberUpdateForm.jsp";
	} // end of memberUpdateForm()
	
	@RequestMapping("memberUpdatePro")
	public String memberUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String id = (String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		int gender = Integer.parseInt(request.getParameter("gender"));
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture");
		
		KicMemberDAO dao = new KicMemberDAO();
		KicMember memdb = dao.getMember(id);
		
		KicMember kic = new KicMember(); // DTO bean
		kic.setId(id);
		kic.setPass(pass);
		kic.setName(name);
		kic.setGender(gender);
		kic.setTel(tel);
		kic.setEmail(email);
		kic.setPass(picture);
		
		String msg = "";
		String url = "memberUpdateForm";
		
		if (memdb != null) {
			if (memdb.getPass().equals(pass)) {
				msg = "수정 완료";
				dao.updateMember(kic);
				url = "joinInfo";
			} else {
				msg = "비밀번호가 틀렸습니다.";
				url = "memberUpdateForm";
			} // end of if (memdb.getPass().equals(pass))
		} else {
			msg = "수정할 수 없습니다.";
		} // end of if (memdb != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of memberUpdatePro()
	
	@RequestMapping("memberDeleteForm")
	public String memberDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/view/member/memberDeleteForm.jsp";
	} // end of memberDeleteForm()
	
	@RequestMapping("memberDeletePro")
	public String memberDeletePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		String id = (String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		
		KicMemberDAO dao = new KicMemberDAO();
		KicMember memdb = dao.getMember(id);
		
		String msg = "";
		String url = "memberDeleteForm";
		
		if (memdb != null) {
			if (memdb.getPass().equals(pass)) {
				msg = "탈퇴 완료";
				session.invalidate();
				dao.deleteMember(id); 	
				url = "index";
			} else {
				msg = "비밀번호가 틀렸습니다.";
			} // end of if (memdb.getPass().equals(pass))
		} else {
			msg = "탈퇴할 수 없습니다.";
		} // end of if (memdb != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of memberDeletePro()
	
	@RequestMapping("memberPassForm")
	public String memberPassForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/view/member/memberPassForm.jsp";
	} // end of memberPassForm()
	
	@RequestMapping("memberPassPro")
	public String memberPassPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = (String)session.getAttribute("id");
		String pass = request.getParameter("pass");
		String modPass = request.getParameter("modPass");
		
		KicMemberDAO dao = new KicMemberDAO();
		KicMember memdb = dao.getMember(id);
		
		String msg = "";
		String url = "memberPassForm";
		
		if (memdb != null) {
			if (memdb.getPass().equals(pass)) {
				msg = "수정 완료";
				session.invalidate();
				dao.modifyPass(id, modPass); 	
				url = "login";
			} else {
				msg = "비밀번호가 틀렸습니다.";
				url = "memberPassForm";
			} // end of if (memdb.getPass().equals(pass))
		} else {
			msg = "수정할 수 없습니다.";
		} // end of if (memdb != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of join()
	
	@RequestMapping("login")
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("nav", "login");
		return "/view/member/login.jsp";
	} // end of login()
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session.invalidate();
		request.setAttribute("msg", "로그아웃 되었습니다.");
		request.setAttribute("url", "index");
		return "/view/alert.jsp";
	} // end of logout()
	
	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		// Connection 객체
		KicMemberDAO dao = new KicMemberDAO();
		
		String msg = id + "님으로 로그인 하셨습니다.";
		String url = "index";
		
		KicMember mem = dao.getMember(id);
		if (mem != null) {
			if (pass.equals(mem.getPass())) {
				session.setAttribute("id", id);
			} else {
				msg = "비밀번호가 틀렸습니다";
				url = "login";
			} // end of if (pass.equals(mem.getPass())))
		} else {
			msg = "존재하지 않는 아이디입니다.";
			url = "login";
		} // end of if (mem != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	} // end of loginPro()
	
	@RequestMapping("memberList")
	public String memberList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		KicMemberDAO dao = new KicMemberDAO();
		List<KicMember> li = dao.memberList();
		
		request.setAttribute("li", li);
		return "/view/member/memberList.jsp";
	} // end of memberList()
	
	@RequestMapping("pictureimgForm")
	public String pictureimgForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/single/pictureimgForm.jsp";
	} // end of pictureimgForm()
	
	@RequestMapping("picturePro")
	public String picturePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("/")
				+"/img/member/picture/";
		System.out.println(path);
		
		String filename = null;
		
		MultipartRequest multi = 
				new MultipartRequest(request, path, 10*1024*1024, "UTF-8");
		
		filename = multi.getFilesystemName("picture");
		
		System.out.println(filename);
		request.setAttribute("filename", filename);
		
		return "/single/picturePro.jsp";
	} // end of picturePro()
}
