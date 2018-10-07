/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.items;

import java.awt.Color;
import java.awt.Graphics2D;
import static java.lang.Math.abs;
import java.util.Random;
import videohra.graphics.Assets;
import videohra.graphics.ui.Fonts;
import videohra.world.WorldMap;

/**
 * soul that is dropped from every enemy
 */
public class Soul extends Item {

    private final Random random;
    private int dirX, dirY;
    private long now, lastTime, changeX, changeY;
    
    public Soul(WorldMap worldMap, double x, double y) {
        super(worldMap, x, y);
        name = "Souls";
        
        random = new Random();
        quantity = random.nextInt(2) + 1;
    }
    
    public Soul(WorldMap worldMap, double x, double y, int quantity) {
        super(worldMap, x, y);
        name = "Souls";
        
        random = new Random();
        this.quantity = quantity;
    }
    
    /**
     * flies in random direction that changes every second horizontally and every 0,3 sec vertically
     * if the player moves over the soul, it gets picked up
     */
    @Override
    public void update() {
        now = System.currentTimeMillis();
        changeX += now - lastTime;
        changeY += now - lastTime;
        lastTime = now;
        
        if (changeX >= 1000) {
            dirX = random.nextInt(3) - 1;
            changeX = 0;
        }
        if (changeY >= 300) {
            dirY = random.nextInt(3) - 1;
            changeY = 0;
        }
        
        x += dirX * 0.5;
        y += dirY * 0.5;
        
        if (abs(worldMap.getEntityManager().getPlayer().getCenterPosX() - x) <= WorldMap.blocksToPixels(1) && 
            abs(worldMap.getEntityManager().getPlayer().getCenterPosY() - y) <= WorldMap.blocksToPixels(2)) {
            getPickedUp();
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.soul, (int) (x - worldMap.getGameCam().getxOffset()), (int) (y - worldMap.getGameCam().getyOffset()), null);
    }
    
    @Override
    public void render(Graphics2D g, int x, int y) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y, Assets.ITEM_WIDTH + 100, Assets.ITEM_HEIGHT);
        g.fillRect(x + Assets.ITEM_WIDTH + 100, y, 20, Assets.ITEM_HEIGHT);
        
        g.drawImage(Assets.soul, x, y, null);
        Fonts.drawString(g, itemFont, Color.WHITE, name, x + Assets.ITEM_WIDTH, y + Assets.ITEM_HEIGHT - 2);
        Fonts.drawString(g, itemFont, Color.WHITE, String.valueOf(quantity), x + Assets.ITEM_WIDTH + 100, y + Assets.ITEM_HEIGHT - 2);
    }
}
