/**
 * A class representing an enemy of the type Landmine
 * @author Naufal Fikri
 *
 */
public class EnemyLandmine extends Enemy implements UnitBehavior{

	public static final int DEFAULT_RENDER_PRIORITY = 0;
	
	private VisualElement defaultExplosionEffect;
	
	/**
	 * Initializes a new Landmine Enemy.
	 * @param imageSrc the source of the image.
	 * @param x the initial x position.
	 * @param y the initial y position.
	 */
	public EnemyLandmine(String imageSrc, float x, float y) {
		super(imageSrc, x, y, DEFAULT_RENDER_PRIORITY);
		defaultExplosionEffect = new VisualElement(SpriteExplosive.DEFAULT_EXPLOSION_IMAGE);
	}
	
	@Override
	public void onKill(Sprite source, Sprite victim) {
		if (source == this) {
			defaultExplosionEffect.displayAt(
 					this.getPixelX() - (float)0.5*defaultExplosionEffect.getWidth(),
 					this.getPixelY() - (float)0.5*defaultExplosionEffect.getHeight(),
 					400);
		}
	}
}
