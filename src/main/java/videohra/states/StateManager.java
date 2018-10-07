/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.states;

import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

/**
 * controls different game states
 * uses a map with state names and the state objects
 * tracks and changes the current state
 */
public class StateManager {
    
    private final Map<String, State> stateMap;
    private State currentState;

    public StateManager() {
        stateMap = new HashMap<>();
    }
    
    
    /**
     * adds state to the state map
     * sets the first state added as a current state
     * @param state state to be added
     */
    public void addState(State state) {
        stateMap.put(state.getName().toUpperCase(), state);
        state.init();
        
        if (currentState == null) {
            currentState = state;
        }
    }
    
    /**
     * if the name is wrong, prints out and error and does nothing
     * @param name name of the state to be set from the state map
     */
    public void setCurrentState(String name) {
        State state = stateMap.get(name.toUpperCase());
        if (state == null) {
            System.err.println("State <" + name + "> doesn't exist.");
            return;
        }
        
        currentState = state;
        currentState.enterState();
    }
    
    public void update() {
        currentState.update(this);
    }
    
    public void render(Graphics2D g) {
        currentState.render(g);
    }
    
    public State getCurrentState() {
        return currentState;
    }
}
