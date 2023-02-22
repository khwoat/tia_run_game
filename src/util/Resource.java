/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author ADMINS
 */
public class Resource {
    public static BufferedImage getResource(String path){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(path));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return img;
    }
}
