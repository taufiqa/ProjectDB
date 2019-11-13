import java.math.BigDecimal;
//import java.util.Date;

public class Items
{	
	protected int itemID;
	protected String title;
	protected String itemDescription;
	//protected Date postDate;
	protected BigDecimal price;

	public Items() 
	{
	}
	
	public Items(int itemID, String title, String itemDescription,BigDecimal price)
	{
		this.title = title;
		this.itemDescription = itemDescription;
		this.price = price;
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
}