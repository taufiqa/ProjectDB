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
@WebServlet("/ReviewsDAO")
public class ReviewsDAO extends HttpServlet {     
	private static final long serialVersionUID = 1L;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public ReviewsDAO() {

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

	public void createDatabase() throws SQLException
	{
		try{
			connect_func();
			statement = (Statement) connect.createStatement();
			
			statement = connect.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
			statement.executeUpdate("DROP TABLE IF EXISTS reviews");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
			
			String sqlstmt = "CREATE TABLE IF NOT EXISTS reviews(" +
								  " reviewID INTEGER NOT NULL auto_increment," + 
								  " score VARCHAR(10)," + 
								  " remark VARCHAR(75)," + 
								  " itemID integer not null," + 
								  " userEmail VARCHAR(25)," + 
								  " reviewDate DATE," + 
								  " PRIMARY KEY (reviewID)" +
								  ");";
			statement.executeUpdate(sqlstmt);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}
		
	}
	
	public void seedDatabase() {
		try {
			connect_func();
			statement = (Statement) connect.createStatement();
			
			statement = connect.createStatement();
			
			preparedStatement = connect.prepareStatement("insert into reviews(score,remark,itemID,userEmail,reviewDate) values (?,?,?,?,?)");
		//	preparedStatement = connect.prepareStatement("insert into reviews(score,remark,itemID,userID) values (?,?,?,?)");

			
			preparedStatement.setString(1, "Excellent");
			preparedStatement.setString(2, "Good");
			preparedStatement.setInt(3, 1);
			preparedStatement.setString(4, "root@gmail.com");
			preparedStatement.setString(5, "2019-11-06");
			preparedStatement.executeUpdate();
			
		/*	preparedStatement.setString(1, "password");
			preparedStatement.setString(2, "John");
			preparedStatement.setString(3, "Doe");
			preparedStatement.setString(4, "johndoe@gmail.com");
			preparedStatement.setString(5, "Male");
			preparedStatement.setString(6, "25");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "random");
			preparedStatement.setString(2, "Nuha");
			preparedStatement.setString(3, "Malik");
			preparedStatement.setString(4, "nuhamal@gmail.com");
			preparedStatement.setString(5, "Female");
			preparedStatement.setString(6, "70");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "randomm");
			preparedStatement.setString(2, "Anika");
			preparedStatement.setString(3, "Taufiq");
			preparedStatement.setString(4, "taufiqa@gmail.com");
			preparedStatement.setString(5, "Female");
			preparedStatement.setString(6, "50");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "gr8Respgr8Pwr");
			preparedStatement.setString(2, "Tom");
			preparedStatement.setString(3, "Holland");
			preparedStatement.setString(4, "imspiderman@gmail.com");
			preparedStatement.setString(5, "Male");
			preparedStatement.setString(6, "18");
			preparedStatement.executeUpdate();
			*/
		}catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}
	}
	
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
		}catch (Exception e) {
			System.out.println(e);
		}finally {
			
		}
	}
}