package gudiSpring.board.controller.reviewboard;




import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import gudiSpring.board.dao.reviewboard.ReviewBoardDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/reviewboard/deleteFile")
public class ReviewDeleteFileController extends HttpServlet {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1868710412261301732L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
		
	}
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/plain; charset=UTF-8");

        int contentNo = Integer.parseInt(req.getParameter("contentNo"));
        String fileName = req.getParameter("fileName");

        Connection conn = null;

        try {
            ServletContext sc = this.getServletContext();
            conn = (Connection) sc.getAttribute("conn");
            if (conn == null) {
                throw new Exception("DB 연결 실패: Connection 객체가 null입니다.");
            }
            ReviewBoardDao boardDao = new ReviewBoardDao();
            boardDao.setConnection(conn);

            // 파일 경로 가져오기 및 삭제
            String basePath = "D:/GudiSpring/img/";
            String filePath = basePath + fileName; // fileName에 이미 reviewboard/가 포함됨 왜지?            
            

            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                if (file.delete()) {
                    boardDao.deleteImagePathFromDB(contentNo, fileName);  // 파일 경로 DB에서도 삭제
                 // 성공적으로 삭제된 경우, 수정 페이지로 리다이렉션
                    res.sendRedirect(req.getContextPath() + "/board/reviewboard/edit?contentNo=" + contentNo);
                } else {
                    res.getWriter().write("파일 삭제 실패");
                }
            } else {
            	
                res.getWriter().write("파일이 존재하지 않습니다");
            }
        } catch (Exception e) {
        	  e.printStackTrace(); // 예외 로그 출력
            res.getWriter().write("서버 오류: " + e.getMessage());
        }
    }
}
