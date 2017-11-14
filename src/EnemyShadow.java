
public class EnemyShadow extends Enemy implements UnitBehavior {
	
	private static final VisualElement SHADOW_KILL_DIALOG  = new VisualElement("vfx/shadow-on-kill");
	
	private boolean invertX;
	private boolean invertY;
	
	public static EnemyShadow parseNew(String imageSrc, float x, float y, String[] properties) {
		boolean invX = false;
		boolean invY = false;
		if (properties.length > 3) {
			String invert = properties[3];
			if (invert.contains("y")) {
				invY = true;
			} 
			if (invert.contains("x")) {
				invX = true;
			}
		}
		return new EnemyShadow(imageSrc,x,y, invX, invY);
	}
	
	/**
	 * Instantiates a new enemy of type Shadow.
	 * @param imageSrc the Image source of the Shadow.
	 * @param x the initial x position of the sprite.
	 * @param y the initial y position of the sprite.
	 * @param invertX whether the x-axis of the player control is inverted.
	 * @param invertY whether the y-axis of the player control is inverted.
	 */
	public EnemyShadow(String imageSrc, float x, float y, boolean invertX, boolean invertY) {
		super(imageSrc, x, y, Enemy.DEFAULT_RENDER_PRIORITY);
		this.invertX = invertX;
		this.invertY = invertY;
		this.setPushBlocks(true);
	}
	
	@Override
	public void onUnitWalk(Sprite s, boolean walkSuccess, float newX, float newY, Direction dir) {
		if (walkSuccess && s instanceof Player) {
			this.walk(decideDirection(dir));
		}
	}
	
	private Direction decideDirection(Direction playerDirection) {
		switch (playerDirection) {
		case LEFT:
		case RIGHT:
			return (this.invertX ? Direction.reverse(playerDirection) : playerDirection);
		case UP:
		case DOWN:
			return (this.invertY ? Direction.reverse(playerDirection) : playerDirection);
		default:
			return playerDirection;
		}
	}
	
	@Override
	public void onKill(Sprite source, Sprite victim) {
		EnemyShadow.SHADOW_KILL_DIALOG.displayAt(
				this.getPixelX() - (float)0.2*EnemyShadow.SHADOW_KILL_DIALOG.getWidth(),
				this.getPixelY() - (float)0.6*EnemyShadow.SHADOW_KILL_DIALOG.getHeight(),
				1500);
	}
}
