public class GUIManager {
	private static Overlay activeOverlay;
	
	public static void setActiveOverlay(Overlay o) {
		GUIManager.activeOverlay = o;
	}
	
	public static void show(VisualElement element) {
		activeOverlay.show(element);
	}
}
