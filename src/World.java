import java.util.*;

import org.newdawn.slick.*;

/**
 * A class representing a world that manages all the sprites.
 * @author Naufal Fikri
 *
 */
public class World{	
	private int moves;
	private int width;
	private int height;
	private String levelName;
	
	private List<Sprite> worldSprites;
	
	/**
	 * Generate world contents from a given filename.
	 * @param filename the file that is going to be loaded.
	 * @return the world object parsed from the file/
	 */
	public static World generateFromFile(String filename) {
		int[] dimensions = Loader.loadWorldDimensions(filename);
		String[] params = Loader.loadWorldHeader(filename);
		List<Sprite> sprites = Loader.loadSprites(filename);
		World w;
		if (params.length > 2) {
			w = new World(dimensions[0],dimensions[1], sprites, params[2]);
		} else {
			w = new World(dimensions[0],dimensions[1], sprites);
		}
		for (Sprite s : w.getAllSprites()) {
			if (s instanceof WorldBehavior) {
				((WorldBehavior) s).onWorldLoaded();
			}
		}
		return w;
	}
	
	/**
	 * Creates a new empty World object with the given width and height.
	 * @param width the width of the world.
	 * @param height the height of the world.
	 */
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.worldSprites = new ArrayList<Sprite>();
	}

	/**
	 * Creates a World object with the given width and height populated with sprites
	 * given.
	 * @param width the width of the world.
	 * @param height the height of the world.
	 * @param sprites the list of sprites that should populate the world.
	 */
	public World(int width, int height, List<Sprite> sprites) {
		this.width = width;
		this.height = height;
		this.worldSprites = new ArrayList<Sprite>();
		this.levelName = "";
		this.addToWorld(sprites);
	}
	
	/**
	 * Creates a World object with the given width and height populated with sprites
	 * given.
	 * @param width the width of the world.
	 * @param height the height of the world.
	 * @param sprites the list of sprites that should populate the world.
	 * @param name the name of the world.
	 */
	public World(int width, int height, List<Sprite> sprites, String name) {
		this.width = width;
		this.height = height;
		this.worldSprites = new ArrayList<Sprite>();
		this.levelName = name;
		this.addToWorld(sprites);
	}
	
	/**
	 * Adds a sprite to the list of sprites contained in this world.
	 * @param s the sprite to be added
	 */
	public void addToWorld(Sprite s) {
		worldSprites.add(s);
		s.moveTo(s.getX(), s.getY(), this);
		Collections.sort(this.worldSprites);
		if (s instanceof Player) {
			this.initializePlayer((Player)s);
		}
	}
	
	/**
	 * Adds a collection of sprites to the list of sprites contained in this world.
	 * @param sprites the list of sprites to add
	 */
	public void addToWorld(List<Sprite> sprites) {
		for (Sprite s : sprites) {
			this.addToWorld(s);
		}
	}
	
	/** 
	 * Destroys a sprite from the world
	 * @param s the sprite to be destroyed.
	 */
	public void destroySprite(Sprite s) {
		if (worldSprites.contains(s)) {
			worldSprites.remove(s);
		}
	}
	
	/**
	 * Gets all the sprite in this world, modifications made to the list returned
	 * does not affect the list of sprites in this world. 
	 * @return A list of all the sprites in this world.
	 */
	public List<Sprite> getAllSprites() {
		List<Sprite> readOnlySprites = new ArrayList<Sprite>(this.worldSprites);
		return readOnlySprites;
	}
	
	/**
	 * Gets the width of the world.
	 * @return the width of the world
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the world.
	 * @return the height of the world.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Update the world state for a frame.
	 * @param input The Input object provided by Slick2D.
	 * @param delta Time that has passed since last update (in milliseconds)
	 */
	public void update(Input input, int delta) {
		for (Sprite s : this.getAllSprites()) {
			s.update(input, delta);
		}
	}
	
	/**
	 * Renders all the sprites contained in this world.
	 * @param g the Slick Graphics object used to render on the screen.
	 */
	public void render(Graphics g) {
		for (Sprite s : this.getAllSprites()) { 
			try {
				s.render(g);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		g.drawString("Moves: " + moves, 10, 0);
	}
	
	/**
	 * Obtains the sprites that are located in tile coordinates x,y.
	 * @param x The x coordinate of location to check.
	 * @param y The y coordinate of the location to check.
	 * @return The list of sprites located at position x,y.
	 */
	public List<Sprite> getSpritesAt(float x, float y) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		for (Sprite s : worldSprites) {
			if (Math.floor(s.getX()) == (double)x
					&& Math.floor(s.getY()) == (double)y)
			{
				sprites.add(s);
			}
		}
		return sprites;
	}
	
	/**
	 * Invokes the response to key press of all sprites in this world.
	 * @param key the key code (from Input class) of the key that is pressed.
	 * @param c character received from input (if a character key is pressed).
	 */
	public void invokeKeyPressBehavior(int key, char c) {
		switch(key) {
		case Input.KEY_UP:
		case Input.KEY_W:
		case Input.KEY_DOWN:
		case Input.KEY_S:
		case Input.KEY_LEFT:
		case Input.KEY_A:
		case Input.KEY_RIGHT:
		case Input.KEY_D:
			//implement history function here
			this.moves += 1;
			break;
		default:
			break;
		}
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof KeyPressBehavior) {
				((KeyPressBehavior)s).onKeyPress(key, c);
			}
		}
	}
	
	/**
	 * Checks whether the level is already completed (all objectives fulfilled)
	 * @return boolean whether the level has been completed.
	 */
	public boolean isComplete() {
		boolean assumed = true;
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof ObjectiveBehavior){
				if (!((ObjectiveBehavior)s).isFulfilled()) {
					return false;
				}
			}
		}
		return this.hasObjective() && assumed;
	}
	
	/**
	 * Returns true if the level can actually be played (has an objective) and false if the level is uncompleteable (so it doesn't
	 * advance to the next level instantly).
	 * @return boolean whether the level has an objective or not.
	 */
	public boolean hasObjective() {
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof ObjectiveBehavior) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Sets the state of all devices in this level (e.g. doors) that are associated to a certain trigger color.
	 * @param color the trigger color
	 * @param state boolean representing the new state of the device that we desire
	 */
	public void setTriggerStates(TriggerColor color, boolean state) {
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof TriggerBehavior) {
				if (((TriggerBehavior)s).getColor().equals(color) || color.equals(TriggerColor.UNIVERSAL)) {
					((TriggerBehavior)s).setState(state);
				}
			}
		}
	}
	
	private void initializePlayer(Player player) {
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof WorldBehavior) {
				((WorldBehavior)s).onSpriteSpawn(player);
			}
		}
	}
	
	/**
	 * Undoes a step of action in the world.
	 */
	public void undo() {
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof UndoBehavior) {
				if (!((UndoBehavior)s).isUndoPossible()) {
					return;
				}
			}
		}
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof UndoBehavior) {
				((UndoBehavior)s).undo();
			}
		}
		--this.moves;
	}
	
	/**
	 * Gets the name of the level (if any).
	 * @return TThe name of the level.
	 */
	public String getLevelName() {
		// TODO Auto-generated method stub
		return this.levelName;
	}
	
	/** 
	 * Sets the name of the level.
	 * @param s the new name of the level.
	 */
	public void setLevelName(String s) {
		this.levelName = s;
	}
	
	/**
	 * Checks whether the level has been failed.
	 * @return boolean representing whether the level has been failed or not.
	 */
	public boolean isFailed() {
		boolean hasPlayer = false;
		for (Sprite s : this.getAllSprites()) {
			if (s instanceof Player) {
				hasPlayer = true;
			}
		}
		return (this.hasObjective() && !hasPlayer);
	}

	/**
	 * Checks whether a position of the map is blocked by an object, assuming that blocks cannot be pushed.
	 * @param x the x coordinate to be checked.
	 * @param y the y coordinate to be checked.
	 * @return boolean whether the location is blocked or not.
	 */
	public boolean isBlocked(float x, float y) {
		for (Sprite s : this.getSpritesAt(x, y)) {
			if (s.isSolid() || s instanceof PushableSprite) {
				return true;
			}
		}
		return (this.getSpritesAt(x, y).size() == 0 ? true : false);
	}
}
