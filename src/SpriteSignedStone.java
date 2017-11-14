/**
 * A Signed Stone sprite, it is similar to a stone but it has a 'signature' or a 'message' that allows Targets to see 
 * two different stone sprites as distinct.
 * @author Naufal Fikri
 * @see SpritePasswordTarget
 */
public class SpriteSignedStone extends SpriteStone{

	private String message;
	
	public static SpriteStone parseNew(String imageSrc, float x, float y, String[] spriteProperties) {
		if (spriteProperties.length > 3) {
			return new SpriteSignedStone(imageSrc, x, y,spriteProperties[3]);
		}
		return new SpriteStone(imageSrc, x, y);
	}
	
	/**
	 * Instantiate a new Signed Stone sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteSignedStone(String imageSrc, float x, float y, String message) {
		super(imageSrc, x, y);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
