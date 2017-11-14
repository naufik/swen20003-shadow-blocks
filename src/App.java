/**
 * Project 2 for SWEN20003: Object Oriented Software Development 2017
 * by Naufal Fikri
 * built upon the skeleton by Eleanor McMurtry.
 */

import java.awt.Font;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.Input;
import java.awt.Desktop;
import java.net.URI;

/**
 * Main class for the game.
 * Handles initialization, input and rendering.
 */
public class App extends BasicGame
{
 	/** screen width, in pixels */
    public static final int SCREEN_WIDTH = 800;
    /** screen height, in pixels */
    public static final int SCREEN_HEIGHT = 600;
    /** size of the tiles, in pixels */
    public static final int TILE_SIZE = 32;
    /** maximum possible value for a render priority of a sprite. */
    public static final int MAX_Z = 10;
    /** where the level is stored */
    public static final String LEVEL_DIRECTORY = "res/levels/";
    public static final String RESOURCES_DIR = "res/";
    public static final String IMG_FORMAT = ".png";
    public static final String WALL_NAME = "wall";
    public static final String PLAYER_NAME = "player";
    public static final String STONE_NAME = "stone";
    public static final int KEY_PRNTSCRN = 183;
    public static final Font GAME_FONT = new Font("Arial", Font.BOLD, 16);
    public static final int REVISION = 1;
	private static final int MAX_LEVEL = 59;
    
	public static boolean SHOW_COORDINATES = false;
	private TrueTypeFont SLICK_FONT;
	
	private GameState currentState;
    private World world;
    private Overlay ui;
    public int CurrentLevel = 0;
    
    private boolean quitFlag;
    
    public App()
    {    	
        super("Shadow Blocks");
        this.quitFlag = false;
    }

    /**
     * Initializes the game
     * @param gc The Slick game container object.
     */ 
    @Override
    public void init(GameContainer gc)
    throws SlickException
    {
        this.SLICK_FONT = new TrueTypeFont(GAME_FONT, true);
        this.displayGameState(GameState.MAIN_MENU);
    }

    
    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
    throws SlickException
    {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        if (world != null) {
        	if (this.currentState == GameState.LEVEL_PLAYING) {
        		world.update(input, delta);
        	}
			if (this.world.isComplete() && !(this.ui instanceof GUIMainMenu)) {
				this.displayGameState(GameState.LEVEL_SUCCESS);
			} else if (this.world.isFailed() && this.ui.getElements().size() == 0) {
				this.displayGameState(GameState.LEVEL_FAILURE);
			}
        }
        ui.update(input, delta);
		if (this.quitFlag) {
			gc.exit();
		}
    }


