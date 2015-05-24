package Logic;

import java.util.ArrayList;

import Server.Listener;


public class Game {
	private MapGenerator map;
	private Listener server;
	private ArrayList<Player> players;
	
	public Game() {
		players = new ArrayList<Player>();
		server = new Listener();
		initGame();
	}
	
	public void initGame() {
		this.map = new MapGenerator();
		players = server.getPlayers();
		
		assignRegions();
	}

	private void assignRegions() {
		map.scrumbleRegions(); 
		int j = 0;
		
		for(int i = 0; i < map.getRegions().size(); i++) {
			map.getRegions().get(i).setOwner(players.get(j));
			map.getRegions().get(i).setTroops(3);
			
			j++;
			if(j == players.size())
				j = 0;
		}
	}

	public ArrayList<Continent> getBoard() {
		return map.getContinents();
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
}
