/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import videohra.Game;

/**
 * class for drawing text
 */
public class Fonts {
    
     /**
     * choose x and y freely
     * @param g graphics object
     * @param f font of the text
     * @param c color
     * @param text text
     * @param x position x
     * @param y position y
     */   
    public static void drawString(Graphics g, Font f, Color c, String text, int x, int y) {
        g.setColor(c);
        g.setFont(f);
        g.drawString(text, x, y);
    }

    /**
     * write in the middle
     * @param g graphics object
     * @param f font of the text
     * @param c color
     * @param text text
     */
    public static void drawString(Graphics g, Font f, Color c, String text) {
        FontMetrics fm = g.getFontMetrics(f);
        int x = (Game.WINDOW_WIDTH - fm.stringWidth(text)) / 2; // horizontal center
        int y = ((Game.WINDOW_HEIGHT - fm.getHeight()) / 2) + fm.getAscent(); //vertical center
        drawString(g, f, c, text, x, y);
    }
    
    /**
     * choose y freely, x in the middle
     * @param g graphics object
     * @param f font of the text
     * @param c color
     * @param text text
     * @param y position y
     */
    public static void drawString(Graphics g, Font f, Color c, String text, int y) {
        FontMetrics fm = g.getFontMetrics(f);
        int x = (Game.WINDOW_WIDTH - fm.stringWidth(text)) / 2; // horizontal center
        drawString(g, f, c, text, x, y);
    }
}
