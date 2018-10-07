/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;
import videohra.world.WorldMap;

/**
 * abstract class for every entity in the game
 */
public abstract class Entity {
    
    private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());
    
    protected double x,y;
    protected double centerPosX, centerPosY;
    protected double entityWidth, entityHeight, boundsX, boundsY;
    protected BufferedImage texture;
    protected WorldMap worldMap;
    protected int currentHealth, maxHealth, baseMaxHealth;
    protected boolean alive;

    public Entity(double x, double y, WorldMap worldMap) {
        this.x = x;
        this.y = y;
        this.worldMap = worldMap;
        baseMaxHealth = 50;
        maxHealth = baseMaxHealth;
        currentHealth = maxHealth;
        alive = true;
    }

    /**
     * calculates position of the entity's center
     */
    public void update() {
        centerPosX = x + boundsX + entityWidth/2;
        centerPosY = y + boundsY + entityHeight/2;
    }
    
    public abstract void render(Graphics2D g);
    
    /**
     * subtracts some amount of health
     * @param amount how much health does the creature lose
     */
    public void hurt(int amount) {
        if (alive) {
            currentHealth -= amount;
            LOGGER.info(this.toString() + " lost " + amount + " amount of health.");
            if (currentHealth <= 0) {
                die();
            }
        }
    }
    
    /**
     * entity dies, sets alive to false
     */
    public void die() {
        alive = false;
    }
    
    /**
     * @param tempX where the entity wants to go horizontally
     * @param tempY where the entity wants to go vertically
     * @return collision rectangle in the place where the entitty wants to go
     */
    public Rectangle getCollisionBounds(double tempX, double tempY) {
        return new Rectangle((int) (x + boundsX + tempX), (int) (y + boundsY + tempY), (int) entityWidth, (int) entityHeight);
    }
    
    /**
     * special check for jumping from entities
     * @param tempX where the entity wants to go horizontally
     * @param tempY where the entity wants to go vertically
     * @return bottom collision rectangle in the place where the entitty wants to go
     */
    public Rectangle getBottomBounds(double tempX, double tempY) {
        return new Rectangle((int) (x + boundsX + 10 + tempX), (int) (y + boundsY + entityHeight - 10 + tempY), (int) entityWidth - 20, 10);
    }
    
    /**
     * special check for jumping from entities
     * @param tempX where the entity wants to go horizontally
     * @param tempY where the entity wants to go vertically
     * @return bottom collision rectangle in the place where the entitty wants to go
     */
    public Rectangle getTopBounds(double tempX, double tempY) {
        return new Rectangle((int) (x + boundsX + 10 + tempX), (int) (y + boundsY + tempY), (int) entityWidth - 20, 10);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }   

    public double getCenterPosX() {
        return centerPosX;
    }

    public double getCenterPosY() {
        return centerPosY;
    }
    
    public double getEntityWidth() {
        return entityWidth;
    }

    public double getEntityHeight() {
        return entityHeight;
    }

    public double getBoundsX() {
        return boundsX;
    }

    public double getBoundsY() {
        return boundsY;
    }    

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public boolean isAlive() {
        return alive;
    }

    public void resurrect() {
        this.alive = true;
    }    
}
