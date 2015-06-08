package gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * Loads the Images to be displayed in board
 * 
 * @author Tiago Tavares
 * 
 */
public class BoardImages {
	private ArrayList<BufferedImage> images;
	private static String resourcesFolder = System.getProperty("user.dir")+"\\resources\\";
	
	public BoardImages() {
		images = new ArrayList<BufferedImage>();
		
		try {
			System.out.println(resourcesFolder+"board.png");
			images.add(ImageIO.read(new File(resourcesFolder+"board.png")));
		} catch (IOException e) {
			System.out.println("Error Loading Images");
			System.exit(0);
		}
	}

	public BufferedImage getBoard() {
		return images.get(0);
	}
}
