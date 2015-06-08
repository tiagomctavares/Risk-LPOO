package logic;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Initializes players on the game and their colors
 * 
 * @author Tiago Tavares
 * 
 */
public class PlayerInit {
	ArrayList<Player> players;
	
	public PlayerInit() {
		players = new ArrayList<Player>();
		players.add(new Player("Tiago", Color.BLUE));
		players.add(new Player("João", Color.RED));
		players.add(new Player("Carlos", Color.GREEN));
		players.add(new Player("Figueira", Color.ORANGE));
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
