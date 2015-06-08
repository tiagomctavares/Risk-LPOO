package logic;

import java.awt.Color;
/**
 * Represents a Player
 * 
 * @author Tiago Tavares
 * 
 */
public class Player {
	private String name;
	private Color color;
	private int deployNumberRemaining;
	
	public Player(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return name;
	}
	
	public int getDeployNumberRemaining() {
		return this.deployNumberRemaining;
	}
	
	public void setDeployNumberRemaining(int reinforcement) {
		this.deployNumberRemaining = reinforcement;
		
	}

	public void deployedTroops(int deployNumber) {
		this.deployNumberRemaining-=deployNumber;
		
	}
}
