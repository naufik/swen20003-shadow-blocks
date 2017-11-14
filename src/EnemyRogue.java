public class EnemyRogue extends Enemy implements UnitBehavior {

	/** The initial facing direction of the rogue. */
	public static final Direction INITIAL_DIRECTION = Direction.LEFT;
	
	private Direction walkDirection;
	
	public EnemyRogue(String imageSrc, float x, float y) {
		super(imageSrc, x, y, Enemy.DEFAULT_RENDER_PRIORITY);
		this.walkDirection = INITIAL_DIRECTION;
		this.setPushBlocks(true);
	}
	
	private void move() {
		if (!this.canWalkTo(this.walkDirection)) {
			this.reverse();
		} else {
		this.walk(this.walkDirection);
		}
	}
	
	private void reverse() {
		this.walkDirection = Direction.reverse(this.walkDirection);
	}
	
	@Override
	public void onPlayerWalk(boolean walkSuccess, float newX, float newY, Direction dir) {
		this.move();
	}
	
	@Override
	public void onUnitWalk(Sprite s, boolean walkSuccess, float newX, float newY, Direction dir) {
		if (s.equals(this) && !walkSuccess) {
			this.getLocation().undo();
		}
	
	}
}
