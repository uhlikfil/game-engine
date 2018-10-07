/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics;

import videohra.Game;
import videohra.entities.Entity;
import videohra.world.WorldMap;

/**
 * class to calculate the camera offset by the player's position
 */
public class GameCam {
    
    private final WorldMap worldMap;
    private double xOffset, yOffset;

    public GameCam(double xOffset, double yOffset, WorldMap worldMap) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.worldMap = worldMap;
    }
    
    /**
     * sets offsets depending on an entity's position
     * @param e entity for the camera to be centered on
     */
    public void centerOnEntity(Entity e) {
        xOffset = e.getX() - Game.WINDOW_WIDTH / 2 + 100;
        yOffset = e.getY() - Game.WINDOW_HEIGHT / 2 + 100;
        checkOutside();
    }
    
    /**
     * stops the camera on the left, right and bottom edge of the map
     */
    public void checkOutside() {
        if (xOffset < 0) { 
            xOffset = 0; 
        } else if (xOffset > WorldMap.blocksToPixels(worldMap.getWorldWidth()) - Game.WINDOW_WIDTH) {
            xOffset = WorldMap.blocksToPixels(worldMap.getWorldWidth()) - Game.WINDOW_WIDTH;
        }
        
        if (yOffset > WorldMap.blocksToPixels(worldMap.getWorldHeight()) - Game.WINDOW_HEIGHT) {
            yOffset = WorldMap.blocksToPixels(worldMap.getWorldHeight()) - Game.WINDOW_HEIGHT;
        }
    }
    
    public double getxOffset() {
        return xOffset;
    }

    public void setxOffset(double xOffset) {
        this.xOffset = xOffset;
    }

    public double getyOffset() {
        return yOffset;
    }

    public void setyOffset(double yOffset) {
        this.yOffset = yOffset;
    }
}
