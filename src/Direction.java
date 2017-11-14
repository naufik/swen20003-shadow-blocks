/**
 * An enumeration representing a direction in the game.
 * @author Naufal Fikri
 *
 */
public enum Direction {
	UP, LEFT, DOWN, RIGHT, NONE;
	
	/** The default direction */
	public static final Direction DEFAULT_DIRECTION = Direction.RIGHT;
	
	/**
	 * Parses a direction from a piece of string.
	 * @param directionString
	 * @return the Direction object parsed.
	 */
	public static Direction parseDirection(String directionString) {
		Direction d = DEFAULT_DIRECTION;
		switch(directionString.toLowerCase()) {
		case "u":
		case "up":
			d = Direction.UP;
			break;
		case "d":
		case "down":
			d = Direction.DOWN;
			break;
		case "l":
		case "left":
			d = Direction.LEFT;
			break;
		case "r":
		case "right":
			d = Direction.RIGHT;
			break;
		case "n":
			d = Direction.NONE;
		default:
			break;
		}
		return d;
	}
	
	/**
	 * Gets the angle required to rotate an image in order to make it face a certain direction. Assumes that the sprite is initially facing
	 * the default direction.
	 * @param d The direction we want the image to face.
	 * @return
	 */
	public static float getRotationAngle(Direction d) {
		float theta = 0F;
		switch(d) {
		case DOWN:
			theta = 90F;
			break;
		case LEFT:
			theta = 180F;
			break;
		case UP:
			theta = 270F;
			break;
		case RIGHT:
		default:
			theta = 0F;
			break;
		}
		return theta;
	}
	
	/**
	 * Gets the reverse of a direction.
	 * @param d the direction that we want to find the reverse of
	 * @return the reverse direction
	 */
	public static Direction reverse(Direction d) {
		switch(d) {
			case UP:
				return DOWN;
			case LEFT:
				return RIGHT;
			case DOWN:
				return UP;
			case RIGHT:
				return LEFT;
			default:
				return NONE;
			}
	}
	
	/**
	 * Gets the direction obtained by rotation, either clockwise or anticlockwise.
	 * @param d the input direction
	 * @param clockwise boolean whether the rotation is clockwise or not.
	 * @return the direction after the rotation.
	 */
	public static Direction rotate(Direction d, boolean clockwise) {
		switch(d) {
		case UP:
			return (clockwise ? RIGHT : LEFT);
		case LEFT:
			return (clockwise ? DOWN : UP); 
		case DOWN:
			return (clockwise ? LEFT : RIGHT); 
		case RIGHT:
			return (clockwise ? UP : DOWN);
		default:
			return NONE;
		}	
	}
}
