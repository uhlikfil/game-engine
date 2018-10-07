/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import videohra.Game;
import videohra.graphics.Assets;
import videohra.graphics.ui.Fonts;
import videohra.input.KeyManager;

/**
 * shows the controls for the game
 */
public class ControlsState extends MenuState {
            
    public ControlsState(Game game) {
        super(game);
    }
    
    @Override
    public void init() {
        
    }
    
    @Override
    public String getName() {
        return "Controls";
    }

    @Override
    public void update(StateManager stateManager) {
        if (KeyManager.wasPressed(KeyEvent.VK_ESCAPE)) {
            stateManager.setCurrentState("Menu");
        }
    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.controls, 0, 0, null);
        Fonts.drawString(g, new Font("Impact", Font.BOLD, 48), Color.DARK_GRAY, "ESC TO GO BACK", Game.WINDOW_HEIGHT - 120);
    }
}
