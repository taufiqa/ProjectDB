
public class Reviews
{	
	protected int reviewId;
	protected String password;
	protected String email;

	public Reviews() 
	{
	}
	
	public Reviews(int reviewId, String password,String email)
	{
		this.password = password;
		this.email = email;
	}
	
	public int getReviewId()
	{
		return reviewId;
	}
	
	public void setReviewId(int reviewId)
	{
		this.reviewId = reviewId;
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