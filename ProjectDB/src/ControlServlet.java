import java.io.IOException;
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
	private UserDAO userDAO;
	private CategoriesDAO categoriesDAO;
	private ItemsDAO itemsDAO;
	private ReviewsDAO reviewsDAO;

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
		categoriesDAO.createDatabase();
		itemsDAO.createDatabase();
		reviewsDAO.createDatabase();

		userDAO.seedDatabase();
		categoriesDAO.seedDatabase();
		itemsDAO.seedDatabase();
		reviewsDAO.seedDatabase();
		
		response.sendRedirect("Login.jsp");
	}
	
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException
	{
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		
		if(userDAO.isUserValid(username,password))
			response.sendRedirect("StorePage.jsp");
		else
			response.sendRedirect("Login.jsp");
	}
}