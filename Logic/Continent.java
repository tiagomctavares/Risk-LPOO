package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Continent implements Serializable {
	private static final long serialVersionUID = 1L;
	public String name;
	private int reinforceValue;
	private ArrayList<Region> regions;
	
	public Continent(String name, int reinforceValue) {
		this.name = name;
		this.reinforceValue = reinforceValue;
		regions = new ArrayList<Region>();
	}

	public String getName() {
		return name;
	}

	public int getReinforceValue() {
		return reinforceValue;
	}
	
	public void addRegion(Region region) {
		regions.add(region);
	}

	public ArrayList<Region> getRegions() {
		return regions;
	}

	public void setRegions(ArrayList<Region> regions) {
		this.regions = regions;
	}
}
