/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities.objects;

import java.awt.Graphics2D;
import static java.lang.Math.abs;
import java.util.logging.Logger;
import videohra.entities.Entity;
import videohra.graphics.Animation;
import videohra.graphics.Assets;
import videohra.world.WorldMap;

/**
 * an entity that is in every map
 * heals the player but resets the whole map
 * player can teleport between maps by sitting at the bonfire
 */
public class Bonfire extends Entity {
    
    private static final Logger LOGGER = Logger.getLogger(Bonfire.class.getName());

    private boolean playerNear;
    private final Animation bonfire;
    private final String[] bonfireLevelsMenu;
    private int currentSelection;
    
    public Bonfire(double x, double y, WorldMap worldMap) {
        super(x, y, worldMap);
        
        // bounds
        boundsX = 32;
        
        // graphics
        bonfire = new Animation(150, Assets.bonfire);
        
        bonfireLevelsMenu = new String[2];
        bonfireLevelsMenu[0] = "firelink_shrine";
        bonfireLevelsMenu[1] = "konoha";
    }
    
    /**
     * controls the bonfire menu
     */
    public void menuUp() {
        currentSelection--;
        if (currentSelection < 0) { 
            currentSelection = bonfireLevelsMenu.length - 1; 
        }
    }

    /**
     * controls the bonfire menu
     */
    public void menuDown() {
        currentSelection++;
        if (currentSelection >= bonfireLevelsMenu.length) { 
            currentSelection = 0; 
        }
    }
    
    /**
     * controls the bonfire menu
     */
    public void menuSelect() {
        worldMap.load(bonfireLevelsMenu[currentSelection]);
        worldMap.getEntityManager().getPlayer().getUp();
        LOGGER.info("Player has teleported to " + bonfireLevelsMenu[currentSelection]);
    }
    
    @Override
    public void update() {
        
        // is player near?
        playerNear = abs(worldMap.getEntityManager().getPlayer().getCenterPosX() - centerPosX) <= WorldMap.blocksToPixels(1) && abs(worldMap.getEntityManager().getPlayer().getCenterPosY() - centerPosY) <= WorldMap.blocksToPixels(1);
        
        // animation
        bonfire.update();
                
        super.update();
    }
    
    /**
     * loads the same map again
     * restarting all the enemies and so on
     */
    public void restartMap() {
        worldMap.load(worldMap.getName());
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(bonfire.getCurrentFrame(), (int) (x - worldMap.getGameCam().getxOffset()), (int) (y - worldMap.getGameCam().getyOffset()), null);
    }

    public boolean isPlayerNear() {
        return playerNear;
    }   

    public int getCurrentSelection() {
        return currentSelection;
    }
    
}
