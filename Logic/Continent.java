package Logic;

import java.util.ArrayList;

public class Continent extends Drawable {
	private int reinforceValue;
	private ArrayList<Region> regions;
	
	public Continent(String name, int x, int y, int reinforceValue) {
		super(name, x, y);
		this.reinforceValue = reinforceValue;
		regions = new ArrayList<Region>();
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
