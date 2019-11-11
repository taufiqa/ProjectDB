
public class Categories
{	
	protected int categoryID;
	protected String categoryName;
	protected int itemID;

	public Categories() 
	{
	}
	
	public Categories(int categoryID, String categoryName, int itemID)
	{
		this.categoryName = categoryName;
		this.itemID = itemID;
	}
	
	public int getCategoryID()
	{
		return categoryID;
	}
	
	public void setCategoryID(int categoryID)
	{
		this.categoryID = categoryID;
	}
	
	public String getCategoryName()
	{
		return categoryName;
	}
	
	public void setCategoryName(String categoryName)
	{
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
}