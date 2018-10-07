/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics.healthbars;

import java.awt.Graphics2D;
import videohra.entities.creatures.Player;
import videohra.graphics.Assets;

/**
 * player healthbar stays in the top left corner of the screen
 */
public class PlayerHealthbar extends Healthbar {
        
    public PlayerHealthbar(Player player) {
        this.ent = player;
        healthbarHeight = 41;
        healthbarWidth = ent.getMaxHealth()*5;
        xPos = 153;
        yPos = 100;
    }

    @Override
    public void update() {
        healthbarWidth = ent.getMaxHealth()*5;
        super.update();
    }
    
    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.healthbar, 0, 0, 400, 200, null);
        super.render(g);
    }
}
