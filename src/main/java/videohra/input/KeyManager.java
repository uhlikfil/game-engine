/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * controls the keyboard input
 */
public class KeyManager extends KeyAdapter {
    
    private static final int NUM_OF_KEYS = 256;
    
    private static final boolean[] keys = new boolean[NUM_OF_KEYS];
    private static final boolean[] lastKeys = new boolean[NUM_OF_KEYS];

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }
    
    public static void update() {
        System.arraycopy(keys, 0, lastKeys, 0, NUM_OF_KEYS);
    } 
    
    /**
     * checks if the key is pressed right now
     * @param keyCode the key on the keyboard
     * @return true if the key is pressed, false if not
     */
    public static boolean isDown(int keyCode) {
        return keys[keyCode];
    }
    
    /**
     * checks if the key is pressed right now and was not pressed the last update
     * @param keyCode the key on the keyboard
     * @return true if the key is pressed and wasn't pressed before
     */
    public static boolean wasPressed(int keyCode) {
        return isDown(keyCode) && !lastKeys[keyCode];
    }
    
    /**
     * checks if the key is not pressed right now but was pressed the last update
     * @param keyCode the key on the keyboard
     * @return true if the key is not pressed but was pressed before
     */
    public static boolean wasReleased(int keyCode) {
        return !isDown(keyCode) && lastKeys[keyCode];
    }
}
