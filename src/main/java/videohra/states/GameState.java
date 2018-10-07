/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.states;

import java.awt.Graphics2D;
import videohra.Game;
import videohra.world.InterfaceControl;
import videohra.world.WorldMap;

/**
 * holds the map and interface controls - updates and renders them
 */
public class GameState implements State {
    
    private final Game game;
    private WorldMap worldMap;
    private InterfaceControl interfaceControl;


    public GameState(Game game) {
        this.game = game;
    }
    
    
    @Override
    public void enterState() {
        interfaceControl.setPaused(false);
    }
    
    @Override
    public void init() {
        worldMap = new WorldMap("firelink_shrine", game);
        interfaceControl = new InterfaceControl(worldMap);
    }
    
    @Override
    public void update(StateManager stateManager) {
        worldMap.update();
        interfaceControl.update(stateManager);
    }

    @Override
    public void render(Graphics2D g) {
        worldMap.render(g);
        interfaceControl.render(g);
    }

    @Override
    public String getName() {
        return "Game";
    }
}
