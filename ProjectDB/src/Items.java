import java.math.BigDecimal;
import java.util.Date;

public class Items
{	
	protected int itemID;
	protected int userID;
	protected String title;
	protected String itemDescription;
	protected String postDate;
	protected double price;
	protected String categoryName;

	public Items() 
	{
	}
	
	public Items(int itemID, int userID, String title, String itemDescription, double price, String postDate, String categoryName)
	{
		this.userID = userID;
		this.title = title;
		this.itemDescription = itemDescription;
		this.price = price;
		this.postDate = postDate;
		this.categoryName = categoryName;

	}
	
	public int getItemID()
	{
		return itemID;
	}
	
	public void setItemID(int itemID)
	{
		this.itemID = itemID;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public void setUserID(int userID)
	{
		this.userID = userID;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getItemDescription()
	{
		return itemDescription;
	}
	
	public void setItemDescription(String itemDescription)
	{
		this.itemDescription = itemDescription;
	}
	
	public String getDate()
	{
		return postDate;
	}
	
	public void setDate(String postDate)
	{
		this.postDate = postDate;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String getcategoryName()
	{
		return categoryName;
	}
	
	public void setIcategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}
}