package application;
	
import java.util.ArrayList;

import controllers.HomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.Invitation;
import models.User;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class HomeRun extends Application {
	@Override
    public void start(Stage primaryStage) throws Exception {
        // Load file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));
        Parent root = loader.load();
        HomeController homeController = loader.getController();
        ArrayList<Invitation> invitationList = new ArrayList<>();
        ArrayList<User> playerList = new ArrayList<>();
        for(int i = 0; i < 12; i++)
    	{
    		boolean isOnline = false;
    		if(i%2 == 0) isOnline = true;
    		User u = new User(i, "player" + i, i*10, isOnline);
    		u.setRank(i);
    		Invitation inv = new Invitation(u);
    		playerList.add(u);
    		invitationList.add(inv);
    	}
        
        homeController.loadData(2, invitationList, playerList, "");
        
        // Thiết lập scene và hiển thị form
        Scene scene = new Scene(root, 1200, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Memory Game");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
