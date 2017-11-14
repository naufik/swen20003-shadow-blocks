/**
 * This class represents a countdown timer that keeps track of time.
 * @author Naufal Fikri
 *
 */
public class EventTimer {
	
	private int timeRegistered;
	private int period;
	private boolean looping;
	private boolean eventFired;
	private boolean paused;
	
	/**
	 * Initializes a new Timer.
	 * @param period the countdown duration of the timer.
	 * @param looping boolean whether the timer fires the event periodically or just fires the event once.
	 */
	public EventTimer(int period, boolean looping) {
		this.timeRegistered = 0;
		this.period = period;
		this.looping = looping;
		this.eventFired = false;
		this.paused = false;
	}
	
	/**
	 * Advances the timer by a discrete amount of time.
	 * @param time Time in milliseconds.
	 */
	public void update(int time) {
		if (!this.paused && !this.eventFired) {
			this.timeRegistered += time;
		}
	}
	
	/**
	 * Resets the timer.
	 */
	public void reset() {
		this.timeRegistered = 0;
		this.eventFired = false;
	}
	
	/**
	 * Pauses the timer, disallowing any updates to the timer to happen.
	 */
	public void pause() {
		this.paused = true;
	}
	
	/**
	 * Resumes the timer, reallowing any updates to the timer to happen.
	 */
	public void unpause() {
		this.paused = false;
	}
	
	/**
	 * Checks if the timer has ticked.
	 * @return true if the specific amount of time has passed.
	 */
	public boolean tick() {
		if (!this.eventFired && this.timeRegistered >= this.period) {
			this.timeRegistered = this.timeRegistered % this.period;
			if (!looping) eventFired = true;
			return true;
		}
		return false;
	}
	
	/**
	 * Updates the timer and checks if the timer has ticked at the same time.
	 * @param time the time to be added to the timer.
	 * @return boolean whether the 
	 */
	public boolean updateAndTick(int time) {
		this.update(time);
		return this.tick();
	}

}
