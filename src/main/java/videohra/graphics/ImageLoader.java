/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * loads an image from the resources folder
 */
public class ImageLoader {
        
    private static final Logger LOGGER = Logger.getLogger(ImageLoader.class.getName());

    static String testPath = "src/main/resources/";
    static String buildPath = "classes/";
    
    /**
     * static function for loading images from resources folder into BufferedImage object
     * @param path path from the recourses folder
     * @return returns the image as a BufferedImage object
     */
    public static BufferedImage loadImage(String path) {
        try {
            File img = new File(testPath + path);
            BufferedImage image = ImageIO.read(img);
            return image;
        } catch (IOException ex) {
            LOGGER.severe("Loading an image failed with exception: " + ex.toString());
            return null;
        }
    }
}
