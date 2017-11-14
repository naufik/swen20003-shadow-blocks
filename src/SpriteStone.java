/**
 * A classic Stone sprite that can trigger targets and fulfill them, allowing the player to advance to the next level
 * @author Naufal Fikri
 * @see SpriteFloorTarget
 *
 */
public class SpriteStone extends PushableSprite {

	/**
	 * Instantiate a new Stone Sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteStone(String imageSrc, float x, float y) {
		super(imageSrc, x, y, PushableSprite.DEFAULT_RENDER_PRIORITY);
		this.setObjectiveTrigger(true);
	}

}
