package gudiSpring.board.controller.freeboard;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.board.dao.freeboard.BoardDao;
import gudiSpring.board.dto.freeboard.BoardDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/board/freeboard/list")
public class BoardListController extends HttpServlet {
   
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) 
    		throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	   Connection conn = null;
    	   
    	   try {
    		   ServletContext sc = this.getServletContext();
    		   // 미리 준비된 DB 객체 불러오기
			conn = (Connection)sc.getAttribute("conn");
			
			BoardDao boardDao = new BoardDao();
			boardDao.setConnection(conn);
			  
			 // 공지사항 조회 (CONTENT_BOARD_INFO_NO = 4 AND CONTENT_NO = 2)
          
           
			
			//여긴 추후수정
			   int page = 1; // 기본 페이지 번호
	            int pageSize = 10; // 페이지당 게시글 수
	         // 페이지 번호 파라미터 처리
	            if (req.getParameter("page") != null) {
	                page = Integer.parseInt(req.getParameter("page"));
	            }
	            int totalCount = boardDao.getTotalCount();
	            int totalPages = (int) Math.ceil(totalCount / (double) pageSize);
	            int startRow = (page - 1) * pageSize;
			  // 게시글 목록 조회
	            List<BoardDto> boardList = boardDao.selectList(startRow, pageSize);
	            req.setAttribute("boardList", boardList);
	            req.setAttribute("currentPage", page);
	            req.setAttribute("totalPages", totalPages);
            // JSP 페이지로 포워딩
            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/board/freeboard/boardListView.jsp");
            dispatcher.forward(req, res);
			
		} catch (Exception e) {
			 e.printStackTrace();  // 예외를 출력해 문제를 파악
			    throw new ServletException(e);  // 혹은 새로운 예외를 던져 JSP에 에러를 표시
		}
    	   
    	   
    }
    

  
    
    
    
    
    
    
    
    
  }
