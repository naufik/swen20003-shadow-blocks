import org.newdawn.slick.*;
/**
 * This defines the visual element that can be displayed on screen such as a UI elements, buttons, and visual effects.
 * There are some major differences between a Visual Element and a Sprite:
 * <ul>
 * <li>A visual element is not tied to the world in any way, but to an overlay</li>
 * <li>The coordinates of a visual element represents screen coordinates rather than tile/world coordinates</li>
 * </ul>
 * @author Naufal Fikri
 *
 */
public class VisualElement{
	
	private String imageSource;
	private Image effectImage;
	private boolean hasImage;
	private boolean visible;
	private EventTimer timer;
	private boolean hasTimer;
	private Overlay parent;
	
	private float x;
	private float y;
	private float height;
	private float width;
	
	/**
	 * Instantiates the visual element
	 * @param imageSource the image source of the element.
	 */
	public VisualElement(String imageSource) {
		this.imageSource = imageSource;
		try {
			this.effectImage = new Image("res/" + imageSource + ".png");
			this.hasImage = true;
			this.width = this.effectImage.getWidth();
			this.height = this.effectImage.getHeight();
		} catch (Exception e) {
			this.effectImage = null;
			this.hasImage = false;
			this.width = 0;
			this.height = 0;
		}
		this.visible = false;
		this.x = 0;
		this.y = 0;
		this.hasTimer = false;
	}
	
	/**
	 * Checks if the screen coordinate x,y is within the bounds of this visual element.
	 * @param x the x coordinate to check
	 * @param y the y coordinate to check
	 * @return
	 */
	public boolean withinBounds(float x, float y) {
		float width = this.getWidth();
		float height = this.getHeight();
		return (this.x <= x && this.y <= y && x <= this.x + width && y <= this.y + height);
	}
	
	/**
	 * Obtains the height of the visual element.
	 * @return the height of the object
	 */
	public float getHeight() {
		return this.height;
	}

	/**
	 * Obtains the width of the visual element.
	 * @return the width of the element.
	 */
	public float getWidth() {
		return this.width;
	}

	/** 
	 * Sets the position of the visual element
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void setPosition(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Obtains the X coordinate of the visual element.
	 * @return the x coordinate
	 */
	public float getX() {
		return this.x;
	}
	/**
	 * Obtains the Y coordinate of the visual element
	 * @return the y coordinate
	 */
	public float getY() {
		return this.y;
	}
	
	/**
	 * Updates the visual element
	 * @param input the input device provided by Slick2D
	 * @param delta time passed since last update
	 */
	public void update(Input input, int delta) {
		if (this.hasTimer) {
			if (timer.updateAndTick(delta)) {
				this.visible = false;
				timer.pause();
			}
		}
	}
	/**
	 * Renders the visual elment
	 * @param g the graphics handler provided by Slick2D
	 */
	public void render(Graphics g) {
		if (this.hasImage && this.visible) {
			g.drawImage(effectImage, this.x,this.y);
		}
	}
	/**
	 * Sets the image of the visual element
	 * @param img the image object
	 */
	public void setImage(Image img) {
		this.effectImage = img;
	}
	/**
	 * Gets the image object associated with the visual element
	 * @return the image object associated with the visual element
	 */
	public Image getImage() {
		return this.effectImage;
	}
	
	/**
	 * Gets the image source associated with this element
	 * @return the name of the image associated with the visual element
	 */
	public String getImageSource() {
		return this.imageSource;
	}
	/**
	 * Displays the visual element on screen indefinitely
	 * @param x the x coordinate to display it at
	 * @param y the y coordinate to display it at
	 */
	public void displayAt(float x, float y) {
		this.visible = true;
		this.x = x;
		this.y = y;
		this.hasTimer = false;
		GUIManager.show(this);
	}
	/**
	 * Display the visual element on screen for a period of time.
	 * @param x the x coordinate to display it at
	 * @param y the y coordinate to display it at
	 * @param time the amount of time (in milliseconds) to display the thing.
	 */
	public void displayAt(float x, float y, int time) {
		this.visible = true;
		this.x = x;
		this.y = y;
		timer = new EventTimer(time, false);
		this.hasTimer = true;
		GUIManager.show(this);
	}
	
	/**
	 * Checks if the visual element is visible
	 * @return
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Attaches this visual element to a parent overlay
	 * @param overlay
	 */
	public void setParent(Overlay overlay) {
		this.parent = overlay;
	}
	
	/**
	 * Gets the parent overlay of this visual element.
	 * @return the Overlay object of the parent.
	 */
	public Overlay getParent() {
		return this.parent;
	}

	/**
	 * Displays the visual element indefinitely at the same place it last displayed.
	 */
	public void display() {
		this.visible = true;
		this.hasTimer = false;
	}
	
	/**
	 * Hides the visual element
	 */
	public void hide() {
		this.visible = false;
	}
	
	/**
	 * Sets the width of this visual element, assuming it does not have an image associated with it. Otherwise the width of the visual
	 * element is retained and is always equal to the width of the image representing it.
	 * @param w the new width
	 */
	public void setWidth(float w) {
		if (!this.hasImage) {
			this.width = w;
		}
	}
	
	/**
	 * Sets the height of this visual element, assuming it does not have an image associated with it. Otherwise the height of the visual
	 * element is retained and is always equal to the height of the image representing it.
	 * @param w the new width
	 */
	public void setHeight(float h) {
		if (!this.hasImage) {
			this.width = h;
		}
	}
}