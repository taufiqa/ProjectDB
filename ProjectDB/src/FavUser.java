
public class FavUser
{	
	protected int userID;
	protected int favUserID;
	protected String userEmail;
	protected String favUserEmail;
	
	public FavUser() {
	}
	
	public FavUser(int userID, int favUserID, String userEmail, String favUserEmail) {
		super();
		this.userID = userID;
		this.favUserID = favUserID;
		this.userEmail = userEmail;
		this.favUserEmail = favUserEmail;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getFavUserID() {
		return favUserID;
	}
	public void setFavUserID(int favUserID) {
		this.favUserID = favUserID;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFavUserEmail() {
		return favUserEmail;
	}
	public void setFavUserEmail(String favUserEmail) {
		this.favUserEmail = favUserEmail;
	}

	
}