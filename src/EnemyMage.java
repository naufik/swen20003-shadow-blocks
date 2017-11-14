/**
 * A class representing an enemy of type mage.
 * @author Naufal Fikri
 */
public class EnemyMage extends Enemy implements UnitBehavior, WorldBehavior{

	/** The visual effect to be shown when a mage kills the player. */
	public static final VisualElement MAGE_KILL_DIALOG = new VisualElement("vfx/mage-on-kill");
	
	private Sprite target;
	
	/**
	 * Initializes a new enemy of type Mage.
	 * @param imageSrc the Image source of the Mage.
	 * @param x the initial x position of the sprite.
	 * @param y the initial y position of the sprite.
	 */
	public EnemyMage(String imageSrc, float x, float y) {
		super(imageSrc, x, y, Enemy.DEFAULT_RENDER_PRIORITY);
		//Used to avoid any kind of null pointer error.
		this.target = this;
	}
	
	private void setTarget(Sprite s) {
		this.target = s;
	}
	
	private void move() {
		float dx = this.target.getX() - this.getX();
		float dy = this.target.getY() - this.getY();
		Direction dir;
		if (Math.abs(dx) > Math.abs(dy)) {
			dir = (dx < 0 ? Direction.LEFT : Direction.RIGHT);
		} else {
			dir = (dy < 0 ? Direction.UP : Direction.DOWN);
		}
		this.walk(dir);
		
	}
	
	@Override
	public void onUnitWalk(Sprite s, boolean success, float newX, float newY, Direction dir) {
		//intentionally not using .equals();
		if (s == this.target) {
			this.move();
		}
	}
	
	
	@Override
	public void onSpriteSpawn(Sprite s)
	{
		if (s instanceof Player) {
			this.setTarget(s);
		}
	}
	
	@Override
	public void onKill(Sprite source, Sprite victim) {
		if (source == this) {
			EnemyMage.MAGE_KILL_DIALOG.displayAt(
					this.getPixelX() - (float)0.2*EnemyMage.MAGE_KILL_DIALOG.getImage().getWidth(),
					this.getPixelY() - (float)0.6*EnemyMage.MAGE_KILL_DIALOG.getImage().getHeight(),
					1500);
		}
	}
}
