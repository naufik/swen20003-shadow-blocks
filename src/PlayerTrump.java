/**
 * A class the represents a Donald Trump Player Character
 * @author Naufal Fikri
 *
 */
public class PlayerTrump extends Player{
	/**
	 * Instantiates a Donald Trump player character.
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public PlayerTrump(String imageSrc, float x, float y) {
		super(imageSrc, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void playerAction() {
		if (this.checkWallFeasibility()) {
			this.getLocation().addToWorld(new SpriteBlueprint(this.getX(),this.getY()));
		}
	}
	
	private boolean checkWallFeasibility() {
		for (Sprite s : this.getCollidingSprites()) {
			if (s instanceof SpriteConveyor) {
				return false;
			}
		}
		return true;
	}
}
