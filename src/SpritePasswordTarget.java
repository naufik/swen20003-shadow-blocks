import java.util.List;
/**
 * A Password Target sprite
 * A specific type of target that is only fulfilled if and only if:
 * <ul>
 * <li>A Pushable Sprite that triggers objectives share the same tile coordinate as the target AND</li>
 * <li>The Pushable Sprite is type of 'Signed Stone' AND </li>
 * <li>The Signed Stone carries a message which is equal to this Sprite's password.</li>
 * An example implementation of this is at level 42 and level 90.
 * </ul>
 * @author Naufal Fikri
 * @see SpriteSignedStone
 *
 */
public class SpritePasswordTarget extends SpriteFloorTarget {
	private String password;
	
	public static SpriteFloorTarget parseNew(String imageSrc, float x, float y, String[] spriteProperties) {
		if (spriteProperties.length > 3) {
			return new SpritePasswordTarget(imageSrc, x, y, 0, spriteProperties[3]);
		}
		return new SpriteFloorTarget(imageSrc, x, y, 0);
	}
	
	/**
	 * Instantiate a new Password Target sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpritePasswordTarget(String imageSrc, float x, float y, int renderPriority, String password) {
		super(imageSrc, x, y, renderPriority);
		this.password = password;
	}
	
	@Override
	public boolean isFulfilled() {
		List<Sprite> spritesHere = this.getCollidingSprites();
		for (Sprite s : spritesHere) {
			if (s instanceof SpriteSignedStone) {
				if (((SpriteSignedStone)s).getMessage().equals(this.password)) {
					return true;
				}
			}
		}
		return false;
	}

}
