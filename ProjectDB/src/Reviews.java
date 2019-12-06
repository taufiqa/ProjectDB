import java.util.Date;

public class Reviews
{	
	protected int reviewID; 
	protected String score;
	protected String remark;
	protected int itemID;
	protected int userID;
	protected Date reviewDate;

	public Reviews() 
	{
	}
	
	public Reviews(String score, String remark, int itemID, int userID, Date reviewDate)
	{
		this.score = score;
		this.remark = remark;
		this.itemID = itemID;
		this.userID = userID;
		this.reviewDate = reviewDate;
	}
	
	public Reviews(int reviewID, String score, String remark, int itemID, int userID, Date reviewDate)
	{
		this.reviewID = reviewID;
		this.score = score;
		this.remark = remark;
		this.itemID = itemID;
		this.userID = userID;
		this.reviewDate = reviewDate;
	}
	
	public int getReviewID()
	{
		return reviewID;
	}
	
	public void setReviewID(int reviewID)
	{
		this.reviewID = reviewID;
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
	
	public Date getReviewDate()
	{
		return reviewDate;
	}
	
	public void setReviewDate(Date reviewDate)
	{
		this.reviewDate = reviewDate;
	}
}