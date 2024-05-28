package controller;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ContentDAO;

import kic.mskim.MskimRequestMapping;
import kic.mskim.RequestMapping;
import model.Content;
import model.KicMember;

@WebServlet("/content/*")
public class ContentController extends MskimRequestMapping {

	@RequestMapping("fortunePro")
	public String fortunePro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String name = request.getParameter("name");

		// Connection 객체
		ContentDAO dao = new ContentDAO();

		String msg = name + "님의 운세는 !!";
		String url = "index";

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return "/view/alert.jsp";
	} // end of loginPro()

	private static final long serialVersionUID = 1L;
	
	@RequestMapping("guessNumberPro")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// 게임을 시작할 때 새로운 숫자 생성
		Random random = new Random();
		int number = random.nextInt(100) + 1; // 1부터 100 사이의 숫자
		session.setAttribute("number", number);
		request.getRequestDispatcher("guessNumber.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int number = (int) session.getAttribute("number");
		int guess = Integer.parseInt(request.getParameter("guess"));

		String message = "";
		if (guess > number) {
			message = "더 작은 숫자입니다.";
		} else if (guess < number) {
			message = "더 큰 숫자입니다.";
		} else {
			message = "축하합니다! 숫자를 맞추셨습니다.";
			// 세션에 저장된 숫자 삭제
			session.removeAttribute("number");
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("guessNumber.jsp").forward(request, response);
	}
}
