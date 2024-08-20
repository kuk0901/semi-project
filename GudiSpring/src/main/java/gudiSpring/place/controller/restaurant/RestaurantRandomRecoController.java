package gudiSpring.place.controller.restaurant;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.place.dao.restaurant.RestaurantDao;
import gudiSpring.place.dto.restaurant.RestaurantDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/area/place/recommendrestaurant")
public class RestaurantRandomRecoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection conn = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            RestaurantDao restaurantDao = new RestaurantDao();
            restaurantDao.setConnection(conn);

            // 랜덤 식당 정보 가져오기
            RestaurantDto restaurant = restaurantDao.getRandomRestaurant();
            if (restaurant != null) {
                int placeNo = restaurant.getPlaceNo();

                // RECO_RESERVATION 증가시키기
                restaurantDao.incrementRecoReservation(placeNo);
                        
                req.setAttribute("restaurant", restaurant);
            }

            // 카페 정보 페이지로 포워드
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/place/restaurant/restaurantInfomationView.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
}
