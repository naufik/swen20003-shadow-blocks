import java.util.*;
import org.newdawn.slick.*;
/**
 * This class controls the behaviors of non-game elements that appear on screen. (i.e. the GUI).
 * @author naufa
 *
 */
public class Overlay {
	private List<VisualElement> elements;
	private App listener;
	
	/**
	 * Initializes a new overlay
	 * @param listener the App object that will listen and respond to changes that happen in the GUI.
	 */
	public Overlay(App listener) {
		this.elements = new ArrayList<>();
		this.listener = listener;
	}
	
	/**
	 * Obtains the elements that exists in the GUI.
	 * @return A copy of the list of element that is displayed in the GUI.
	 */
	public List<VisualElement> getElements() {
		return new ArrayList<>(this.elements);
	};
	
	/**
	 * Renders the overlay
	 * @param g The slick Graphics object to handle rendering.
	 */
	public void render(Graphics g) {
		for (VisualElement elem : this.getElements()) {
			elem.render(g);
		}
	}
	/**
	 * Updates the overlay to respond to input and time changes.
	 * @param input the Slick2D Input object representing an input device
	 * @param delta the time that has passed since the last update.
	 */
	public void update(Input input, int delta) {
		for (VisualElement elem : this.getElements()) {
			elem.update(input, delta);
			if (!elem.isVisible()) {
				this.elements.remove(elem);
			}
		}
	}
	
	/**
	 * Show a specific visual element onscreen.
	 * @param element the visual element that needs to be shown.
	 */
	public void show(VisualElement element) {
		if (!this.elements.contains(element)) {
			this.elements.add(element);
			element.setParent(this);
		}
	}
	
	/**
	 * Invokes the mouse press behavior of member elements.
	 * @param button The integer representing the mouse button that is pressed
	 * @param x the x-coordinate of the cursor
	 * @param y the y-coordinate of the cursor
	 */
	public void invokeMousePressBehavior(int button, int x, int y) {
		for (VisualElement ve : this.getElements()) {
			if (ve instanceof MouseBehavior) {
				((MouseBehavior)ve).onMousePress(button, x, y);
			}
		}
	}

	/**
	 * Invokes the mouse press release of member elements.
	 * @param button The integer representing the mouse button that is pressed
	 * @param x the x-coordinate of the cursor
	 * @param y the y-coordinate of the cursor
	 */
	public void invokeMouseReleaseBehavior(int button, int x, int y) {
		for (VisualElement ve : this.getElements()) {
			if (ve instanceof MouseBehavior) {
				((MouseBehavior)ve).onMouseRelease(button, x, y);
			}
		}
	}

	/**
	 * Invokes the mouse move behavior of member elements.
	 * @param oldx the old x-coordinate of the cursor
	 * @param oldy the old y-coordinate of the cursor
	 * @param newx the new x-coordinate of the cursor
	 * @param newy the new y-coordiante of the cursor
	 */
	public void invokeMouseMoveBehavior(int oldx, int oldy, int newx, int newy) {
		for (VisualElement ve : this.getElements()) {
			if (ve instanceof MouseBehavior) {
				((MouseBehavior)ve).onMouseMove(oldx, oldy, newx, newy);
				if (ve.withinBounds(newx, newy) && !ve.withinBounds(oldx,oldy)){
					((MouseBehavior)ve).onMouseEnter();
				} else if (ve.withinBounds(oldx, oldy) && !ve.withinBounds(newx, newy)) {
					((MouseBehavior)ve).onMouseLeave();
				}
			}
		}
	}
	/**
	 * Clears all the elements in the GUI
	 */
	public void clear() {
		this.elements.clear();
	}
	
	/**
	 * Sends an event to the listener App when a change in any member in the GUI is clicked.
	 * @param sender the visual element where the event happens
	 * @param message the message contents of the event
	 */
	public void memberClicked(VisualElement sender, Object message) {
		listener.onGUIEvent(sender, message);
	}
}
