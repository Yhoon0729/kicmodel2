package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import dao.KicBoardDAO;
import dao.KicMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicBoard;
import model.KicMember;

@WebServlet("/board/*")
public class BoardController extends MskimRequestMapping{
	
	HttpSession session;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		super.service(request, response);
	}
	
	@RequestMapping("index")
	public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/view/index.jsp";
	} // end of index()
	
	@RequestMapping("boardForm")
	public String boardForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		return "/view/board/boardForm.jsp";
	} // end of boardForm()
	
	@RequestMapping("boardPro")
	public String boardPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletContext().getRealPath("/")+"img/board/";
		
		MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8");
		
		String boardid = (String) session.getAttribute("boardid");
		KicBoard kicboard = new KicBoard();
		kicboard.setName(multi.getParameter("name"));
		kicboard.setPass(multi.getParameter("pass"));
		kicboard.setSubject(multi.getParameter("subject"));
		kicboard.setContent(multi.getParameter("content"));
		kicboard.setFile1(multi.getFilesystemName("file1"));
		kicboard.setBoardid(boardid);
		
		KicBoardDAO dao = new KicBoardDAO();
		int num = dao.insertBoard(kicboard);
		
		String msg = "게시물 등록 성공";
		String url = "boardList?boardid="+boardid;
		
		if(num==0) {
			msg = "게시물 등록 실패";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of boardPro()
	
	@RequestMapping("boardList")
	public String boardList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		KicBoardDAO dao = new KicBoardDAO();
		String boardid = request.getParameter("boardid");
		session.setAttribute("boardid", boardid);
		String boardName="";
		switch(boardid) {
			case "1" : boardName="공지사항";
			break;
			case "2" : boardName="자유게시판";
			break;
			case "3" : boardName="Q&A";
			break;
			default : boardName="";
		}
		
		int count = dao.boardCount(boardid);
		
		List<KicBoard> li = dao.boardList(boardid);
		
		request.setAttribute("boardName", boardName);
		request.setAttribute("li", li);
		request.setAttribute("boardid", boardid);
		request.setAttribute("nav", boardid);
		request.setAttribute("count", count);
		return "/view/board/boardList.jsp";
	} // end of boardList()
	
	@RequestMapping("boardInfo")
	public String boardInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println(num);
		KicBoardDAO dao = new KicBoardDAO();
		int count = dao.addReadCount(num);
		KicBoard board = dao.getBoard(num);
		
		request.setAttribute("board", board);
		return "/view/board/boardInfo.jsp";
	} // end of boardInfo()
	
	@RequestMapping("boardUpdateForm")
	public String boardUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		KicBoardDAO dao = new KicBoardDAO();
		KicBoard board = dao.getBoard(num);
		
		request.setAttribute("board", board);
		return "/view/board/boardUpdateForm.jsp";
	} // end of boardUpdateForm()
	
	@RequestMapping("boardUpdatePro")
	public String boardUpdatePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletContext().getRealPath("/")+"img/board/";
		
		MultipartRequest multi = new MultipartRequest(request, path, 10*1024*1024, "UTF-8");

		int num = Integer.parseInt(multi.getParameter("num"));
		String pass = multi.getParameter("pass");
		
		KicBoard board = new KicBoard(); // DTO bean
		board.setName(multi.getParameter("name"));
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		board.setNum(num);
		
		if(multi.getFilesystemName("file1")==null) {
			board.setFile1(multi.getParameter("originfile"));
		} else {
			board.setFile1(multi.getFilesystemName("file1"));
		}
		
		KicBoardDAO dao = new KicBoardDAO();
		KicBoard boarddb = dao.getBoard(num);
		
		String msg = "";
		String url = "boardUpdateForm?num="+num;
		
		if (boarddb != null) {
			if (boarddb.getPass().equals(pass)) {
				msg = "수정 완료";
				dao.updateBoard(board);
				url = "boardInfo?num="+num;
			} else {
				msg = "비밀번호가 틀렸습니다.";
				url = "boardUpdateForm?num="+num;
			} // end of if (boarddb.getPass().equals(pass))
		} else {
			msg = "수정할 수 없습니다.";
		} // end of if (boarddb != null)
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of boardUpdatePro()
	
	@RequestMapping("boardDeleteForm")
	public String boardDeleteForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		KicBoardDAO dao = new KicBoardDAO();
		KicBoard board = new KicBoard();
		
		
		request.setAttribute("num", request.getParameter("num"));
		request.setAttribute("board", board);
		return "/view/board/boardDeleteForm.jsp";
	} // end of boardDeleteForm()
	
	@RequestMapping("boardDeletePro")
	public String boardDeletePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pass = request.getParameter("pass");
		System.out.println(num + ","+pass);
		String boardid = (String) session.getAttribute("boardid");
		
		KicBoardDAO dao = new KicBoardDAO();
		KicBoard boarddb = dao.getBoard(num);
		System.out.println("===================="+boarddb);
		
		String msg = "";
		String url = "boardDeleteForm?num="+num;
		
		if (boarddb != null) {
			System.out.println("ok");
			if (pass.equals(boarddb.getPass())) {
				
				int count = dao.deleteBoard(num);
				if(count==1) {
					msg = "게시물 삭제 완료";
					url="boardList?boardid="+boardid;
				} 
			} else {
				msg = "비밀번호 확인하시오";
			} // end of if (pass.equals(boarddb.getPass()))
		} else {
			msg = "게시물이 없습니다.";
		} // end of if (boarddb != null)

		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		return "/view/alert.jsp";
	} // end of boardDeletePro()
	
}
