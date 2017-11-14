import org.newdawn.slick.Color;
/**
 * This interface defines behavior of an object in response of changes in triggers that exist in the world.
 * @author Naufal Fikri
 *
 */
public interface TriggerBehavior {
	
	/**
	 * Gets the rendering-ready color that represents a specific trigger color.
	 * @param color the trigger color we want to render.
	 * @return a Slick2D color object that matches to the trigger color we want to display, this can be passed to
	 * render methods.
	 */
	public static Color getRenderColor(TriggerColor color) { 
		switch (color) {
		case RED:
			return Color.red;
		case BLUE:
			return Color.blue;
		case AQUA:
			return Color.cyan;
		case PURPLE:
			return Color.pink;
		case GOLD:
			return Color.orange;
		case GREEN:
			return Color.green;
		default:
			return Color.transparent;
		}
	}
	
	/**
	 * Sets the state of the device.
	 * @param newState true to turn the device on, false to turn the device off.
	 */
	public default void setState(boolean newState) {
		if (newState) {
			this.onActivation();
		} else {
			this.onDeactivation();
		}
		this.onStateChange(newState);
	}
	
	/**
	 * Gets the trigger color of the current device.
	 * @return the trigger color associated to the device.
	 */
	public TriggerColor getColor();
	/**
	 * Gets whether the device is active or not.
	 * @return boolean representing the state of the device.
	 */
	public boolean isActive();
	/**
	 * Sets the trigger color associated with the device.
	 * @param color the new color desired to be associated to the device.
	 */
	public void setTriggerColor(TriggerColor color);
	
	
	//callbacks
	
	/**
	 * A callback method that is called automatically when the state of the device is changed
	 * @param value the new value of the trigger state, true for activated and false otherwise.
	 */
	public default void onStateChange(boolean value) {
		
	}
	/**
	 * A callback method that is called automatically when the device is activated.
	 */
	public default void onActivation() {
		
	}
	/**
	 * A callback method that is called automatically when the device is deactivated.
	 */
	public default void onDeactivation() {
		
	}
}
