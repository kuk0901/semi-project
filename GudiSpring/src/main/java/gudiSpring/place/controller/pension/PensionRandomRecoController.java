package gudiSpring.place.controller.pension;

import java.io.IOException;
import java.sql.Connection;

import gudiSpring.place.dao.cafe.CafeDao;
import gudiSpring.place.dao.pension.PensionDao;
import gudiSpring.place.dto.cafe.CafeDto;
import gudiSpring.place.dto.pension.PensionDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(value = "/area/place/recommendpension")
public class PensionRandomRecoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Connection conn = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");

            PensionDao pensionDao = new PensionDao();
            pensionDao.setConnection(conn);

            // 랜덤 카페 정보 가져오기
            PensionDto pension = pensionDao.getRandomPension();
            if (pension != null) {
                int placeNo = pension.getPlaceNo();

                // RECO_RESERVATION 증가시키기
                pensionDao.incrementRecoReservation(placeNo);
                        
                req.setAttribute("pension", pension);
            }

            // 카페 정보 페이지로 포워드
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/place/pension/pensionInfomationView.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            e.printStackTrace();            
        }
    }
}
