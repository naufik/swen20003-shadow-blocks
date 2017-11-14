/**
 * A class that defines the behavior of a Blueprint Sprite.
 * 
 * Intended behaviors of the Blueprint:
 * <ul>
 * <li> Intended to be placed by Donald Trump player objects to build a wall.
 * <li> When placed, the blueprint stays as is. However once nothing is stepping on it the blueprint will turn into a cracked wall.
 * </ul>
 * @author Naufal Fikri
 *
 */
public class SpriteBlueprint extends Sprite implements CollisionBehavior {

	private static final String DEFAULT_IMAGE_SOURCE = "blueprint";
	private static final int DEFAULT_RENDER_PRIORITY = 1;
	
	public SpriteBlueprint(float x, float y) {
		super(DEFAULT_IMAGE_SOURCE, x, y, DEFAULT_RENDER_PRIORITY);
	}
	
	@Override
	public void onCollisionEnd(Sprite collidingSprite) {
		boolean hasUnit = false;
		for (Sprite s : this.getCollidingSprites()) {
			if (s instanceof Player || s instanceof PushableSprite || s instanceof Enemy) {
				hasUnit = true;
			}
		}
		if (!hasUnit) {
			Sprite wall = new SpriteCrackedWall(this.getX(),this.getY(),1);
			this.getLocation().addToWorld(wall);
			this.getLocation().destroySprite(this);
		}
	}
}
