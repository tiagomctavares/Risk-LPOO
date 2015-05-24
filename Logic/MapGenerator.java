package Logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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

		Region ukraine = new Region(europe, "Ukraine", 426, 110, 430, 140);
		Region scandinavia = new Region(europe, "Scandinavia", 355, 83, 360, 100);
		Region northEurope = new Region(europe, "N. Europe", 355, 170, 355, 173);
		Region southEurope = new Region(europe, "S. Europe", 353, 207, 370, 210);
		Region iceland = new Region(europe, "Iceland", 300, 88, 310, 95);
		Region greatBritain = new Region(europe, "G. Britain", 257, 190, 305, 160);
		Region westEurope = new Region(europe, "W. Europe", 290, 252, 305, 225);
		
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
		Region greenland = new Region(northAmerica, "Greenland", 245, 40, 255, 55);
		Region nwTerritory = new Region(northAmerica, "nwTerritory", 90, 90, 100, 60);
		Region ontario = new Region(northAmerica, "Ontario", 146, 140, 150, 110);
		Region quebec = new Region(northAmerica, "Quebec", 197, 139, 205, 110);
		Region easternUS = new Region(northAmerica, "East US", 150, 200, 160, 170);
		Region alberta = new Region(northAmerica, "Alberta", 96, 136, 100, 100);
		Region westernUS = new Region(northAmerica, "West US", 95, 194, 105, 160);
		Region alaska = new Region(northAmerica, "Alaska", 35, 93, 45, 63);
		Region centralAmerica = new Region(northAmerica, "Cent. America", 101, 248, 110, 215);
		
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
		Region venezuela = new Region(southAmerica, "Venezuela", 161, 280, 155, 280);
		Region peru = new Region(southAmerica, "Peru", 175, 353, 150, 320);
		Region brazil = new Region(southAmerica, "Brazil", 195, 305, 220, 315);
		Region argentina = new Region(southAmerica, "Argentina", 177, 390, 183, 400);
		
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
		Region northAfrica = new Region(africa, "N. Africa", 318, 330, 328, 290);
		Region egypt = new Region(africa, "Egypt", 385, 282, 395, 285);
		Region eastAfrica = new Region(africa, "E. Africa", 410, 320, 420, 325);
		Region congo = new Region(africa, "Congo", 375, 377, 405, 357);
		Region southAfrica = new Region(africa, "S. Africa", 390, 452, 395, 412);
		Region madagascar = new Region(africa, "Madagascar", 452, 447, 468, 417);
		
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
		
		Region middleEast = new Region(asia, "Middle East", 420, 240, 445, 250);
		Region india = new Region(asia, "India", 506, 242, 536, 256);
		Region siam = new Region(asia, "Siam", 580, 275, 600, 280);
		Region china = new Region(asia, "China", 554, 235, 570, 205);
		Region afghanistan = new Region(asia, "Afghanistan", 469, 175, 490, 185);
		Region ural = new Region(asia, "Ural", 495, 100, 505, 120);
		Region siberia = new Region(asia, "Siberia", 530, 60, 540, 80);
		Region mongolia = new Region(asia, "Mongolia", 575, 168, 595, 175);
		Region japan = new Region(asia, "Japan", 673, 213, 670, 175);
		Region irkutsk = new Region(asia, "Irkutsk", 567, 150, 590, 120);
		Region yakutsk = new Region(asia, "Yakutsk", 595, 60, 595, 65);
		Region kamchatka = new Region(asia, "Kamchatka", 645, 65, 645, 69);
		
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

		Region indonesia = new Region(oceania, "Indonesia", 573, 341, 605, 350);
		Region newGuinea = new Region(oceania, "New Guinea", 653, 319, 670, 335);
		Region westAustralia = new Region(oceania, "W. Australia", 665, 425, 670, 395);
		Region eastAustralia = new Region(oceania, "E. Australia", 625, 450, 635, 420);
		
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
		continents.add(new Continent("Europe", 0, 0, 5));
		continents.add(new Continent("N. America", 0, 0, 5));
		continents.add(new Continent("S. America", 0, 0, 2));
		continents.add(new Continent("Africa", 0, 0, 3));
		continents.add(new Continent("Asia", 0, 0, 7));
		continents.add(new Continent("Oceania", 0, 0, 2));
	}

	public void scrumbleRegions() {
		long seed = System.nanoTime();
		Collections.shuffle(regions, new Random(seed));
	}
}
