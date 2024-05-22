package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KicBoardDAO;
import dao.KicMemberDAO;
import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.KicBoard;
import model.KicMember;

@WebServlet("/board/*")
public class BoardController extends MskimRequestMapping{
	
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
		KicBoard kicboard = new KicBoard();
		kicboard.setName(request.getParameter("name"));
		kicboard.setPass(request.getParameter("pass"));
		kicboard.setSubject(request.getParameter("subject"));
		kicboard.setContent(request.getParameter("content"));
		kicboard.setFile1("");
		
		KicBoardDAO dao = new KicBoardDAO();
		int num = dao.insertBoard(kicboard);
		
		String msg = "게시물 등록 성공";
		String url = "boardList";
		
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
		List<KicBoard> li = dao.boardList();
		
		request.setAttribute("li", li);
		return "/view/board/boardList.jsp";
	} // end of boardList()
}
