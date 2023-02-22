/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game.gameobject;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import util.Animation;

/**
 *
 * @author ADMINS
 */
public abstract class Dog {
    public abstract Animation getAnimation();
    public abstract void setBound(int y);
    public abstract Rectangle getBound();
    public abstract BufferedImage getImageJump();
    public abstract BufferedImage getImageOver();
    public abstract BufferedImage getImageSelect();
    public abstract BufferedImage getImageSelected();
    public abstract BufferedImage getImageBuy();
    
    public abstract int getPrice();
    public abstract void setPurchased(boolean purchased);
    public abstract boolean isPurchased();
    public abstract float getPosX();
    public abstract float getPosY();
    
}
