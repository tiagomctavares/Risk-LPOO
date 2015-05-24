package Logic;

import java.util.ArrayList;

public class MapGenerator {
	private ArrayList<Continent> continents;
	private ArrayList<Region> regions;
	
	public MapGenerator() {
		createContinents();		
		createRegions();
		
		// Combine all regions into arrayList
		populateRegions();
	}
	
	public ArrayList<Region> getRegions() {
		return regions;
	}
	
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	private void populateRegions() {
		regions = new ArrayList<Region>();
		for(int i=0; i<continents.size(); i++)
			regions.addAll(continents.get(i).getRegions());
	}

	private void createRegions() {
		// Europe Regions
		Continent europe = continents.get(0);

		Region ukraine = new Region(europe, "Ukraine");
		Region scandinavia = new Region(europe, "Scandinavia");
		Region northEurope = new Region(europe, "N. Europe");
		Region southEurope = new Region(europe, "S. Europe");
		Region iceland = new Region(europe, "Iceland");
		Region greatBritain = new Region(europe, "Great Britain");
		Region westEurope = new Region(europe, "W. Europe");
		
		// Europe Connections
		ukraine.addBiConnection(scandinavia);
		ukraine.addBiConnection(northEurope);
		ukraine.addBiConnection(southEurope);
		scandinavia.addBiConnection(iceland);
		scandinavia.addBiConnection(northEurope);
		northEurope.addBiConnection(southEurope);
		northEurope.addBiConnection(greatBritain);
		northEurope.addBiConnection(westEurope);
		southEurope.addBiConnection(westEurope);
		iceland.addBiConnection(greatBritain);
		greatBritain.addBiConnection(westEurope);

		// North America
		Continent northAmerica = continents.get(1);
		Region greenland = new Region(northAmerica, "Ukraine");
		Region nwTerritory = new Region(northAmerica, "nwTerritory");
		Region ontario = new Region(northAmerica, "Ontario");
		Region quebec = new Region(northAmerica, "Quebec");
		Region easternUS = new Region(northAmerica, "Eastern US");
		Region alberta = new Region(northAmerica, "Alberta");
		Region westernUS = new Region(northAmerica, "Western US");
		Region alaska = new Region(northAmerica, "Alaska");
		Region centralAmerica = new Region(northAmerica, "Cent. America");
		
		// Europe North America Connections
		greenland.addBiConnection(iceland);
		
		// North America connections
		greenland.addBiConnection(nwTerritory);
		greenland.addBiConnection(ontario);
		greenland.addBiConnection(quebec);
		nwTerritory.addBiConnection(ontario);
		nwTerritory.addBiConnection(alberta);
		nwTerritory.addBiConnection(alaska);
		alaska.addBiConnection(alberta);
		alberta.addBiConnection(ontario);
		alberta.addBiConnection(westernUS);
		ontario.addBiConnection(quebec);
		ontario.addBiConnection(westernUS);
		ontario.addBiConnection(easternUS);
		quebec.addBiConnection(easternUS);
		easternUS.addBiConnection(westernUS);
		easternUS.addBiConnection(centralAmerica);
		westernUS.addBiConnection(centralAmerica);
		
		// North America
		Continent southAmerica = continents.get(2);
		Region venezuela = new Region(northAmerica, "Venezuela");
		Region peru = new Region(northAmerica, "Peru");
		Region brazil = new Region(northAmerica, "Brazil");
		Region argentina = new Region(northAmerica, "Argentina");
		
		// North | South America Connections
		venezuela.addBiConnection(centralAmerica);
		
		// South America Connections
		venezuela.addBiConnection(brazil);
		venezuela.addBiConnection(peru);
		peru.addBiConnection(argentina);
		peru.addBiConnection(brazil);
		brazil.addBiConnection(argentina);
		
		// Africa
		Continent africa = continents.get(3);
		Region northAfrica = new Region(africa, "N. Africa");
		Region egypt = new Region(africa, "Egypt");
		Region eastAfrica = new Region(africa, "East Africa");
		Region congo = new Region(africa, "Congo");
		Region southAfrica = new Region(africa, "S. Africa");
		Region madagascar = new Region(africa, "Madagascar");
		
		// Africa Europe Connections
		northAfrica.addBiConnection(westEurope);
		northAfrica.addBiConnection(southEurope);
		egypt.addBiConnection(southEurope);
		
		// Africa South America Connections
		northAfrica.addBiConnection(brazil);
		
		// Africa Connections
		northAfrica.addBiConnection(egypt);
		northAfrica.addBiConnection(congo);
		northAfrica.addBiConnection(eastAfrica);
		egypt.addBiConnection(eastAfrica);
		eastAfrica.addBiConnection(congo);
		eastAfrica.addBiConnection(madagascar);
		eastAfrica.addBiConnection(southAfrica);
		congo.addBiConnection(southAfrica);
		southAfrica.addBiConnection(madagascar);
		
		// Asia
		Continent asia = continents.get(4);
		
		Region middleEast = new Region(asia, "Middle East");
		Region india = new Region(asia, "India");
		Region siam = new Region(asia, "Siam");
		Region china = new Region(asia, "China");
		Region afghanistan = new Region(asia, "Afghanistan");
		Region ural = new Region(asia, "Ural");
		Region siberia = new Region(asia, "Siberia");
		Region mongolia = new Region(asia, "Mongolia");
		Region japan = new Region(asia, "Japan");
		Region irkutsk = new Region(asia, "Irkutsk");
		Region yakutsk = new Region(asia, "Yakutsk");
		Region kamchatka = new Region(asia, "Kamchatka");
		
		// Asia Europe connections
		southEurope.addBiConnection(middleEast);
		ukraine.addBiConnection(middleEast);
		ukraine.addBiConnection(afghanistan);
		ukraine.addBiConnection(ural);
		
		// North America Asia Connections
		kamchatka.addBiConnection(alaska);
		
		// Africa Asia Connections
		middleEast.addBiConnection(egypt);
		middleEast.addBiConnection(eastAfrica);
		
		// Asia Connections
		middleEast.addBiConnection(india);
		middleEast.addBiConnection(afghanistan);
		india.addBiConnection(afghanistan);
		india.addBiConnection(china);
		india.addBiConnection(siam);
		siam.addBiConnection(china);
		afghanistan.addBiConnection(china);
		afghanistan.addBiConnection(ural);
		china.addBiConnection(ural);
		china.addBiConnection(siberia);
		china.addBiConnection(mongolia);
		ural.addBiConnection(siberia);
		siberia.addBiConnection(yakutsk);
		siberia.addBiConnection(irkutsk);
		siberia.addBiConnection(mongolia);
		mongolia.addBiConnection(japan);
		mongolia.addBiConnection(irkutsk);
		mongolia.addBiConnection(kamchatka);
		irkutsk.addBiConnection(yakutsk);
		irkutsk.addBiConnection(kamchatka);
		yakutsk.addBiConnection(kamchatka);
		kamchatka.addBiConnection(japan);
		
		// Oceania
		Continent oceania = continents.get(5);

		Region indonesia = new Region(oceania, "Indonesia");
		Region newGuinea = new Region(oceania, "New Guinea");
		Region westAustralia = new Region(oceania, "W. Australia");
		Region eastAustralia = new Region(oceania, "E. Australia");
		
		// Asian Oceania Connections
		indonesia.addBiConnection(siam);
		
		// Oceania Connections
		indonesia.addBiConnection(newGuinea);
		indonesia.addBiConnection(westAustralia);
		newGuinea.addBiConnection(eastAustralia);
		westAustralia.addBiConnection(eastAustralia);
		
	}

	private void createContinents() {
		this.continents = new ArrayList<Continent>();
		continents.add(new Continent("Europe", 5));
		continents.add(new Continent("N. America", 5));
		continents.add(new Continent("S. America", 2));
		continents.add(new Continent("Africa", 3));
		continents.add(new Continent("Asia", 7));
		continents.add(new Continent("Oceania", 2));
	}
}
