package Logic;

import java.awt.Component;
import java.util.ArrayList;

import Server.ServerPlayers;


public class Game {
	private MapGenerator map;
	private ServerPlayers server;
	private ArrayList<Player> players;
	private int currentPlayer;
	
	public Game() {
		players = new ArrayList<Player>();
		server = new ServerPlayers();
		initGame();
	}
	
	public void initGame() {
		this.map = new MapGenerator();
		players = server.getPlayers();
		currentPlayer = 0;
		
		assignRegions();
		calculatePlayerReinforcement();
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

	private ArrayList<Region> getPlayableRegions() {
		ArrayList<Region> regions = new ArrayList<Region>();
		Player player = players.get(currentPlayer);
		
		for(Region region : map.getRegions())
			if(region.canAttack(player))
				regions.add(region);
		
		return regions;
	}
	
	public String[] getPlayableRegionsList() {
		return arrayListToArray(getPlayableRegions());
	}

	private ArrayList<Region> getAttackableRegions(String regionName) {
		
		ArrayList<Region> regions = new ArrayList<Region>();
		
		for(Region region : getPlayableRegions()) {
			if(region.getName() == regionName) {
				for(Region connection : region.getConnections())
					if(region.getOwner() != connection.getOwner())
						regions.add(connection);
				break;
			}
		}
		
		return regions;
	}
	
	public String[] getAttackableRegionsList(String regionName) {
		return arrayListToArray(getAttackableRegions(regionName));
	}
	
	private String[] arrayListToArray(ArrayList<Region> arrayList) {
		String [] objectList = new String[arrayList.size()];
		
		for(int i=0; i<arrayList.size(); i++){
			objectList[i] = arrayList.get(i).toString();
		}
		
		return objectList;
	}

	public boolean performAttack(String regionNameFrom, String regionNameTo) {
		Region regionFrom = map.getRegionByName(regionNameFrom);
		Region regionTo = map.getRegionByName(regionNameTo);
		boolean canMoveFromConquerRegion = false;
		
		Integer[] results = Dice.simulateAttack(regionFrom.getNumberAttackers(), regionTo.getNumberDefenders());

		if(results[0] == 1)
			canMoveFromConquerRegion = regionTo.lostTroop(regionFrom);
		else
			regionFrom.lostTroop(regionTo);
		
		if(results[1] != -1) {
			if(results[1] == 1)
				canMoveFromConquerRegion = regionTo.lostTroop(regionFrom);
			else
				regionFrom.lostTroop(regionTo);
		}
		
		if(canMoveFromConquerRegion)
			return true;
		else
			return false;
	}

	public int getReinforcements(String regionNameFrom) {
		return map.getRegionByName(regionNameFrom).getMaxReinforcements();
	}

	public void moveTroops(String conquerRegionFrom, String conquerRegionTo, int number) {
		Region regionFrom = map.getRegionByName(conquerRegionFrom);
		Region regionTo = map.getRegionByName(conquerRegionTo);
		
		regionFrom.transferTroops(regionTo, number);
	}
	
	private ArrayList<Region> getReinforceRegions() {
		ArrayList<Region> regions = new ArrayList<Region>();
		Player player = players.get(currentPlayer);
		
		for(Region region : map.getRegions())
			if(region.canReinforce(player))
				regions.add(region);
		
		return regions;
	}

	public String[] getReinforceRegionsList() {
		return arrayListToArray(getReinforceRegions());
	}
	
	private ArrayList<Region> getReinforceDestinationRegions(Region reinforceFrom) {
		ArrayList<Region> regions = new ArrayList<Region>();
		Player player = players.get(currentPlayer);
		
		for(Region region : map.getRegions())
			if(region.canBeReinforce(player, reinforceFrom))
				regions.add(region);
		
		return regions;
	}

	public String[] getReinforceDestinationRegionsList(String string) {
		return arrayListToArray(getReinforceDestinationRegions(map.getRegionByName(string)));
	}

	public void playerEndTurn() {
		currentPlayer++;
		if(players.size() == currentPlayer)
			currentPlayer = 0;
		calculatePlayerReinforcement();
	}

	private void calculatePlayerReinforcement() {
		int reinforcement = reinforcementFromContinents();
		reinforcement += reinforcementFromRegions();
		players.get(currentPlayer).setDeployNumberRemaining(reinforcement);
	}

	private int reinforcementFromRegions() {
		int numberRegions = 0;
		for(Region region : map.getRegions()) {
			if(region.getOwner() == players.get(currentPlayer))
				numberRegions++;
		}
		
		int deployNumber = numberRegions / 3;
		if(deployNumber < 3)
			deployNumber = 3;
		
		return deployNumber;
	}

	private int reinforcementFromContinents() {
		int total = 0;
		for(Continent continent : map.getContinents()) {
			boolean continentBelongsToPlayer = true;
			for(Region region : continent.getRegions())
				if(region.getOwner() != players.get(currentPlayer)) {
					continentBelongsToPlayer = false;
					break;
				}
			if(continentBelongsToPlayer)
				total += continent.getReinforceValue();
		}
		return total;
	}

	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	private ArrayList<Region> getDeployRegions() {
		ArrayList<Region> regions = new ArrayList<Region>();
		Player player = players.get(currentPlayer);
		
		for(Region region : map.getRegions())
			if(region.canDeployRegion(players.get(currentPlayer)))
				regions.add(region);
		
		return regions;
	}
	
	public String[] getDeployRegionsList() {
		return arrayListToArray(getDeployRegions());
	}

	public int getDeployNumberForPlayer() {
		return players.get(currentPlayer).getDeployNumberRemaining();
	}

	public void deployTroops(String regionDeploy, int deployNumber) {
		Region region = map.getRegionByName(regionDeploy);
		region.deployTroops(deployNumber);
		players.get(currentPlayer).deployedTroops(deployNumber);
	}
}
