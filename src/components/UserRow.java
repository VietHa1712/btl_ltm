package components;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import models.User;

public class UserRow extends JPanel
{
	private User user;
	
	private JButton inviteButton;
	private JButton historyButton;
	
	public UserRow(User user)
	{
		this.user = user;
        setLayout(new BorderLayout());
        setBackground(new Color(250, 250, 250));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        JPanel playerPanel = new JPanel();
        playerPanel.setLayout(new BorderLayout());
        playerPanel.setBackground(new Color(250, 250, 250));
        playerPanel.setBorder(new EmptyBorder(0, 10, 0, 20));

        JLabel playerRankLabel = new JLabel(String.format("#%03d", user.getRank()));
        playerRankLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playerRankLabel.setForeground(new Color(0, 140, 255));
        playerPanel.add(playerRankLabel, BorderLayout.WEST);
        
        JLabel playerNameLabel = new JLabel(user.getUsername());
        playerNameLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        Color statusColor = new Color(5, 166, 21);
        if(!user.getIsOnline())
        {
        	statusColor = new Color(190, 190, 190);
        }
        playerNameLabel.setForeground(statusColor);
        playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerNameLabel.setBorder(new EmptyBorder(0, 10, 0, 10));
        playerPanel.add(playerNameLabel, BorderLayout.CENTER);
        
        JLabel playerStarsLabel = new JLabel("" + user.getStars());
        playerStarsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        playerStarsLabel.setHorizontalAlignment(JLabel.RIGHT);
        playerStarsLabel.setForeground(new Color(255, 90, 255));
        playerStarsLabel.setPreferredSize(new Dimension(50, 50));
        playerPanel.add(playerStarsLabel, BorderLayout.EAST);

        JPanel availableButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        availableButtonPanel.setBackground(new Color(250, 250, 250));
        
        inviteButton = new JButton("Invite");
        inviteButton.setBackground(new Color(208, 80, 0));
        inviteButton.setForeground(Color.white);
        inviteButton.addActionListener(new InviteButtonClick());
        if(!user.getIsOnline())
        {
        	inviteButton.setEnabled(false);
        }

        historyButton = new JButton("History");
        historyButton.setBackground(new Color(194, 24, 91));
        historyButton.setForeground(Color.white);
        historyButton.addActionListener(new HistoryButtonClick());

        availableButtonPanel.add(inviteButton);
        availableButtonPanel.add(historyButton);

        add(playerPanel, BorderLayout.CENTER);
        add(availableButtonPanel, BorderLayout.EAST);

	}
	
	class HistoryButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Show history of player " + user.getUsername());
		}
    }
	
	class InviteButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Invite " + user.getUsername());
		}
	}
}
