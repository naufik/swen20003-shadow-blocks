/**
 * An interface that allows implementing classes to respond to collisions.
 * @author Naufal Fikri
 */
public interface CollisionBehavior {
	/**
	 * A callback method that is called on each update if the sprite is colliding with another sprite. This includes self-collision.
	 * @param sprite The other sprite that it is colliding with.
	 */
	public default void onCollision(Sprite sprite) {
		
	}
	/**
	 * A callback method that is called when a sprite (either self, or the other) changes in position and has started colliding with
	 * the sprite implementing the method.
	 * @param sprite The other sprite that it is colliding with.
	 */
	public default void onCollisionStart(Sprite sprite) {
		
	}
	/**
	 * A callback method that is called when a sprite (either self, or the other) changes in position and has stopped colliding with 
	 * each other.
	 * @param sprite The other sprite that it is colliding with
	 */
	public default void onCollisionEnd(Sprite sprite) {
		
	}
	
}
