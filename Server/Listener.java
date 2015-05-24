package Server;
import java.awt.Color;
import java.util.ArrayList;

import Logic.Player;

public class Listener {
	ArrayList<Player> players;
	
	public Listener() {
		players = new ArrayList<Player>();
		players.add(new Player(1, "Tiago", Color.BLUE));
		players.add(new Player(2, "Botas", Color.RED));
		players.add(new Player(2, "Carvalhinho", Color.GREEN));
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
