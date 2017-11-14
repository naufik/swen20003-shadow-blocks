/**
 * An Interface defining the behaviors that can response to changes that happen to a Unit.
 * @author Naufal Fikri
 *
 */
public interface UnitBehavior {
	
	/**
	 * A callback method that is called whenever a unit walks, the unit can be either an Enemy, the Player, or any custom defined unit.
	 * @param source the calling sprite.
	 * @param walkSuccess boolean representing whether the walk was successful or not.
	 * @param newX the new x-coordinate of the Sprite 'source'
	 * @param newY the new y-coordinate of the Sprite 'source'
	 * @param dir the direction that the source object walked in
	 */
	public default void onUnitWalk(Sprite source, boolean walkSuccess, float newX, float newY, Direction dir) {
		//no implementation by default
	}
	
	/**
	 * A callback method that is called whenever a Player sprite walks. It is better to use onUnitWalk and check that the source is
	 * the player since this method does not give a reference to the player object and does not behave nicely when there are multiple player
	 * objects.
	 * @param walkSuccess boolean representing whether the walk was successful or not.
	 * @param newX the new x-coordinate of the Sprite 'source'
	 * @param newY the new y-coordinate of the Sprite 'source'
	 * @param dir the direction that the source object walked in
	 */
	public default void onPlayerWalk(boolean walkSuccess, float newX, float newY, Direction dir) {
		//no implementation by default
	}
	
	/**
	 * A callback method that is called when a unit is about to walk.
	 * @param source the calling sprite.
	 * @param x the x position of the sprite.
	 * @param y the y position of the sprite.
	 * @param dir the direction that it is about to move to.
	 */
	public default void preUnitWalk(Sprite source, float x, float y, Direction dir) {
		//no implementation by default
	}
	
	/**
	 * A callback method that is called when a unit is about to walk.
	 * @param source the calling sprite for the method, that is the unit that kills.
	 * @param victim the sprite that is being killed.
	 */
	public default void onKill(Sprite source, Sprite victim) {
		//no implementation by default
	}
}
