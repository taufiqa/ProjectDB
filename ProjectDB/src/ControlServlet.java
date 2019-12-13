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
	
	private static final long serialVersionUID = 1L;

	// Driver Name and DB URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/";
	static final String PROJDB_URL = "jdbc:mysql://localhost:3306/projectdb?";
	
	// Database credentials
	static final String USER = "john";
	static final String PASS = "pass1234";
		
		
	private PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	static HttpSession session = null;
	
	User user = new User();
	FavUser favuser = new FavUser();
	FavItem favitem = new FavItem();

	private UserDAO userDAO;
	private ItemsDAO itemsDAO;
	private ReviewsDAO reviewsDAO;

	
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
        String addFaveSellerAction = request.getParameter("favSelleraction");
		String addFaveItemAction = request.getParameter("favItemaction");
		String postReviewAction = request.getParameter("postReviewaction");
		String deleteFaveSellerAction = request.getParameter("delFavSelleraction");
		String deleteFaveItemAction = request.getParameter("delFavItemaction");

		System.out.println(action);
        //System.out.println(addFavSelleraction);
        System.out.println(addFaveSellerAction);
        try {
			if (addFaveSellerAction!= null) {
        		System.out.println("I'm in addFavoriteSellers case switch!");
           	 	addFavoriteSellers(request,response);
        	}
        	else if(addFaveItemAction != null) {
        		System.out.println("I'm in addFavoriteItems case switch!");
           	 	addFavoriteItems(request,response);
        	}
        	else if(postReviewAction != null) {
        		System.out.println("I'm in postReview case switch!");
           	 	postReview(request,response);
        	}
        	else if(deleteFaveSellerAction != null) {
        		System.out.println("I'm in deleteFaveSellerAction case switch!");
        		deleteFavoriteSeller(request,response);
        	}
        	else if(deleteFaveItemAction != null) {
        		System.out.println("I'm in deleteFaveItemAction case switch!");
        		deleteFavoriteItem(request,response);
        	}
        	else {
        	
	        	switch (action) 
		        {
		            case "/initialize":
		            	initializeDatabase(request, response);
		            	break;
		            case "/login":
		            	login(request, response);
		            	break;
		            case "/logout":
		            	logout(request, response);
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
		            /*case "/addFavoriteSellers":
		            	System.out.println("I'm in addFavoriteSellers case switch!");
		            	 addFavoriteSellers(request,response);
		            	break;
		            	*/
		           case "/favoriteSellers":
		            	System.out.println("I'm in favoriteSellers case switch!");
		            	 favoriteSellers(request,response);
		            	break;
		           case "/favoriteItems":
		            	System.out.println("I'm in favoriteItems case switch!");
		            	 favoriteItems(request,response);
		            	break;
		            default:
		            	break;
		        }
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
		HttpSession session = request.getSession();
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		user.setEmail(email);
		user.setPassword(password);
		
		if(userDAO.isUserValid(email,password)) {
			session.setAttribute("currentUser", email);
			response.sendRedirect("StorePage.jsp");
		}
		else
			response.sendRedirect("Login.jsp");
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		/* session = request.getSession(false); */
		HttpSession sess = request.getSession(false);

		if(sess != null ) {
			sess.invalidate();
			response.sendRedirect("Login.jsp");
		}
		else {
			response.sendRedirect("Login.jsp");
		}
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
		Class.forName(JDBC_DRIVER);
		Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
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
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			Statement st=connect.createStatement();
			
		int i=st.executeUpdate("insert into items(userEmail,title,itemDescription,postDate,price,categoryName)"
				+ "values('" + userEmail + "','" +title + "','" + itemDescription + "','" + postDate +"','"+ price +"','"+ categoryName + "')");
		
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
	
	
 	private void postReview(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
 		String score = request.getParameter("score");
		String remark = request.getParameter("remark");
		int itemID = Integer.parseInt(request.getParameter("itemID"));
		LocalDate reviewDate = LocalDate.now();
		String userEmail = user.getEmail();
		//String favUserEmail = favuser.getFavUserEmail();
		
		System.out.println("I'M IN THE POST REVIEW METHOD");
 		try {
 			System.out.println("I'M IN THE POST REVIEW TRY BLOCK");

 			Class.forName(JDBC_DRIVER);
 			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			Statement st=connect.createStatement();
			PreparedStatement ps = null;
			
			int i=st.executeUpdate("INSERT INTO Reviews(score,remark,itemID,userEmail,reviewDate)"
					+ "VALUES('" + score + "','" +remark + "','" + itemID + "','" + 
					userEmail +"','"+ reviewDate +"')");
		/*	
			if(ps.executeUpdate()>0) {
				
				System.out.println("Review successfully added");
			//	session.setAttribute("reviewTrigger", false);

			}else System.out.println("Review wasn't added!");
			*/
	        request.getRequestDispatcher("StorePage.jsp").forward(request, response);
			
 		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
		
	}
 	

 	private void search(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{

		String category = request.getParameter("searchCategory").toLowerCase();

		try
		{
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			
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
				itemNew.setItemID(resultSet.getInt("itemID"));

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
 	
 	
 	private void addFavoriteSellers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
    	System.out.println("I'm in addFavoriteSellers method!");

		String userEmail = user.getEmail();
		//String favUserEmail = favuser.getFavUserEmail();
		String favUserEmail =request.getParameter("favUserEmail");
		System.out.print("button value " +favUserEmail);
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			Statement st=connect.createStatement();

			//response.sendRedirect("StorePage.jsp?name1=value1");
        	System.out.println("\nI'm in addFavoriteSellers method try block");
        	
        	int i=st.executeUpdate("insert into favUsers(userEmail,favUserEmail)"
    				+ "values('" + userEmail + "','" + favUserEmail + "')");
 
		
	        request.getRequestDispatcher("favoriteSellers.jsp").forward(request, response);
		}

		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}

	}
	
	
 	private void favoriteSellers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
 		String userEmail = request.getParameter("userEmail");
 		String favUserEmail = request.getParameter("favUserEmail");
 		//String category = request.getParameter("userID");
    	System.out.println("I'm in addFavoriteSellers method!");

		try
		{
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			
	 		System.out.println("I'M IN THE FAVORITE SELLERS TRY BLOCK!");

			//response.sendRedirect("StorePage.jsp?name1=value1");
			
			String favUserQuery = "SELECT * FROM favUsers WHERE userEmail ='" +userEmail+"'";
			//String favUserQuery = "SELECT * FROM favUsers WHERE userEmail LIKE'%" + userEmail + "%'";
			//String searchQuery = "SELECT * FROM items WHERE categoryName LIKE'%" + category + "%'";    
			preparedStatement = (PreparedStatement)connect.prepareStatement(favUserQuery);	
			ResultSet resultSet = preparedStatement.executeQuery();

			java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			List<FavUser> favUserRows = new ArrayList<FavUser>();
			while (resultSet.next())
			{
				FavUser favUserNew = new FavUser();

				favUserNew.setUserEmail(resultSet.getString("userEmail"));
				favUserNew.setFavUserEmail(resultSet.getString("favUserEmail"));
				
				favUserRows.add(favUserNew);
			}

			request.setAttribute("favUserRows", favUserRows); // Will be available as ${columnValues} in JSP
	        request.getRequestDispatcher("favoriteSellers.jsp").forward(request, response);
		}

		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}

	}
 	
 	private void deleteFavoriteSeller(HttpServletRequest request, HttpServletResponse response) {
 		System.out.println("I'm in deleteFavoriteSellers method!");

 		//String userEmail = user.getEmail();
		//String favUserEmail =request.getParameter("userEmail");
		String favUserEmail =request.getParameter("favUserEmail");
		System.out.print("button value " +favUserEmail);

		try
		{
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			Statement st=connect.createStatement();

        	System.out.println("\nI'm in deleteFavoriteSellers method try block");
        	
    		/*String sql = "DELETE FROM favUsers WHERE favUserEmail='" + favUserEmail + "'";

        	PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, favUserEmail);	//user adding to the table, obtain from session info
			ps.execute();
			ps.close();
			System.out.println("User deleted from favorites..."+ps);
			*/
        	
			int deleteFavSeller=st.executeUpdate("DELETE favUsers WHERE favUserEmail='" + favUserEmail + "");
 
        	if(deleteFavSeller > 0)
        		 System.out.println("Deleted successfully");
        		else
        			System.out.println("Nothing deleted");
        			
		
	        request.getRequestDispatcher("favoriteSellers.jsp").forward(request, response);
		}

		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}
 		
	}
 	
 	private void addFavoriteItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
    	System.out.println("I'm in addFavoriteItems method!");

		String currentUserEmail = user.getEmail();
		String favItemUserEmail = request.getParameter("favItemUserEmail");
 		String title = request.getParameter("title");
 		
		try
		{
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			Statement st=connect.createStatement();

			//response.sendRedirect("StorePage.jsp?name1=value1");
        	System.out.println("\nI'm in addFavoriteItems method try block");

        	int i=st.executeUpdate("insert into favItems(userEmail,favItemUserEmail,title)"
    				+ "values('" + currentUserEmail + "','" + favItemUserEmail  + "','" + title + "')");
    		
        	
	        request.getRequestDispatcher("favoriteItems.jsp").forward(request, response);
		}

		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}

	}
 	
 	private void favoriteItems(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
 		
    	System.out.println("I'm in FavoriteItems method!");
    	
    	
    	String currentUserEmail = user.getEmail();
		String favItemUserEmail = request.getParameter("userEmail");
 		String title = request.getParameter("title");
 		
 		try
		{
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			
	 		System.out.println("I'M IN THE FAVORITE ITEMS TRY BLOCK!");

			//response.sendRedirect("StorePage.jsp?name1=value1");
			
			String favItemQuery = "SELECT * FROM favItems WHERE userEmail ='" +currentUserEmail+"'";
			//String favUserQuery = "SELECT * FROM favUsers WHERE userEmail LIKE'%" + userEmail + "%'";
			//String searchQuery = "SELECT * FROM items WHERE categoryName LIKE'%" + category + "%'";    
			preparedStatement = (PreparedStatement)connect.prepareStatement(favItemQuery);	
			ResultSet resultSet = preparedStatement.executeQuery();

			java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();

			List<FavItem> favItemRows = new ArrayList<FavItem>();
			while (resultSet.next())
			{
				FavItem favItemNew = new FavItem();

				favItemNew.setCurrentUserEmail(resultSet.getString("userEmail"));
				favItemNew.setFavItemUserEmail(resultSet.getString("favUserEmail"));
				favItemNew.setTitle(resultSet.getString("title"));

				favItemRows.add(favItemNew);
			}

			request.setAttribute("favItemRows", favItemRows); // Will be available as ${columnValues} in JSP
	        request.getRequestDispatcher("favoriteItems.jsp").forward(request, response);
		}

		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}

	}
 	
 	private void deleteFavoriteItem(HttpServletRequest request, HttpServletResponse response) {
 		System.out.println("I'm in deleteFavoriteItem method!");

 		//String userEmail = user.getEmail();
		String favUserEmail =request.getParameter("userEmail");
		String title = request.getParameter("title");

		try
		{
			Class.forName(JDBC_DRIVER);
			Connection connect = DriverManager.getConnection(PROJDB_URL, USER, PASS);
			Statement st=connect.createStatement();

        	System.out.println("\nI'm in deleteFavoriteItems method try block");
        	
    		/*String sql = "DELETE FROM favUsers WHERE favUserEmail='" + favUserEmail + "'";

        	PreparedStatement ps = connect.prepareStatement(sql);
			ps.setString(1, favUserEmail);	//user adding to the table, obtain from session info
			ps.execute();
			ps.close();
			System.out.println("User deleted from favorites..."+ps);
			*/
        	
			int deleteFavItem=st.executeUpdate("DELETE FROM favUsers WHERE favUserEmail='" + favUserEmail + "'AND title='" +title + "'");
 
        	if(deleteFavItem > 0)
        		 System.out.println("Deleted successfully" +st);
        		else
        			System.out.println("Nothing deleted"+ st);
        			
		
	        request.getRequestDispatcher("favoriteItems.jsp").forward(request, response);
		}

		catch(Exception e)
		{
			System.out.print(e);
			e.printStackTrace();
		}
 		
	}
	
	
}