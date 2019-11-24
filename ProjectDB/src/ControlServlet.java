import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List; 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "Sql786!!");
			Statement st=connect.createStatement();
			
		int i=st.executeUpdate("insert into items(userEmail,title,itemDescription,postDate,price)values('" + userEmail + "','" +title + "','" + itemDescription + "','" + postDate +"','"+ price + "')");
		int j = st.executeUpdate("insert into categories(categoryName,itemTitle)values('" + categoryName + "','" + title + "')");
		
		if (i != 0)response.sendRedirect("success.jsp");
		else {
			response.sendRedirect("error.jsp"); //make an error.jsp
			}
		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}

	}
/*
	private void searchCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		List<Items> itemsList = new ArrayList<Items>();
		
		String category = request.getParameter("searchCategory");
		
		itemsList = ItemsDAO.getItemCategoryList(category);
		
		if (!category.isEmpty())
		{
			request.setAttribute("searchCategory", category);
			
			System.out.println("if statement");
			//listItem(request, response, itemsList, null);
		}
		else
		{
			System.out.println("else statement");
			//allItems(request, response);
		}
	}
	*/
}