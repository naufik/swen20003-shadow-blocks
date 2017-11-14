import java.util.*;

import org.newdawn.slick.*;

public class GUILevelViewer extends VisualElement implements MouseBehavior {

	public static final String BUTTON_IMAGE_PATH = "gui/blevel";
	private int pageStart;
	private int pageSize;
	private float xMargin;
	private float entryWidth;
	private float yMargin;
	private float entryHeight;
	
	private List<String> levelFiles;
	private List<GUIButton> buttons;
	
	public GUILevelViewer(int width, int height) {
		super("");
		this.pageStart = 0;
		this.pageSize = 15;
		this.xMargin = 96;
		this.yMargin = 32;
		this.entryWidth = 160;
		this.entryHeight = 32;
		this.levelFiles = new ArrayList<String>();
		this.buttons = new ArrayList<GUIButton>();
		
	}
	
	@Override
	public void render(Graphics g) {
		renderChildren(g);
	}
	
	private void renderChildren(Graphics g) {
		for (int i = pageStart; i < pageStart + pageSize; ++i) {
			if (buttons.size() > i) {
				float xpos = this.getChildX(i);
				float ypos = this.getChildY(i);
				this.buttons.get(i).setPosition(xpos, ypos);
				this.buttons.get(i).render(g);
				g.setColor(new Color(0F,0F,0F));
				g.drawString("Level " +(i+1),xpos+10,ypos);
				g.setColor(new Color(1F,1F,1F));
			}
		}
	}
	
	private float getChildX(int n) {
		n = n - this.pageStart;
		return this.getX() + (n % 3)*(this.xMargin + this.entryWidth);
	}
	
	private float getChildY(int n) {
		n = n - this.pageStart;
		return this.getY() + ((float)Math.floor(n/3))*(this.entryHeight+this.yMargin);
	}
	
	public void clear() {
		this.levelFiles.clear();
		this.buttons.clear();
	}
	
	public void addEntry(String entry) {
		GUIButton newButton = new GUIButton(BUTTON_IMAGE_PATH);
		newButton.display();
		newButton.setParent(this.getParent());
		newButton.setMessage(new String[]{"load", "" + this.levelFiles.size()});
		this.levelFiles.add(entry);
		this.buttons.add(newButton);
	}
	
	private List<GUIButton> displayedButtons() {
		List<GUIButton> buttons = new ArrayList<GUIButton>();
		for (int i = this.pageStart; i < this.pageSize + this.pageStart; ++i) {
			if (this.buttons.size() > i) {
				buttons.add(this.buttons.get(i));
			}
		}
		return buttons;
	}
	
	public void onMousePress(int button, int x, int y) {
		for (GUIButton ve : this.displayedButtons()) {
			ve.onMousePress(button, x, y);
		}
	}
	
	public void onMouseRelease(int button, int x, int y) {
		for (GUIButton ve : this.displayedButtons()) {
			ve.onMouseRelease(button, x, y);
		}
	}
	
	public void onMouseMove(int oldx, int oldy, int newx, int newy) {
		for (GUIButton ve : this.displayedButtons()) {
			ve.onMouseMove(oldx, oldy, newx, newy);
			if (!ve.withinBounds(oldx, oldy) && ve.withinBounds(newx, newy)) {
				ve.onMouseEnter();
			} else if (!ve.withinBounds(newx, newy) && ve.withinBounds(oldx, oldy)) {
				ve.onMouseLeave();
			}
		}
	}

	public void nextPage() {
		if (this.pageStart + this.pageSize < this.levelFiles.size()) {
			this.pageStart += this.pageSize;
		}
	}
	
	public void prevPage() {
		if (this.pageStart - this.pageSize >= 0) {
			this.pageStart -= this.pageSize;
		} else {
			this.pageStart = 0;
		}
	}

}
