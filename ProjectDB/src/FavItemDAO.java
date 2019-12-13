import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/favItemDAO")
public class FavItemDAO extends HttpServlet {     
	private static final long serialVersionUID = 1L;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public FavItemDAO() {

	}
	       
    /**
     * @see HttpServlet#HttpServlet()
     */
	public void connect_func() throws SQLException
	{
		if (connect == null || connect.isClosed())
		{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
			connect = (Connection) DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/projectdb?"
					          + "user=john&password=pass1234");
			System.out.println(connect);
        }
	}
}
