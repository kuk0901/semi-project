package gudiSpring.board.controller.reviewboard;
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

      

        response.setHeader("Content-Type", getServletContext().getMimeType(simpleFileName));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + simpleFileName + "\"");
        Files.copy(file.toPath(), response.getOutputStream()); // 파일 내용 전송
    }
    

}
