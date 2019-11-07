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
					          + "user=root&password=Sql786!!");
			System.out.println(connect);
        }
	}
	
	public boolean isUserValid(String email, String password) {
		boolean flag = false;
		try {
			connect_func();
			statement = (Statement) connect.createStatement();
			
			String sqlstmt = "SELECT * FROM Users WHERE email ='" + email + "' AND password = '" + password + "'";
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
			statement.executeUpdate("DROP TABLE IF EXISTS User");
			statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
			
			String sqlstmt = "CREATE TABLE IF NOT EXISTS users(" +
								  " UserId INTEGER not NULL AUTO_INCREMENT, " +
								  " Password VARCHAR(20) not NULL, " +
								  " FirstName VARCHAR(20) not NULL, " +
								  " LastName VARCHAR(20) not NULL, " +
								  " Email VARCHAR(50), " +
								  " Gender VARCHAR(30) not NULL, " +
								  " Age VARCHAR(20) not NULL, " +
								  " PRIMARY KEY (userId), " +
								  " UNIQUE KEY (email));";
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
			
			preparedStatement = connect.prepareStatement("insert into Users(firstName,lastName,password,email, age) values (?,?,?,?,?)");
			
			preparedStatement.setString(1, "Root");
			preparedStatement.setString(2, "User");
			preparedStatement.setString(3, "pass1234");
			preparedStatement.setString(4, "root");
			preparedStatement.setString(5, "M");
			preparedStatement.setString(6, "45");
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
	
	 /* public void initUserTable() throws SQLException
	 
	{
		List<User> userList = new ArrayList<User>();
		userList.add(new User(0, "root", "pass1234","root@gmail.com"));
		userList.add(new User(0, "john", "pass1234","john@gmail.com"));
		userList.add(new User(0, "chandler", "pass1234","chandler@gmail.com"));
		userList.add(new User(0, "joey", "pass1234","joey@gmail.com"));
		userList.add(new User(0, "rachel", "pass1234","rachel@gmail.com"));
		userList.add(new User(0, "monica", "pass1234","monica@gmail.com"));
		userList.add(new User(0, "phoebe", "pass1234","phoebe@gmail.com"));
		userList.add(new User(0, "ross", "pass1234","ross@gmail.com"));

		insertUser(userList);
	}
	
	public void dropUserTable() throws SQLException
	{
		connect();
		Statement statement = connect.createStatement();
		statement.executeUpdate("DROP TABLE IF EXISTS User");
		statement.close();
		disconnect();
	}
	
    
    public void disconnect() throws SQLException
	{
		if (connect != null && !connect.isClosed())
		{
	        connect.close();
	    }
	}
    
    public boolean insertUser(List<User> userList) throws SQLException
	{
    	User user = new User();
		boolean status = false;
		
		String sqlInsert = "INSERT INTO User (userName, password, email) " +
							"VALUES (?, ?, ?)";
		connect();
		for (int i = 0; i < userList.size(); i++)
		{
			user = userList.get(i);
			
			PreparedStatement preparedStatement = connect.prepareStatement(sqlInsert);
			preparedStatement.setString(1, user.getUserName());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(5, user.getEmail());
			
			status &= preparedStatement.executeUpdate() > 0;
			preparedStatement.close();
		}		
		disconnect();
		
		return status;
	}
    
    public boolean insertUser(User user) throws SQLException
	{
    	connect();
		String sql = "INSERT INTO User(userName, password, email) " +
							"VALUES (?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, user.getUserName());
		preparedStatement.setString(2, user.getPassword());
		preparedStatement.setString(5, user.getEmail());
		
		boolean status = preparedStatement.executeUpdate() > 0;
		preparedStatement.close();
		disconnect();
		
		return status;
	}
    
    public boolean deleteUser(int userId) throws SQLException
	{
		String sql = "DELETE FROM User WHERE userId = ?";
		connect();
		
		PreparedStatement prepareStatement = connect.prepareStatement(sql);
		prepareStatement.setInt(1, userId);
		
		boolean status = prepareStatement.executeUpdate() > 0;
		prepareStatement.close();
		disconnect();
		
		return status;
	}
    
    public boolean updateUser(User user) throws SQLException
	{
		String sql = "UPDATE User SET userName = ?, password = ?, email = ? WHERE userId = ?";
		connect();
		
		PreparedStatement prepareStatement = connect.prepareStatement(sql);
		prepareStatement.setString(1, user.getUserName());
		prepareStatement.setString(2, user.getPassword());
		prepareStatement.setString(3, user.getEmail());
		prepareStatement.setInt(4, user.getUserId());
		
		boolean status = prepareStatement.executeUpdate() > 0;
		prepareStatement.close();
		disconnect();
		
		return status;
	}
    */
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	public User getUser(String userName) throws SQLException
	{
		User user = null;
		String sqlGet = "SELECT * FROM User WHERE userName = ?";
		connect();
		
		PreparedStatement preparedStatement = connect.prepareStatement(sqlGet);
		preparedStatement.setString(1, userName);
		ResultSet result = preparedStatement.executeQuery();
		
		while (result.next())
		{
			int userId = result.getInt("userId");
			String password = result.getString("password");
			String email = result.getString("email");
			
			user = new User(userId, userName, password, email);
		}
		
		result.close();
		preparedStatement.close();
		disconnect();
		
		return user;
	}
	
	public List<User> getUserList() throws SQLException
	{
		List<User> userList =  new ArrayList<User>();
		String sqlQuery = "SELECT * FROM User";
		
		connect();
		Statement statement = connect.createStatement();
		ResultSet result = statement.executeQuery(sqlQuery);
		
		
		if (result.next()) {
			int userId = result.getInt("userId");
			String userName = result.getString("userName");
			String password = result.getString("password");
			String email = result.getString("email");
             
			User user = new User(userId, userName, password, email);			
			userList.add(user);
		}
		
		result.close();
		statement.close();
		disconnect();
		
		return userList;
	}
	*/
}
