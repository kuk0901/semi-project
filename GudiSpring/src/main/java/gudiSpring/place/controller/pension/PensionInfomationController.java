package gudiSpring.place.controller.pension;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import gudiSpring.place.dao.pension.PensionDao;
import gudiSpring.place.dto.pension.PensionDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/place/pension/infomation")
public class PensionInfomationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 펜션 정보 출력
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

			PensionDao pensionDao = new PensionDao();
			pensionDao.setConnection(conn);

			PensionDto pension = pensionDao.selectPensionInfomation(placeNo);

			req.setAttribute("pension", pension);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/place/pension/pensionInfomationView.jsp");
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
