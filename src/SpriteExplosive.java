import java.util.List;
/**
 * A TNT or Explosive, defined with the following behaviors:
 * <ul>
 * <li>Acts like other Pushable Sprites</li>
 * <li>Does not trigger objectives</li>
 * </li>If pushed into a tile witha cracked wall, displays an explosion visual effect and destroys the cracked wall.</li>
 * </ul>
 * @author Naufal Fikri
 *
 */
public class SpriteExplosive extends PushableSprite implements PushBehavior {
	
	/** The default image to be shown when an explosion happens */
	public static final String DEFAULT_EXPLOSION_IMAGE = "explosion";
	private VisualElement defaultExplosionEffect;
	
	/**
	 * Instantiates a new Explosive Sprite
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteExplosive(String imageSrc, float x, float y) {
		super(imageSrc, x, y, PushableSprite.DEFAULT_RENDER_PRIORITY);
		defaultExplosionEffect = new VisualElement(DEFAULT_EXPLOSION_IMAGE);
	}
	
	@Override
	public void onPushFailed(Sprite source, Direction dir) {
		try {
			this.destroyWith(this.getNeighboringSprites(dir));
		} catch (Exception e) {
			
		}
	}
	
	private void destroyWith(List<Sprite> sprites) {
		boolean destroyableExists = false;
 		for (Sprite s : sprites) {
			if (s instanceof SpriteCrackedWall) {
		 		this.getLocation().destroySprite(s);
		 		defaultExplosionEffect.displayAt(
	 					s.getPixelX() - (float)0.5*defaultExplosionEffect.getWidth(),
	 					s.getPixelY() - (float)0.5*defaultExplosionEffect.getHeight(),
	 					400);
		 		destroyableExists = true;
			}
		}
 		if(destroyableExists) {
 			this.getLocation().destroySprite(this);
 		}
 		
	}

}
