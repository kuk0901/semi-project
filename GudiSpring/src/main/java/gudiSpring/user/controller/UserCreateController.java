package gudiSpring.user.controller;

import java.io.IOException;
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

@WebServlet(value = "/auth/signup")
public class UserCreateController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;

		try {
//			
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			UserDao userDao = new UserDao();
			userDao.setConnection(conn);

//			---------------------------------
			String checkFrm = req.getParameter("checkFrm");
			String userName = req.getParameter("checkUserName");
			String nickname = req.getParameter("checkNickname");
			String id = req.getParameter("checkId");
			String userPassWord = req.getParameter("checkUserPassword");
			String userPhone = req.getParameter("checkUserPhone");

			UserDto userDto = new UserDto(userName, nickname, id, userPassWord, userPhone);

			switch (checkFrm) {

			case null: {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/auth/signupView.jsp");
				dispatcher.forward(req, res);
				return;
			}

			case "nickname": {

				int nickResult = userDao.checkNickname(nickname);

				req.setAttribute("userDto", userDto);
				req.setAttribute("nickResult", nickResult);

				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/auth/signupView.jsp");
				dispatcher.forward(req, res);

				return;
			}

			case "id": {

				int idResult = userDao.checkId(id);

				req.setAttribute("userDto", userDto);
				req.setAttribute("idResult", idResult);

				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/auth/signupView.jsp");
				dispatcher.forward(req, res);

				return;
			}
			
			case "myPageNickname": {

				int nickResult = userDao.checkNickname(nickname);

				req.setAttribute("userDto", userDto);
				req.setAttribute("nickResult", nickResult);

				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/user/myPageView.jsp");
				dispatcher.forward(req, res);

				return;
			}
			
			case "myPageId": {

				int idResult = userDao.checkId(id);

				req.setAttribute("userDto", userDto);
				req.setAttribute("idResult", idResult);

				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/user/myPageView.jsp");
				dispatcher.forward(req, res);

				return;
			}

			default:
				System.out.println();
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
	        dispatcher.forward(req, res);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;

		try {
			String nameStr = req.getParameter("userName");
			String nicknameStr = req.getParameter("nickname");
			String idStr = req.getParameter("id");
			String passwordStr = req.getParameter("password");
			String phoneStr = req.getParameter("phone");

			UserDto userDto = new UserDto();
			userDto.setName(nameStr);
			userDto.setNickname(nicknameStr);
			userDto.setId(idStr);
			userDto.setPassword(passwordStr);
			userDto.setPhone(phoneStr);

			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			UserDao userDao = new UserDao();
			userDao.setConnection(conn);

			int result;

			result = userDao.userInsert(userDto);

			if (result == 0) {
				req.setAttribute("msg", "회원가입에 실패했습니다.");
				
				doGet(req, res);
				
				return;
			} else if (result > 0) {

				req.setAttribute("msg", "회원가입에 성공했습니다.");
			
				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/auth/signinView.jsp");
				dispatcher.forward(req, res);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
