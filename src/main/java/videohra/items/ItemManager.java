/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.items;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * manages all items
 * updates and renders all items in the worldmap
 */
public final class ItemManager {
 
    private ArrayList<Item> items;

    public ItemManager() {
        loadItemManager();
    }
    
    public void loadItemManager() {
        items = new ArrayList<>();
    }
    
    /**
     * update all items, remove the picked ones
     */
    public void update() {
        Iterator<Item> it = items.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            i.update();
            if (i.isPickedUp()) {
                it.remove();
            }
        }
    }
    
    /**
     * render all items
     */
    public void render(Graphics2D g) {
        for (Item i : items) {
            i.render(g);
        }
    }
    
    /**
     * add an item to the world map
     * @param i item to be added
     */
    public void addItem(Item i) {
        items.add(i);
    }
    
    /**
     * remove item from the world map
     * @param iToRemove item to be removed
     */
    public void removeItem(Item iToRemove) {
        for (Item i : items) {
            if (i.equals(iToRemove)) {
                items.remove(i);
                return;
            }
        }
    }
}
