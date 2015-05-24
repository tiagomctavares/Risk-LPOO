package cli;
import java.util.ArrayList;

import Logic.Continent;
import Logic.Game;
import Logic.Player;
import Logic.Region;

public class cli {
	private static Game game;
	private static ArrayList<Player> players;
	
	public static void main(String [] args) {
		System.out.println("Program Started");
		game = new Game();
		System.out.println("");
		System.out.println("Players Created");
		System.out.println("Continents Created and assigned");
		System.out.println("");
		displayPlayers();
		System.out.println("");
		System.out.println("Game Started");
		System.out.println("");
		displayGame();
		System.out.println("");
		System.out.println("Game Ended");
	}

	private static void displayPlayers() {
		System.out.println("Players");
		players = game.getPlayers();
		for(int i=0; i<players.size(); i++)
			System.out.println(players.get(i).getName() + " - " + players.get(i).getColor());
	}

	private static void displayGame() {
		System.out.println("Board");
		ArrayList<Continent> continents;
		continents = game.getBoard();
		int counter = 1;

		for(int i=0; i<continents.size();i++) {
			Continent c1 = continents.get(i);
			System.out.println(c1.getName());
			for(int j=0; j < c1.getRegions().size(); j++) {
				Region r1 = c1.getRegions().get(j);
				System.out.println(counter + " - " + r1.getName() + " (" + r1.getTroops() + ")" + " -> " + r1.getOwner());
				counter++;
			}
		}
	}
}
