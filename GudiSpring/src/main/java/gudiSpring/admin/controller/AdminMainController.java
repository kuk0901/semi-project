package gudiSpring.admin.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gudiSpring.admin.dao.AdminDao;
import gudiSpring.user.dao.UserDao;
import gudiSpring.user.dto.UserDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/main")
public class AdminMainController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Connection conn = null;

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			UserDao userDao = new UserDao();

			userDao.setConnection(conn);

			ArrayList<UserDto> userList = (ArrayList) userDao.selectUserList();

			req.setAttribute("userList", userList);
			
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/mainView.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO: search form 부분 doGet으로 분리?
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {		
			
			String formName = req.getParameter("formName");
			
			switch (formName) {
				case "searchUsersForm": {
					ArrayList<UserDto> userList = (ArrayList<UserDto>) handleUserSearchForm(req, res);
					
					req.setAttribute("userList", userList);
					
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/mainView.jsp");
					rd.forward(req, res);
					 
					return;
				}
				
				case "removeUsersForm": {
					int result = handleUserRemoveForm(req, res);
					
					if (result > 0) {
						
						doGet(req, res);
						return;
					} else if (result == 0) {
						RequestDispatcher rd = req.getRequestDispatcher("/jsp/error/e500.jsp");
						
						rd.forward(req, res);
						return;
					}
					
				}
			
				default: System.out.println();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<UserDto> handleUserSearchForm(HttpServletRequest req, HttpServletResponse res) {
		
		ArrayList<UserDto> userList = null;
		
		String userName = req.getParameter("search");
		
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		UserDao userDao = new UserDao();
		userDao.setConnection(conn);

		userList = (ArrayList) userDao.searchUserList(userName);
		
		return userList;
		
	}
	
	private int handleUserRemoveForm(HttpServletRequest req, HttpServletResponse res) {
		int result = 0;
		
		List<Integer> removeUsersNo = Arrays.stream(req.getParameterValues("remove"))
				.map(Integer::parseInt) // 각 요소 형 변환
				.collect(Collectors.toCollection(ArrayList::new)); // 변환된 요소들을 새로운 ArrayList 만들어 수집
		
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		UserDao userDao = new UserDao();
		userDao.setConnection(conn);
		
		result = userDao.deleteUsers((ArrayList)removeUsersNo);
		
		return result;
	}
}
