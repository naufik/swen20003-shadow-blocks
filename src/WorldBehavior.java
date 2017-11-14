/**
 * An interface that allows implementing classes to interact with the World.
 * @author Naufal Fikri
 *
 */
public interface WorldBehavior {
	
	/**
	 * A method that is called when a Sprite spawns.
	 * @param s the sprite that just spawns.
	 */
	public default void onSpriteSpawn(Sprite s) {
		//no implementation by default.
	}
	
	/**
	 * A callback method that is called when the level containing the sprite is loaded.
	 */
	public default void onWorldLoaded() {
		//no implementation by default.
	}
	
}
