/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game.gameobject;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import static screen_game.MainCharacter.x;
import static userinterface.PlayScreen.GROUNDY;
import util.Animation;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class Tia extends Dog{
    private BufferedImage imageJump, imageOver, imageSelect, imageSelected;
    private float y;
    private int price = 0;
    private boolean purchased= true;
    
    private Rectangle rect;
    private Animation characterRun;
    
    public Tia(){
        characterRun = new Animation(200);
        rect = new Rectangle();
        
        characterRun.addFrame(Resource.getResource("data/character/tia/tia1.png"));
        characterRun.addFrame(Resource.getResource("data/character/tia/tia2.png"));
        characterRun.addFrame(Resource.getResource("data/character/tia/tia3.png"));
        
        imageJump = Resource.getResource("data/character/tia/tia1.png");
        imageOver = Resource.getResource("data/character/tia/tia4.png");
        imageSelect = Resource.getResource("data/character/tia/tia_select.png");
        imageSelected = Resource.getResource("data/character/tia/tia_selected.png");
        
        y = GROUNDY - imageJump.getHeight();       
    }
    
    @Override
    public void setBound(int y) {        
        rect.x = (int)x + 20;
        rect.y = (int)y;
        rect.width = imageJump.getWidth() - 40;
        rect.height = imageJump.getHeight();
    }
    
    @Override
    public Animation getAnimation() {
        return characterRun;
    }
    
    @Override
    public Rectangle getBound() {        
        return rect;
    }

    @Override
    public BufferedImage getImageJump() {
        return imageJump;
    }

    @Override
    public BufferedImage getImageOver() {
        return imageOver;
    }

    @Override
    public float getPosX() {
        return x;
    }

    @Override
    public float getPosY() {
        return y;
    }
    
    @Override
    public BufferedImage getImageSelect() {
        return imageSelect;
    }

    @Override
    public BufferedImage getImageSelected() {
        return imageSelected;
    }

    @Override
    public BufferedImage getImageBuy() {
        return imageSelect;
    }

    @Override
    public boolean isPurchased() {
        return purchased;
    }

    @Override
    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    @Override
    public int getPrice() {
        return price;
    }

    
}
