/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities.creatures;

import java.util.logging.Logger;

/**
 * attack of the chosen creature, runs in a separate thread
 */
public class Attack implements Runnable {

    private final Creature creature;
    private static final Logger LOGGER = Logger.getLogger(Attack.class.getName());
    
    public Attack(Creature c) {
        creature = c;
    }
    
    @Override
    public void run() {
        try {
            creature.canAttack = false;
            creature.chargingAttack = true;
            Thread.sleep(creature.attackSpeed);
            creature.attacking = true;
            creature.chargingAttack = false;
            if (creature.isAlive()) {
                creature.doDamage(creature.getAttackRectangle());
            }
            Thread.sleep(200);
            creature.attacking = false;
            creature.canAttack = true;
        } catch (InterruptedException ex) {
            LOGGER.severe("Attack failed with exception: " + ex.toString());
        }
    }
    
}
