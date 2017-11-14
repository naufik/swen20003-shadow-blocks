import java.util.List;
/**
 * A class representing an enemy object.
 * @author naufa
 */
public class Enemy extends Sprite implements CollisionBehavior {

	/** The default render priority for an enemy */
	public static final int DEFAULT_RENDER_PRIORITY = App.MAX_Z - 1;
	
	private boolean canPushBlocks = false;
	
	/** Constructs a simple enemy object. */
	public Enemy(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
	}
	
	@Override
	public void onCollisionStart(Sprite s) {
		if (s instanceof Player) {
			this.kill(s);
		}
	}
	
	private void kill(Sprite s) {
		if (this instanceof UnitBehavior) {
			((UnitBehavior)this).onKill(this, s);
		}
		this.getLocation().destroySprite(s);
	}
	
	/**
	 * Checks whether it is possible to walk to a certain direction.
	 * @param dir the direction we want to check.
	 * @return boolean representing whether the movement is possible.
	 */
	public boolean canWalkTo(Direction dir) {
		List<Sprite> spritesAtDestination = this.getNeighboringSprites(dir);
		if (spritesAtDestination.size() == 0) return false;
		for (Sprite s : spritesAtDestination) {
			if (s instanceof PushableSprite) {
				if (this.canPushBlocks) {
					if (!((PushableSprite)s).tryPushTo(dir,this,0)) {
						return false;
					}
				} else {
					return false;
				}
			} else if (s.isSolid()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Try walking into a direction, does nothing if it is impossible to do so.
	 * @param dir the direction the player is supposed to walk to.
	 */
	public void walk(Direction dir) {
		boolean success = this.canWalkTo(dir);
		switch (dir) {
		case UP:
			if (success) {
				this.moveTo(this.getX(),this.getY()-1);
			}
			break;
		case DOWN:
			if (success) {
				this.moveTo(this.getX(), this.getY()+1);
			}
			break;
		case LEFT:
			if (success) {
				this.moveTo(this.getX()-1, this.getY());
			}
			break;
		case RIGHT:
			if (success) {
				this.moveTo(this.getX()+1, this.getY());
			}
			break;
		default:
			break;
		}
		for (Sprite s : this.getLocation().getAllSprites()) {
			if (s instanceof UnitBehavior) {
				((UnitBehavior)s).onUnitWalk(this, success, this.getX(), this.getY(), dir);
			}
		}
	}
	
	/**
	 * Sets whether the unit is able to push blocks or not.
	 * @param value boolean whether the object is able to push blocks or not.
	 */
	public void setPushBlocks(boolean value) {
		this.canPushBlocks = value;
	}
}
