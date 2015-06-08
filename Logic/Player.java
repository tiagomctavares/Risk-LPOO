package Logic;

import java.awt.Color;

public class Player {
	private int id;
	private String name;
	private Color color;
	private int deployNumberRemaining;
	
	public Player(int id, String name, Color color) {
		this.id = id;
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
