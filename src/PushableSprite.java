import java.util.ArrayList;
import java.util.List;
/**
 * A class that represents a Sprite that is pushable by units and defines their behavior.
 * @author naufa
 *
 */
public class PushableSprite extends Sprite implements UndoBehavior, UnitBehavior{
	
	/** Maximum number of blocks can be pushed simultaneously.*/
	private static int MAX_PUSH_CHAIN = 1;
	/** The default render priority of the block */
	public static final int DEFAULT_RENDER_PRIORITY = 2;
	
	private boolean trigger = false;
	private List<Float> xHist;
	private List<Float> yHist;
	
	/**
	 * Instantiates a new pushable sprit object, with no extra behavior.
	 * @param imageSrc the image source of the sprite
	 * @param x the initial x position
	 * @param y the inital y position
	 */
	public PushableSprite(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
		xHist = new ArrayList<Float>();
		yHist = new ArrayList<Float>();
	}
	
	/**
	 * Check whether the pushable sprite is able to trigger objectives or targets.
	 * @return boolean whether the sprite triggers objectives.
	 */
	public boolean isObjectiveTrigger() {
		return trigger;
	}
	
	/**
	 * Sets whether the pushable sprite is able to trigger objectives or targets.
	 * @param value
	 */
	public void setObjectiveTrigger(boolean value) {
		trigger = value;
	}
	
	/** 
	 * 'Attempt' pushing the block to location x,y
	 * @param x coordinate x
	 * @param always 
	 * @return boolean whether push is successful.
	 */
	public boolean tryPushTo(Direction dir, Sprite source, int chain) {
		float x = this.getX();
		float y = this.getY();
		switch(dir) {
			case UP:
				--y;
				break;
			case DOWN:
				++y; break;
			case LEFT:
				--x; break;
			case RIGHT:
				++x; break;
			case NONE:
				return false;
		}
		List<Sprite> spritesAtDestination = this.getNeighboringSprites(dir);
		if (spritesAtDestination.size() == 0) return false;
		for(Sprite s : this.getNeighboringSprites(dir)) {
			if (s.isSolid() || chain >= PushableSprite.MAX_PUSH_CHAIN || s instanceof Player || s instanceof Enemy) {
				this.sendPushFailedEvent(source, dir);
				return false;
			} else {
				if (s instanceof PushableSprite) {
					PushableSprite w = (PushableSprite)s;
					if (w.tryPushTo(dir, source, chain + 1)){
						this.moveTo(x, y);
					} else {
						this.sendPushFailedEvent(source,dir);
						return false;
					}
				}
			}
				
		}
		if (this instanceof PushBehavior) {
			((PushBehavior)this).onPush(source, dir);
			if (source instanceof Player) {
				((PushBehavior)this).onPlayerPush(dir);
			}
		}
		this.moveTo(x, y);
		return true;
		}
	
	private void sendPushFailedEvent(Sprite source, Direction dir) {
		if (this instanceof PushBehavior)
		{
			((PushBehavior)this).onPushFailed(source,dir);
		}
	}
	
	private void recordUndoHistory() {
		this.xHist.add(0, this.getX());
		this.yHist.add(0, this.getY());
		if (xHist.size() > 20) {
			xHist.remove(xHist.size() - 1);
			yHist.remove(yHist.size() - 1);
		}
	}

	@Override
	public boolean isUndoPossible() {
		return (!yHist.isEmpty() && !xHist.isEmpty());
	}

	@Override
	public void onUndo() {
		this.moveTo(xHist.get(0), yHist.get(0));
		xHist.remove(0);
		yHist.remove(0);
	}
	
	@Override
	public void preUnitWalk(Sprite src, float newX, float newY, Direction dir) {
		if (src instanceof Player) {
			this.recordUndoHistory();
		}
	}
}
