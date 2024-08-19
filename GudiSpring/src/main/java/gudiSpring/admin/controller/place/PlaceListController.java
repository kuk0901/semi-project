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

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {
			String formName = req.getParameter("formName");
			
			int currentPage = 1; // 기본값
      int pageSize = 10; // 한 페이지에 표시할 항목 수

      String pageStr = req.getParameter("page");
      if (pageStr != null && !pageStr.isEmpty()) {
          currentPage = Integer.parseInt(pageStr);
          
        if (currentPage < 1) {
          currentPage = 1; // 페이지가 1보다 작으면 1로 설정
        }
      }
			
			// FIXME: req.setAttribute()에서 사용하는 key는 일관되게 유지하는 것이 좋음
			// => JSP에서 결과 처리시 혼동이 줄어듦
			// category 별로 다르게 조회
			switch (formName) {
			case null, "selectAll", "removePlacesForm": {

				ArrayList<PlaceDto> placeList = (ArrayList<PlaceDto>) handlePlaceSelectForm(req, res, currentPage, pageSize);

				req.setAttribute("placeList", placeList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/place/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "selectPension": {
				ArrayList<PensionDto> pensionList = (ArrayList<PensionDto>) handlePensionSelectForm(req, res);

				req.setAttribute("placeList", pensionList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/place/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "selectRestaurant": {
				ArrayList<RestaurantDto> restaurantList = (ArrayList<RestaurantDto>) handleRestaurantSelectForm(req, res);

				req.setAttribute("placeList", restaurantList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/place/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "selectCafe": {
				ArrayList<CafeDto> cafeList = (ArrayList<CafeDto>) handleCafeSelectForm(req, res);

				req.setAttribute("placeList", cafeList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/place/placeListView.jsp");
				rd.forward(req, res);

				return;
			}

			case "searchPlaceForm": {

				ArrayList<PlaceDto> placeList = (ArrayList<PlaceDto>) handlePlaceSearchForm(req, res, currentPage, pageSize);

				req.setAttribute("placeList", placeList);

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/admin/place/placeListView.jsp");
				rd.forward(req, res);

				return;

			}

			default:
				System.out.println();

			}

		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			
			dispatcher.forward(req, res);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		try {

			if (req.getParameterValues("remove") == null) {
				doGet(req, res);
			} else {

				List<Integer> removePlaceNo = Arrays.stream(req.getParameterValues("remove"))
						.map(Integer::parseInt)
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

	// 하나의 페이지에서 각 다른 리스트를 보여주기 위해
	// dao 사용 부분을 컨트롤러의 메서드로 분리
	private List<PlaceDto> handlePlaceSelectForm(HttpServletRequest req, HttpServletResponse res, int currentPage, int pageSize) {
		
		ServletContext sc = this.getServletContext();
  	
		Connection conn = (Connection) sc.getAttribute("conn");

		PlaceDao placeDao = new PlaceDao();
		placeDao.setConnection(conn);

		// 총 장소 수를 가져옴
    int totalPlaces = placeDao.placeTotalCount();
    
    // 전체 페이지 수 계산
 		int totalPages = (int) Math.ceil((double) totalPlaces / pageSize);
    
 		// 시작 인덱스 계산
    int start = (currentPage - 1) * pageSize;
		
		ArrayList<PlaceDto> placeList = (ArrayList<PlaceDto>) placeDao.selectPlaceList(start, pageSize);

		int pageGroupSize = 5; // 페이지 그룹의 크기
		int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
		int startPage = (currentGroup - 1) * pageGroupSize + 1;
		int endPage = Math.min(currentGroup * pageGroupSize, totalPages);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageGroupSize", pageGroupSize);
		
		return placeList;
	}

	// dao 사용 method
	private List<PensionDto> handlePensionSelectForm(HttpServletRequest req, HttpServletResponse res) {
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		PensionDao pensionDao = new PensionDao();
		pensionDao.setConnection(conn);

		ArrayList<PensionDto> pensionList = (ArrayList<PensionDto>) pensionDao.selectPensionList();

		return pensionList;
	}

	private List<RestaurantDto> handleRestaurantSelectForm(HttpServletRequest req, HttpServletResponse res) {
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		RestaurantDao restaurantDao = new RestaurantDao();
		restaurantDao.setConnection(conn);

		ArrayList<RestaurantDto> restaurantList = (ArrayList<RestaurantDto>) restaurantDao.selectRestaurantList();

		return restaurantList;
	}

	private List<CafeDto> handleCafeSelectForm(HttpServletRequest req, HttpServletResponse res) {
		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		CafeDao cafeDao = new CafeDao();
		cafeDao.setConnection(conn);

		ArrayList<CafeDto> cafeList = (ArrayList<CafeDto>) cafeDao.selectCafeList();

		return cafeList;
	}

	// 장소 검색
	private List<PlaceDto> handlePlaceSearchForm(HttpServletRequest req, HttpServletResponse res, int currentPage, int pageSize) {

		String placeName = req.getParameter("search");

		ServletContext sc = this.getServletContext();
		Connection conn = (Connection) sc.getAttribute("conn");

		PlaceDao placeDao = new PlaceDao();
		placeDao.setConnection(conn);

		// 총 검색 결과 수
		int totalPlaces = placeDao.searchTotalCount(placeName);
		
		// 전체 페이지 수 계산
 		int totalPages = (int) Math.ceil((double) totalPlaces / pageSize);
	    
 		// 시작 인덱스 계산
    int start = (currentPage - 1) * pageSize;
    
    ArrayList<PlaceDto> placeList = (ArrayList<PlaceDto>) placeDao.searchPlaceList(placeName, start, pageSize);

    // 페이지 정보 설정
    int pageGroupSize = 5; // 페이지 그룹의 크기
		int currentGroup = (int) Math.ceil((double) currentPage / pageGroupSize);
		int startPage = (currentGroup - 1) * pageGroupSize + 1;
		int endPage = Math.min(currentGroup * pageGroupSize, totalPages);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageGroupSize", pageGroupSize);
    
		return placeList;
	}

}
