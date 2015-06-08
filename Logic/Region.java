package Logic;

import java.util.ArrayList;

public class Region extends Drawable {
	private Continent continent;
	private String name;
	private int troops;
	private Player owner;
	private ArrayList<Region> connections;
	int troopX;
	int troopY;
	
	public Region(Continent continent, String name, int x, int y) {
		super(name, x, y);
		this.continent = continent;
		this.name = name;
		this.troops = 0;
		this.troopX = 0;
		this.troopY = 0;
		
		connections = new ArrayList<Region>();
		continent.addRegion(this);
	}
	
	public Region(Continent continent, String name, int x, int y, int troopX, int troopY) {
		super(name, x, y);
		this.continent = continent;
		this.name = name;
		this.troops = 0;
		this.troopX = troopX;
		this.troopY = troopY;
		
		connections = new ArrayList<Region>();
		continent.addRegion(this);
	}
	
	@Override
	public String toString() {
		return name;
	}

	public int getTroopX() {
		return troopX;
	}

	public int getTroopY() {
		return troopY;
	}

	public void addBiConnection(Region region) {
		connections.add(region);
		region.addConnection(this);
	}
	
	public void addConnection(Region region) {
		connections.add(region);
	}

	public Continent getContinent() {
		return continent;
	}

	public String getName() {
		return name;
	}

	public Integer getTroops() {
		return troops;
	}

	public Player getOwner() {
		return owner;
	}

	public ArrayList<Region> getConnections() {
		return connections;
	}

	public void setTroops(int troops) {
		this.troops = troops;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public void setConnections(ArrayList<Region> connections) {
		this.connections = connections;
	}

	public boolean lostTroop(Region regionFrom) {
		this.troops--;
		
		if(this.troops == 0) {
			this.owner = regionFrom.owner;
			this.troops++;
			regionFrom.troops--;
			if(regionFrom.getTroops() > 1)
				return true;
		}
		
		return false;
	}

	public int getNumberAttackers() {
		if(this.troops > 3)
			return 3;
		else 
			return this.troops-1;
	}
	
	public int getNumberDefenders() {
		if(this.troops >= 2)
			return 2;
		else 
			return 1;
	}

	public boolean canAttack(Player player) {
		
		if(this.owner == player && this.troops > 1) {
			for(Region neighbour: this.connections)
				if(neighbour.getOwner() != player)
					return true;
		}
		return false;
	}

	public int getMaxReinforcements() {
		return this.troops-1;
	}

	public void transferTroops(Region regionTo, int number) {
		this.troops-=number;
		regionTo.troops+=number;		
	}

	public boolean canReinforce(Player player) {		
		if(this.owner == player && this.troops > 1) {
			for(Region neighbour: this.connections)
				if(neighbour.getOwner() == player)
					return true;
		}
		return false;
	}

	public boolean canBeReinforce(Player player, Region reinforceFrom) {
		ArrayList<Region> tree = getConnectionsTree(reinforceFrom);
		
		if(tree.contains(this))
			return true;
		
		return false;
	}

	private ArrayList<Region> getConnectionsTree(Region reinforceFrom) {
		ArrayList<Region> regions = new ArrayList<Region>();
		regions.add(reinforceFrom);
		
		getConnectionsTree2(regions, reinforceFrom);
		
		regions.remove(0);
		
		return regions;
	}
	
	private ArrayList<Region> getConnectionsTree2(ArrayList<Region> alreadyConnected, Region actualRegion) {
		
		for(Region region : actualRegion.getConnections()) {
			if(region.getOwner() == actualRegion.getOwner() && !alreadyConnected.contains(region)) {
				alreadyConnected.add(region);
				alreadyConnected = getConnectionsTree2(alreadyConnected, region);
			}
		}
		
		return alreadyConnected;
	}

	public boolean canDeployRegion(Player player) {
		if(this.getOwner() == player)
			return true;
		return false;
	}

	public void deployTroops(int deployNumber) {
		this.troops+=deployNumber;
	}
}
