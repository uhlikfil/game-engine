/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics.healthbars;

import java.awt.Color;
import java.awt.Graphics2D;
import videohra.entities.Entity;

/**
 * abstract class for healthbars
 */
public abstract class Healthbar {
    
    protected Entity ent;
    protected int healthbarWidth, healthbarHeight;
    protected int remHpWidth, lostHpWidth, xPos, yPos;
    
    /**
     * calculates the width of the healthbar by remaining health of the creature
     */
    public void update() {
        remHpWidth = healthbarWidth * ent.getCurrentHealth() / ent.getMaxHealth();
        lostHpWidth = healthbarWidth - remHpWidth;
    }
    
    public void render(Graphics2D g) {
        if (ent.isAlive()) {
            g.setColor(Color.RED);
            g.fillRect(xPos, yPos, remHpWidth, healthbarHeight);
            g.setColor(Color.GRAY);
            g.fillRect(xPos + remHpWidth, yPos, lostHpWidth, healthbarHeight);
        }
    };
}
