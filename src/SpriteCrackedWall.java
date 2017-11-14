/**
 * A class that defines the behaviors of the cracked wall, the class does not store anything in particular except for the default
 * sprite - as the destroying behavior is defined in SpriteExplosive.
 * @author Naufal Fikri
 *
 */
public class SpriteCrackedWall extends Sprite{
	
	public static final String DEFAULT_SPRITE = "cracked_wall";
	
	/**
	 * Instantiates one new Cracked Wall object.
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteCrackedWall(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
	}
	
	/**
	 * Instantiates one new Cracked Wall object with the default cracked wall sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteCrackedWall(float x, float y, int renderPriority) {
		super(SpriteCrackedWall.DEFAULT_SPRITE, x, y, renderPriority);
		this.setSolid(true);
	}

}
