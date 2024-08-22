package gudiSpring.admin.controller.admin;

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

@WebServlet(value = "/admin/list/detail")
public class AdminDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		try {
			ServletContext sc = this.getServletContext();
			
			int adminNo = Integer.parseInt(req.getParameter("adminNo"));
			
			conn = (Connection) sc.getAttribute("conn");

			AdminDao adminDao = new AdminDao();
			adminDao.setConnection(conn);

			AdminDto adminDto = adminDao.selectOneAdmin(adminNo);
			
			if (adminDto == null) {
				RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");

				dispatcher.forward(req, res);
				return;
			}
			
			req.setAttribute("adminDto", adminDto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/admin/adminDetailView.jsp");

			dispatcher.forward(req, res);
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
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");	
			
			String formName = req.getParameter("formName");
			
			switch (formName) {
			case "updateAdminForm": {
				
				int adminNo = Integer.parseInt(req.getParameter("adminNo"));
				String name = req.getParameter("name");
				String id = req.getParameter("id");
				String pwd = req.getParameter("pwd");
				String phone = req.getParameter("phone");
				
				AdminDao adminDao = new AdminDao();
				adminDao.setConnection(conn);
				
				int result = adminDao.updateAdmin(adminNo, name, id, pwd, phone);
				
				if (result > 0) {
					req.setAttribute("msg", "계정을 정보를 변경했습니다.");
				} else if (result == 0) {
					req.setAttribute("msg", "계정을 정보를 변경하지 못했습니다.");
				}

				doGet(req, res);
				break;
			}
			case "deleteAdminForm": {
				int adminNo = Integer.parseInt(req.getParameter("adminNo"));
				
				AdminDao adminDao = new AdminDao();
				adminDao.setConnection(conn);
				
				int result = adminDao.deleteAdmin(adminNo);
				
				// 변경 못한 경우
				if (result == 0) {
					
					req.setAttribute("msg", "계정을 삭제하지 못했습니다.");
					
					doGet(req, res);
					return;
				} 
				
				req.setAttribute("msg", "계정이 삭제되었습니다.");
				
				HttpSession session = req.getSession();
				session.invalidate();
				
				RequestDispatcher dispatcher = req.getRequestDispatcher(req.getContextPath() + "/admin");
				dispatcher.forward(req, res);
				
				break;
			}
			
			default:
				System.out.println();
				break;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");

			dispatcher.forward(req, res);
		}
	}

	

}
