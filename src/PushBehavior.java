/**
 * A interface containing callback methods to response when changes happen to pushable sprites.
 * @author Naufal Fikri
 *
 */
public interface PushBehavior {
	/**
	 * A callback method that is called whenever the object is pushed.
	 * @param source the object that pushes.
	 * @param dir the direction that the object is pushed.
	 */
	public default void onPush(Sprite source, Direction dir) {
		
	}
	/**
	 * A callback method that is called whenever the object was about to be pushed, but failed (due to being blocked by a wall, etc).
	 * @param source the object that pushes.
	 * @param dir the direction that the object is pushed.
	 */
	public default void onPushFailed(Sprite source, Direction dir) {
		
	}
	
	@Deprecated
	/**
	 * A callback method that is called whenever a Player instance pushes the block, use of this method is deprecated since
	 * there is no way to obtain the source object using this method.
	 * @param dir the direction that the object is being pushed to.
	 */
	public default void onPlayerPush(Direction dir) {
		
	}
}
