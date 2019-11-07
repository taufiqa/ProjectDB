
public class Categories
{	
	protected int categoriesId;
	protected String categoriesName;
	protected String password;
	protected String email;

	public Categories() 
	{
	}
	
	public Categories(int categoriesId, String categoriesName, String password,String email)
	{
		this.categoriesName = categoriesName;
		this.password = password;
		this.email = email;
	}
	
	public int getCategoriesId()
	{
		return categoriesId;
	}
	
	public void setCategoriesId(int categoriesId)
	{
		this.categoriesId = categoriesId;
	}
	
	public String getCategoriesName()
	{
		return categoriesName;
	}
	
	public void setCategoriesName(String categoriesName)
	{
		this.categoriesName = categoriesName;
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