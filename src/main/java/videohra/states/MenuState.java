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
import videohra.graphics.ui.Fonts;
import videohra.graphics.Assets;
import videohra.graphics.ui.Button;
import videohra.input.KeyManager;

/**
 * menu that opens at the start of the game
 */
public class MenuState implements State {
    
    protected final Game game;
    private Button[] menuOptions;
    private int currentSelection;
    
    private boolean up, down, select;

    public MenuState(Game game) {
        this.game = game;
    }

    
    @Override
    public void enterState() {
        currentSelection = 0;
    }
    
    /**
     * initialization of the menu state
     * creates button array and adds the buttons with the menu options
     */
    @Override
    public void init() {        
        Font normalFont = new Font("Impact", Font.PLAIN, 32);
        Font selectedFont = new Font("Impact", Font.BOLD, 48);
        
        menuOptions = new Button[3];
        menuOptions[0] = new Button("PLAY", Game.WINDOW_HEIGHT / 3, normalFont,
                selectedFont, Color.WHITE, Color.DARK_GRAY);
        menuOptions[1] = new Button("CONTROLS", Game.WINDOW_HEIGHT / 2, normalFont,
                selectedFont, Color.WHITE, Color.DARK_GRAY);
        menuOptions[2] = new Button("EXIT",  Game.WINDOW_HEIGHT / 3 * 2, normalFont,
                selectedFont, Color.WHITE, Color.DARK_GRAY);
    }
    
    /**
     * checks the input
     * navigates the menu
     * @param stateManager state manager to set the new current state
     */
    @Override
    public void update(StateManager stateManager) {
            keyInput();

            if (up) {
                currentSelection--;
                if (currentSelection < 0) currentSelection = menuOptions.length - 1;
            }
            if (down) {
                currentSelection++;
                if (currentSelection >= menuOptions.length) currentSelection = 0;
            }
            if (select) {
                select(stateManager);
            }
    }
    
    /**
     * updates the input variables
     */
    private void keyInput() {       
        up = KeyManager.wasPressed(KeyEvent.VK_UP) || KeyManager.wasPressed(KeyEvent.VK_W);
        down = KeyManager.wasPressed(KeyEvent.VK_DOWN) || KeyManager.wasPressed(KeyEvent.VK_S);
        select = KeyManager.wasPressed(KeyEvent.VK_ENTER) || KeyManager.wasPressed(KeyEvent.VK_SPACE);
    }
    
    private void select(StateManager stateManager) {
        switch (currentSelection) {
            case 0:
                stateManager.setCurrentState("Game");
                break;
            case 1:
                stateManager.setCurrentState("Controls");
                break;
            case 2:
                game.stop();
                break;
        }
    }
    
    @Override
    public void render(Graphics2D g) {
            g.drawImage(Assets.menuBackground, 0, 0, Game.WINDOW_WIDTH, Game.WINDOW_HEIGHT, null);
            Fonts.drawString(g, new Font("Impact", Font.BOLD, 72), Color.DARK_GRAY, Game.GAME_TITLE,
                                Game.WINDOW_WIDTH / 10, 180);

            for (int i = 0; i < menuOptions.length; i++) {
                if (i == currentSelection) {
                    menuOptions[i].setSelected(true);
                } else {
                    menuOptions[i].setSelected(false);            
                }
                menuOptions[i].render(g);
            }
    }

    @Override
    public String getName() {
        return "Menu";
    }
}
