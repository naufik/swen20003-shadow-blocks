import java.util.List;

import org.newdawn.slick.*;

/**
 * A class that represents a Sprite, a superclass for all other instances of objects that exists in a world in the game.
 * @author Naufal Fikri
 *
 */
public class Sprite implements Comparable<Sprite>{
	
	private float x;
	private float y;
	private boolean hasImage;
	private Image spriteImage;
	private String imageSource;
	private int layer;
	private World location;
	private boolean solid;
	private boolean visible;
	
	/**
	 * Check whether the object is solid. 
	 * @return boolean if the object is solid (blocks paths)
	 */
	public boolean isSolid() {
		return solid;
	}
	
	/**
	 * Sets whether the object is solid or not.
	 * @param true makes the object solid, false makes the object not solid.
	 */
	public void setSolid(boolean input) {
		solid = input;
	}
	
	/**
	 * check if the sprite if visible
	 * @return boolean whether the sprite is visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * makes the sprite hidden
	 */
	public void hide() {
		visible = false;
	}
	
	/**
	 * makes the sprite visible.
	 */
	public void show() {
		visible = true;
	}
	
	
	/**
	 * Creates a Sprite object specific x,y without placing it in a world.
	 * @param imageSrc the source of the image
	 * @param x initial x coordinate of the sprite
	 * @param y initial y coordinate of the sprite
	 * @param renderPriority the render priority of the sprite (lower value means it is rendered first)
	 */
	public Sprite(String imageSrc, float x, float y, int renderPriority) {
		this.x = x;
		this.y = y;
		this.imageSource = imageSrc;
		try {
			this.spriteImage = new Image("res/"+imageSrc+".png");
			this.hasImage = true;
			this.imageSource = imageSrc;
		} catch (Exception e) {
			this.hasImage = false;
		}
		this.setRenderPriority(renderPriority);
		this.visible = true;
	}
	
	/**
	 * Creates a Sprite object specific x,y without placing it in a world.
	 * @param imageSrc the source of the image.
	 * @param x initial x coordinate of the sprite.
	 * @param y initial y coordinate of the sprite.
	 * @param renderPriority the render priority of the sprite (lower value means it is rendered first)
	 * @param world the world that this Sprite will be placed at.
	 */
	public Sprite(String imageSrc, float x, float y, int renderPriority, World world) {
		this.x = x;
		this.y = y;
		this.imageSource = imageSrc;
		try {
			this.spriteImage = new Image(App.LEVEL_DIRECTORY+imageSrc+App.IMG_FORMAT);
			this.hasImage = true;
			this.imageSource = imageSrc;
		} catch (Exception e) {
			this.hasImage = false;
		}
		this.setRenderPriority(renderPriority);
		this.location = world;
		this.visible = true;
		location.addToWorld(this);
		
	}
	
	/**
	 * Moves this sprite to another location.
	 * @param x the new x coordinate (in tiles).
	 * @param y the new y coordinate (in tiles).
	 * @param world the new world this sprite will be located.
	 */
	public void moveTo(float x, float y, World world) {
		this.x = x;
		this.y = y;
		if (this.location != world) {
			if (this.location != null)
			this.location.destroySprite(this);
		}
		this.location = world;
		if (!this.location.getAllSprites().contains(this)) {
			world.addToWorld(this);
		}
		
		for (Sprite s : this.getCollidingSprites()) {
			if (s instanceof CollisionBehavior) {
				((CollisionBehavior)s).onCollisionStart(this);
			}
			if (this instanceof CollisionBehavior) {
				((CollisionBehavior)this).onCollisionStart(s);
			}
		}
	}
	
	/**
	 * Moves this sprite to a location, without changing worlds.
	 * @param x the x coordinate (in tiles).
	 * @param y the y coordinate (in tiles).
	 */
	public void moveTo(float x, float y) {
		List<Sprite> pastCollisions = this.getCollidingSprites();
		this.moveTo(x,y, this.getLocation());
		for (Sprite s : pastCollisions) {
			if (s != this) {
				if (s instanceof CollisionBehavior) {
					((CollisionBehavior)s).onCollisionEnd(this);
				}
				if (this instanceof CollisionBehavior) {
					((CollisionBehavior)this).onCollisionEnd(s);
				}
			}
		}
	}
	
	
	/**
	 * Gets the name of the sprite.
	 * @return the name of the image source of the sprite.
	 */
	public String getImageName() {
		return imageSource;
	}
	
