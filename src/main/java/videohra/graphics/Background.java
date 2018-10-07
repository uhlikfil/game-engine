/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import videohra.Game;
import videohra.world.WorldMap;

/**
 * background image class
 * gets an image and calculates it's width and position
 */
public class Background {
    
    private final BufferedImage bgImg;
    private final WorldMap worldMap;
    private int bgX;
    private final int bgWidth;

    public Background(BufferedImage bgImg, WorldMap worldMap) {
        this.bgImg = bgImg;
        this.worldMap = worldMap;
        bgWidth = Game.WINDOW_HEIGHT * bgImg.getWidth() / bgImg.getHeight();
    }
    
    /**
     * x coordinate moves with the camera so that it ends with the world (goes more slowly if the world is wider than the bg image)
     */            
    public void update() {
        bgX = (int) (0 - (worldMap.getGameCam().getxOffset() * (bgWidth - Game.WINDOW_WIDTH) / (WorldMap.blocksToPixels(worldMap.getWorldWidth()) - Game.WINDOW_WIDTH)));

    }       
    
    public void render(Graphics2D g) {
        g.drawImage(bgImg, bgX, 0, bgWidth, Game.WINDOW_HEIGHT, null);
    }
}
