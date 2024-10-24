package components;

import javax.swing.*;
import java.awt.*;

public class TransparentPanel extends JPanel 
{
	private Color color;
	private float opacity;

    public TransparentPanel(Color color, float opacity) 
    {
    	this.color = color;
    	this.opacity = opacity;
    	
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.setColor(color);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}

