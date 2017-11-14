import java.util.*;
import java.io.*;

/**
 * This class consists of exclusively static methods that is used to interact with the level files.
 * @author Naufal Fikri
 *
 */
public class Loader {
	
	public static final String PLAYER_NAME = "player";
	public static final String TRUMP_NAME = "trump";
	public static final String STONE_NAME = "stone";
	public static final String WALL_NAME = "wall";
	public static final String FLOOR_NAME = "floor";
	public static final String SWITCH_NAME = "switch";
	public static final String DOOR_NAME = "door";
	public static final String ICE_NAME = "ice";
	public static final String TARGET_NAME = "target";
	public static final String EXPLOSIVE_NAME = "tnt";
	public static final String CRACKED_WALL_NAME = "cracked";
	public static final String SKELETON_NAME = "skeleton";
	public static final String ROGUE_NAME = "rogue";
	public static final String MAGE_NAME = "mage";
	public static final String LASER_NAME = "laser";
	public static final String SHADOW_NAME = "shadow";
	public static final String FAKETARGET_NAME = "faketarget";
	public static final String MAGIC_STONE_NAME = "magicstone";
	public static final String AUTOMATIC_STONE_NAME = "autostone";
	public static final String MINE_NAME = "landmine";
	public static final String CONVEYOR_BELT_NAME = "conveyor";
	private static final String IMAGE_NAME = "img";
	/**
	 * Find the top-left X coordinate of the corner where we have to drawing the world
	 * @param world the world that is going to be drawn.
	 * @return y-pixel coordinate of the top-left corner of the world
	 */
	public static int getCornerX(World world) {
		int screenWidthTiles = App.SCREEN_WIDTH/App.TILE_SIZE;
		return screenWidthTiles/2 - world.getWidth()/2;
	}
	
	/**
	 * Find the top-left Y coordinate of the corner where we have to drawing the world
	 * @param world the world that is going to be drawn.
	 * @return y-pixel coordinate of the top-left corner of the world
	 */
	public static int getCornerY(World world) {
		int screenHeightTiles = App.SCREEN_HEIGHT/App.TILE_SIZE;
		return screenHeightTiles/2 - world.getHeight()/2;
	}
	
	/**
	 * Reads in a file and turns into a list of Sprites.
	 * @param filename the filename that is going to be loaded
	 * @return A list of sprites obtained from the file.
	 */
	public static List<Sprite> loadSprites(String filename) {
		List<Sprite> sprites = new ArrayList<Sprite>();
		boolean skip = true;
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String text;
			while ((text = reader.readLine()) != null) {
				if (skip) {
					skip = false;
					continue;
				}
				try {
					sprites.add(Loader.parseSprite(text));
				} catch (Exception e) {
					//do nothing and go on with life.
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sprites;
	}
	
	/**
	 * Given a sprite property in the form of plain text, parses it into a Sprite object
	 * @param props
	 * @return
	 * @throws Exception 
	 */
	public static Sprite parseSprite(String text) throws Exception { 
		//Inspired by the Project 1 solution by Eleanor.
		//This long ass function better be shortened.
		String[] spriteProperties = text.split(",");
		String spriteName = spriteProperties[0].split(":")[0];
		String[] spriteHead = spriteProperties[0].split(":");
		String spriteImage = (spriteHead.length > 1 ? spriteHead[1] : spriteHead[0]);
		Sprite newInstance;
		switch (spriteName) {
		case PLAYER_NAME:
			newInstance = new Player(
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case TRUMP_NAME:
			newInstance = new PlayerTrump(
					"trump",
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case STONE_NAME:
			newInstance = SpriteSignedStone.parseNew(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case ICE_NAME:
			newInstance = new SpriteIce(spriteImage, 
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case EXPLOSIVE_NAME:
			newInstance = new SpriteExplosive(spriteImage, 
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case DOOR_NAME:
			newInstance = SpriteDoor.parseDoor(spriteImage, 
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case SWITCH_NAME:
			newInstance = SpriteButton.parseButton(spriteImage, 
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case CRACKED_WALL_NAME:
			newInstance = new SpriteCrackedWall(
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					0);
			newInstance.setSolid(true);
			 break;
		case TARGET_NAME:
			newInstance = SpritePasswordTarget.parseNew(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case SKELETON_NAME:
			newInstance = new EnemySkeleton(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case ROGUE_NAME:
			newInstance = new EnemyRogue(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case MAGE_NAME:
			newInstance = new EnemyMage(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case FAKETARGET_NAME:
			newInstance = new SpriteFakeTarget(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					0);
			break;
		case SHADOW_NAME:
			newInstance = EnemyShadow.parseNew(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case AUTOMATIC_STONE_NAME:
			newInstance = SpriteAutoblock.parseNew(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case MAGIC_STONE_NAME:
			newInstance = SpriteDisappearingStone.parseNew(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case CONVEYOR_BELT_NAME:
			newInstance = SpriteConveyor.parseNew(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case LASER_NAME:
			newInstance = EnemyLaser.parseNew(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					spriteProperties);
			break;
		case MINE_NAME:
			newInstance = new EnemyLandmine(spriteImage,
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]));
			break;
		case IMAGE_NAME:
		case WALL_NAME:
		case FLOOR_NAME:
			newInstance = new Sprite(spriteImage, 
					Float.parseFloat(spriteProperties[1]),
					Float.parseFloat(spriteProperties[2]),
					0);
			newInstance.setSolid(spriteProperties[0].equals(App.WALL_NAME));
			break;
		default:
			throw new Exception("sprite name doesn't exist");
		}

		return newInstance;
		
	}
	
	
	/**
	 * Loads the dimensions of the world prescribed in a given file.
	 * @param filename the filename of the world going to be loaded
	 * @return array of two integers containing the width and height respectively.
	 */
	public static int[] loadWorldDimensions(String filename) {
		int[] dims = {0, 0};
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String[] text = reader.readLine().split(",");
			dims[0] = Integer.parseInt(text[0]);
			dims[1] = Integer.parseInt(text[1]);
		} 
		catch (Exception e) {
			
		}
		return dims;
	}
	
	public static String[] loadWorldHeader(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))){
			String[] text = reader.readLine().split(",");
			return text;
		} catch (Exception e) {
			
		}
		String[] empty = {};
		return empty;
	}

	public static List<String> getLevelFiles() {
		File folder = new File(App.LEVEL_DIRECTORY);
		ArrayList<String> listNames = new ArrayList<String>();
		for (File f : folder.listFiles()){
			listNames.add(f.getName());
		}
		return listNames;
	}
}
