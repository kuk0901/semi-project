package gudiSpring.user.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.board.dto.freeboard.BoardDto;
import gudiSpring.user.dao.UserDao;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/user/detail")
public class UserUpdatetController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;

		try {
			int userNo = Integer.parseInt(req.getParameter("userNo"));

			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			UserDao userDao = new UserDao();
			BoardDao boardDao = new BoardDao();
			userDao.setConnection(conn);
			boardDao.setConnection(conn);

			UserDto userDto = userDao.userSelect(userNo);
			List<BoardDto> myPageBoardList = boardDao.selectMapageContentList(userNo);

			req.setAttribute("userDto", userDto);
			req.setAttribute("myPageBoardList", myPageBoardList);

			RequestDispatcher rd = req.getRequestDispatcher("/jsp/user/myPageView.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
	         dispatcher.forward(req, res);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;

		UserDto userDto = null;

		try {
			ServletContext sc = this.getServletContext();

			int userNo = Integer.parseInt(req.getParameter("userNo"));
			String nameStr = req.getParameter("userName");
			String nicknameStr = req.getParameter("nickname");
			String idStr = req.getParameter("id");
			String phoneStr = req.getParameter("phone");

			userDto = new UserDto();

			userDto.setUserNo(userNo);
			userDto.setName(nameStr);
			userDto.setNickname(nicknameStr);
			userDto.setId(idStr);
			userDto.setPhone(phoneStr);

			conn = (Connection) sc.getAttribute("conn");

			UserDao userDao = new UserDao();
			userDao.setConnection(conn);

			int result = userDao.userUpdate(userDto);

			if (result == 0) {
				req.setAttribute("msg", "회원 정보를 수정하지 못했습니다.");
				
				doGet(req, res);
				return;
			}

			req.setAttribute("msg", "회원 정보를 수정했습니다.");
			
			RequestDispatcher dispathcer = req.getRequestDispatcher("/jsp/user/myPageView.jsp");

			dispathcer.forward(req, res);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e);
			RequestDispatcher dispatcher = req.getRequestDispatcher("./Error.jsp");

			dispatcher.forward(req, res);
		}

	}

}
