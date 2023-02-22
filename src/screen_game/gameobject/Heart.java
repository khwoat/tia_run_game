/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game.gameobject;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
import static userinterface.PlayScreen.GROUNDY;
import static userinterface.PlayScreen.speed;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class Heart extends PlayObject{
    private Random random;
    private Rectangle rect;
    
    private BufferedImage largeHeart, smallHeart;
    private int posX, posY;
    public static int numHeart = 0;
    
    public Heart(){
        random = new Random();
        rect = new Rectangle();
        
        largeHeart = Resource.getResource("data/heart/heart_large.png");
        smallHeart = Resource.getResource("data/heart/heart_small.png");
        posX = 1600 + random.nextInt(11)*100;
        posY = (int)GROUNDY - largeHeart.getHeight() - 200;
    }
    
    @Override
    public void update(){       
        posX -= 10 + (1*speed);//
        rect.x = posX + 12;
        rect.y = posY + 23;
        rect.width = largeHeart.getWidth() -12;
        rect.height = largeHeart.getHeight() -23;
    }

    @Override
    public Rectangle getBound(){
        return rect;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(largeHeart, posX, posY, null);
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public BufferedImage getImage() {
        return largeHeart;
    }

    public BufferedImage getSmallHeart() {
        return smallHeart;
    }
    
}
