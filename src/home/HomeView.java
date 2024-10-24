package home;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import components.BackgroundPanel;
import components.InvitationRow;
import components.UserRow;
import models.Invitation;
import models.User;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomeView extends JFrame {
	
    public static int ALL_FILTER = 0;
    public static int ONLINE_FILTER = 1;
    
    private JLabel playerInfo;
    private JButton historyButton;
    private JButton logOutButton;
    
    private JLabel playButtonsLabel;
    private JButton cancelFindingButton;
    private JButton findMatchButton;
    private JButton practiceButton;
    
    private JComboBox<String> filterComboBox;
    private JTextField playerListSearchField;
    private JButton playerListSearchButton;
    
    private JPanel playerListContent;
    private JPanel invitationListContent;
    
    public HomeView() {
        setTitle("Game - Card Matching");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
	    setMinimumSize(new Dimension(1000, 600));
	    setLocationRelativeTo(null);

        //Set icon
        try {
            Image icon = ImageIO.read(new File("./resources/icon.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(200, 200, 200));

        // Top Menu Panel
        JPanel topMenu = new JPanel();
        topMenu.setLayout(new BorderLayout(10, 10));
        topMenu.setBackground(new Color(5, 166, 21));

        JPanel playerInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        playerInfoPanel.setBackground(new Color(5, 166, 21));
        playerInfo = new JLabel("Player: nguyenvietha    Rank: #003    Stars: 100");
        playerInfo.setBorder(new EmptyBorder(5, 10, 0, 0));
        playerInfo.setForeground(Color.WHITE);
        playerInfo.setFont(new Font("Arial", Font.BOLD, 16));
        playerInfoPanel.add(playerInfo, BorderLayout.CENTER);

        JPanel historyLogoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        historyLogoutPanel.setBackground(new Color(5, 166, 21));
        historyButton = new JButton("History");
        logOutButton = new JButton("Log out");
        logOutButton.setBackground(new Color(2, 69, 0));
        logOutButton.setForeground(Color.WHITE);
        historyButton.setBackground(new Color(194, 24, 91));
        historyButton.setForeground(Color.WHITE);
        historyLogoutPanel.add(historyButton);
        historyLogoutPanel.add(logOutButton);

        topMenu.add(playerInfoPanel, BorderLayout.WEST);
        topMenu.add(historyLogoutPanel, BorderLayout.EAST);

        JPanel playButtonsAndPlayerListPanel = new JPanel();
        playButtonsAndPlayerListPanel.setLayout(new BorderLayout(0, 20));
        playButtonsAndPlayerListPanel.setBackground(new Color(209, 209, 209));
        playButtonsAndPlayerListPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Play Buttons Panel
        JPanel playButtonsPanel = new BackgroundPanel("./resources/background.png");
        playButtonsPanel.setOpaque(false);
        playButtonsPanel.setLayout(new GridLayout(2, 2, 40, 20));
        playButtonsPanel.setBackground(new Color(250, 250, 250));
        playButtonsPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
//        Border playButtonBorder = BorderFactory.createLineBorder(
//        		new Color(150, 150, 150), 1);
//        playButtonsPanel.setBorder(new CompoundBorder(playButtonBorder,
//        		new EmptyBorder(20, 40, 20, 40)));

        playButtonsLabel = new JLabel("Choose a play mode");
        playButtonsLabel.setBorder(new EmptyBorder(0, 5, 0, 0));
        playButtonsLabel.setForeground(Color.white);
        playButtonsLabel.setFont(new Font("Arial", Font.ITALIC | Font.BOLD, 25));
        playButtonsLabel.setVerticalAlignment(JLabel.CENTER);
        playButtonsPanel.add(playButtonsLabel);
        
        cancelFindingButton = new JButton("Cancel");
        cancelFindingButton.setForeground(Color.white);
        cancelFindingButton.setBackground(new Color(190, 0, 0));
        cancelFindingButton.setFont(new Font("Arial", Font.BOLD, 20));
        cancelFindingButton.setVisible(false);
        playButtonsPanel.add(cancelFindingButton);
        
        
        findMatchButton = new JButton("Find Match");
        findMatchButton.setForeground(Color.white);
        findMatchButton.setBackground(new Color(208, 80, 0));
        findMatchButton.setFont(new Font("Arial", Font.BOLD, 20));
        findMatchButton.setBorder(null);
        playButtonsPanel.add(findMatchButton);
        
        practiceButton = new JButton("Practice");
        practiceButton.setForeground(Color.white);
        practiceButton.setBackground(new Color(0, 164, 197));
        practiceButton.setFont(new Font("Arial", Font.BOLD, 20));
        practiceButton.setBorder(null);
        playButtonsPanel.add(practiceButton);
        
        playButtonsAndPlayerListPanel.add(playButtonsPanel, BorderLayout.NORTH);
        
        // Player List Panel
        
        JPanel playerListPanel = new JPanel();
        playerListPanel.setLayout(new BorderLayout());
        playerListPanel.setBackground(new Color(0, 150, 15));
        Border playerListBorder = BorderFactory.createLineBorder(
        		new Color(0, 150, 15), 3);
        playerListPanel.setBorder(playerListBorder);
        
        JLabel rankingLabel = new JLabel("PLAYER LIST", SwingConstants.CENTER);
        rankingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        rankingLabel.setForeground(new Color(240, 240, 240));
        rankingLabel.setBorder(new EmptyBorder(10, 0, 10, 0));
        
        JPanel playerListSearchPanel = new JPanel();
        playerListSearchPanel.setLayout(new BorderLayout());
        playerListSearchPanel.setBackground(new Color(250, 250, 250));
        playerListSearchPanel.setBorder(new EmptyBorder(10, 5, 10, 5));
        
        playerListSearchField = new JTextField(30);
        playerListSearchField.setFont(new Font("Arial", Font.PLAIN, 20));
        
        playerListSearchButton = new JButton("Search");
        playerListSearchButton.setBackground(new Color(127, 0, 165));
        playerListSearchButton.setForeground(Color.white);
        
        String[] filterItems = {"All", "Online"};
        filterComboBox = new JComboBox<String>(filterItems);
        filterComboBox.setBackground(Color.white);
        playerListSearchPanel.add(filterComboBox, BorderLayout.WEST);
        playerListSearchPanel.add(playerListSearchButton, BorderLayout.EAST);
        playerListSearchPanel.add(playerListSearchField, BorderLayout.CENTER);
        
        JPanel playerListLabelAndSearchPanel = new JPanel();
        playerListLabelAndSearchPanel.setLayout(new BorderLayout());
        playerListLabelAndSearchPanel.setBackground(new Color(0, 150, 15));
        playerListLabelAndSearchPanel.add(rankingLabel, BorderLayout.NORTH);
        playerListLabelAndSearchPanel.add(playerListSearchPanel, BorderLayout.SOUTH);
        playerListPanel.add(playerListLabelAndSearchPanel, BorderLayout.NORTH);
        
        playerListContent = new JPanel();
        playerListContent.setBackground(new Color(235, 235, 235));
        playerListContent.setLayout(new BoxLayout(playerListContent, BoxLayout.Y_AXIS));

        JScrollPane playerListScroll = new JScrollPane(playerListContent);
        playerListScroll.setBorder(null);
        playerListPanel.add(playerListScroll, BorderLayout.CENTER);

        
        playButtonsAndPlayerListPanel.add(playerListPanel, BorderLayout.CENTER);

        // Invitations Panel
        JPanel invitationsPanel = new JPanel();
        invitationsPanel.setPreferredSize(new Dimension(300, 100));
        invitationsPanel.setLayout(new BorderLayout());
        invitationsPanel.setBackground(new Color(2, 69, 0));
        invitationsPanel.setBorder(new EmptyBorder(0, 20, 20, 10));
        
        invitationListContent = new JPanel();
        invitationListContent.setLayout(new BoxLayout(invitationListContent, BoxLayout.Y_AXIS));
        invitationListContent.setBackground(new Color(2, 69, 0));
        invitationListContent.setBorder(new EmptyBorder(0, 0, 0, 10));
        
        JLabel invitationsLabel = new JLabel("INVITATIONS");
        invitationsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        invitationsLabel.setForeground(Color.white);
        invitationsLabel.setHorizontalAlignment(JLabel.CENTER);
        invitationsLabel.setBorder(new EmptyBorder(15, 0, 20, 0));
        invitationsPanel.add(invitationsLabel, BorderLayout.NORTH);

        JScrollPane inviteScroll = new JScrollPane(invitationListContent);
        inviteScroll.setBorder(null);
        invitationsPanel.add(inviteScroll, BorderLayout.CENTER);

        // Add everything to mainPanel
        mainPanel.add(topMenu, BorderLayout.NORTH);
        mainPanel.add(playButtonsAndPlayerListPanel, BorderLayout.CENTER);
        mainPanel.add(invitationsPanel, BorderLayout.EAST);
        // Add mainPanel to JFrame
        add(mainPanel);
    }
    
    public void setClientInfo(User client)
    {
    	String content = String.format("Player: %s    Rank: #%03d    Stars: %d",
    			client.getUsername(), client.getRank(), client.getStars());
    	playerInfo.setText(content);
    }
    
    public void setPlayButtonsLabelText(String content)
    {
    	playButtonsLabel.setText(content);
    }
    
    public void setCancelFidningButtonVisible(boolean visible)
    {
    	cancelFindingButton.setVisible(visible);
    }
    
    public void setFindMatchButtonEnabled(boolean enabled)
    {
    	findMatchButton.setEnabled(enabled);
    }
    
    public void setPracticeButtonEnabled(boolean enabled)
    {
    	practiceButton.setEnabled(enabled);
    }
    
    public void setSearchButtonEnabled(boolean enabled)
    {
    	playerListSearchButton.setEnabled(enabled);
    }
    
    public String getSearchFieldText()
    {
    	return playerListSearchField.getText();
    }
    
    public int getSearchFilterIndex()
    {
    	return filterComboBox.getSelectedIndex();
    }
    
    public void addHistoryButtonListener(ActionListener listener)
    {
    	historyButton.addActionListener(listener);
    }
    
    public void addLogoutButtonListener(ActionListener listener)
    {
    	logOutButton.addActionListener(listener);
    }
    
    public void addFindMatchButtonListener(ActionListener listener)
    {
    	findMatchButton.addActionListener(listener);
    }
    
    public void addPracticeButtonListener(ActionListener listener)
    {
    	practiceButton.addActionListener(listener);
    }
    
    public void addCancelFindingButtonListener(ActionListener listener)
    {
    	cancelFindingButton.addActionListener(listener);
    }
    
    public void addSearchButtonListener(ActionListener listener)
    {
    	playerListSearchButton.addActionListener(listener);
    }
    
    public void addSearchFilterListener(ActionListener listener)
    {
    	filterComboBox.addActionListener(listener);
    }
    
    public void addSearchFieldListener(ActionListener listener)
    {
    	playerListSearchField.addActionListener(listener);
    }
    
    public void insertPlayerRow(User user)
    {
    	UserRow userRow = new UserRow(user);
        playerListContent.add(userRow);
        playerListContent.add(Box.createRigidArea(new Dimension(0, 3)));
    }
    
    public void removeAllPlayerRows()
    {
    	playerListContent.removeAll();
    }
    
    public void reloadPlayerListUI()
    {
    	playerListContent.revalidate();
    	playerListContent.repaint();
    }
    
    public void insertInvitationRow(Invitation invitation)
    {
    	InvitationRow invitationRow = new InvitationRow(invitation);
        invitationListContent.add(invitationRow);
        invitationListContent.add(Box.createRigidArea(new Dimension(0, 20)));
    }
    
    public void removeAllInvitations()
    {
    	invitationListContent.removeAll();
    }
    
    public void reloadInvitationListUI()
    {
    	invitationListContent.revalidate();
    	invitationListContent.repaint();
    }
}
