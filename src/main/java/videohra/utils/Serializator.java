/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videohra.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.logging.Logger;

/**
 *
 * @author W8
 */
public class Serializator {
    
    private static final Logger LOGGER = Logger.getLogger(Serializator.class.getName());
    
    /**
     * saves an integer to a file
     * @param fileName name of the file to save to
     * @param i integer to be saved
     */
    public static void saveToFile(String fileName, int i) {
        try (Writer wr = new FileWriter("souls.txt")) {
            wr.write(i);
            wr.close();
        } catch (IOException ex) {
            LOGGER.severe("Saving to file caused an error with exception: " + ex.toString());
        }
    }

    /**
     * loads an integer from a file
     * @param fileName name of the file to be loaded from
     * @return returns the loaded integer
     */
    public static int loadFromFile(String fileName) {
        try {
            Reader r = new FileReader(fileName);
            
            int i = r.read();
            return i;
        } catch (FileNotFoundException ex) {
            LOGGER.severe("Loading from file caused an error with exception: " + ex.toString());
        } catch (IOException ex) {
            LOGGER.severe("Loading from file caused an error with exception: " + ex.toString());
        }
        return 0;
    }
    
}
