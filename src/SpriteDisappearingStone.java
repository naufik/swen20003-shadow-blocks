/**
 * A Disappearing Stone, is acts like a stone but with the following extra behaviors:
 *<ul>
 *<li>Starts off visible for DEFAULT_SHOW_TIME milliseconds.</li>
 *<li>Goes invisible for DEFAULT_HIDE_TIME milliseconds.</li>
 *<ul>
 *and the process repeats over and over again. 
 * @author Naufal Fikri
 *
 */
public class SpriteDisappearingStone extends SpriteStone implements TimedBehavior {

	public static final int DEFAULT_SHOW_TIME = 2000;
	public static final int DEFAULT_HIDE_TIME = 8000;
	
	private EventTimer timer;
	
	/**
	 * Parses a new Disappearing Stone from a level file entry.
	 * @param spriteImage The source image for the sprite.
	 * @param x The initial x coordinate of the sprite
	 * @param y The initial y coordinate of the sprite.
	 * @return
	 */
	public static SpriteDisappearingStone parseNew(String spriteImage, float x, float y) {
		return new SpriteDisappearingStone("stone", x, y);
	}
	
	/**
	 * Instantiates a new Disappearing Stone sprite.
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteDisappearingStone(String imageSrc, float x, float y) {
		super(imageSrc, x, y);
		this.timer = new EventTimer(DEFAULT_SHOW_TIME, false);
	}
	
	@Override
	public EventTimer getTimer() {
		// TODO Auto-generated method stub
		return this.timer;
	}
	
	@Override
	public void onTimerTick() {
		if (this.isVisible()) {
			this.hide();
			this.timer = new EventTimer(DEFAULT_HIDE_TIME, false);
		} else {
			this.show();
			this.timer = new EventTimer(DEFAULT_SHOW_TIME, false);
		}
	}
}
