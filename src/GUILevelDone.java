
public class GUILevelDone extends Overlay{
	public static final VisualElement LEVEL_DONE_SCREEN = new VisualElement("bg/well-done-h1");
	
	public GUILevelDone(App listener) {
		super(listener);
		GUIManager.setActiveOverlay(this);
		LEVEL_DONE_SCREEN.displayAt(0, 0);
	}

}
