/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities.creatures;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import videohra.entities.Entity;
import videohra.graphics.Animation;
import videohra.graphics.Assets;
import videohra.graphics.healthbars.PlayerHealthbar;
import videohra.input.KeyManager;
import videohra.items.Inventory;
import videohra.items.Soul;
import videohra.utils.Serializator;
import videohra.world.WorldMap;

/**
 * the player class
 */
public class Player extends Creature {
    
    private boolean resting;
    private boolean left, right, jump, attack; // keys
    private final Animation idle, walking_right, walking_left;
    private final PlayerHealthbar healthbar;
    private final Inventory inventory;
    
    public Player(double x, double y, WorldMap worldMap) {
        super(x, y, worldMap);
        maxRunSpeed = 6;
        
        // collision box
        entityWidth = 80;
        entityHeight = 170;
        boundsX = 60;
        boundsY = 5;
        attackRange = (int) boundsX;
        
        // animations
        idle = new Animation(600, Assets.player_idle);
        walking_right = new Animation(250, Assets.player_walking_right);
        walking_left = new Animation(250, Assets.player_walking_left);
        
        // hp bar
        healthbar = new PlayerHealthbar(this);
        
        // inventory
        inventory = new Inventory();
        inventory.addItem(new Soul(worldMap, 0, 0, Serializator.loadFromFile("souls.txt")));     
        
        // stats
        baseDamage = 5;
        baseAttackSpeed = 1000;
        buffBySouls();
        currentHealth = maxHealth;
    }
    
    private void buffBySouls() {
        int tempDamage = baseDamage + inventory.getNumberOfSouls();
        if (tempDamage < 200) {
            damage = tempDamage;
        } else {
            damage = 200;
        }
        
        int tempMaxHealth = baseMaxHealth + inventory.getNumberOfSouls();
        if (tempMaxHealth < 300) {
            maxHealth = tempMaxHealth;
        } else {
            maxHealth = 300;
        }
        
        int tempAttackSpeed = baseAttackSpeed - inventory.getNumberOfSouls() * 10;
        if (tempAttackSpeed > 150) {
            attackSpeed = tempAttackSpeed;
        } else {
            attackSpeed = 150;
        }
    }
    
    
    @Override
    public void update() {
        // Buffs
        buffBySouls();
        
        // Healthbar
        healthbar.update();
        
        if (alive) {
            // Animations
            idle.update();
            walking_right.update();
            walking_left.update();

            // Movement
            keyInput();
            
            if (!resting) {
                if (attack) { attack(); }
                if (right) { runRight(); }
                if (left) { runLeft(); }
                if (jump) { jump(jumpStrength); }
            }
            
            if (KeyManager.wasReleased(KeyEvent.VK_A) || KeyManager.wasReleased(KeyEvent.VK_D) ||
                KeyManager.wasReleased(KeyEvent.VK_LEFT) || KeyManager.wasReleased(KeyEvent.VK_RIGHT)) { moveX = 0; }
        }
        
        super.update();
        worldMap.getGameCam().centerOnEntity(this);
    }
    
    private void keyInput() {
        left = KeyManager.isDown(KeyEvent.VK_A) || KeyManager.isDown(KeyEvent.VK_LEFT);
        right = KeyManager.isDown(KeyEvent.VK_D) || KeyManager.isDown(KeyEvent.VK_RIGHT);
        jump = KeyManager.isDown(KeyEvent.VK_W) || KeyManager.isDown(KeyEvent.VK_UP);
        attack = KeyManager.isDown(KeyEvent.VK_SPACE);
    }
    
    
    /**
     * hurts all other entities
     */
    @Override
    protected void doDamage(Rectangle ar) {
        for (Entity e : worldMap.getEntityManager().getEntities()) {
            if (e.equals(this)) {}
            else if (e.getCollisionBounds(0, 0).intersects(ar)) {
                e.hurt(damage);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(this.getCurrentAnimationFrame(), (int) (x - worldMap.getGameCam().getxOffset()), (int) (y - worldMap.getGameCam().getyOffset()), null);
        healthbar.render(g);
    }
    
    @Override
    protected BufferedImage getCurrentAnimationFrame() {
        if (!alive) {
            return Assets.player_dead;
        } else if (resting) {
            return Assets.player_resting;
        } else if (chargingAttack && facingRight) {
            return Assets.player_charge_right;
        } else if (chargingAttack && !facingRight) {
            return Assets.player_charge_left;
        } else if (attacking && facingRight) {
            return Assets.player_att_right;
        } else if (attacking && !facingRight) {
            return Assets.player_att_left;
        } else if (moveX > 0) {
            return walking_right.getCurrentFrame();
        } else if (moveX < 0) {
            return walking_left.getCurrentFrame();
        } else {
            return idle.getCurrentFrame();
        }
    }
    
    /**
     * removes 10 souls from the inventory
     */
    @Override
    public void die() {
        super.die();
        inventory.removeItem(new Soul(worldMap, 0, 0), 10);
    }
    
    /**
     * makes the player rest at the bonfire, healing him and resetting the world
     */
    public void rest() {
        worldMap.getEntityManager().getBonfire().restartMap();
        resting = true;
        moveX = 0;
        fullHeal();
    }
    
    /**
     * stops resting at the bonfire
     */
    public void getUp() {
        resting = false;
    }

    public boolean isResting() {
        return resting;
    }

    public void setResting(boolean resting) {
        this.resting = resting;
    }

    public Inventory getInventory() {
        return inventory;
    }
}
