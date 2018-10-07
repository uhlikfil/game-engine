/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics.healthbars;

import java.awt.Graphics2D;
import videohra.entities.Entity;
import videohra.world.WorldMap;

/**
 * enemy healthbar
 * sets the default width of the bar and updates it's position so it stays above the creature
 */
public class EnemyHealthbar extends Healthbar {
    
    private final WorldMap worldMap;
    
    public EnemyHealthbar(Entity ent, WorldMap worldMap) {
        this.ent = ent;
        this.worldMap = worldMap;
        healthbarWidth = 100;
        healthbarHeight = 5;
    }
    
    @Override
    public void update() {
        xPos = (int) (ent.getX() - worldMap.getGameCam().getxOffset() + ent.getBoundsX() + ent.getEntityWidth()/2 - healthbarWidth/2); 
        yPos = (int) (ent.getY() - worldMap.getGameCam().getyOffset());
        super.update();
    }
    
    @Override
    public void render(Graphics2D g) {
        super.render(g);
    }
}
