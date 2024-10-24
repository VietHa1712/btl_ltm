package models;

public class User {
	
	private int id;
	private String username;
	private int stars;
	private int rank;
	private boolean isOnline;
	
	public User(int id, String username, int stars, boolean isOnline)
	{
		this.id = id;
		this.username = username;
		this.stars = stars;
		this.isOnline = isOnline;
		this.rank = 0;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getUsername()
	{
		return username; 
	}
	
	public int getStars()
	{
		return stars;
	}
	
	public void setRank(int value)
	{
		rank = value;
	}
	
	public int getRank()
	{
		return rank;
	}
	
	public boolean getIsOnline()
	{
		return isOnline;
	}
}
