import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.result.ResultSetMetaData;

public class ControlServlet extends HttpServlet
{
	User user = new User();
	private UserDAO userDAO;
	private CategoriesDAO categoriesDAO;
	private ItemsDAO itemsDAO;
	private ReviewsDAO reviewsDAO;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

	public void init()
	{
		userDAO = new UserDAO();
		categoriesDAO = new CategoriesDAO();
		itemsDAO = new ItemsDAO();
		reviewsDAO = new ReviewsDAO();

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        doGet(request, response);
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) 
            {
	            case "/initialize":
	            	initializeDatabase(request, response);
	            	break;
	            case "/login":
	            	login(request, response);
	            	break;
	            case "/register":
	            	register(request,response);
	            	break;
	            case "/newItem":
	            	newItem(request,response);
	            	break;
	            case "/search":
	            	search(request,response);
	            	break;
	            default:
	            	break;
            }
        }catch (SQLException exception)
        {
            throw new ServletException(exception);
        }
    }
	
	private void initializeDatabase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		userDAO.createDatabase();
		itemsDAO.createDatabase();
		reviewsDAO.createDatabase();
		categoriesDAO.createDatabase();

		userDAO.seedDatabase();
		itemsDAO.seedDatabase();
		reviewsDAO.seedDatabase();
		categoriesDAO.seedDatabase();

		response.sendRedirect("index.jsp");
	}
	
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		user.setEmail(email);
		user.setPassword(password);
		
		if(userDAO.isUserValid(email,password))
			response.sendRedirect("StorePage.jsp");
		else
			response.sendRedirect("Login.jsp");
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		int age = Integer.parseInt(request.getParameter("age"));
		char gender = request.getParameter("gender").charAt(0); 

		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setAge(age);
		user.setGender(gender);
		
		
		response.sendRedirect("Login.jsp"); //redirect immediately to login page to allow user to login

		try
		{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "Sql786!!");
		Statement st=connect.createStatement();

		int i=st.executeUpdate("insert into users(firstName,lastName,password,email,age,gender)values('" + firstName + "','" + lastName + "','" + password + "','" + email + "','" + age + "','" + gender +"')");

		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}

	}
	
	public void newItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		String title = request.getParameter("title");
		String itemDescription = request.getParameter("itemDescription");
		LocalDate postDate = LocalDate.now();
		double price = Double.parseDouble(request.getParameter("price"));
		String categoryName = request.getParameter("categoryName").toLowerCase();
		String userEmail = user.getEmail();   
		
		response.sendRedirect("StorePage.jsp");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "Sql786!!");
			Statement st=connect.createStatement();
			
		int i=st.executeUpdate("insert into items(userEmail,title,itemDescription,postDate,price,"
				+ "categoryName)values('" + userEmail + "','" +title + "','" + itemDescription + "','" + 
				postDate +"','"+ price  +"','"+ categoryName + "')");
		
		//WORK ON REDIRECTION
		/*if (i != 0)response.sendRedirect("success.jsp");
		else if (i == 0){
			response.sendRedirect("error.jsp"); //make an error.jsp
			}*/
		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}

	}

	private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		
		String category = request.getParameter("searchCategory").toLowerCase();
			
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "Sql786!!");
		
			PrintWriter printOut = response.getWriter();
			//response.sendRedirect("StorePage.jsp?name1=value1");
			
			
			String searchQuery = "SELECT * FROM items WHERE categoryName LIKE'%" + category + "%'";    
			preparedStatement = (PreparedStatement)connect.prepareStatement(searchQuery);	
			ResultSet resultSet = preparedStatement.executeQuery();
			
			java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
		
			//printOut.print("hello");
			/*List<String> columnNames = new ArrayList<String>();
			for (int j = 2; j < columnCount + 1; j++)
			{
				String columnName = metaData.getColumnName(j) + "   ";
				//printOut.print(metaData.getColumnName(j) + "   ");
				columnNames.add(columnName);
			}
			
			request.setAttribute("columnNames", columnNames); // Will be available as ${columnValues} in JSP
*/
			//printOut.println();
			
			List<Items> rows = new ArrayList<Items>();
			while (resultSet.next())
			{
				Items itemNew = new Items();
				
				itemNew.setUserEmail(resultSet.getString("userEmail"));
				itemNew.setTitle(resultSet.getString("title"));
				itemNew.setItemDescription(resultSet.getString("itemDescription"));
				itemNew.setPostDate(resultSet.getString("postDate"));
				itemNew.setPrice(resultSet.getBigDecimal("price"));
				itemNew.setCategoryName(resultSet.getString("categoryName"));

				rows.add(itemNew);
				/*for (int k = 2; k < columnCount + 1; k++)
				{
					String columnValue = resultSet.getString(k) + "\n";
					columnValues.add(columnValue);
					//printOut.print(columnValue + "   ");
				}*/
				//printOut.println();
			}
			
			request.setAttribute("rows", rows); // Will be available as ${columnValues} in JSP
	        request.getRequestDispatcher("StorePage.jsp").forward(request, response);
		}
		
		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}
		
	}
	
}