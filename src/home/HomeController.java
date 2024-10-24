package home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Timer;

import models.Invitation;
import models.User;

public class HomeController {
	
	private ArrayList<User> userList;
	private ArrayList<Invitation> invitationList;
	private HomeView homeView;
	private User client;
	
	public HomeController(int clientId, ArrayList<User> userList, ArrayList<Invitation> invitationList, HomeView homeView)
	{
		this.userList = userList;
		this.homeView = homeView;
		this.invitationList = invitationList;
		
		for(User u : userList)
		{
			if(u.getId() == clientId)
			{
				this.client = u;
				break;
			}
		}
		
		this.homeView.setClientInfo(client);
		this.homeView.addHistoryButtonListener(new HistoryButtonClick());
		this.homeView.addLogoutButtonListener(new LogOutButtonClick());
		this.homeView.addFindMatchButtonListener(new QuickMatchButtonClick());
		this.homeView.addPracticeButtonListener(new PracticeButtonClick());
		this.homeView.addCancelFindingButtonListener(new CancelFindingButtonClick());
		this.homeView.addSearchFilterListener(new SearchButtonClick());
		this.homeView.addSearchFieldListener(new SearchButtonClick());
		this.homeView.addSearchButtonListener(new SearchButtonClick());
		
		updatePlayerList("", HomeView.ALL_FILTER);
		updateInvitationList();
	}
	
	private boolean checkFilter(int filter, boolean isOnline)
	{
		if(filter == HomeView.ALL_FILTER || filter == HomeView.ONLINE_FILTER && isOnline)
		{
			return true;
		}
		return false;
	}
	
	public void updatePlayerList(String searchText, int filter)
	{
		homeView.removeAllPlayerRows();
		for(int i = 0; i < userList.size(); i++)
		{
			User u = userList.get(i);
			String combinedName = String.format("#%03d", u.getRank()) + u.getUsername();
			if(combinedName.contains(searchText) && checkFilter(filter, u.getIsOnline()))
			{				
				homeView.insertPlayerRow(u);
			}
		}
		homeView.reloadPlayerListUI();
	}
	
	public void updateInvitationList()
	{
		homeView.removeAllInvitations();
		for(Invitation invitation : invitationList)
		{
			homeView.insertInvitationRow(invitation);
		}
		homeView.reloadInvitationListUI();
	}
	
	class HistoryButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Show history");
		}
    }
	
	class LogOutButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Log out");
		}
		
	}
	
	class QuickMatchButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			homeView.setPlayButtonsLabelText("Finding match...");
			homeView.setFindMatchButtonEnabled(false);
			homeView.setPracticeButtonEnabled(false);
			homeView.setCancelFidningButtonVisible(true);
		}
		
	}
	
	class PracticeButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			homeView.setPlayButtonsLabelText("Starting practicing");
			homeView.setFindMatchButtonEnabled(false);
			homeView.setPracticeButtonEnabled(false);
			homeView.setCancelFidningButtonVisible(true);
		}
		
	}
	
	class CancelFindingButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			homeView.setPlayButtonsLabelText("Choose a play mode");
			homeView.setFindMatchButtonEnabled(true);
			homeView.setPracticeButtonEnabled(true);
			homeView.setCancelFidningButtonVisible(false);
		}
		
	}
	
	class SearchButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int searchFilterIndex = homeView.getSearchFilterIndex();
			String searchFieldText = homeView.getSearchFieldText();
			updatePlayerList(searchFieldText, searchFilterIndex);
		}
		
	}
}
