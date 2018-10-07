/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.items;

import java.awt.Font;
import java.awt.Graphics2D;
import videohra.world.WorldMap;

/**
 * abstract class for items
 */
public abstract class Item {
            
    protected WorldMap worldMap;
    protected double x, y;
    protected boolean pickedUp;
    protected int quantity;
    protected String name;
    protected Font itemFont;


    public Item(WorldMap worldMap, double x, double y) {
        this.worldMap = worldMap;
        this.x = x;
        this.y = y;
        pickedUp = false;
        quantity = 1;
        itemFont = new Font("Impact", Font.PLAIN, 18);
    }
    
    public abstract void update();
    
    public abstract void render(Graphics2D g);
    
    public abstract void render(Graphics2D g, int x, int y);

    public boolean isPickedUp() {
        return pickedUp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
        
    protected void getPickedUp() {
        pickedUp = true;
        worldMap.getEntityManager().getPlayer().getInventory().addItem(this);
    }

    public String getName() {
        return name;
    }
}
