/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities;

import java.awt.Graphics2D;
import java.util.ArrayList;
import videohra.entities.creatures.Player;
import videohra.entities.objects.Bonfire;

/**
 * manages all entities
 * updates and renders all entities
 */
public final class EntityManager {
    
    private final Player player;
    private final Bonfire bonfire;
    private ArrayList<Entity> entities;

    public EntityManager(Player player, Bonfire bonfire) {
        this.player = player;
        this.bonfire = bonfire;
        loadEntityManager();
    }
    
    /**
     * clears the entities list
     * adds the player and bonfire, because they need to be in every map
     */
    public void loadEntityManager() {
        entities = new ArrayList<>();
        addEntity(bonfire);
        addEntity(player);
    }
    
    /**
     * updates every entity in the game right now
     */
    public void update() {
        for (Entity e : entities) {
            e.update();
        }
    }
    
    /**
     * renders every entity in the game right now
     */
    public void render(Graphics2D g) {
        for (Entity e : entities) {
            e.render(g);
        }
    }
    
    /**
     * add entity to the entities arraylist
     * @param e entity to be added
     */
    public final void addEntity(Entity e) {
        entities.add(e);
    }

    public Player getPlayer() {
        return player;
    }

    public Bonfire getBonfire() {
        return bonfire;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }
}
