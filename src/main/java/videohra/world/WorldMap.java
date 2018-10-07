/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import videohra.Game;
import videohra.entities.EntityManager;
import videohra.entities.creatures.Creature;
import videohra.entities.creatures.Enemy;
import videohra.entities.creatures.Player;
import videohra.entities.objects.Bonfire;
import videohra.graphics.Background;
import videohra.graphics.GameCam;
import videohra.graphics.ImageLoader;
import videohra.items.ItemManager;

/**
 * loads the world image
 * holds all entities and blocks
 * updates and renders all entities and blocks
 */
public final class WorldMap {
        
    private String name;
    private int worldWidth, worldHeight;
    private Block[] blocks;
    private Background background;
    
    private final EntityManager entityManager;
    private final ItemManager itemManager;
    private final GameCam gameCam;
    
    
    public WorldMap(String name, Game game) {        
        entityManager = new EntityManager(new Player(0, 0, this), new Bonfire(0, 0, this));
        itemManager = new ItemManager();
        
        load(name);
        
        gameCam = new GameCam(0, 0, this);
    }
    
    /**
     * converts pixels to blocks
     * ~ px / block size
     * @param pixels how many pixels
     * @return how many blocks that is
     */
    public static int pixelsToBlocks(int pixels) {
        return pixels >> Block.BLOCK_SIZE_BITS;
    }
    
    /**
     * converts blocks to pixels
     * ~ blocks * block size
     * @param blocks how many blocks
     * @return how many pixels that is
     */
    public static int blocksToPixels(int blocks) {
        return blocks << Block.BLOCK_SIZE_BITS;
    }
    
    /**
     * loads the world image, sets width and height
     * creates one dimensional array of all blocks
     * creates array for pixels and adds pixel rgb code to the corresponding position
     * fills the blocks array with blocks with positions corresponding to the rgb code in the map
     * @param name name of the level image in the levels folder
     */
    public void load(String name) {
        this.name = name;        
        
        entityManager.loadEntityManager();
        itemManager.loadItemManager();
        
        background = new Background(ImageLoader.loadImage("textures/backgrounds/" + name + ".jpg"), this);
        
        BufferedImage levelImg = ImageLoader.loadImage("levels/" + name + ".png");
        worldWidth = levelImg.getWidth();
        worldHeight = levelImg.getHeight();
        
        blocks = new Block[worldWidth*worldHeight];
        int[] pixels = levelImg.getRGB(0, 0, worldWidth, worldHeight, null, 0, worldWidth);
        
        for (int y = 0; y < worldHeight; y++) {
            for (int x = 0; x < worldWidth; x++) {
                int id = pixels[x + y*worldWidth];
                if (id == 0xFF0000FF) { entityManager.getPlayer().setX(blocksToPixels(x)); entityManager.getPlayer().setY(blocksToPixels(y)); }
                else if (id == 0xFFFF6400) { entityManager.getBonfire().setX(blocksToPixels(x)); entityManager.getBonfire().setY(blocksToPixels(y)); }
                else if (id == 0xFF0000C8) { entityManager.addEntity(new Enemy(blocksToPixels(x), blocksToPixels(y), this)); }
                else if (Block.getTypeOfBlockByID(id) != null) { setBlock(x, y, Block.getTypeOfBlockByID(id)); }
            }
        } 
    }
    
    /**
     * calls update function of all entities
     */
    public void update() {
        background.update();
        itemManager.update();
        entityManager.update();
    }
    
    /**
     * calculates the camera offset
     * renders the visible blocks
     * renders entities and player
     * @param g graphics object
     */
    public void render(Graphics2D g) {
        background.render(g);
        
        int xStart = (int) Math.max(0, pixelsToBlocks((int) gameCam.getxOffset()));
        int xEnd = (int) Math.min(Game.WINDOW_WIDTH, pixelsToBlocks((int) gameCam.getxOffset() + Game.WINDOW_WIDTH) + 1);
        int yStart = (int) Math.max(0, pixelsToBlocks((int) gameCam.getyOffset()));
        int yEnd = (int) Math.min(Game.WINDOW_WIDTH, pixelsToBlocks((int) gameCam.getyOffset() + Game.WINDOW_WIDTH) + 1);
        
        for (int y = yStart; y < yEnd; y++) {
            for (int x = xStart; x < xEnd; x++) {
                Block b = getBlock(x, y);
                if (b != null) { b.render(g, (int) (blocksToPixels(x) - gameCam.getxOffset()), (int) (blocksToPixels(y) - gameCam.getyOffset())); }
            }
        }
        
        itemManager.render(g);
        entityManager.render(g);
    }
    
