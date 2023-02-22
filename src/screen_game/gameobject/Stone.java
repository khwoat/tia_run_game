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
public class Stone extends PlayObject{
    private Random random;
    
    private BufferedImage image;
    private Rectangle rect;
    private int posX, posY;
    
    public Stone(int i){
        rect = new Rectangle();
        random = new Random();
        
        image = Resource.getResource("data/obstacle/stone.png");
        posX = 1600 + (i*1000) + random.nextInt(3)*100;
        posY = (int)GROUNDY - image.getHeight() + 14;
    }
    
    @Override
    public void update(){
        posX -= 10 + (1*speed);//
        rect.x = posX + 12;
        rect.y = posY + 23;
        rect.width = image.getWidth() -12;
        rect.height = image.getHeight() -23;
    }
    
    @Override
    public Rectangle getBound(){
        return rect;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, posX, posY, null);
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
        return image;
    }
}
