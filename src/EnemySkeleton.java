
public class EnemySkeleton extends Enemy implements TimedBehavior {
	
	/** The default initial direction of the skeleton object. */
	public static final Direction DEFAULT_INITIAL_DIRECTION = Direction.UP;
	/** The default walk interval (in milliseconds) of the skeleton enemy */
	public static final int WALK_INTERVAL = 1000;
	
	private EventTimer walkTimer;
	private Direction walkDirection;
	
	/**
	 * Instantiates a new enemy of type Shadow.
	 * @param imageSrc the Image source of the Shadow.
	 * @param x the initial x position of the sprite.
	 * @param y the initial y position of the sprite.
	 */
	public EnemySkeleton(String imageSrc, float x, float y) {
		super(imageSrc, x, y, Enemy.DEFAULT_RENDER_PRIORITY);
		this.walkDirection = EnemySkeleton.DEFAULT_INITIAL_DIRECTION;
		this.walkTimer = new EventTimer(EnemySkeleton.WALK_INTERVAL, true);
	}

	@Override
	public EventTimer getTimer() {
		// TODO Auto-generated method stub
		return this.walkTimer;
	}
	
	@Override
	public void onTimerTick() {
		if (!this.canWalkTo(this.walkDirection)) {
			this.reverse();
		}
		this.walk(this.walkDirection);
	}
	
	private void reverse() {
		this.walkDirection = Direction.reverse(this.walkDirection);
	}
}
