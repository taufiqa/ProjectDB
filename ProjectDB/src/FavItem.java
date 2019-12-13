
public class FavItem
{	
	protected String currentUserEmail;
	protected String favItemUserEmail;
	protected String title;
	
	public FavItem() {
	}

	public FavItem(String currentUserEmail, String favItemUserEmail, String title) {
		super();
		this.currentUserEmail = currentUserEmail;
		this.favItemUserEmail = favItemUserEmail;
		this.title = title;
	}

	public String getCurrentUserEmail() {
		return currentUserEmail;
	}

	public void setCurrentUserEmail(String currentUserEmail) {
		this.currentUserEmail = currentUserEmail;
	}

	public String getFavItemUserEmail() {
		return favItemUserEmail;
	}

	public void setFavItemUserEmail(String favItemUserEmail) {
		this.favItemUserEmail = favItemUserEmail;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}