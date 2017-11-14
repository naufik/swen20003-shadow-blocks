
public class SpriteConveyor extends Sprite implements TimedBehavior, CollisionBehavior {

	public static final int DEFAULT_PUSH_INTERVAL = 130;
	
	public static SpriteConveyor parseNew(String imageSrc, float x, float y, String[] properties) {
		Direction d = Direction.DEFAULT_DIRECTION;
		if (properties.length > 3) {
			d = Direction.parseDirection(properties[3]);
		}
		int time = 120;
		return new SpriteConveyor(imageSrc,x,y,0,d,time);
	}
	
	private EventTimer timer;
	private Direction pushDirection;
	

	public SpriteConveyor(String imageSrc, float x, float y, int renderPriority, Direction head, int time) {
		super(imageSrc, x, y, renderPriority);
		this.timer = new EventTimer(SpriteConveyor.DEFAULT_PUSH_INTERVAL, true);
		this.pushDirection = head;
		if (this.getImage() != null) {
			this.getImage().setRotation(Direction.getRotationAngle(this.pushDirection));
		}
	}
	
	@Override
	public EventTimer getTimer() {
		return timer;
	}
	
	@Override
	public void onTimerTick() {
		for (Sprite s : this.getCollidingSprites()) {
			if (s instanceof PushableSprite) {
				((PushableSprite)s).tryPushTo(pushDirection, this, 0);
			}
		}
	}
	
	@Override
	public void onCollisionStart(Sprite s) {
		if (s instanceof PushableSprite) {
			this.timer.reset();
		}
	}

}
