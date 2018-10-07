/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * button has two fonts and colors that change whether it is selected or not 
*/
public class Button extends Rectangle {
    
    private final Font font, selectedFont;
    private final Color color, selectedColor;
    private boolean selected;
    private final String text;
    private final int textY;

    public Button(String text, int textY, Font font, Font selectedFont, Color color, Color selectedColor) {
        this.font = font;
        this.selectedFont = selectedFont;
        this.color = color;
        this.selectedColor = selectedColor;
        this.text = text;
        this.textY = textY;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    
    /**
     * render in the button x, y position
     */
    public void render(Graphics2D g) {
        if (selected) {
            Fonts.drawString(g, selectedFont, selectedColor, text, textY);
        } else {
            Fonts.drawString(g, font, color, text, textY);
        }
    }
    
    /**
     * render by parameters
     * @param x x position of the button
     * @param y y position of the button
     */
    public void render(Graphics2D g, int x, int y) {
        if (selected) {
            Fonts.drawString(g, selectedFont, selectedColor, text, x, y);
        } else {
            Fonts.drawString(g, font, color, text, x, y);
        }
    }
    
}
