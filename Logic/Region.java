package Logic;

import java.util.ArrayList;

public class Region {
	private Continent continent;
	private String name;
	private int troops;
	private Player owner;
	private ArrayList<Region> connections;
	
	public Region(Continent continent, String name) {
		super();
		this.continent = continent;
		this.name = name;
		connections = new ArrayList<Region>();
		continent.addRegion(this);
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

	public int getTroops() {
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
