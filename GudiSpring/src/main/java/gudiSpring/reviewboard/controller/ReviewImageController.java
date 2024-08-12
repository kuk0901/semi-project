package gudiSpring.reviewboard.controller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/image/reviewboard/*")
public class ReviewImageController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String filename = request.getPathInfo().substring(1); // URL에서 파일 이름 추출
        File file = new File("C:/GudiSpring/img/reviewboard", filename); // 전체 파일 경로

        // 파일 이름만 추출
        String simpleFileName = file.getName();

        System.out.println("파일 이름 확인: " + simpleFileName);

        response.setHeader("Content-Type", getServletContext().getMimeType(simpleFileName));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + simpleFileName + "\"");
        Files.copy(file.toPath(), response.getOutputStream()); // 파일 내용 전송
    }
        
        //     <img src="/test/image/freeboard/sana.webp" alt="Sana Image">
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//	    String fullPath = request.getPathInfo().substring(1);
//	    String filename = new File(fullPath).getName(); // 파일 이름만 추출
//	    request.setAttribute("filename", filename); // 요청 속성에 저장
//	    request.getRequestDispatcher("/jsp/board/boardDetailView.jsp").forward(request, response); // JSP 페이지로 전달
//	}

}
