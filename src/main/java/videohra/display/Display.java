/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.display;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * class for creating and managing the display window
 */
public class Display {
    
    private JFrame frame;
    private Canvas canvas;
    
    private final String title;
    private final int width, height;
    
    /** 
     * constructor for the Display class
     * @param title sets the title of the window
     * @param width width of the window
     */
    public Display(String title, int width) {
        this.title = title;
        this.width = width;
        this.height = width / 16 * 9;
        
        createDisplay();
    }
    
    /** 
     * method that creates a JFrame window with the data from constructor with a canvas to render stuff
     */
    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false); // app can't focus on canvas, so the pressed keys go to JFrame

        frame.add(canvas);
        frame.pack(); //resize canvas to see everything
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getHeight() {
        return height;
    }
}
