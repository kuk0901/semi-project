package gudiSpring.admin.controller.place;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Connection;

import gudiSpring.place.dao.PlaceDao;
import gudiSpring.place.dto.PlaceDto;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(
		fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 10, 
    maxRequestSize = 1024 * 1024 * 50, 
    location = "D:\\uploads"
)
@WebServlet(value = "/admin/place/add")
public class PlaceAddController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/admin/place/placeAddView.jsp");

		dispatcher.forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Connection conn = null;
		
		try {
			String category = getPartAsString(req.getPart("category"));
			String areaNoStr = getPartAsString(req.getPart("areaNo"));
			String placeName = getPartAsString(req.getPart("placeName"));
			String plAddress = getPartAsString(req.getPart("plAddress"));
			String plPhone = getPartAsString(req.getPart("plPhone"));
			String plWebsite = getPartAsString(req.getPart("plWebsite"));
			int areaNo = Integer.parseInt(areaNoStr); // int로 사용		
			
			Part filePart = req.getPart("image");

			// uploads 폴더 경로 설정
			String folderPath = "D:\\GudiSpring\\img\\place";

			// 폴더가 존재하지 않으면 생성
			File folder = new File(folderPath);

			if (!folder.exists()) {
				folder.mkdirs();
			}

			// 파일 이름 가져오기
			String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			
			// 지정된 바탕화면 경로에 파일 저장
			File file = new File(folderPath, fileName);

			// 동일 이름의 파일이 있는 경우 지우고 생성 => 덮어씀
			// 자동 리소스 관리, 세미콜론(;)으로 여러 리소스 구분
			try (InputStream in = filePart.getInputStream(); FileOutputStream out = new FileOutputStream(file)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = in.read(buffer)) > 0) {
					out.write(buffer, 0, length);
				}
			}

			PlaceDto placeDto = new PlaceDto(category, areaNo, placeName, plAddress, plPhone, plWebsite, fileName);

			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");

			PlaceDao placeDao = new PlaceDao();
			placeDao.setConnection(conn);

			int result;
			result = placeDao.placeInsert(placeDto);

			// validation
			if (result > 0) {
				req.setAttribute("msg", "장소를 추가했습니다.");
			} else if (result == 0) {
				req.setAttribute("msg", "장소를 추가하지 못했습니다.");
			}

			doGet(req, res);

		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/error/e500.jsp");
			
			dispatcher.forward(req, res);
		}
	}

	private String getPartAsString(Part part) throws IOException {
		if (part != null) {
			try (InputStream is = part.getInputStream(); ByteArrayOutputStream os = new ByteArrayOutputStream()) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) != -1) {
					os.write(buffer, 0, length);
				}
				return new String(os.toByteArray(), StandardCharsets.UTF_8);
			}
		}
		return null;
	}

}
