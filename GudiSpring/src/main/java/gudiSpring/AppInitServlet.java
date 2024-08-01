package gudiSpring;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class AppInitServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("AppInitServlet 준비...");

		super.init(config);

		// try문 안에 예외처리가 관련이 없더라도
		// 모든 관련 로직은 어떤 문제가 생기는지 알 수 있도록 처리해야 함
		try {

			ServletContext sc = this.getServletContext();

			String driver = "";
			String url = "";
			String user = "";
			String password = "";

			Connection conn = null;

			driver = sc.getInitParameter("driver");
			url = sc.getInitParameter("url");
			user = sc.getInitParameter("user");
			password = sc.getInitParameter("password");

			// 오라클 객체 불러오기
			Class.forName(driver);

			conn = DriverManager.getConnection(url, user, password);

			sc.setAttribute("conn", conn);

			System.out.println("DB 연결 성공");

		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		System.out.println("AppInitServlet 마무리...");

		super.destroy();

		ServletContext sc = this.getServletContext();

		Connection conn = (Connection) sc.getAttribute("conn");

		try {
			if (conn != null) {
				conn.close();
				System.out.println("DB 연결 해제");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
