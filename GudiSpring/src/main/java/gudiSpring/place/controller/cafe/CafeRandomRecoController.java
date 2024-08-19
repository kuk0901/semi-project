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

@WebServlet(value = "/area/place/recommendcafe")
public class CafeRandomRecoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection conn = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            CafeDao cafeDao = new CafeDao();
            cafeDao.setConnection(conn);

            // 랜덤 카페 정보 가져오기
            CafeDto cafe = cafeDao.getRandomCafe();
            if (cafe != null) {
                int placeNo = cafe.getPlaceNo();

                // RECO_RESERVATION 증가시키기
                cafeDao.incrementRecoReservation(placeNo);
                        
                req.setAttribute("cafe", cafe);
            }

            // 카페 정보 페이지로 포워드
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/place/cafe/cafeInfomationView.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
}
