package gudiSpring.place.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;

import gudiSpring.place.dao.PlaceDao;
import gudiSpring.place.dto.PlaceDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/main")
public class RandomPlaceController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// 카페 리스트 출력
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<PlaceDto> randomPlaceList = null;
		
		Connection conn = null;

		try {
			ServletContext sc = this.getServletContext();


			conn = (Connection) sc.getAttribute("conn");				
			

			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);


			randomPlaceList = getRandomPlaceList();

			ArrayList<PlaceDto> randomPlaceList = (ArrayList<PlaceDto>) placeDao.getRandomPlace();


			
			req.setAttribute("randomPlaceList", randomPlaceList);

			RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
			dispatcher.forward(req, res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ArrayList<PlaceDto> getRandomPlaceList() {
		ArrayList<PlaceDto> placeList = null;
		int[] randomPlaceNos = new int[5];
		Random random = new Random(System.currentTimeMillis());
		
		Connection conn = null;

		try {
			
			ServletContext sc = this.getServletContext();

			conn = (Connection) sc.getAttribute("conn");
			
			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);
			
			for (int i = 0; i < 5; i++) {
				randomPlaceNos[i] = random.nextInt(placeDao.getLastPlaceNo()) + 1;
				
				// 중복 검사
				for (int j = 0; j < i; j++) {
			        if (randomPlaceNos[i] == randomPlaceNos[j]) {
			          i--;
			        }
			    }
			}
			
			placeList = (ArrayList<PlaceDto>) placeDao.getRandomPlace(randomPlaceNos);			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return placeList;
	}
}
