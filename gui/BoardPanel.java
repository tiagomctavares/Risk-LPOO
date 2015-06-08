package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import logic.Continent;
import logic.Game;
import logic.Player;
import logic.Region;

public class BoardPanel extends JPanel {
	private static final long serialVersionUID = -8592548139766967002L;
	private BoardImages images;
	private Game game;
	
	public BoardPanel(Game game) {
		this.game = game;
		repaint();
		images = new BoardImages();
		this.setPreferredSize(new Dimension(750,520));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

	private void drawBoard(Graphics g) {
		g.drawImage(images.getBoard(), 0, 0, null);
		displayRegions(g);
		displayPlayers(g);
	}

	private void displayRegions(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 9)); 
		for (Continent continent : game.getBoard()) {
			for(Region region : continent.getRegions()) {
				g.setColor(Color.BLACK);
				g.drawString(region.getName(), region.getX(), region.getY());
				
				g.setColor(region.getOwner().getColor());
				g.fillOval(region.getTroopX(), region.getTroopY(), 20, 20);
				
				g.setColor(Color.WHITE);
				g.drawString(region.getTroops().toString(), region.getTroopX()+5, region.getTroopY()+13);
			}
		}		
	}

	private void displayPlayers(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 9)); 
		int i = 0;
		for( Player p1 : game.getPlayers()) {
			g.setColor(p1.getColor());
			g.drawString(p1.getName(), 10, 250+(i*10));
			i++;
		}
	}
}
