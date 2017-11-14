
public class GUILevelSelect extends Overlay {

	public static final VisualElement BACKGROUND = new VisualElement("bg/level-select-bg");
	public static final GUIButton BUTTON_MAIN_MENU = new GUIButton("gui/bsmenu");
	public static final GUILevelViewer LEVEL_GRID = new GUILevelViewer(800,288);
	public static final GUIButton PREV_BUTTON = new GUIButton("gui/bprev");
	public static final GUIButton NEXT_BUTTON = new GUIButton("gui/bnext");
	
	public GUILevelSelect(App listener) {
		super(listener);
		GUIManager.setActiveOverlay(this);
		BACKGROUND.displayAt(0, 0);
		LEVEL_GRID.displayAt(64,160);
		BUTTON_MAIN_MENU.displayAt(320,512);
		LEVEL_GRID.clear();
		for (String s : Loader.getLevelFiles()) {
			LEVEL_GRID.addEntry(s);
		}
		PREV_BUTTON.displayAt(2*32, 16*32);
		NEXT_BUTTON.displayAt(18*32, 16*32);
		// TODO Auto-generated constructor stub
	}
	
	public void nextPage() {
		LEVEL_GRID.nextPage();
	}
	
	public void previousPage() {
		LEVEL_GRID.prevPage();
	}
	  
}
