
public class Items
{	
	protected int itemId;
	protected String itemName;
	protected String password;
	protected String email;

	public Items() 
	{
	}
	
	public Items(int itemId, String itemName, String password,String email)
	{
		this.itemName = itemName;
		this.password = password;
		this.email = email;
	}
	
	public int getItemId()
	{
		return itemId;
	}
	
	public void setItemId(int itemId)
	{
		this.itemId = itemId;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	
	public String getEmail()
	{
		return email;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
}