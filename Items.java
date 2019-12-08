import java.math.BigDecimal;
//import java.util.Date;

public class Items
{	
	protected int itemID;
	protected String userEmail;
	protected String title;
	protected String itemDescription;
	protected String postDate;
	protected BigDecimal price;
	protected String categoryName;

	public Items() 
	{
	}
	
	public Items(String userEmail, String title, String itemDescription, String postDate,
			BigDecimal price, String categoryName)
	{
		this.userEmail = userEmail;
		this.title = title;
		this.itemDescription = itemDescription;
		this.postDate = postDate;
		this.price = price;
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
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}