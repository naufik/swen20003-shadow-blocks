/**
 * An interface that defines the behavior of an object when it is undone.
 * @author Naufal Fikri
 *
 */
public interface UndoBehavior {
	/**
	 * Checks whether it is possible to undo the sprite or not.
	 * @return boolean representing whether undo is possible or not.
	 */
	public boolean isUndoPossible();
	
	/**
	 * Undoes the state of an object to a previous state.
	 */
	public default void undo() {
		if (this.isUndoPossible()) {
			this.onUndo();
		}
	}

	/**
	 * A callback method that is called when an undo signal is received. This handles the actual undo behavior.
	 */
	public void onUndo();
}
