package gudiSpring.admin.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import gudiSpring.place.dao.PlaceDao;
import gudiSpring.place.dao.cafe.CafeDao;
import gudiSpring.place.dao.pension.PensionDao;
import gudiSpring.place.dao.restaurant.RestaurantDao;
import gudiSpring.place.dto.PlaceDto;
import gudiSpring.place.dto.cafe.CafeDto;
import gudiSpring.place.dto.pension.PensionDto;
import gudiSpring.place.dto.restaurant.RestaurantDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/admin/place")
public class AdminPlaceListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {

			String formName = req.getParameter("formName");

			
			// FIXME: req.setAttribute()에서 사용하는 key는 일관되게 유지하는 것이 좋음
			// => JSP에서 결과 처리시 혼동이 줄어듦
			// category 별로 다르게 조회
			switch (formName) {
			case null, "selectAll", "removePlacesForm": {

				ArrayList<PlaceDto> placeList = (ArrayList) handlePlaceSelectForm(req, res);

				req.setAttribute("placeList", placeList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "selectPension": {
				ArrayList<PensionDto> pensionList = (ArrayList) handlePensionSelectForm(req, res);

				req.setAttribute("placeList", pensionList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "selectRestaurant": {
				ArrayList<RestaurantDto> restaurantList = (ArrayList) handleRestaurantSelectForm(req, res);

				req.setAttribute("placeList", restaurantList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "selectCafe": {
				ArrayList<CafeDto> cafeList = (ArrayList) handleCafeSelectForm(req, res);

				req.setAttribute("placeList", cafeList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "searchPlaceForm": {

				ArrayList<PlaceDto> placeList = (ArrayList) handlePlaceSearchForm(req, res);

				req.setAttribute("placeList", placeList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/placeListView.jsp");
				rd.forward(req, res);

				return;

			}

			default:
				System.out.println();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {

			if (req.getParameterValues("remove") == null) {

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/placeListView.jsp");
				rd.forward(req, res);
				
			} else {

				List<Integer> removePlaceNo = Arrays.stream(req.getParameterValues("remove")).map(Integer::parseInt)
				    .collect(Collectors.toCollection(ArrayList::new));

				ServletContext sc = this.getServletContext();

				Connection conn = (Connection) sc.getAttribute("conn");

				PlaceDao placeDao = new PlaceDao();
				placeDao.setConnection(conn);

				int result = placeDao.placeDelete((ArrayList) removePlaceNo);

				if (result > 0) {

					doGet(req, res);
					return;
				} else if (result == 0) {
					RequestDispatcher rd = req.getRequestDispatcher("/jsp/error/e500.jsp");

					rd.forward(req, res);
					return;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 하나의 페이지에서 각 다른 리스트를 보여주기 위해
	// dao 사용 부분을 컨트롤러의 메서드로 분리
	private List<PlaceDto> handlePlaceSelectForm(HttpServletRequest req, HttpServletResponse res) {
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		PlaceDao placeDao = new PlaceDao();
		placeDao.setConnection(conn);

		ArrayList<PlaceDto> placeList = (ArrayList) placeDao.selectPlaceList();

		return placeList;
	}

	// dao 사용 method
	private List<PensionDto> handlePensionSelectForm(HttpServletRequest req, HttpServletResponse res) {
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		PensionDao pensionDao = new PensionDao();
		pensionDao.setConnection(conn);

		ArrayList<PensionDto> pensionList = (ArrayList) pensionDao.selectPensionList();

		return pensionList;
	}

	private List<RestaurantDto> handleRestaurantSelectForm(HttpServletRequest req, HttpServletResponse res) {
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		RestaurantDao restaurantDao = new RestaurantDao();
		restaurantDao.setConnection(conn);

		ArrayList<RestaurantDto> restaurantList = (ArrayList) restaurantDao.selectRestaurantList();

		return restaurantList;
	}

	private List<CafeDto> handleCafeSelectForm(HttpServletRequest req, HttpServletResponse res) {
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		CafeDao cafeDao = new CafeDao();
		cafeDao.setConnection(conn);

		ArrayList<CafeDto> cafeList = (ArrayList) cafeDao.selectCafeList();

		return cafeList;
	}

	// 장소 검색
	private List<PlaceDto> handlePlaceSearchForm(HttpServletRequest req, HttpServletResponse res) {

		PlaceDto placeDto = null;

		String placeName = req.getParameter("search");

		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		PlaceDao placeDao = new PlaceDao();
		placeDao.setConnection(conn);

		ArrayList<PlaceDto> placeList = (ArrayList) placeDao.searchPlaceList(placeName);

		return placeList;
	}

}
