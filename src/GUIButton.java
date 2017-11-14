import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GUIButton extends VisualElement implements MouseBehavior {
	
	private Image unhoveredImage;
	private Image hoveredImage;
	private String[] message;
	
	public GUIButton(String imageSource) {
		super(imageSource);
		this.unhoveredImage = this.getImage();
		try {
			this.hoveredImage = new Image("res/" + this.getImageSource() + "-hover" + ".png");
		} catch (SlickException e) {
			this.hoveredImage = null;
		}
	}
	
	public String[] getMessage() {
		return this.message;
	}
	
	public void setMessage(String[] value) {
		this.message = value;
	}
	@Override
	public void onMouseRelease(int button, int x, int y) {
		if (this.withinBounds(x, y)) {
			this.onClick();
		}
	}
	
	public void onClick() {
		this.getParent().memberClicked(this, this.message);
	}
	
	@Override
	public void onMouseEnter() {
		if (this.hoveredImage != null) {
			this.setImage(this.hoveredImage);
		}
	}
	@Override
	public void onMouseLeave() {
		this.setImage(this.unhoveredImage);
	}
}
