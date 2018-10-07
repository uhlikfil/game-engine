/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities.creatures;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import videohra.graphics.Animation;
import videohra.graphics.Assets;
import videohra.graphics.healthbars.EnemyHealthbar;
import videohra.world.WorldMap;

/**
 * basic enemy creature
 */
public class Enemy extends Creature {

    private final Animation idle, walking_right, walking_left;
    private final EnemyHealthbar healthbar;
    
    private final double initX;
    
    public Enemy(double x, double y, WorldMap worldMap) {
        super(x, y, worldMap);
        
        // collision box
        entityWidth = 80;
        entityHeight = 170;
        boundsX = 60;
        boundsY = 5;
        attackRange = (int) boundsX;
        
        // starting position = center of the creature
        initX = x + boundsX + entityWidth/2;
        
        // animations
        idle = new Animation(800, Assets.enemy_idle);
        walking_right = new Animation(250, Assets.enemy_walking_right);
        walking_left = new Animation(250, Assets.enemy_walking_left);
        
        // hp bar
        healthbar = new EnemyHealthbar(this, worldMap);
    }
    
    /**
     * the enemy's "AI"
     */
    @Override
    public void update() {
        if (alive) {
           // Animations
            idle.update();
            walking_right.update();
            walking_left.update();

            // Healthbar
            healthbar.update();

            // What to do

            if (abs(centerPosX - worldMap.getEntityManager().getPlayer().getCenterPosX()) > WorldMap.blocksToPixels(5) || !worldMap.getEntityManager().getPlayer().isAlive()) {
                getBack(centerPosX);
            } else if (centerPosX < worldMap.getEntityManager().getPlayer().getCenterPosX()) {
                runRight();
                if (worldMap.horizontalCollision(this)) { jump(jumpStrength); }
            } else if (centerPosX > worldMap.getEntityManager().getPlayer().getCenterPosX()) {
                runLeft();
                if (worldMap.horizontalCollision(this)) { jump(jumpStrength); }
            } if (abs(centerPosX - worldMap.getEntityManager().getPlayer().getCenterPosX()) <= WorldMap.blocksToPixels(2) && worldMap.getEntityManager().getPlayer().isAlive()) {
                attack();
            } 
        }
        
        super.update();
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(this.getCurrentAnimationFrame(), (int) (x - worldMap.getGameCam().getxOffset()), (int) (y - worldMap.getGameCam().getyOffset()), null);
        healthbar.render(g);
//    g.draw(getCollisionBounds(-worldMap.getGameCam().getxOffset(), -worldMap.getGameCam().getyOffset()));
    }
    
    @Override
    protected BufferedImage getCurrentAnimationFrame() {
        if (!alive) {
            return Assets.enemy_dead;
        } else if (chargingAttack && facingRight) {
            return Assets.enemy_charge_right;
        } else if (chargingAttack && !facingRight) {
            return Assets.enemy_charge_left;
        } else if (attacking && facingRight) {
            return Assets.enemy_att_right;
        } else if (attacking && !facingRight) {
            return Assets.enemy_att_left;
        } else if (moveX > 0) {
            return walking_right.getCurrentFrame();
        } else if (moveX < 0) {
            return walking_left.getCurrentFrame();
        } else {
            return idle.getCurrentFrame();
        }
    }
    
    
    /**
     * go back to it's original position
     */
    private void getBack(double myPos) {
        if (myPos > initX + maxRunSpeed) { runLeft(); }
        else if (myPos < initX - maxRunSpeed) { runRight(); }
        else { moveX = 0; }
    }
}
