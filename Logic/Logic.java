package Logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Logic {
	private static ArrayList<Continent> continents;
	
	public static void main(String [] args)
	{
		//loadContinents();
		//loadRegions();
		new MapGenerator();
	}
	
	private static void loadRegions() {
		// Europe List
		
		/*continents.get(0).addRegion(new Region(, 5));
		regions.add();
		regions.add(new Region("N. America", 5));
		regions.add(new Region("S. America", 2));
		regions.add(new Region("Africa", 3));
		regions.add(new Region("Asia", 7));
		regions.add(new Region("Oceania", 2));
		serializeRegions(regions);

		regions = deserializeRegions();*/
		
		System.out.println("Deserialized Regions...");
		
	}

	/*private static void serializeRegions(ArrayList<Region> regions) {

		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("loadData/regions.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(regions);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in loadData/regions.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
	}

	private static ArrayList<Region> deserializeRegions() {
		
		return null;
	}*/

	private static void loadContinents() {
		ArrayList<Continent> continents = new ArrayList<Continent>();
		continents.add(new Continent("Europe", 5));
		continents.add(new Continent("N. America", 5));
		continents.add(new Continent("S. America", 2));
		continents.add(new Continent("Africa", 3));
		continents.add(new Continent("Asia", 7));
		continents.add(new Continent("Oceania", 2));
		serializeContinents(continents);
		
		continents = deserializeContinents();
		
		System.out.println("Deserialized Continents...");
	}

	private static ArrayList<Continent> deserializeContinents() {
		ArrayList<Continent> continents = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream("loadData/continents.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         continents = (ArrayList<Continent>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Continent class not found");
	         c.printStackTrace();
	         return null;
	      }
	      
		return continents;
	}

	private static void serializeContinents(ArrayList<Continent> continents) {
		
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("loadData/continents.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(continents);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in loadData/continents.ser");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		
	}
}
