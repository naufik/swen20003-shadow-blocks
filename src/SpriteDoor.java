import org.newdawn.slick.*;

public class SpriteDoor extends Sprite implements TriggerBehavior {
	
	public static final String MARKER_IMAGE_SOURCE = "device_overlay";
	
	private TriggerColor triggerColor;
	private boolean open;
	private Image markerImage;
	
	public static SpriteDoor parseDoor(String imgSource, float x, float y, String[] spriteProperties) {
		SpriteDoor door = new SpriteDoor(imgSource, x, y, App.MAX_Z - 1);
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
			door.setTriggerColor(color);
		}
		return door;
	}
	public SpriteDoor(String imageSrc, float x, float y, int renderPriority) {
		super(imageSrc, x, y, renderPriority);
		this.triggerColor = TriggerColor.UNIVERSAL;
		this.setSolid(true);
		try {
			markerImage = new Image("res/" + MARKER_IMAGE_SOURCE+".png");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public TriggerColor getColor() {
		return this.triggerColor;
	}
	
	@Override
	public void onActivation() {
		this.setDoorOpen(true);
	}
	
	@Override
	public void onDeactivation() {
		this.setDoorOpen(false);
	}
	
	public void setDoorOpen(boolean open) {
		if (open) {this.hide();} else {this.show();};
		this.open = open;
		this.setSolid(!open);
	}
	
	@Override
	public void additionalRender(Graphics g) {
		g.drawImage(markerImage, this.getX()*App.TILE_SIZE+Loader.getCornerX(this.getLocation())*App.TILE_SIZE, 
				this.getY()*App.TILE_SIZE+Loader.getCornerY(this.getLocation())*App.TILE_SIZE, 
				TriggerBehavior.getRenderColor(this.getColor()));
	}

	@Override
	public boolean isActive() {
		return open;
	}


	@Override
	public void setTriggerColor(TriggerColor color) {
		this.triggerColor = color;
	}
	
}
