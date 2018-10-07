/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics;

import java.awt.image.BufferedImage;

/**
 * takes an array of images and a speed and cycles them to create an animation
 */
public class Animation {
    
    private final int speed;
    private int index;
    private long lastTime, timer;
    private final BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
        timer = 0;
    }
    
    /**
     * increments the array index every (speed) miliseconds
     */
    public void update() {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if (timer > speed) {
            timer = 0;
            index ++;
            if (index >= frames.length) {
                index = 0;
            }
        }
    }
    
    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
