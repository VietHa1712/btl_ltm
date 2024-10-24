package models;

public class Invitation {
	
	private User sender;
	
	public Invitation(User sender)
	{
		this.sender = sender;
	}
	
	public String getSenderName()
	{
		return sender.getUsername();
	}
	
	public int getSenderStars()
	{
		return sender.getStars();
	}
	
	public int getSenderRank()
	{
		return sender.getRank();
	}
	
	public boolean isAvailable()
	{
		return sender.getIsOnline();
	}
}
