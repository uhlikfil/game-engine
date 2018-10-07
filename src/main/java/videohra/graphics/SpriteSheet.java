/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics;

import java.awt.image.BufferedImage;

/**
 * Sprite sheet loads one BufferedImage object with all the images at once
 */
public class SpriteSheet {
    
    private final BufferedImage sheet;
    
    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }
    
    /**
     * crops the part of sprite sheet that we want
     * @param x x position of the cropped part
     * @param y y position of the cropped part
     * @param width width of the cropped part
     * @param height height of the cropped part
     * @return returns the part of sheet specified by arguments
     */
    public BufferedImage crop(int x, int y, int width, int height) {
        return sheet.getSubimage(x, y, width, height);
    }
    
}
