
public class GUIDeath extends Overlay {

	public static final VisualElement GAME_OVER_SCREEN = new VisualElement("bg/gameover");
	
	public GUIDeath(App listener) {
		super(listener);
		GUIManager.setActiveOverlay(this);
		GAME_OVER_SCREEN.displayAt(0, 0);
	}

}
