package entities;

public abstract class Resource {
	
	public static final int WOOD = 0;
	public static final int WHEAT = 1;
	public static final int BRICK = 2;
	public static final int SHEEP = 3;
	public static final int ROCK = 4;
	public static final int DESERT = 5;
	
	private final int id;
	
	public Resource(int id) {
		this.id = id;
	}
	
	public final int getId() {
		return id;
	}
}