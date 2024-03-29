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
@WebServlet("/UserDAO")
public class UserDAO extends HttpServlet {     
	private static final long serialVersionUID = 1L;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public UserDAO() {

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
	
	public boolean isUserValid(String username, String password) {
		boolean flag = false;
		try {
			connect_func();
			statement = (Statement) connect.createStatement();
			
			String sqlstmt = "SELECT * FROM Users WHERE email ='" + username + "' AND password = '" + password + "'";
			ResultSet rs = statement.executeQuery(sqlstmt);
			
			if(rs.next())
				flag = true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}
		
		return flag; //returns true if user exists returns false if user doesn't exist
	}
    
	public void createDatabase() throws SQLException
	{
		try{
			connect_func();
			statement = (Statement) connect.createStatement();
			
			statement = connect.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
			statement.executeUpdate("DROP TABLE IF EXISTS Users");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
			
			String sqlstmt = "CREATE TABLE IF NOT EXISTS users(" +
								  " userID INTEGER NOT NULL auto_increment," + 
								  " firstName VARCHAR(25)," + 
								  " lastName VARCHAR(25)," + 
								  " password VARCHAR(8)," + 
								  " email VARCHAR(25)," + 
								  " gender CHAR(2)," + 
								  " age INTEGER," + 
								  " PRIMARY KEY(userID,email)," + 
								  " CHECK (gender IN ('M', 'F'))" + 
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
			
			preparedStatement = connect.prepareStatement("insert into users(firstName,lastName,password,email,gender, age) values (?,?,?,?,?,?)");
			
			preparedStatement.setString(1, "Root");
			preparedStatement.setString(2, "User");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "root@gmail.com");
			preparedStatement.setString(5, "M");
			preparedStatement.setString(6, "45");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Nuha");
			preparedStatement.setString(2, "Malik");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "nuhamal@gmail.com");
			preparedStatement.setString(5, "F");
			preparedStatement.setString(6, "70");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Anika");
			preparedStatement.setString(2, "Taufiq");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "taufiqa@gmail.com");
			preparedStatement.setString(5, "F");
			preparedStatement.setString(6, "50");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Tom");
			preparedStatement.setString(2, "Holland");
			preparedStatement.setString(3, "avengers");
			preparedStatement.setString(4, "imspiderman@gmail.com");
			preparedStatement.setString(5, "M");
			preparedStatement.setString(6, "18");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Tony");
			preparedStatement.setString(2, "Stark");
			preparedStatement.setString(3, "ily3000");
			preparedStatement.setString(4, "imtony@gmail.com");
			preparedStatement.setString(5, "M");
			preparedStatement.setString(6, "50");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Tasneem");
			preparedStatement.setString(2, "G");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "tazzyg@gmail.com");
			preparedStatement.setString(5, "F");
			preparedStatement.setString(6, "9");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Guy");
			preparedStatement.setString(2, "User");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "guyuser@gmail.com");
			preparedStatement.setString(5, "M");
			preparedStatement.setString(6, "35");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Girl");
			preparedStatement.setString(2, "User");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "girluser@gmail.com");
			preparedStatement.setString(5, "F");
			preparedStatement.setString(6, "35");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Ranya");
			preparedStatement.setString(2, "Crayon");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "RCrayon@gmail.com");
			preparedStatement.setString(5, "F");
			preparedStatement.setString(6, "20");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "Saular");
			preparedStatement.setString(2, "Raffi");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "SRaffi@gmail.com");
			preparedStatement.setString(5, "M");
			preparedStatement.setString(6, "100");
			preparedStatement.executeUpdate();
			
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
