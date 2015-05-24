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
}
