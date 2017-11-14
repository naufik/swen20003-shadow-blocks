public class GUIMainMenu extends Overlay {
		
	public static final VisualElement MENU_BACKGROUND = new VisualElement("bg/main-menu-bg");
	public static final GUIButton PLAY_BUTTON = new GUIButton("gui/bplay");
	public static final GUIButton LEVEL_SELECT_BUTTON = new GUIButton("gui/bselect");
	public static final GUIButton LEVEL_EDIT_BUTTON = new GUIButton("gui/beditor");
	public static final GUIButton QUIT_BUTTON = new GUIButton("gui/bquit");	
	
	public GUIMainMenu(App listener) {
		super(listener);
		GUIManager.setActiveOverlay(this);
		MENU_BACKGROUND.displayAt(0, 0);
		PLAY_BUTTON.displayAt(300,200);
		LEVEL_SELECT_BUTTON.displayAt(300, 260);
		QUIT_BUTTON.displayAt(300,340);
	}
	
	
}
