package gudiSpring.place.controller.cafe;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.place.dao.cafe.CafeDao;
import gudiSpring.place.dto.cafe.CafeDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/place/cafe/infomation")
public class CafeInfomationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 카페 정보 받아와 출력
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

			CafeDao cafeDao = new CafeDao();
			cafeDao.setConnection(conn);

			// 카페 정보를 조회합니다.
			CafeDto cafe = cafeDao.selectCafeInfomation(placeNo);

				

			req.setAttribute("cafe", cafe);

			// JSP 페이지로 포워딩
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/place/cafe/cafeInfomationView.jsp");
			dispatcher.forward(req, res);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
