/**
 * An abstract object that is a superclass of all Sprites that can act as triggers.
 * @author Naufal Fikri
 *
 */
public abstract class TriggerSprite extends Sprite{
	
	private TriggerColor color;
	private boolean activated;
	
	//implement stuff here later
	private boolean negated;
	
	/**
	 * Instantiate a new Trigger Sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 * @param renderPriority the priority that the sprite is rendered, higher number means it will be rendered last and display on top.
	 */
	public TriggerSprite(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
		// TODO Auto-generated constructor stub
		this.color = TriggerColor.UNIVERSAL; 
		this.activated = false;
		this.negated = false;
	}
	/**
	 * Instantiate a new Trigger Sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 * @param color the color associated with the trigger.
	 * @param renderPriority the priority that the sprite is rendered, higher number means it will be rendered last and display on top.
	 */
	public TriggerSprite(String imageSrc, float x, float y, int renderPriority, TriggerColor color) {
		super(imageSrc, x, y, renderPriority);
		// TODO Auto-generated constructor stub
		this.color = color;
		this.activated = false;
		this.negated = false;
	}
	
	/**
	 * Gets the color associated with the trigger
	 * @return the color associated with the trigger
	 */
	public TriggerColor getColor() { 
		return color;
	}
	
	/**
	 * Sets the color associated with the trigger
	 * @param the color associated with the trigger
	 */
	public void setTriggerColor(TriggerColor color) {
		this.color = color;
	}
	
	/**
	 * Gets whether the trigger is activated or not. 
	 * @return boolean whether the trigger is activated or not.
	 */
	public boolean isActivated() {
		return activated;
	}
	/**
	 * Sets the trigger state of the trigger
	 * @param value boolean whether the trigger should be activated or not.
	 */
	public void trigger(boolean value)
	{
		this.activated = value;
		this.signal();
	}
	
	/**
	 * Sends a signal to the world containing this trigger to activate all the devices with the same color.
	 */
	private void signal() {
		this.getLocation().setTriggerStates(this.color, this.activated ^ this.negated);
	}
}
