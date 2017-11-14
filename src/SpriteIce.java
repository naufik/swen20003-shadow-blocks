/**
 * An Ice Sprite, it is defined with the following behavior:
 * <ul>
 * <li>On Push: start sliding (i.e. pushing itself) every SLIDE_INTERVAL milliseconds towards the direction it was pushed to.</li>
 * </ul>
 * @author Naufal Fikri
 *
 */
public class SpriteIce extends PushableSprite implements PushBehavior, TimedBehavior {
	
	/** Default interval between slides of the ice block, in milliseconds */
	public static final int SLIDE_INTERVAL = 100;
	
	private EventTimer slideTimer;
	private Direction slidingDirection;
	/**
	 * Instantiate a new Ice Sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteIce(String imageSrc, float x, float y) {
		super(imageSrc, x, y, PushableSprite.DEFAULT_RENDER_PRIORITY);
		this.slidingDirection = Direction.NONE;
		this.slideTimer = new EventTimer(SpriteIce.SLIDE_INTERVAL,true);
		this.setObjectiveTrigger(true);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onTimerTick() {
		if(this.slidingDirection != Direction.NONE) {
			this.slide(this.slidingDirection);
		}
	}
	
	@Override
	public void onPush(Sprite source, Direction dir) {
		if (source != this) {
			this.slideTimer.reset(); 
			this.slidingDirection = dir;
		}
	}

	@Override
	public EventTimer getTimer() {
		return slideTimer;
	}

	/**
	 * Slides the ice block to a direction.
	 * @param dir The direction that it is sliding to.
	 */
	private void slide(Direction dir) {
		if (!super.tryPushTo(dir,this,0)) {
			this.slidingDirection = Direction.NONE;
		}
	}
	
}
