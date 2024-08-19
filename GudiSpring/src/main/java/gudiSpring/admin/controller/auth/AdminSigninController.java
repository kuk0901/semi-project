package gudiSpring.admin.controller.auth;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.admin.dao.AdminDao;
import gudiSpring.admin.dto.AdminDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(value = "/admin")
public class AdminSigninController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/auth/signinView.jsp");
		
		rd.forward(req, res);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;
		
		try {
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			AdminDao adminDao = new AdminDao();
			adminDao.setConnection(conn);
			
			String id = req.getParameter("id");
			String pwd = req.getParameter("pwd");
			
			AdminDto adminDto = adminDao.adminExist(id, pwd);
						
			// 관리자 로그인 실패시 관련 UI를 화면에 띄움
			if (adminDto == null) {
				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/auth/signinView.jsp");
				
				req.setAttribute("error", "로그인에 실패했습니다. 다시 시도해주십시오.");
				
				rd.forward(req, res);
			
				return;
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("adminDto", adminDto);
			
			res.sendRedirect("./admin/main");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			
			dispatcher.forward(req, res);
		}
	}
}