	/** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
    throws SlickException
    {
		g.setFont(SLICK_FONT);
		if (this.world != null) {
			world.render(g);
			g.drawString("Level " + (this.CurrentLevel+1) + (!world.getLevelName().equals("") ? ": " + world.getLevelName() : ""),10,20);
		}
    	ui.render(g);
    	
    }

    /** Start-up method. Creates the game and runs it.
     * @param args Command-line arguments (ignored).
     */
    public static void main(String[] args)
    throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new App());
        // setShowFPS(true), to show frames-per-second.
        app.setShowFPS(false);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }
    
    /**
     * A Listener method provided by Slick, automatically invoked when a key is pressed. 
     * Do not manually invoke.
     * @param key The key that is detected.
     * @param c The character that is obtained by pressing the key.
     */
    @Override
    public void keyPressed(int key, char c) {
			if (this.world != null) {
				if (key == Input.KEY_R) {
	    			this.loadLevel();
	    		} else if (key == Input.KEY_Z) {
	    			this.world.undo();
	    		}
				if (this.currentState == GameState.LEVEL_PLAYING) {
		    		world.invokeKeyPressBehavior(key, c);
		    	
				}
				if (key == Input.KEY_PERIOD) {
		    		this.advanceLevel();
		    	}
		    	if (key == Input.KEY_COMMA) {
		    		if (this.CurrentLevel > 0) {
			    		--this.CurrentLevel;
			    		this.loadLevel();
		    		}
		    	}
		    	if (key == Input.KEY_ENTER) {
		    		if (this.currentState == GameState.LEVEL_SUCCESS) {
		    			this.advanceLevel();
		    		}
		    	}
			}
    		if (key == Input.KEY_ESCAPE) {
    			this.quitFlag = true;
    		}
    		//TESTING FEATURES
    		if (key == Input.KEY_V) {
    			App.SHOW_COORDINATES = !App.SHOW_COORDINATES;
    		}	
    }
    
    @Override
    public void mousePressed(int button, int x, int y) {
    	ui.invokeMousePressBehavior(button, x, y);
    }
    
    @Override
    public void mouseReleased(int button, int x, int y) {
    	ui.invokeMouseReleaseBehavior(button, x, y);
    }
    
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy) {
    	ui.invokeMouseMoveBehavior( oldx, oldy, newx, newy);
    }
    
    private void advanceLevel() {
    	if (this.CurrentLevel < App.MAX_LEVEL) {
	    	++this.CurrentLevel;
			this.loadLevel();
    	}
    }
    
    /**
     * Gets the level file of a certain level
     * @param level the name of the level
     * @return the string of directory the level file
     */
    public String getLevelFile(String level) {
    	return App.LEVEL_DIRECTORY + level + ".lvl";
    }
    
    private void loadLevel() {
		this.world = World.generateFromFile(this.getLevelFile(this.CurrentLevel + ""));
		this.displayGameState(GameState.LEVEL_PLAYING);
    }
    
    private void loadLevel(String filename) {
    	this.world = World.generateFromFile(App.LEVEL_DIRECTORY + filename);
    	this.displayGameState(GameState.LEVEL_PLAYING);
    }
    
    private void loadLevel(int level) {
    	this.world = World.generateFromFile(this.getLevelFile("" + level));
    	this.CurrentLevel = level;
    	this.displayGameState(GameState.LEVEL_PLAYING);
    }
    
    private void setGUIOverlay(Overlay o) {
    	this.ui = o;
    	GUIManager.setActiveOverlay(ui);
    }
    
    /**
     * An event that is called by GUI when a change in GUI happens
     * @param sender the object.
     * @param args the arguments/message passed by the GUI object.
     */
	public void onGUIEvent(VisualElement sender, Object args) {
		if (sender == GUIMainMenu.PLAY_BUTTON) {
			this.loadLevel();
			this.displayGameState(GameState.LEVEL_PLAYING);
		} else if (sender == GUIMainMenu.QUIT_BUTTON) {
				try {
					if(Desktop.isDesktopSupported())
					{
						Desktop.getDesktop().browse(new URI("http://lmgtfy.com/?q=how+to+quit+vim"));
					}
				} catch (Exception e){
			
					
				}
			this.quitFlag = true;	 
		} else if (sender == GUIMainMenu.LEVEL_SELECT_BUTTON) {
			this.displayGameState(GameState.LEVEL_SELECT);
		} else if (sender == GUILevelSelect.BUTTON_MAIN_MENU) {
			this.displayGameState(GameState.MAIN_MENU);
		}
		else if (sender == GUILevelSelect.NEXT_BUTTON) {
			GUILevelSelect.LEVEL_GRID.nextPage();
		} else if (sender == GUILevelSelect.PREV_BUTTON) {
			GUILevelSelect.LEVEL_GRID.prevPage();
		} else if (args instanceof String[]) {
			String[] stringArgs = (String[])args;
			switch (stringArgs[0]) {
			case "load":
				this.loadLevel(Integer.parseInt(stringArgs[1]));
				break;
			}
		}
	}
	
	/**
	 * Displays the GUI that is related to the state of the game given.
	 * @param state the state of the game we want to display
	 */
	public void displayGameState(GameState state) {
		switch(state) {
		case MAIN_MENU:
			this.setGUIOverlay(new GUIMainMenu(this));
			break;
		case LEVEL_PLAYING:
			this.setGUIOverlay(new Overlay(this));
			break;
		case LEVEL_SUCCESS:
			this.setGUIOverlay(new GUILevelDone(this));
			break;
		case LEVEL_FAILURE:
			this.setGUIOverlay(new GUIDeath(this));
			break;
		case LEVEL_SELECT:
			this.setGUIOverlay(new GUILevelSelect(this));
		default:
			break;
		}
		this.currentState = state;
	}
 
}