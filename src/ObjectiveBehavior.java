/**
 * This interface allows implementers to be able to act as an objective and check if they are already fulfilled
 * @author Naufal Fikri
 *
 */
public interface ObjectiveBehavior {
	/**
	 * Test whether the objective is already fulfilled or not.
	 * @return boolean whether the objective is already fulfilled.
	 */
	public boolean isFulfilled();

}
