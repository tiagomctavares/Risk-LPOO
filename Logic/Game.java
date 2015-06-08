package logic;

import java.util.ArrayList;


/**
 * Represents a Game Logic.
 * 
 * @author Tiago Tavares
 * 
 */
public class Game {
	private MapGenerator map;
	private PlayerInit server;
	private ArrayList<Player> players;
	private int currentPlayer;

	/**
	 * Constructs and initializes Game for default board
	 * 
	 */	
	public Game() {
		players = new ArrayList<Player>();
		server = new PlayerInit();
		initGame();
	}
	
	private void initGame() {
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

	/**
	 * Returns the board represented by an array of continents
	 * 
	 * @return <code>ArrayList<Continent></code> representation of the Continents
	 */
	public ArrayList<Continent> getBoard() {
		return map.getContinents();
	}

	/**
	 * Returns the an Array list of players
	 * 
	 * @return <code>ArrayList<Player></code> representation of the players
	 */
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

	/**
	 * Returns the an Array of Strings of playable regions for the current Player
	 * 
	 * @return <code>String[]</code> representation of the regions that can play
	 */
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

	/**
	 * Returns the an Array of Strings of the regions that can be attacked from the region
	 * 
	 * @param regionName
	 * 			The region Name that is attacking
	 * @return <code>String[]</code> representation of the regions that can play
	 */
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

	/**
	 * Returns the if the region has been conquered or not
	 * 
	 * @param regionNameFrom
	 * 			The region Name that is attacking
	 * @param regionNameTo
	 * 			The region Name that is being attacked
	 * @return <code>True</code> if region has been conquered
	 */
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

	/**
	 * Returns the max number of reinforcements you can move from a Region 
	 * 
	 * @param regionNameFrom
	 * 			The region Name that moving troops
	 * @return <code>Int</code> the max number of the troops that can transfer
	 */
	public int getReinforcements(String regionNameFrom) {
		return map.getRegionByName(regionNameFrom).getMaxReinforcements();
	}

	/**
	 * Moves Troops from one Region to another
	 * 
	 * @param conquerRegionFrom
	 * 			The region Name that moving troops
	 * @param conquerRegionFrom
	 * 			The region Name that receiving troops
	 * @param number
	 * 			The number of troops to transfer
	 * @return <code>Int</code> the max number of the troops that can transfer
	 */
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

	/**
	 * Returns the an Array of Strings of the regions that can reinforce others from the current Player
	 * 
	 * @return <code>String[]</code> representation of the regions that can reinforce others
	 */
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

	/**
	 * Returns the an Array of Strings of the Regions that can be reinforced
	 * 
	 * @param regionName
	 * 			The region name that will be reinforcing
	 * 
	 * @return <code>String[]</code> representation of the regions that can reinforce others
	 */
	public String[] getReinforceDestinationRegionsList(String regionName) {
		return arrayListToArray(getReinforceDestinationRegions(map.getRegionByName(regionName)));
	}

	/**
	 * Ends the turn of the current Player and stats a new turn for next player
	 * 
	 */
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

	/**
	 * Returns for current Player
	 * 
	 * @return <code>Player</code> Returns the current Player
	 */
	public Player getCurrentPlayer() {
		return players.get(currentPlayer);
	}
	
	private ArrayList<Region> getDeployRegions() {
		ArrayList<Region> regions = new ArrayList<Region>();
		Player player = players.get(currentPlayer);
		
		for(Region region : map.getRegions())
			if(region.canDeployRegion(player))
				regions.add(region);
		
		return regions;
	}

	/**
	 * Returns the an Array of Strings of the Regions that the player can Deploy Troops
	 * 
	 * @return <code>String[]</code> representation of the regions that the player can deploy troops
	 */
	public String[] getDeployRegionsList() {
		return arrayListToArray(getDeployRegions());
	}

	/**
	 * Returns the number of the troops the player can still deploy
	 * 
	 * @return <code>int</code> the number of the troops the player can still deploy
	 */
	public int getDeployNumberForPlayer() {
		return players.get(currentPlayer).getDeployNumberRemaining();
	}

	/**
	 * Deploys Troops on Region
	 * 
	 * @param regionDeploy
	 * 			Region name to deploy troops
	 * @param deployNumber
	 * 			Number of troops to deploy on that region
	 * 
	 */
	public void deployTroops(String regionDeploy, int deployNumber) {
		Region region = map.getRegionByName(regionDeploy);
		region.deployTroops(deployNumber);
		players.get(currentPlayer).deployedTroops(deployNumber);
	}

	/**
	 * Returns the Region by its name
	 * 
	 * @param name
	 * 			Region name
	 * @return Region
	 * 			The Region
	 * 
	 */
	public Region getRegionByName(String name) {
		return map.getRegionByName(name);
	}
}
