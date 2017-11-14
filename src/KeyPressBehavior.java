/**
 * This interface allows objects to respond to key presses given by the user playing the game.
 * @author Naufal Fikri
 *
 */
public interface KeyPressBehavior {
	/**
	 * Defines the behavior of the sprite when a key is pressed. Automatically invoked
	 * when a key is pressed. Has no implementation unless implemented by subclass
	 * @param key the key code (from Input class) of the key that is pressed.
	 * @param c character received from input (if a character key is pressed).
	 */
	public void onKeyPress(int key, char c);
}
