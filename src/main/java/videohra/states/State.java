/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.states;

import java.awt.Graphics2D;

/**
 * interface for all the states
 */
public interface State {
    
    public void init();
    public void update(StateManager stateManager);
    public void render(Graphics2D g);
    public String getName();
    public void enterState();
}
