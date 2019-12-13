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
			statement.executeUpdate("DROP TABLE IF EXISTS items");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
			
			String sqlstmt = "CREATE TABLE IF NOT EXISTS items(" +
								  " itemID INTEGER NOT NULL auto_increment," + 
								  " userEmail VARCHAR(25)," + 
								  " title VARCHAR(20)," + 
								  " itemDescription VARCHAR(100)," + 
								  " postDate DATE," + 
								  " price DECIMAL(9, 2)," + 
								  " categoryName VARCHAR(75)," +
								  " PRIMARY KEY(itemID)" + 
								  " );";
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
			
			preparedStatement = connect.prepareStatement("insert into items(userEmail,title,itemDescription,postDate,price,categoryName) values (?,?,?,?,?,?)");

			preparedStatement.setString(1, "root@gmail.com");
			preparedStatement.setString(2, "iphone");
			preparedStatement.setString(3, "whodis?");
			preparedStatement.setString(4, "2019-11-06");
			preparedStatement.setDouble(5, 15.00);
			preparedStatement.setString(6, "electronics,phone");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "root@gmail.com");
			preparedStatement.setString(2, "tesla");
			preparedStatement.setString(3, "v cool car");
			preparedStatement.setString(4, "2019-11-06");
			preparedStatement.setDouble(5, 1000.00);
			preparedStatement.setString(6, "electronics,car,auto,automobile");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "root@gmail.com");
			preparedStatement.setString(2, "designer waterbottle");
			preparedStatement.setString(3, "bougee");
			preparedStatement.setString(4, "2019-11-06");
			preparedStatement.setDouble(5, 70.00);
			preparedStatement.setString(6, "food,designer,drink");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "taufiqa@gmail.com");
			preparedStatement.setString(2, "socks");
			preparedStatement.setString(3, "i like socks");
			preparedStatement.setString(4, "2019-11-06");
			preparedStatement.setDouble(5, 1.00);
			preparedStatement.setString(6, "apparel,clothing");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "taufiqa@gmail.com");
			preparedStatement.setString(2, "FRIENDS mug");
			preparedStatement.setString(3, "i also like FRIENDS");
			preparedStatement.setString(4, "2019-12-06");
			preparedStatement.setDouble(5, 16.00);
			preparedStatement.setString(6, "food,mug,drink");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "nuhamal@gmail.com");
			preparedStatement.setString(2, "NOMINAL necklace");
			preparedStatement.setString(3, "i wear these a lot. i have 75.");
			preparedStatement.setString(4, "2019-10-13");
			preparedStatement.setDouble(5, 20.00);
			preparedStatement.setString(6, "jewelry");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "nuhamal@gmail.com");
			preparedStatement.setString(2, "designer hijab");
			preparedStatement.setString(3, "hard to hear when you are this bougee");
			preparedStatement.setString(4, "2019-12-05");
			preparedStatement.setDouble(5, 95.00);
			preparedStatement.setString(6, "clothing,apparel,deisgner");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "SRaffi@gmail.com");
			preparedStatement.setString(2, "MacBook - Gladys");
			preparedStatement.setString(3, "comes with a cleaning wipe named Agnus!");
			preparedStatement.setString(4, "2019-02-13");
			preparedStatement.setDouble(5, 900.00);
			preparedStatement.setString(6, "electronics,weird names,laptop");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "imspiderman@gmail.com");
			preparedStatement.setString(2, "sandwiches");
			preparedStatement.setString(3, "Delmar has the best sandwiches");
			preparedStatement.setString(4, "2019-11-30");
			preparedStatement.setDouble(5, 5.00);
			preparedStatement.setString(6, "food,sandwiches");
			preparedStatement.executeUpdate();
			
			preparedStatement.setString(1, "taufiqa@gmail.com");
			preparedStatement.setString(2, "green tea");
			preparedStatement.setString(3, "only for sophisticated drinkers");
			preparedStatement.setString(4, "2019-10-20");
			preparedStatement.setDouble(5, 10.00);
			preparedStatement.setString(6, "food,drink");
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