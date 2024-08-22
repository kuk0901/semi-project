package gudiSpring.user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import gudiSpring.user.dao.UserDao;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(value = "/auth/signin")
public class UserSigninController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/auth/signinView.jsp");

		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;

		try {

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			UserDao userDao = new UserDao();
			userDao.setConnection(conn);

			String id = req.getParameter("id");
			String password = req.getParameter("password");

			UserDto userDto;

			userDto = userDao.userLogin(id, password);

			if(userDto == null) {
				req.setAttribute("msg", "일치하는 계정이 없습니다.");
				doGet(req, res);
				return; 
			}

			HttpSession session = req.getSession();
			session.setAttribute("userDto", userDto);

//			RequestDispatcher dispatcher = req.getRequestDispatcher("./index.jsp");
//			
//			dispatcher.forward(req, res);
			res.sendRedirect(req.getContextPath());

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
	         dispatcher.forward(req, res);
		}
	}

}
