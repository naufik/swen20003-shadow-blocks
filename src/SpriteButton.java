import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 * This class defines the property of a button. The intended behavior of a button is to:
 * <ul>
 * <li>Sets trigger state to ON when a Pushable Sprite lies in the same tile as it</li>
 * <li>Sets trigger state to OFF otherwise</li>
 * </ul>
 * @author Naufal Fikri
 *
 */
public class SpriteButton extends TriggerSprite implements CollisionBehavior {
	
	public static SpriteButton parseButton(String imgSource, float x, float y, String[] spriteProperties) {
		SpriteButton button = new SpriteButton(imgSource, x, y, 1);
		TriggerColor color;
		if (spriteProperties.length > 3) {
			switch (spriteProperties[3]) {
			case "red":
				color = TriggerColor.RED;
				break;
			case "blue":
				color = TriggerColor.BLUE;
				break;
			case "green":
				color = TriggerColor.GREEN;
				break;
			case "gold":
				color = TriggerColor.GOLD;
				break;
			case "purple":
				color = TriggerColor.PURPLE;
				break;
			case "aqua":
				color = TriggerColor.AQUA;
				break;
			default:
				color = TriggerColor.UNIVERSAL;
			}
			button.setTriggerColor(color);
		}
		return button;
	}
	
	private static final String MARKER_IMAGE_SOURCE = "trigger_overlay";
	private Image markerImage;
	
	/**
	 * Instantiates a button object
	 * @param imageSrc The image source for the Sprite.
	 * @param x the initial x position of the Sprite.
	 * @param y the initial y position of the Sprite.
	 */
	public SpriteButton(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
		try {
			markerImage = new Image("res/"+MARKER_IMAGE_SOURCE+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCollision(Sprite s) {
		if (s instanceof PushableSprite) {
			this.trigger(true);
		}
	}
	
	@Override
	public void onCollisionEnd(Sprite s) {
		if (s instanceof PushableSprite) {
			this.trigger(false);
		}
	}
	
	@Override
	public void additionalRender(Graphics g) {
		g.drawImage(markerImage, this.getX()*App.TILE_SIZE+Loader.getCornerX(this.getLocation())*App.TILE_SIZE, 
				this.getY()*App.TILE_SIZE+Loader.getCornerY(this.getLocation())*App.TILE_SIZE, 
				TriggerBehavior.getRenderColor(this.getColor()));
	}


}
