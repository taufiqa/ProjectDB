
public class Reviews
{	
	protected String score;
	protected String remark;
	protected int itemID;
	protected int userID;
	//protected Date reviewDate;

	public Reviews() 
	{
	}
	
	public Reviews(int reviewID, String score, String remark, int itemID, int userID)
	{
		this.score = score;
		this.remark = remark;
		this.itemID = itemID;
		this.userID = userID;
	}
	
	public int getReviewID()
	{
		return userID;
	}
	
	public void setReviewID(int reviewID)
	{
		this.userID = reviewID;
	}
	
	public String getScore()
	{
		return score;
	}
	
	public void setScore(String score)
	{
		this.score = score;
	}
	
	public String getRemark()
	{
		return remark;
	}
	
	public void setRemark(String remark)
	{
		this.remark = remark;
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
}