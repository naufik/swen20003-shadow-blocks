/**
 * A Fake Target sprite with behavior defined as follows:
 * <ul>
 * <li>If a player pushes an objective pushable sprite into the fake target, it will be killed and the level will eventually be failed</li>
 * </ul>
 * @author Naufal Fikri
 *
 */
public class SpriteFakeTarget extends Sprite implements WorldBehavior, CollisionBehavior {
	private Sprite player;
	
	/**
	 * Instantiates a fake target sprite.
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteFakeTarget(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onSpriteSpawn(Sprite s) {
		if (s instanceof Player) {
			this.player = s;
		}
	}
	
	@Override
	public void onCollision(Sprite s) {
		if (s instanceof PushableSprite) {
			if (((PushableSprite)s).isObjectiveTrigger()) {
				this.getLocation().destroySprite(player);
			}
		}
	}
	

}
