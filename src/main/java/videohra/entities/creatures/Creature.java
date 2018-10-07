/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities.creatures;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static java.lang.Math.abs;
import videohra.entities.Entity;
import videohra.world.WorldMap;
import videohra.items.Soul;

/**
 * abstract class for creatures (entities that can move)
 */
public abstract class Creature extends Entity {
    
    protected int baseAttackSpeed, baseDamage;
    protected int attackRange, attackSpeed, damage;
    protected double moveX, moveY, acceleration, maxRunSpeed, maxFallSpeed, gravity, jumpStrength;
    protected boolean facingRight, canAttack, chargingAttack, attacking, canJump;
    
    public Creature(double x, double y, WorldMap worldMap) {
        super(x, y, worldMap);
        alive = true;
        gravity = 0.5;
        maxFallSpeed = 22;
        maxRunSpeed = 3;
        acceleration = 0.1;
        jumpStrength = 18;
        facingRight = true;
        canAttack = true;
        chargingAttack = false;
        attacking = false;
        baseDamage = 10;
        baseAttackSpeed = 800;
        
        damage = baseDamage;
        attackSpeed = baseAttackSpeed;
    }

    @Override
    public void update() {
        super.update();
        move();
        fall();
    }
    
    /**
     * checks collision with blocks and entities and moves the creature
     */
    public void move() {
        if (canAttack && alive) {
            if (!checkEntityCollision(moveX, 0f) && !worldMap.horizontalCollision(this)) { x += moveX; }
        }

        if (!checkEntityCollision(0f, moveY) && !worldMap.verticalCollision(this)) { y += moveY; }
    }
    
    protected void runRight() {
        if (canAttack) {
            facingRight = true;
            moveX += acceleration;
            if (moveX > maxRunSpeed) { moveX = maxRunSpeed; }
        }
    }
    
    protected void runLeft() {
        if (canAttack) {
            facingRight = false;
            moveX -= acceleration;
            if (abs(moveX) > maxRunSpeed) { moveX = -maxRunSpeed; }
        }
    }
    
    protected void fall() {
        moveY += gravity;
        if (moveY > maxFallSpeed) { moveY = maxFallSpeed; }
    }
    
    protected void jump(double jumpHeight) {
        if (canJump) {
            moveY -= jumpHeight;
            canJump = false;
        }
    }
    
    /**
     * @param ar the area where the creature does damage
     * if the rectangle collides with the player, subtract from his hp
     */
    protected void doDamage(Rectangle ar) {     
        if (worldMap.getEntityManager().getPlayer().getCollisionBounds(0, 0).intersects(ar)) {
            worldMap.getEntityManager().getPlayer().hurt(damage);
        }
    }
    
    /**
     * attacks in a new thread
     */
    protected void attack() {
        if (canAttack) {
            Thread attack = new Thread(new Attack(this));
            attack.start();
        }
    }
    
    /**
     * @return returns a rectangle where the creature does damage
     */
    protected Rectangle getAttackRectangle() {
        if (facingRight) {
            return new Rectangle((int) (x + boundsX + entityWidth), (int) (y + boundsY), attackRange, (int) entityHeight);
        } else {
            return new Rectangle((int) (x + boundsX - attackRange), (int) (y + boundsY), attackRange, (int) entityHeight);  
        }
    }
    
    /**
     * checks collision with all other entities
     * @param tempX where the entity wants to go horizontally
     * @param tempY where the entity wants to go vertically
     * @return true if collides, false if not
     */
    public boolean checkEntityCollision(double tempX, double tempY) {
        for (Entity e : worldMap.getEntityManager().getEntities()) {
            if (e.equals(this) || !e.isAlive()) {
                continue;
            }
            if (e.getTopBounds(0f, 0f).intersects(getBottomBounds(tempX, tempY))) {
                setCanJump(true);
                return true;
            } else if (e.getBottomBounds(0f, 0f).intersects(getTopBounds(tempX, tempY))) {
                setMoveY(0);
            } else if (e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(tempX, tempY))) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * death stops the creature's movement
     * if the creature is not the player, it drops souls
     */
    @Override
    public void die() {
        super.die();
        moveX = 0;
        
        if (!(this instanceof Player)) {
            worldMap.getItemManager().addItem(new Soul(worldMap, centerPosX, centerPosY));
        }
    }
    
    public void fullHeal() {
        currentHealth = maxHealth;
    }
    
    public double getMoveX() {
        return moveX;
    }

    public void setMoveX(double moveX) {
        this.moveX = moveX;
    }

    public double getMoveY() {
        return moveY;
    }

    public void setMoveY(double moveY) {
        this.moveY = moveY;
    }
    
    public boolean getCanJump() {
        return canJump;
    }
        
    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public boolean getCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public void setFacingRight(boolean facingRight) {
        this.facingRight = facingRight;
    }

    public int getDamage() {
        return damage;
    }
      
    protected abstract BufferedImage getCurrentAnimationFrame();
}
