/**
 * This interface defines behavior of an object in response to time passing.
 * @author Naufal Fikri
 *
 */
public interface TimedBehavior {
	/**
	 * Gets the timer object.
	 * @return A timer object that keeps track of time tied to the behavior.
	 */
	public EventTimer getTimer();
	
	/**
	 * A callback method that is invoked when the timer has ticked, i.e. when the countdown of the timer has ran out.
	 */
	public void onTimerTick();
	
	/**
	 * Notifies the implementing object that a certain time has passed from the this method is invoked.
	 * @param time the amount of time that have passed.
	 */
	public default void onTimePass(int time) {
		if (this.getTimer().updateAndTick(time)) {
			this.onTimerTick();
		}
	}
	

}
