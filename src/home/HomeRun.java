package home;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import models.Invitation;
import models.User;

public class HomeRun {
	public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Random r = new Random();
				ArrayList<User> userList = new ArrayList<User>();
				ArrayList<Invitation> invitationList = new ArrayList<Invitation>();
				
				for(int i = 0; i < 5; i++)
				{
					User u = new User(i, "player" + i, r.nextInt(1000), r.nextBoolean());
					userList.add(u);
					if(u.getIsOnline())
					{
						invitationList.add(new Invitation(u));
					}
				}
				
				Collections.sort(userList, new Comparator<User>() {
		            public int compare(User u1, User u2) {
		                return Integer.compare(u2.getStars(), u1.getStars());
		            }
		        });
				
				for(int i = 0; i < userList.size(); i++)
				{
					User u = userList.get(i);
					u.setRank(i+1);
				}
				
			    HomeView homeView = new HomeView();
			    homeView.setVisible(true);
			    int clientId = 3;
			    HomeController homeController = new HomeController(clientId, userList, invitationList, homeView);
			}
		});
    }
}
