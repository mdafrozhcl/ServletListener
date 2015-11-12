import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListenersTestServlet
 */
public class ListenersTestServlet extends HttpServlet {
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListenersTestServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = (String) request.getAttribute("message");
		HttpSession s = request.getSession();
		s.setAttribute("message", message);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		ServletContext context = request.getServletContext();
		Connection con = (Connection) context.getAttribute("mycon");
		try {
			PreparedStatement stmt = con.prepareStatement("Select * from books");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				out.println("Bno: " + rs.getInt(1) + " Book Name: "
						+ rs.getString(2) + " Book Price: " + rs.getDouble(3)
						+ "<br>");
			}
			//rs.close();
			//stmt.close();
			//con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		out.println("<a href=\"/ListenerRequest/Servlet2\">click</a>");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
