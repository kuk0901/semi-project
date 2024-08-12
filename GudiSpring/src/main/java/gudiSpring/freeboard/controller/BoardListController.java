package gudiSpring.freeboard.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import gudiSpring.freeboard.dao.BoardDao;
import gudiSpring.freeboard.dto.BoardDto;
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
			// TODO: handle exception
		}
    	   
    	   
    }
    

  
    
    
    
    
    
    
    
    
  }
