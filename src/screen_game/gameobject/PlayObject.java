/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author ADMINS
 */
public abstract class PlayObject {
    public abstract void update();
    public abstract void draw(Graphics g);
    public abstract Rectangle getBound();

    public abstract int getPosX();
    public abstract int getPosY();
    public abstract BufferedImage getImage();
}
