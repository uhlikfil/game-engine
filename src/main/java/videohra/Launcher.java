/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra;


/**
 * main class to start the game
 */
public class Launcher {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Game game = new Game("DarkSouls2D", 1280);
        game.start();
    }
    
}
