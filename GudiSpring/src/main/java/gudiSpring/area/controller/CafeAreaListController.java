package gudiSpring.area.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.area.dao.AreaDao;
import gudiSpring.area.dto.AreaDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/area/cafe")
public class CafeAreaListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			AreaDao areaDao = new AreaDao();
			areaDao.setConnection(conn);

			ArrayList<AreaDto> areaList = (ArrayList<AreaDto>) areaDao.selectList();
			req.setAttribute("areaList", areaList);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/area/cafeAreaListView.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
}
