import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * An auto-stone sprite, that is a sprite that acts like a stone but is able to move itself autonomously in an interval
 * of time.
 * @author Naufal Fikri
 *
 */
public class SpriteAutoblock extends PushableSprite implements TimedBehavior, PushBehavior {

	private Image directionArrow;
	private EventTimer timer;
	private Direction moveDirection;
	
	public static SpriteAutoblock parseNew(String imageSrc, float x, float y, String[] properties) {
		int time = 5000;
		Direction d = Direction.RIGHT;
		if (properties.length > 3) {
			time = Integer.parseInt(properties[3]);
		} 
		if (properties.length > 4) {
			d = Direction.parseDirection(properties[4]);
		}
		return new SpriteAutoblock(imageSrc, x, y, d, time);
	}
	
	/**
	 * Instantiates a new Auto-Stone sprite.
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 * @param head the direction that the block is heading.
	 * @param time the interval of time needed before the stone moves by one tile.
	 */
	public SpriteAutoblock(String imageSrc, float x, float y, Direction head, int time) {
		super(imageSrc, x, y, PushableSprite.DEFAULT_RENDER_PRIORITY);
		this.timer = new EventTimer(time, true);
		this.moveDirection = head;
		try {
			this.directionArrow = new Image(App.RESOURCES_DIR + "arrow" + App.IMG_FORMAT);
			this.directionArrow.setRotation(Direction.getRotationAngle(this.moveDirection));
		} catch (SlickException e) {
			
		}
		this.setObjectiveTrigger(true);
	}

	@Override
	public EventTimer getTimer() {
		return timer;
	}
	
	@Override
	public void onTimerTick() {
		this.move();
	}
	
	private void move() {
		this.tryPushTo(moveDirection, this, 0);
	}
	
	@Override
	public void onPush(Sprite source, Direction d) {
		timer.reset();
	}
	
	@Override
	public void additionalRender(Graphics g) {
		this.directionArrow.draw(this.getPixelX(), this.getPixelY());
	}

}
