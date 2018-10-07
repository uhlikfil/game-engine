/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.entities.creatures;

import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import videohra.Game;
import videohra.world.WorldMap;

/**
 *
 * @author W8
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    Player player;
    Enemy enemy;
    
    @Before
    public void setUp() {
        Game game = new Game("test", 100);
        WorldMap wm = new WorldMap("firelink_shrine", game);
        player = new Player(0, 0, wm);
        
        enemy = new Enemy(0, 0, wm);
        
        wm.getEntityManager().addEntity(enemy);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void setCurrentHealth() {
        player.setCurrentHealth(2);
        
        Assert.assertEquals(2, player.getCurrentHealth());
    }
    
    @Test
    public void testFullHeal() {
        player.fullHeal();
        
        Assert.assertEquals(player.getMaxHealth(), player.getCurrentHealth());
    }
    
    @Test
    public void testHurt() {
        player.fullHeal();
        player.hurt(10);
        
        Assert.assertEquals(player.getMaxHealth() - 10, player.getCurrentHealth());
    }

    @Test
    public void testDie() {
        player.die();
        
        Assert.assertEquals(false, player.isAlive());
    }
    
    @Test
    public void testResurrect() {
        player.resurrect();
        
        Assert.assertEquals(true, player.isAlive());
    }
    
    @Test public void testAttackCollision() {
        enemy.setX(player.getEntityWidth() + player.getBoundsX() - enemy.getEntityWidth() +1);
        
        player.setFacingRight(true);
        player.attack();
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(PlayerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        assertTrue(enemy.getCurrentHealth() == enemy.getMaxHealth() - player.getDamage());
    }
}