	/**
	 * Gets the sprite image, as an image object.
	 * @return Slick Image object that is shown when the sprite is rendered.
	 */
	public Image getImage() {
		return spriteImage;
	}
	
	/**
	 * Gets the x coordinate of the sprite.
	 * @return the x coordinate of the sprite.
	 */
	public float getX() {
		return this.x;
	}
	
	/**
	 * Gets the y coordinate of the sprite.
	 * @return the y coordinate of the sprite.
	 */
	public float getY() {
		return this.y;
	}
	
	/**
	 * Gets the starting x coordinate of the image of the sprite when rendered onscreen.
	 * @return
	 */
	public float getPixelX() {
		int cornerX = Loader.getCornerX(location);
		return cornerX*App.TILE_SIZE + this.x*App.TILE_SIZE;
	}
	
	/**
	 * Gets the starting y coordinate of the image of the sprite when rendered onscreen.
	 * @return
	 */
	public float getPixelY() {
		int cornerY = Loader.getCornerY(location);
		return cornerY*App.TILE_SIZE + this.y*App.TILE_SIZE; 
	}
	
	/**
	 * Gets the 'layer' that the sprite is located in, it is equivalent to render priority.
	 * @return int renderPriority
	 */
	public int getRenderPriority() {
		return this.layer;
	}
	
	/**
	 * Sets the render priority of the sprite.
	 * @param renderPriority the render priority of the sprite (lower is rendered first).
	 */
	public void setRenderPriority(int renderPriority) {
		if (renderPriority < App.MAX_Z && renderPriority >= 0) {
			this.layer = renderPriority;
		} else if (renderPriority > App.MAX_Z) {
			this.layer = App.MAX_Z;
		} else {
			renderPriority = 0;
		}
	}
	
	/**
	 * Gets the world that the Sprite is located in.
	 * @return World object referring to the world this Sprite is located in.
	 */
	public World getLocation() {
		return location;
	}
	
	/**
	 * Updates the Sprite state for a frame.
	 * @param input Input object provided by Slick2D library to detect changes in input
	 * @param delta Time that has passed since last update (in millisecond)
	 */
	public void update(Input input, int delta) {
		if (this instanceof CollisionBehavior) {
			for (Sprite s : this.getCollidingSprites()) {
				((CollisionBehavior)this).onCollision(s);
			}
		}
		if (this instanceof TimedBehavior) {
			((TimedBehavior)this).onTimePass(delta);
		}
	}
	
	
	/**
	 * Renders the sprite on the screen.
	 * @param g Graphics object in Slick2D library used to render on-screen.
	 * @throws SlickException
	 */
	public void render(Graphics g) throws SlickException {
		if (this.visible) {
			if (!hasImage) {
				g.drawString("NAN",
						this.getPixelX(),
						this.getPixelY());
			} else {
				g.drawImage(this.spriteImage,
						this.getPixelX(),
						this.getPixelY());
			}
			this.additionalRender(g);
			if (App.SHOW_COORDINATES) {
				g.drawString((int)this.getX() +","+(int)this.getY() ,
								this.getPixelX(),
								this.getPixelY());
			}
			
		}
	}
	
	/**
	 * Renders additional images if needed.
	 * @param g Graphics object in Slick2D library used to render on-screen.
	 */
	public void additionalRender(Graphics g) {
		
	}
	
	
	/**
	 * Compares the render priority of this sprite to another. Used at most cases
	 * only for sorting.
	 * @param o the Sprite to compare with.
	 * @return integer representing the difference in render priorities, positive if this sprite
	 * is higher, otherwise negative.
	 */
	@Override
	public int compareTo(Sprite o) {
		return this.layer - o.getRenderPriority();
	}
	
	/**
	 * Gets all the sprite that is of unit tile difference in a direction.
	 * @param dir the direction that is checked.
	 * @return List of all sprites at one block to the direction of this sprite.
	 */
	public List<Sprite> getNeighboringSprites(Direction dir) {
		float x = this.getX();
		float y = this.getY();
		switch(dir) {
		case UP: 
			--y; break;
		case DOWN: 
			++y; break;
		case LEFT: 
			--x; break;
		case RIGHT:
			++x; break;
		case NONE: break;
		}
		return this.location.getSpritesAt(x, y);
	}
	
	/**
	 * Gets all the sprites that share the same location as this sprite. Includes self,
	 * @return List of all sprites sharing the same location as this sprite.
	 */
	public List<Sprite> getCollidingSprites() {
		return this.getNeighboringSprites(Direction.NONE);
	}

}
