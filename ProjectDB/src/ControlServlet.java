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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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
	private ItemsDAO itemsDAO;
	private ReviewsDAO reviewsDAO;
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	static HttpSession session = null;
	
	public void init()
	{
		userDAO = new UserDAO();
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
	            case "/StorePage":
	            	String sort = request.getParameter("sort");
	            	List<Items> listOfItems = null;
	            	listOfItems = ControlServlet.listItems(sort);
	            	listItems(request,response);
	            	break;
	            case "/postReview":
	            	postReview(request,response);
	            	break;
	            default:
	            	break;
	            }
        }
		catch (Exception e)
        {
            throw new ServletException(e);
        }
    }
	
	private void initializeDatabase(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		userDAO.createDatabase();
		itemsDAO.createDatabase();
		reviewsDAO.createDatabase();

		userDAO.seedDatabase();
		itemsDAO.seedDatabase();
		reviewsDAO.seedDatabase();

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
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "goldPa!nt51");
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
		
		List<Items> listOfItems = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "goldPa!nt51");
			Statement st=connect.createStatement();
			
		int i=st.executeUpdate("insert into items(userEmail,title,itemDescription,postDate,price,categoryName)values('" + userEmail + "','" +title + "','" + itemDescription + "','" + postDate +"','"+ price +"','"+  categoryName + "')");
				
		listOfItems = ControlServlet.listItems("categoryName");
		
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
	
	
	private static List<Items> listItems(String sort) throws SQLException {
		List<Items> listOfItems = new ArrayList<Items>();
		
		int itemID = resultSet.getInt("itemID");
		int userID = resultSet.getInt("userID");
		String title = resultSet.getString("title");
		String itemDescription = resultSet.getString("itemDescription");
		double price = resultSet.getDouble("price");
		String categoryName = resultSet.getString("categoryName");
		String datePosted = resultSet.getDate("datePosted").toString();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "goldPa!nt51");
			Statement st=connect.createStatement();
			
			String sql = "SELECT * from item";
			if(sort.equals("category"))
				sql = "SELECT * from Items as I order by I.category";
			else if(sort.equals("price"))
				sql = "SELECT * from Items as I order by I.price asc";
		
			ResultSet resultSet = st.executeQuery(sql);
			
			while (resultSet.next()) {
				Items item = new Items(itemID, userID, title, itemDescription, price, categoryName, datePosted);
				listOfItems.add(item);
			}
			return listOfItems;
			
		}
		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}
		
		return listOfItems;
	}

	/*  public static List<Items> listAllItems(String sortBy) throws SQLException {
	 
		List<Items> listItems = new ArrayList<Items>();

		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "goldPa!nt51");
			Statement st=connect.createStatement();
			
			String sql = "SELECT * from item";
			if(sortBy.equals("categoryName"))
				sql = "SELECT * FROM Items AS I ORDER BY I.categoryName";
			else if(sortBy.equals("price"))
				sql = "SELECT * FROM Items AS I ORDER BY I.price ASC";
			
			ResultSet resultSet = st.executeQuery(sql);

			while (resultSet.next()) {
				int itemID = resultSet.getInt("itemID");
				int userID = resultSet.getInt("userID");
				String title = resultSet.getString("title");
				String itemDescription = resultSet.getString("itemDescription");
				Date postDate = resultSet.getDate("postDate");
				double price = resultSet.getDouble("price");
				String categoryName = resultSet.getString("categoryName");
				
				Items item = new Items(itemID, userID, title, itemDescription, postDate, price, categoryName);
				listItems.add(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return listItems;
	}
	  */
	
 	public void listItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
 
 	{
		String title = request.getParameter("title");
		String itemDescription = request.getParameter("itemDescription");
		String postDate = request.getParameter("postDate");
		String price = request.getParameter("price");
		String categoryName = request.getParameter("categoryName");

		PrintWriter out = response.getWriter();
		
		try
		{
			response.setContentType("text/html");
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "goldPa!nt51");
			Statement st=connect.createStatement();

			String sql = "SELECT itemID,title,itemDescription,postDate,price,categoryName FROM items WHERE categoryName = '" + categoryName +"';";
		/*	if(categoryName.equals("categoryName"))
				sql = "SELECT * FROM Items AS I ORDER BY I.categoryName";
			else if(categoryName.equals("price"))
				sql = "SELECT * FROM Items AS I ORDER BY I.price ASC";
		*/	
			ResultSet resultSet = st.executeQuery(sql);
			
		//	String sql="SELECT * FROM Items INNER JOIN categories ON Items.title = Categories."+categoryName+")";
		//	resultSet = st.executeQuery(sql);

			out.print("<html><body><table border><tr><td>itemid</td><td>Title</td><td>Description</td><td>Date</td><td>Price</td><td>Category Name</td></tr>");

			while(resultSet.next())
			{
				out.println("<tr><td><a href = \"/ProjectDB/postReview/{$itemID}\">"+resultSet.getInt(1)+"</a></td><td>"+resultSet.getString(2)+"</td><td>"+resultSet.getString(3)+"</td><td>"+resultSet.getString(4)+"</td><td>"+resultSet.getString(5)+"</td><td>"+resultSet.getString(6)+"</td></tr>");
			}
			out.println("</table></body></html>");
		}
		catch(Exception e)
		{
		System.out.print(e);
		e.printStackTrace();
		}
	}
	
 	
 	private void postReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
 		String score = request.getParameter("score");
		String remark = request.getParameter("remark");
		int itemID = Integer.parseInt(request.getParameter("itemID"));
		int userID = (int) session.getAttribute("userID");
		
		Calendar date = Calendar.getInstance();
		java.sql.Date reviewDate = new java.sql.Date(date.getTime().getTime());
		
		PrintWriter out = response.getWriter();
		
 		try {
 			response.setContentType("text/html");
			Class.forName("com.mysql.jdbc.Driver");
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectdb?", "root", "goldPa!nt51");
			Statement st=connect.createStatement();
			PreparedStatement ps = null;
			
			Reviews review = new Reviews(score, remark, itemID, userID, reviewDate);

			String sql = "INSERT INTO reviews (score,remark,itemID,userID,reviewDate) VALUES (?,?,?,?,?)";
			
			ps = connect.prepareStatement(sql);
			ps.setString(1, review.getScore());
			ps.setString(2, review.getRemark());
			ps.setInt(3, review.getItemID());
			ps.setInt(4, review.getUserID());
			ps.setDate(5, reviewDate);
			
			out.print("<html><body><table border><tr><td>Title</td><td>Description</td><td>Date</td><td>Price</td><td>Category Name</td></tr>");
			while(resultSet.next())
			{
				out.println("<tr><td>"+resultSet.getString(1)+"</td><td>"+resultSet.getString(2)+"</td><td>"+resultSet.getString(3)+"</td><td>"+resultSet.getString(4)+"</td><td>"+resultSet.getString(5)+"</td></tr>");
			}
			out.println("</table></body></html>");

 		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		
		/* Calendar date = Calendar.getInstance();
		java.sql.Date dateOfReview = new java.sql.Date(date.getTime().getTime());
		
		
		String insertQuery = "INSERT INTO Reviews (userID,itemID,rating,description,dateOfReview) VALUES (?,?,?,?,?)";
		ps = connection.prepareStatement(insertQuery);
		ps.setInt(1, review.getUserID());
		ps.setInt(2, review.getItemID());
		ps.setString(3, review.getRating());
		ps.setString(4, review.getDescription());
		ps.setDate(5, dateOfReview);
		if(ps.executeUpdate() > 0) {
			System.out.println("Review added sucessfully...");
			session.setAttribute("reviewTrigger", false);
		}
		else {
			System.out.println("Review was not added...");
		} 
		*/
		
	}
 	

	/*private void searchCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
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