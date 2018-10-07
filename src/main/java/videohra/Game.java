/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra;

import videohra.display.Display;
import videohra.graphics.Assets;
import videohra.input.KeyManager;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;
import videohra.states.*;


/**
 * the game class that puts everything together
 */
public class Game implements Runnable {
    
    private static final Logger LOGGER = Logger.getLogger(Game.class.getName());
    
    private Display display;
    public static String GAME_TITLE;
    public static int WINDOW_WIDTH, WINDOW_HEIGHT;
        
    private boolean running = false;
    private Thread thread;
        
    private BufferStrategy bs;
    private Graphics gF;
    private Graphics2D g;
    
    // states
    private StateManager stateManager;
    
    // input
    private final KeyManager keyManager;
    
    
    public Game(String title, int width) {
        GAME_TITLE = title;
        WINDOW_WIDTH = width;
        keyManager = new KeyManager();
    }
    
    /**
     * initialization of the game =>
     * creates a display window
     * adds a key listener to the JFrame window
     * initializes assets
     * creates state objects for all the states
     */
    private void init() {
        display = new Display(GAME_TITLE, WINDOW_WIDTH);
        WINDOW_HEIGHT = display.getHeight();
        display.getFrame().addKeyListener(keyManager);
        Assets.init();
                
        // states
        stateManager = new StateManager();
        
        stateManager.addState(new MenuState(this));
        stateManager.addState(new ControlsState(this));
        stateManager.addState(new GameState(this));
    }
    
    /**
     * update function for the game loop
     * calls an update function depending on the current state of game
     */
    private void update() {
        stateManager.update();
    }
    
    /**
     * calls a render function depending on the current state of the game
     */
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        gF = bs.getDrawGraphics();
        g = (Graphics2D) gF;
        
        g.clearRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        //drawing
        stateManager.render(g);
        //end of drawing
        
        bs.show();
        g.dispose();
    }
    
    /**
     * starts the game = calls initialization of the game
     * controls fps
     * game loop = update + render
     */
    @Override
    public void run() {
        init();
        
        int fps = 60; // frames per second
        double timePerUpdate = 1000000000/fps; // maximum amount of time to execute update and render
                                               // every 1/60 seconds = one update (one frame)
        double delta = 0; // time until it has to update
        long now;
        long lastTime = System.nanoTime(); // time of my computer in nanoseconds
        
        long timer = 0; // fps check
        int ticks = 0; // fps check
        
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerUpdate; // when adds up to 1 => new update and render
            
            timer += now - lastTime; //fps check - timer adds up to one second
            
            lastTime = now;         
            
            if (delta >= 1) { // when adds up to 1 => new update and render
                update();
                KeyManager.update();
                render();
                ticks ++; // fps check = counts updates
                delta--;
            }   
            
            
            // fps check = prints out how many updates there were during the last second
            if (timer >= 1000000000) {
                System.out.println("Ticks and Frames " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
    }
    
    /**
     * start of the game - creates a new thread, sets running to true
     */
    protected synchronized void start() {
        if (running) {
            return;
        }
        
        running = true;
        thread = new Thread(this, "GameMainThread");
        thread.start();
    }
    
    /**
     * end of the game - closes the thread, sets running to false
     */
    public synchronized void stop() {
        if (!running) {
            return;
        }
        
        running = false;
        System.exit(0);
        try {
            thread.join();
        } catch (InterruptedException ex) {
            LOGGER.severe("Thread join failed with exception " + ex.toString());
        }
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public StateManager getStateManager() {
        return stateManager;
    }
}
