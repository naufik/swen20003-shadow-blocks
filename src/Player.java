import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.*;

/**
 * A class representing a Player.
 * @author Naufal Fikri
 *
 */
public class Player extends Sprite implements KeyPressBehavior, UnitBehavior, UndoBehavior{

	private static final String defaultImage = "player_left";
	private static final int defaultRenderPriority = App.MAX_Z - 1;
	
	private List<Float> xHist;
	private List<Float> yHist;
	
	private boolean controlsEnabled;
	
	
	/**
	 * Creates a new Player object without initializing it in a world.
	 * @param imageSrc The name of the image source.
	 * @param x The initial x coordinate where the player is located.
	 * @param y The initial y coordinate where the player is located
	 * @param renderPriority The renderPriority of the player
	 */
	public Player(String imageSrc, float x, float y, int renderPriority) {
		//change the render priority appropriately.
		super(imageSrc, x, y, renderPriority);
		this.controlsEnabled = true;
		this.initialize();
	}
	
	/**
	 * Creates a new Player object without initializing it in a world and
	 * setting it the default render priority for the player.
	 * @param imageSrc The name of the image source.
	 * @param x The initial x coordinate where the player is located.
	 * @param y The initial y coordinate where the player is located
	 */
	public Player(String imageSrc, float x, float y) {
		//change the render priority appropriately.
		super(imageSrc, x, y, Player.defaultRenderPriority);
		this.controlsEnabled = true;
		this.initialize();
	}
	
	/**
	 * Creates a new Player object with the default player sprite and the default player
	 * render priority without initializing it in any world.
	 * @param x The initial x coordinate where the player is located.
	 * @param y The initial y coordinate where the player is located
	 */
	public Player(float x, float y) {
		//change the render priority appropriately.
		super(Player.defaultImage, x, y, Player.defaultRenderPriority);
		this.controlsEnabled = true;
		this.initialize();
	}
	
	
	private void initialize() { 
		this.xHist = new ArrayList<Float>();
		this.yHist = new ArrayList<Float>();
	}
	
	/**
	 * Defines the responses of the player object when a key is pressed. Automatically
	 * invoked when a key is pressed, do not invoke manually.
	 * @param key the key input that is received.
	 * @param c the character that is received in case the key pressed is a character key.
	 */
	@Override
	public void onKeyPress(int key, char c) {
		if (this.controlsEnabled) {
			switch(key) {
			case Input.KEY_W:
			case Input.KEY_UP:
				this.walk(Direction.UP);
				break;
			case Input.KEY_S:
			case Input.KEY_DOWN:
				this.walk(Direction.DOWN);
				break;
			case Input.KEY_D:
			case Input.KEY_RIGHT:
				this.walk(Direction.RIGHT);
				break;
			case Input.KEY_A:
			case Input.KEY_LEFT:
				this.walk(Direction.LEFT);
				break;
			case Input.KEY_E:
				this.playerAction();
				break;
			}
		}
	}

	/**
	 * Checks if it is possible to walk to a certain direction
	 * @param dir direction
	 * @return whether player can or cannot walk to the direction.
	 */
	public boolean canWalkTo(Direction dir) {
		List<Sprite> spritesAtDestination = this.getNeighboringSprites(dir);
		if (spritesAtDestination.size() == 0) return false;
		for (Sprite s : spritesAtDestination) {
			if (s instanceof PushableSprite) {
//				This part pushes the blocks when the player walks to 
				if (!((PushableSprite)s).tryPushTo(dir,this,0)) {
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
	private void walk(Direction dir) {
		float newX = this.getX();
		float newY = this.getY();
		
		for (Sprite s : this.getLocation().getAllSprites()) {
			if (s instanceof UnitBehavior) {
				((UnitBehavior)s).preUnitWalk(this, newX, newY, dir);
			}
		}
		
		switch (dir) {
		case UP:
			--newY;
			break;
		case DOWN:
			++newY;
			break;
		case LEFT:
			--newX;
			break;
		case RIGHT:
			++newX;
			break;
		default:
			break;
		}
		
		boolean success;
		if (success = this.canWalkTo(dir)) {
			this.moveTo(newX, newY);
		}
		
		
		for (Sprite s : this.getLocation().getAllSprites()) {
			if (s instanceof UnitBehavior) {
				((UnitBehavior)s).onUnitWalk(this, success, this.getX(), this.getY(), dir);
				((UnitBehavior)s).onPlayerWalk(success, this.getX(), this.getY(), dir);
			}
		}
	}
	
	@Override
	public boolean isUndoPossible() {
		return !this.xHist.isEmpty() && !this.yHist.isEmpty();
	}

	private void recordUndoHistory() {
		this.xHist.add(0, this.getX());
		this.yHist.add(0, this.getY());
		if (xHist.size() > 10) {
			xHist.remove(xHist.size() - 1);
			yHist.remove(yHist.size() - 1);
		}
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

	/**
	 * Casts the 'action' of the player.
	 */
	public void playerAction() {
		//To be implemented by children
	}
}