    /**
     * checks horizontal collision with blocks
     * creates a temporary x position (where the creature wants to go)
     * if on the temporary x position and any of the creatures corresponding bounds rectangle y position is solid block
     * returns true and sets the x position next to the block for perfect collision
     * @param c creature for who we check collsion
     * @return true if there is a collision, false if not 
     */
    public boolean horizontalCollision(Creature c) {
        if (c.getMoveX() > 0) { // moving right
            int tempX = pixelsToBlocks((int) (c.getX() + c.getMoveX() + c.getBoundsX() + c.getEntityWidth()));
            
            for (int y = pixelsToBlocks((int) (c.getY() + c.getBoundsY())); y < pixelsToBlocks((int) (c.getY() + c.getBoundsY() + c.getEntityHeight())) + 1; y++) {
                if (getBlock(tempX,  y) != null && getBlock(tempX, y).isSolid()  || (tempX >= worldWidth)) { 
                    c.setX(blocksToPixels(tempX) - c.getBoundsX() - c.getEntityWidth() - 1);
                    return true; 
                }
            }
        }
        
        if (c.getMoveX() < 0) { // moving left
            int tempX = pixelsToBlocks((int) (c.getX() + c.getMoveX() + c.getBoundsX()));
            
            for (int y = pixelsToBlocks((int) (c.getY() + c.getBoundsY())); y < pixelsToBlocks((int) (c.getY() + c.getBoundsY() + c.getEntityHeight())) + 1; y++) {
                if ((getBlock(tempX,  y) != null && getBlock(tempX, y).isSolid()) || (tempX < 0)) {
                    c.setX(blocksToPixels(tempX) + Block.BLOCK_SIZE - c.getBoundsX());
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * checks vertical collision with blocks
     * creates a temporary y position (where the creature wants to go)
     * if on the temporary y position and any of the creatures corresponding bounds rectangle x position is solid block
     * returns true and sets the y position next to the block for perfect collision
     * @param c creature for who we check collsion
     * @return true if there is a collision, false if not 
     */
    public boolean verticalCollision(Creature c) {
        if (c.getMoveY() < 0) { // jumping 
            int tempY = pixelsToBlocks((int) (c.getY() + c.getMoveY() + c.getBoundsY()));
            
            for (int x = pixelsToBlocks((int) (c.getX() + c.getBoundsX())); x < pixelsToBlocks((int) (c.getX() + c.getBoundsX() + c.getEntityWidth())) + 1; x++) {
                if (getBlock(x,  tempY) != null && getBlock(x, tempY).isSolid()) {
                    c.setMoveY(0);
                    c.setY(blocksToPixels(tempY) + Block.BLOCK_SIZE - c.getBoundsY());
                    return true; 
                }
            }
        }
        
        if (c.getMoveY() > 0) { // falling
            int tempY = pixelsToBlocks((int) (c.getY() + c.getMoveY() + c.getBoundsY() + c.getEntityHeight()));
            
            for (int x = pixelsToBlocks((int) (c.getX() + c.getBoundsX())); x < pixelsToBlocks((int) (c.getX() + c.getBoundsX() + c.getEntityWidth())) + 1; x++) {
                if (getBlock(x,  tempY) != null && getBlock(x, tempY).isSolid()) {
                    c.setMoveY(0);
                    c.setCanJump(true);
                    return true; 
                }
            }
        }
        
        return false;
    }
    
    /**
     * @param x x position in the blocks array
     * @param y y position in the blocks array
     * @param b the block to be set on that position
     */
    public void setBlock(int x, int y, Block b) {
        blocks[x + y*worldWidth] = b;
    }
    
    /**
     * @param x x position of a block in the blocks array
     * @param y y position of a block in the blocks array
     * @return the block on that position
     */
    public Block getBlock(int x, int y) {
        if (x < 0 || x >= worldWidth || y < 0 || y >= worldHeight) { return null; }
        return blocks[x + y*worldWidth];
    }

    public String getName() {
        return name;
    }

    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public GameCam getGameCam() {
        return gameCam;
    }
    
    
}
