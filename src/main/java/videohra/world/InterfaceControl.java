/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.world;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import videohra.Game;
import videohra.entities.creatures.Player;
import videohra.entities.objects.Bonfire;
import videohra.graphics.Assets;
import videohra.graphics.ui.Button;
import videohra.graphics.ui.Fonts;
import videohra.input.KeyManager;
import videohra.states.StateManager;
import videohra.utils.Serializator;

/**
 * controls the menu in game state
 */
public class InterfaceControl {
    
    private final Player player;
    private final Bonfire bonfire;
    private boolean paused;
    private boolean up, down, select, use, back; // keys
    private final WorldMap worldMap;
    private final Button[] bonfireRenderMenu;

    private final Font restMessage;

    public InterfaceControl(WorldMap worldMap) {
        this.worldMap = worldMap;
        restMessage = new Font("Arial", Font.PLAIN, 24);
        
        bonfire = worldMap.getEntityManager().getBonfire();
        player = worldMap.getEntityManager().getPlayer();        
        
        Font normalFont = new Font("Impact", Font.PLAIN, 32);
        Font selectedFont = new Font("Impact", Font.BOLD, 48);
        
        bonfireRenderMenu = new Button[2];
        bonfireRenderMenu[0] = new Button("Firelink Shrine", Game.WINDOW_HEIGHT / 3, normalFont,
                selectedFont, Color.WHITE, Color.WHITE);
        bonfireRenderMenu[1] = new Button("Konoha", Game.WINDOW_HEIGHT / 2, normalFont,
                selectedFont, Color.WHITE, Color.WHITE);
    }
    
    /**
     * updates the input variables
     */
    private void keyInput() {       
        up = KeyManager.wasPressed(KeyEvent.VK_UP) || KeyManager.wasPressed(KeyEvent.VK_W);
        down = KeyManager.wasPressed(KeyEvent.VK_DOWN) || KeyManager.wasPressed(KeyEvent.VK_S);
        select = KeyManager.wasPressed(KeyEvent.VK_ENTER);
        use = KeyManager.wasPressed(KeyEvent.VK_X);
        back = KeyManager.wasPressed(KeyEvent.VK_ESCAPE);
    }
    
    /**
     * gets the key input and updates the menus
     * @param stateManager to change the game state
     */
    public void update(StateManager stateManager) {
        keyInput();
        
        if (!player.isAlive() && !paused && select) {
            player.resurrect();
            player.rest();
            player.getUp();
        } else if (bonfire.isPlayerNear() && !player.isResting() && !paused && use) {
            player.rest();
        } else if (player.isResting()) {
            if (up) {
                bonfire.menuUp();
            }
            if (down) {
                bonfire.menuDown();
            }
            if (select || use) {
                bonfire.menuSelect();
            }
            if (back) {
                player.getUp();
            }
        } else if (!paused && back) {
            paused = true; 
        } else if (paused && back) {
            paused = false;  
        } else if (paused && select) {
            exitToMenu(stateManager);
        }
    }
    
    /**
     * renders the different menus that the player can access (bonfire teleports, exiting and so on...)
     */
    public void render(Graphics2D g) {
        if (player.isResting()) {
            for (int i = 0; i < bonfireRenderMenu.length; i++) {
                if (i == bonfire.getCurrentSelection()) {
                    bonfireRenderMenu[i].setSelected(true);
                } else {
                    bonfireRenderMenu[i].setSelected(false);            
                }
                bonfireRenderMenu[i].render(g, 200, 300 + 60*i);
            }
        } else if (!paused && !player.isAlive()) {
            g.drawImage(Assets.youDied, Game.WINDOW_WIDTH / 2 - Assets.youDied.getWidth()/2, Game.WINDOW_HEIGHT / 2 - Assets.youDied.getHeight()/2, null);
        } else if (paused) {
            g.drawImage(Assets.textBg, Game.WINDOW_WIDTH / 2 - Assets.textBg.getWidth()/2, Game.WINDOW_HEIGHT / 2 - Assets.textBg.getHeight()/2, null);
            Fonts.drawString(g, new Font("Impact", Font.BOLD, 32), Color.WHITE, "ENTER TO EXIT");
            worldMap.getEntityManager().getPlayer().getInventory().render(g);
        } else if (bonfire.isPlayerNear() && !player.isResting()) {
            g.drawImage(Assets.textBg, Game.WINDOW_WIDTH / 2 - Assets.textBg.getWidth()/2, Game.WINDOW_HEIGHT - 138, null);
            Fonts.drawString(g, restMessage, Color.WHITE, "(x) rest at the bonfire", Game.WINDOW_HEIGHT - 100);
        }
    }
     
    /**
     * saves the number of souls
     * changes the game state to menustate
     * @param stateManager to change states
     */
    public void exitToMenu(StateManager stateManager) {
        Serializator.saveToFile("souls.txt", player.getInventory().getNumberOfSouls());
        stateManager.setCurrentState("Menu");
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }    
}
