/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.items;

import java.awt.Graphics2D;
import java.util.ArrayList;
import videohra.graphics.Assets;

/**
 * inventory holds the player's items
 * the number of souls gets saved at the exit of the game state
 */
public class Inventory {
    
    private ArrayList<Item> inventory;

    public Inventory() {
        inventory = new ArrayList<>();
    }
    
    /**
     * adds item to the inventory
     * @param newItem item to be added
     */
    public void addItem(Item newItem) {
        for (Item i : inventory) {
            if (newItem.getClass().equals(i.getClass())) {
                i.setQuantity(i.getQuantity() + newItem.getQuantity());
                return;
            }
        }
        inventory.add(newItem);
    }
    
    public void render(Graphics2D g) {
        for (int i = 0; i < inventory.size(); i++) {
            inventory.get(i).render(g, 100, 200+Assets.ITEM_HEIGHT*i);
        }
    }
    
    public int getNumberOfSouls() {
        for (Item i : inventory) {
            if (i instanceof Soul) {
                return i.getQuantity();
            }
        }
        return 0;
    }
    
    /**
     * removes chosen item by chosen amount
     * @param iToRemove item to be removed
     * @param amount how many of that item to be removed
     */
    public void removeItem(Item iToRemove, int amount) {
        for (Item i : inventory) {
            if (iToRemove.getClass().equals(i.getClass())) {
                int tempQuantity = i.getQuantity() - amount;
                if (tempQuantity > 0) {
                    i.setQuantity(tempQuantity);
                } else {
                    inventory.remove(i);
                }
            }
        }
    }
}
