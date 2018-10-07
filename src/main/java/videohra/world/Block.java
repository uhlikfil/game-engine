/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import videohra.graphics.Assets;

/**
 * blocks from which are the levels made
 */
public class Block {
    
    public static int BLOCK_SIZE = 64, BLOCK_SIZE_BITS = 6;
    protected BufferedImage texture;
    protected boolean solid;
    protected int id;
    
    private static final Map<Integer, Block> blockList = new HashMap<Integer, Block>(); // list of all the types of blocks used in the world
    
    public static final Block stoneBottom = new Block(0xFF323232, Assets.stone_bottom, true);
    public static final Block stoneTop = new Block(0xFF646464, Assets.stone_top, true);
    public static final Block pillar = new Block(0xFFFF0000, Assets.pillar, false);
    public static final Block roof = new Block(0xFF960096, Assets.roof, true);
    public static final Block wood = new Block(0xFFB45A00, Assets.wood, true);
    public static final Block wood_bg = new Block(0xFF965000, Assets.wood_bg, false);

    
    /**
     * @param id id is the rgb code for the block type used in the map
     * @param texture the texture to be rendered
     * puts the constructed block into the list of blocks with its id as a key
     */
    private Block(int id, BufferedImage texture, boolean solid) {
        this.id = id;
        this.texture = texture;
        this.solid = solid;
        blockList.put(id, this);
    }
    
    public void render(Graphics2D g, int x, int y) {
        g.drawImage(texture, (int) x, (int) y, BLOCK_SIZE, BLOCK_SIZE, null);
    }
    
    /**
     * gets a block from the list of blocks by its id
     * @param id of the type of block we want
     * @return returns the type of block we wanted
     */
    public static Block getTypeOfBlockByID(int id) {
        return blockList.get(id);
    }
    
    public boolean isSolid() {
        return solid;
    }
}
