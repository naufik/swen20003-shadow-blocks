import java.util.List;

/**
 * A target sprite that acts as an objective: 
 * It is fulfilled when a PushableSprite that triggers objectives lie on top of it. 
 * @author Naufal Fikri
 *
 */
public class SpriteFloorTarget extends Sprite implements ObjectiveBehavior {
	/**
	 * Instantiates the sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteFloorTarget(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isFulfilled() {
		List<Sprite> spritesHere = this.getLocation().getSpritesAt(this.getX(),this.getY());
		for (Sprite s : spritesHere) {
			if (s instanceof PushableSprite) {
				return ((PushableSprite)s).isObjectiveTrigger();
			}
		}
		return false;
	}

}
