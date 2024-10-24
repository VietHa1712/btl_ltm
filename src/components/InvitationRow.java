package components;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Invitation;

public class InvitationRow extends JPanel
{	
	private Invitation invitation;
	
	private JButton acceptButton;
	private JButton declineButton;
	
	public InvitationRow(Invitation invitation)
	{
		this.invitation = invitation;
		setMaximumSize(new Dimension(400, 140));
        setLayout(new GridLayout(4, 2, 10, 0));
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setBackground(new Color(250, 250, 250));
        
        JLabel playerNameInvitationLabel = new JLabel("From:");
        playerNameInvitationLabel.setForeground(Color.black);
        playerNameInvitationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(playerNameInvitationLabel);
        
        JLabel playerNameInvitationContent = new JLabel(invitation.getSenderName());
        playerNameInvitationContent.setForeground(new Color(5, 166, 21));
        playerNameInvitationContent.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 18));
        add(playerNameInvitationContent);
        
        JLabel playerRankInvitationLabel = new JLabel("Rank:");
        playerRankInvitationLabel.setForeground(Color.black);
        playerRankInvitationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(playerRankInvitationLabel);
        
        JLabel playerRankInvitationContent = new JLabel(String.format("#%03d", invitation.getSenderRank()));
        playerRankInvitationContent.setForeground(new Color(0, 140, 255));
        playerRankInvitationContent.setFont(new Font("Arial", Font.BOLD, 18));
        add(playerRankInvitationContent);
        
        JLabel playerStarsInvitationLabel = new JLabel("Stars: ");
        playerStarsInvitationLabel.setForeground(Color.black);
        playerStarsInvitationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(playerStarsInvitationLabel);
        
        JLabel playerStarsInvitationContent = new JLabel("" + invitation.getSenderStars());
        playerStarsInvitationContent.setForeground(new Color(255, 90, 255));
        playerStarsInvitationContent.setFont(new Font("Arial", Font.BOLD, 18));
        add(playerStarsInvitationContent);
        
        acceptButton = new JButton("Accept");
        acceptButton.setForeground(Color.white);
        acceptButton.setBackground(new Color(5, 166, 21));
        acceptButton.addActionListener(new AcceptButtonClick());
        add(acceptButton);
        
        declineButton = new JButton("Decline");
        declineButton.setForeground(Color.white);
        declineButton.setBackground(new Color(190, 0, 0));
        declineButton.addActionListener(new DeclineButtonClick());
        add(declineButton);
	}
	
	class AcceptButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("Start match with " + invitation.getSenderName());
		}
    }
	
	class DeclineButtonClick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Container parent = InvitationRow.this.getParent();
	        if (parent != null) {
	            int index = parent.getComponentZOrder(InvitationRow.this); 
	            parent.remove(InvitationRow.this);
	            parent.remove(index);

	            parent.revalidate();
	            parent.repaint();
	        }
		}
    }
}
