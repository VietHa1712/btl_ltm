package controllers;

import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import models.Invitation;
import models.User;

public class HomeController {
	
	public final int ALL_FILTER = 0;
	public final int ONLINE_FILTER = 1;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea chatTextArea;

    @FXML
    private TextField chatTextField;

    @FXML
    private Button clientHistoryButton;

    @FXML
    private Label clientInfoLabel;

    @FXML
    private Button clientLogoutButton;

    @FXML
    private Button findMatchButton;

    @FXML
    private VBox invitationsContent;

    @FXML
    private Label playModeLabel;

    @FXML
    private VBox playerListContent;

    @FXML
    private Button practiceButton;

    @FXML
    private Button searchButton;

    @FXML
    private ComboBox<String> searchFilterComboBox;

    @FXML
    private TextField searchTextField;

    @FXML
    private Button sendButton;
    
    private User client;
    private ArrayList<Invitation> invitationList;
    private ArrayList<User> playerList;
    private String chatServerContent;
    
    @FXML
    private void initialize()
    {
    	searchFilterComboBox.getItems().addAll("All", "Online");
    	searchFilterComboBox.setValue(searchFilterComboBox.getItems().get(0));
    	cancelButton.setVisible(false);
    	
    	searchFilterComboBox.setOnAction(event -> {
            updatePlayerList();
        });
    	
    	searchTextField.setOnAction(event -> {
            updatePlayerList();
        });
    	
    	searchButton.setOnAction(event -> {
            updatePlayerList();
        });
    	
    	clientHistoryButton.setOnAction(event -> {
    		clientHistoryButtonClicked();
    	});
    	
    	clientLogoutButton.setOnAction(event -> {
    		clientLogoutButtonClicked();
    	});
    	
    	sendButton.setOnAction(event -> {
    		sendButtonClicked();
    	});
    	
    	chatTextField.setOnAction(event -> {
    		sendButtonClicked();
    	});
    	
    	findMatchButton.setOnAction(event -> {
    		findMatchButtonClicked();
    	});
    	
    	practiceButton.setOnAction(event -> {
    		practiceButtonClicked();
    	});
    	
    	cancelButton.setOnAction(event -> {
    		cancelButtonClicked();
    	});
    }
    
    public void loadData(int clientId, ArrayList<Invitation> invitationList,
    		ArrayList<User> playerList, String chatServerContent)
    {
    	this.invitationList = invitationList;
    	this.playerList = playerList;
    	this.chatServerContent = chatServerContent;
    	setClient(clientId);
    	updatePlayerList();
    	updateInvitationList();
    	updateChatServerContent();
    }
    
    private void setClient(int id)
    {
    	for(User u : playerList)
    	{
    		if(id == u.getID())
    		{
    			this.client = u;
    		}
    	}
    	clientInfoLabel.setText(String.format("Player: %s    Rank: #%03d    Stars: %d", 
    			client.getUsername(), client.getRank(), client.getStar()));
    }
    
    public void updatePlayerList()
    {
    	String searchText = searchTextField.getText();
    	int filter = searchFilterComboBox.getSelectionModel().getSelectedIndex();
    	playerListContent.getChildren().clear();
    	for(int i = 0; i < playerList.size(); i++)
		{
			User u = playerList.get(i);
			if(u.getUsername().contains(searchText) && checkFilter(filter, u.isOnline()))
			{				
				insertUserItem(u);
			}
		}
    }
    
    private boolean checkFilter(int filter, boolean isOnline)
	{
		if(filter == ALL_FILTER || filter == ONLINE_FILTER && isOnline)
		{
			return true;
		}
		return false;
	}
    
    public void updateInvitationList()
    {
    	invitationsContent.getChildren().clear();
    	for(Invitation inv : invitationList)
    	{
    		insertInvitationItem(inv);
    	}
    }
    
    public void updateChatServerContent()
    {
    	chatTextArea.setText(chatServerContent);
    }
    
    private void insertUserItem(User user)
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserItem.fxml"));
            GridPane item = loader.load();
            UserItemController controller = loader.getController();
            controller.init(user);
            if(user.getID() == client.getID())
            {
            	controller.hideInviteButton();
            }
            playerListContent.getChildren().add(item);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void insertInvitationItem(Invitation invitation)
    {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/InvitationItem.fxml"));
    		GridPane item = loader.load();
    		InvitationItemController controller = loader.getController();
    		controller.init(invitation);
    		
            invitationsContent.getChildren().add(item);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void clientLogoutButtonClicked()
    {
    	System.out.println("Log out");
    }
    
    private void clientHistoryButtonClicked()
    {
    	System.out.println("Show client history");
    }
    
    private void sendButtonClicked()
    {
    	String chatMessage = chatTextField.getText();
    	chatTextField.setText("");
    	System.out.println("Send chat: " + chatMessage);
    }
    
    private void findMatchButtonClicked()
    {
    	playModeLabel.setText("Finding match...");
    	cancelButton.setVisible(true);
    	findMatchButton.setDisable(true);
    	practiceButton.setDisable(true);
    }
    
    private void practiceButtonClicked()
    {
    	playModeLabel.setText("Start practicing...");
    	cancelButton.setVisible(true);
    	findMatchButton.setDisable(true);
    	practiceButton.setDisable(true);
    }
    
    private void cancelButtonClicked()
    {
    	playModeLabel.setText("Choose a play mode");
    	cancelButton.setVisible(false);
    	findMatchButton.setDisable(false);
    	practiceButton.setDisable(false);
    }

}
