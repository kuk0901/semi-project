package gudiSpring.place.controller.restaurant;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.place.dao.restaurant.RestaurantDao;
import gudiSpring.place.dto.restaurant.RestaurantDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/place/restaurant/infomation")
public class RestaurantInfomationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 식당 정보 출력
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection conn = null;

		try {
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");

			String placeNoParam = req.getParameter("placeNo");
			int placeNo = -1;

			if (placeNoParam != null && !placeNoParam.isEmpty()) {
				try {
					placeNo = Integer.parseInt(placeNoParam);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}

			RestaurantDao restaurantDao = new RestaurantDao();
			restaurantDao.setConnection(conn);

			RestaurantDto restaurant = restaurantDao.selectRestaurantInfomation(placeNo);
			
			req.setAttribute("restaurant", restaurant);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/place/restaurant/restaurantInfomationView.jsp");
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
