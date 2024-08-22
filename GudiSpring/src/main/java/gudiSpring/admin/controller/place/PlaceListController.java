package gudiSpring.admin.controller.place;

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

@WebServlet(value = "/admin/place/list")
public class PlaceListController extends HttpServlet {

	private static final int PAGE_SIZE = 10;
	private static final int PAGE_GROUP_SIZE = 5;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			String formName = getFormName(req);
			int currentPage = getCurrentPage(req);
			String searchKeyword = req.getParameter("search");

			List<?> placeList = handlePlaceList(req, formName, currentPage);

			req.setAttribute("placeList", placeList);
			req.setAttribute("currentFormName", formName);
			if (searchKeyword != null && !searchKeyword.trim().isEmpty()) {
				req.setAttribute("searchKeyword", searchKeyword);
			}

			RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/place/placeListView.jsp");
			rd.forward(req, res);

		} catch (Exception e) {
			handleError(req, res, e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {

			if (req.getParameterValues("remove") == null) {
				doGet(req, res);
			} else {

				List<Integer> removePlaceNo = Arrays.stream(req.getParameterValues("remove")).map(Integer::parseInt)
				    .collect(Collectors.toCollection(ArrayList::new));

				ServletContext sc = this.getServletContext();

				Connection conn = (Connection) sc.getAttribute("conn");

				PlaceDao placeDao = new PlaceDao();
				placeDao.setConnection(conn);

				int result = placeDao.placeDelete((ArrayList<Integer>) removePlaceNo);

				if (result > 0) {
					req.setAttribute("msg", "장소를 모두 삭제했습니다.");
				} else if (result == 0) {
					req.setAttribute("msg", "장소를 삭제하지 못했습니다.");
				}

				doGet(req, res);
			}
		} catch (Exception e) {
			e.printStackTrace();

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");

			dispatcher.forward(req, res);
		}

	}

	private String getFormName(HttpServletRequest req) {
		String formName = req.getParameter("formName");
		if (formName == null) {
			formName = (String) req.getSession().getAttribute("currentFormName");
			if (formName == null) {
				formName = "selectAll";
			}
		}
		req.getSession().setAttribute("currentFormName", formName);
		return formName;
	}

	private int getCurrentPage(HttpServletRequest req) {
		String pageStr = req.getParameter("page");
		if (pageStr != null && !pageStr.isEmpty()) {
			return Math.max(1, Integer.parseInt(pageStr));
		}
		return 1;
	}

	private List<?> handlePlaceList(HttpServletRequest req, String formName, int currentPage) throws Exception {
		Connection conn = getConnection();
		int start = (currentPage - 1) * PAGE_SIZE;
		String searchKeyword = req.getParameter("search");

		if ("searchPlaceForm".equals(formName) && searchKeyword != null && !searchKeyword.trim().isEmpty()) {
			return handlePlaceSearch(req, conn, currentPage, start, searchKeyword);
		}

		switch (formName) {
		case "selectPension":
			return handlePensionList(req, conn, currentPage, start);
		case "selectRestaurant":
			return handleRestaurantList(req, conn, currentPage, start);
		case "selectCafe":
			return handleCafeList(req, conn, currentPage, start);
		default:
			return handleAllPlacesList(req, conn, currentPage, start);
		}
	}

	private List<PlaceDto> handleAllPlacesList(HttpServletRequest req, Connection conn, int currentPage, int start)
	    throws Exception {
		PlaceDao placeDao = new PlaceDao();
		placeDao.setConnection(conn);
		int totalPlaces = placeDao.placeTotalCount();
		List<PlaceDto> placeList = placeDao.selectPlaceList(start, PAGE_SIZE);
		setPaginationAttributes(req, currentPage, totalPlaces);
		return placeList;
	}

	private List<PensionDto> handlePensionList(HttpServletRequest req, Connection conn, int currentPage, int start)
	    throws Exception {
		PensionDao pensionDao = new PensionDao();
		pensionDao.setConnection(conn);
		int totalPlaces = pensionDao.pensionTotalCount();
		List<PensionDto> pensionList = pensionDao.selectPensionList(start, PAGE_SIZE);
		setPaginationAttributes(req, currentPage, totalPlaces);
		return pensionList;
	}

	private List<RestaurantDto> handleRestaurantList(HttpServletRequest req, Connection conn, int currentPage, int start)
	    throws Exception {
		RestaurantDao restaurantDao = new RestaurantDao();
		restaurantDao.setConnection(conn);
		int totalPlaces = restaurantDao.restaurantTotalCount();
		List<RestaurantDto> restaurantList = restaurantDao.selectRestaurantList(start, PAGE_SIZE);
		setPaginationAttributes(req, currentPage, totalPlaces);
		return restaurantList;
	}

	private List<CafeDto> handleCafeList(HttpServletRequest req, Connection conn, int currentPage, int start)
	    throws Exception {
		CafeDao cafeDao = new CafeDao();
		cafeDao.setConnection(conn);
		int totalPlaces = cafeDao.cafeTotalCount();
		List<CafeDto> cafeList = cafeDao.selectCafeList(start, PAGE_SIZE);
		setPaginationAttributes(req, currentPage, totalPlaces);
		return cafeList;
	}

	private List<PlaceDto> handlePlaceSearch(HttpServletRequest req, Connection conn, int currentPage, int start,
	    String placeName) throws Exception {
		PlaceDao placeDao = new PlaceDao();
		placeDao.setConnection(conn);
		int totalPlaces = placeDao.searchTotalCount(placeName);
		List<PlaceDto> placeList = placeDao.searchPlaceList(placeName, start, PAGE_SIZE);
		setPaginationAttributes(req, currentPage, totalPlaces);

		req.setAttribute("searchKeyword", placeName);

		return placeList;
	}

	private void setPaginationAttributes(HttpServletRequest req, int currentPage, int totalPlaces) {
		int totalPages = (int) Math.ceil((double) totalPlaces / PAGE_SIZE);
		int startPage = Math.max(1, currentPage - (PAGE_GROUP_SIZE / 2));
		int endPage = Math.min(startPage + PAGE_GROUP_SIZE - 1, totalPages);

		if (endPage - startPage + 1 < PAGE_GROUP_SIZE) {
			startPage = Math.max(1, endPage - PAGE_GROUP_SIZE + 1);
		}

		req.setAttribute("currentPage", currentPage);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageGroupSize", PAGE_GROUP_SIZE);
	}

	private Connection getConnection() {
		ServletContext sc = this.getServletContext();
		return (Connection) sc.getAttribute("conn");
	}

	private void handleError(HttpServletRequest req, HttpServletResponse res, Exception e)
	    throws ServletException, IOException {
		e.printStackTrace();
		req.getRequestDispatcher("/jsp/error/e500.jsp").forward(req, res);
	}

}
