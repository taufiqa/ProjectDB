import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
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
import java.util.Date;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/ItemsDAO")
public class ItemsDAO extends HttpServlet {     
	private static final long serialVersionUID = 1L;
	private static Connection connect = null;
	private static Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	
	public ItemsDAO() {

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
					          + "user=root&password=goldPa!nt51");
			System.out.println(connect);
        }
	}
	/*
	
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
	
	*/
    
	public void createDatabase() throws SQLException
	{
		try{
			connect_func();
			statement = (Statement) connect.createStatement();
			
			statement = connect.createStatement();
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
			statement.executeUpdate("DROP TABLE IF EXISTS items");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
			
			String sqlstmt = "CREATE TABLE IF NOT EXISTS items(" +
								  " itemID INTEGER NOT NULL auto_increment," + 
								  " title VARCHAR(20)," + 
								  " itemDescription VARCHAR(100)," + 
								  " postDate DATE," + 
								  " price DECIMAL(5, 2)," + 
								  " PRIMARY KEY(itemID));";
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
			
		//	preparedStatement = connect.prepareStatement("insert into items(title,itemDescription,postDate,price) values (?,?,?,?)");
			preparedStatement = connect.prepareStatement("insert into items(title,itemDescription,price) values (?,?,?)");

			preparedStatement.setString(1, "phone");
			preparedStatement.setString(2, "newphonewhodis");
		//	preparedStatement.setDate(3, new java.sql.Date(Date.getTime()));
			preparedStatement.setDouble(3, 15.00);
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
	/*
	
	public List<Items> getItemCategoryList(String category) throws SQLException
	{
	    List<Items> itemsByCategory =  new ArrayList<Items>();
	    String sqlQuery = 
	           "SELECT *" +
	            " FROM (items title, categories category)" +
	            " WHERE (items.itemID = categories.itemID AND categories.categoryName = ?)" +
	            " ORDER BY items.title";
	    try {
	    connect_func();
		statement = (Statement) connect.createStatement();
		
		statement = connect.createStatement();
		
		preparedStatement = connect.prepareStatement(sqlQuery);
		preparedStatement.setString(1, category);
		ResultSet result = preparedStatement.executeQuery();
		
		while (result.next())
		{
			int itemID = result.getInt("itemID");
			String title = result.getString("title");
			String itemDescription = result.getString("itemDescription");
		//	java.sql.Date jokePostDate =  result.getDate("jokePostDate");
			BigDecimal price = new BigDecimal(result.getDouble("price"));
			
			Items items = new Items(itemID, title, itemDescription, price);			
			itemsByCategory.add(items);
		}
	    }catch (Exception e) {
			System.out.println(e);
		} finally {
			close();
		}
		return itemsByCategory;
	}
	*/
}