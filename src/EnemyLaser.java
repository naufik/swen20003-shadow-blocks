import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * A class representing an enemy of the type Laser Emitter
 * @author naufa
 *
 */
public class EnemyLaser extends Enemy implements TimedBehavior, UnitBehavior {
	
	public static final Color LASER_COLOR = new Color(1F,0F,0F);
	
	private List<Direction> movePath;
	private EventTimer moveTimer;
	private Direction laserFacing;
	private float endX;
	private float endY;
	
	
	public static EnemyLaser parseNew(String imageSrc, float x, float y, String[] spriteProperties) {
		int defaultDelay = 2000;
		Direction face = Direction.RIGHT;
		List<Direction> path = new ArrayList<Direction>();
		if (spriteProperties.length > 3) {
			face = Direction.parseDirection(spriteProperties[3]);
		}
		if (spriteProperties.length > 4) {
			for (int i = 0; i < spriteProperties[4].length(); ++i) {
				path.add(Direction.parseDirection(spriteProperties[4].substring(i,i+1)));
			}
		} else {
			path.add(Direction.NONE);
		}
		
		if (spriteProperties.length > 5) {
			try {
				defaultDelay = Integer.parseInt(spriteProperties[5]);
			} catch (Exception e) {
				defaultDelay = 2000;
			}
		}
		return new EnemyLaser(imageSrc, x, y, face, path, defaultDelay);
	}
	
	/**
	 * Initializes a new Laser Emitter Enemy.
	 * @param imageSrc the source of the image.
	 * @param x the initial x position.
	 * @param y the initial y position.
	 * @param face the direction that the laser is facing
	 * @param path the path of the laser emitter.
	 * @param delay the delay of the laser emitter between steps.
	 */
	public EnemyLaser(String imageSrc, float x, float y, Direction face, List<Direction> path, int delay) {
		super(imageSrc, x, y, Enemy.DEFAULT_RENDER_PRIORITY);
		this.laserFacing = face;
		this.movePath = path;
		this.moveTimer = new EventTimer(delay, true);
		this.endX = x;
		this.endY = y;
	}

	@Override
	public EventTimer getTimer() {
		// TODO Auto-generated method stub
		return moveTimer;
	}
	
	@Override
	public void onUnitWalk(Sprite source, boolean walkSuccess, float newX, float newY, Direction dir) {
		for (Sprite s : this.getSpritesInSight(this.laserFacing)) {
			if (s instanceof Player) {
				this.getLocation().destroySprite(s);
			}
		}
		if (source == this) {
			this.movePath.add(this.movePath.get(0));
			this.movePath.remove(0);
		}
	}
	
	@Override
	public void onTimerTick() {
		this.move();
	}
	
	private void move() {
		this.walk(movePath.get(0));
	}



	@Override
	public void additionalRender(Graphics g) {
		g.setColor(EnemyLaser.LASER_COLOR);
		g.drawLine(this.getPixelX()+0.5F*App.TILE_SIZE,
				this.getPixelY()+0.5F*App.TILE_SIZE,
				(Loader.getCornerX(this.getLocation()) + this.endX + 0.5F)*App.TILE_SIZE,
				(Loader.getCornerY(this.getLocation()) + this.endY + 0.5F)*App.TILE_SIZE);
		g.setColor(new Color(1F,1F,1F));
	}
	
	private List<Sprite> getSpritesInSight(Direction dir) {
		if (dir == Direction.NONE) {
			return this.getCollidingSprites();
		}
		List<Sprite> sprites = new ArrayList<Sprite>();
		float scanX = this.getX();
		float scanY = this.getY();
		while (!(this.getLocation().isBlocked(scanX,scanY))) {
			sprites.addAll(this.getLocation().getSpritesAt(scanX,scanY));
			switch(dir) {
			case UP:
				--scanY;
				break;
			case DOWN:
				++scanY;
				break;
			case LEFT:
				--scanX;
				break;
			case RIGHT:
				++scanX;
				break;
			default:
				break;
			}
			this.endX = scanX;
			this.endY = scanY;
		}
		return sprites;
	}
}
