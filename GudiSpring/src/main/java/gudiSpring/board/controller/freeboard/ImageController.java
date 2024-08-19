package gudiSpring.board.controller.freeboard;

import java.nio.file.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ImageController extends HttpServlet {
    // 기본 경로 설정
    private static final String BASE_DIRECTORY = "D:/GudiSpring/img/freeboard/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 클라이언트로부터 파일 이름을 파라미터로 받아옴
        String fileName = request.getParameter("fileName");

        // 파일 이름이 비어 있거나 잘못된 경우 오류 처리
        if (fileName == null || fileName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "파일 이름을 지정해야 합니다.");
            return;
        }

        // 전체 파일 경로 생성
        String filePath = BASE_DIRECTORY + fileName;
        Path path = Paths.get(filePath);

        // 파일 존재 여부 확인 및 처리
        if (Files.exists(path)) {
            // 파일 MIME 타입 설정 (이미지 파일일 경우)
            response.setContentType(Files.probeContentType(path));
            
            // 파일 크기 설정
            response.setContentLength((int) Files.size(path));

            // 파일을 응답 스트림에 쓰기
            Files.copy(path, response.getOutputStream());
            
            // 스트림 플러시
            response.getOutputStream().flush();
        } else {
            // 파일이 존재하지 않을 경우 404 오류 반환
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
        }
    }
}
