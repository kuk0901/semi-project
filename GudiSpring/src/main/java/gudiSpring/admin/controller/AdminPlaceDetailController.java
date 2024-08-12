package gudiSpring.admin.controller;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.place.dao.PlaceDao;
import gudiSpring.place.dto.PlaceDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/place/update") 
public class AdminPlaceDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Connection conn = null;
		
		try {
			
			int placeNo = Integer.parseInt(req.getParameter("placeNo"));
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);
			
			PlaceDto placeDto = placeDao.adminPlaceDetailSelect(placeNo);
			
			req.setAttribute("placeDto", placeDto);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/placeDetailView.jsp");
			dispatcher.forward(req, res);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		
		try {
			int placeNo = Integer.parseInt(req.getParameter("placeNo"));
			String category = req.getParameter("category");
			String placeName = req.getParameter("placeName");
			String plAddress = req.getParameter("plAddress");
			String plPhone = req.getParameter("plPhone");
			String phWebsite = req.getParameter("plWebsite");
			int genRerervation = Integer.parseInt(req.getParameter("genRerervation"));
			int recoRerervation = Integer.parseInt(req.getParameter("recoRerervation"));
			
			PlaceDto placeDto = new PlaceDto(placeNo, category, placeName, plAddress, plPhone, phWebsite, genRerervation, recoRerervation);
			
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);
			
			int result = placeDao.placeUpdate(placeDto);
			
			// 유효성 검사
			if (result == 0) {
				
				req.setAttribute("errorMsg", "장소 정보 수정에 실패했습니다.");
				doGet(req, res);
				
				return;
			}
			
			req.setAttribute("placeDto", placeDto);
			doGet(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
