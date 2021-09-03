package com.phonebook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phonebook.dao.PhoneBookDAO;
import com.phonebook.dao.PhoneBookDAOImpl;
import com.phonebook.vo.PhoneBookVO;

@WebServlet("/")
public class PhoneBookServlet extends HttpServlet {

	@Override // 지원
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 폼으로 보내는거 get으로 처리
		String actionName = req.getParameter("a");

		if ("insertform-modal".equals(actionName)) {
			// a = insertform-modal -> 새 주소 추가 눌렀을 때
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/phonebook/insertform-modal.jsp");
			rd.forward(req, resp);
		} else {
			// DAO에서 목록을 받아서 jsp로 전달
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			List<PhoneBookVO> list = dao.getList();

			// 요청에 list를 추가
			// list 객체를 list 키로 추가
			req.setAttribute("list", list);
			// JSP로 요청을 전달(FORWARD)

			// Dispatcher 확보
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String actionName = req.getParameter("a");

		if ("insert".equals(actionName)) {
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");

			// VO 객체 생성
			PhoneBookVO vo = new PhoneBookVO();
			vo.setName(name);
			vo.setHp(hp);
			vo.setTel(tel);

			// INSERT 처리
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			boolean insertedCount = dao.insert(vo);

			// 처리 후 list 페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/");

		} else if ("delete".equals(actionName)) {

			long id = Integer.parseInt(req.getParameter("id"));
			// INSERT 처리
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			boolean deletedCount = dao.delete(id);

			// 처리 후 list 페이지로 리다이렉트
			resp.sendRedirect(req.getContextPath() + "/");
		} else if ("list".equals(actionName)) { // 목록 화면
			// DAO에서 목록을 받아서 jsp로 전달
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			List<PhoneBookVO> list = dao.getList();
			// 요청에 list를 추가
			// list 객체를 list 키로 추가
			req.setAttribute("list", list);
			// JSP로 요청을 전달(FORWARD)

			// Dispatcher 확보
			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, resp);
		} else if ("search".equals(actionName)) {
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			List<PhoneBookVO> list = dao.search(req.getParameter("keyword"));

			// 요청에 list를 추가
			// list 객체를 list 키로 추가
			req.setAttribute("list", list);
			// JSP로 요청을 전달(FORWARD)

			// Dispatcher 확보
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/phonebook/search.jsp");
//			RequestDispatcher rd = req.getRequestDispatcher("/");
			rd.forward(req, resp);
		} else {
			doGet(req, resp);
		}
	}

}