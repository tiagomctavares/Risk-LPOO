package Logic;

public class Drawable {
	private int x;
	private int y;
	private String name;
	
	public Drawable(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
