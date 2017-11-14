/**
 * This interface gives implementation to objects that allow them to interact with changes triggered by the mouse input of the user.
 * @author Naufal Fikri
 *
 */
public interface MouseBehavior {
	/**
	 * A callback method that is triggered when the mouse is pressed.
	 * @param button the button code for the button that is pressed.
	 * @param x the x screen coordinate of the mouse.
	 * @param y the y screen coordinate of the mouse.
	 */
	public default void onMousePress(int button, int x, int y) {
		
	}
	
	/**
	 * A callback method that is triggered when the mouse is released.
	 * @param button the button code for the button that is pressed.
	 * @param x the x screen coordinate of the mouse.
	 * @param y the y screen coordinate of the mouse.
	 */
	public default void onMouseRelease(int button, int x, int y) {
		
	}

	/**
	 * A callback method that is triggered when the mouse enters the region of the object.
	 */
	public default void onMouseEnter() {
		
	}

	/**
	 * A callback method that is triggered when the mouse leaves the region of the object.
	 */
	public default void onMouseLeave() {
		
	}
	/**
	 * A callback method that is triggered when the mouse is moved from one point to another point.
	 * @param oldx the old x coordinate of the mouse
	 * @param oldy the old y coordinate of the mouse
	 * @param newx the new x coordinate of the mouse
	 * @param newy the new y coordinate of the mouse
	 */
	public default void onMouseMove(int oldx, int oldy, int newx, int newy) {
		
	}

}
