/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import screen_game.MainCharacter;
import screen_game.gameobject.Heart;
import static userinterface.GameMeneger.font;
import static userinterface.GameMeneger.heartClass;
import static userinterface.GameMeneger.sound;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class ContinueScreen {
    private BufferedImage bgImage, useButton1, useButton2,
                          shower,  cancelButton1, cancelButton2;
    private int useButtonX, useButtonY,
                cancelButtonX, cancelButtonY;
    private int heartY = 675;
    private float speedY = -5f;
    
    private Rectangle rect;
    private Font replay, mainMenu, showMoney;
    private MainCharacter character;
    private Font heartFont;
    
    public ContinueScreen(MainCharacter character){
        this.character = character;
        rect = new Rectangle();
        
        bgImage = Resource.getResource("data/gamebackground/continue.png");
        shower = Resource.getResource("data/gamebackground/water.png");
        
        useButton1 = Resource.getResource("data/button/use1.png");
        useButton2 = Resource.getResource("data/button/use2.png"); 
        useButtonX = 1600/4 - 30 - useButton1.getWidth()/2;
        useButtonY = 900/2 - useButton1.getHeight()/2 + 70;
        
        cancelButton1 = Resource.getResource("data/button/cancel1.png");
        cancelButton2 = Resource.getResource("data/button/cancel2.png"); 
        cancelButtonX = 1600*3/4 + 5 - cancelButton1.getWidth()/2;
        cancelButtonY = 900/2 - cancelButton1.getHeight()/2 + 70;
    }
    
    public static MOUSE_STATE_CONTINUE useMouseState = MOUSE_STATE_CONTINUE.NORMAL,
                                       cancelMouseState = MOUSE_STATE_CONTINUE.NORMAL;
    
    public static enum MOUSE_STATE_CONTINUE{
        NORMAL,
        PRESSED
    }
    
    public void update(){
        heartY += speedY;
        speedY += 0.5;
        if(heartY >= 675){
            speedY = -5f;
        }
        if(heartY == 670 && speedY < 0){
            sound.jumpSound();
        }
    }
    
    public void draw(Graphics g){
        g.drawImage(bgImage, 0, 0, null);
        g.drawImage(character.dog.getImageOver(), 680, 600 - character.dog.getImageOver().getHeight(), null);
        g.drawImage(shower, 590, 630 - shower.getHeight(), null);
        g.drawImage(heartClass.getImage(), 710, heartY, null);
        
        heartFont = font.deriveFont(Font.BOLD, 35f);
        g.setFont(heartFont);
        g.drawString("X " + String.valueOf(Heart.numHeart), 785, 710);
        
        /* use button */
        switch (useMouseState) {
            case PRESSED:
                g.drawImage(useButton2, useButtonX, useButtonY, null);
                break;
            default:
                g.drawImage(useButton1, useButtonX, useButtonY, null);
                break;
        }
        
        /* cancel button */
        switch (cancelMouseState) {
            case PRESSED:
                g.drawImage(cancelButton2, cancelButtonX, cancelButtonY, null);
                break;
            default:
                g.drawImage(cancelButton1, cancelButtonX, cancelButtonY, null);
                break;
        }
    }
    
    public void reset(){
        heartY = 675;
        speedY = -5f;
    }

    public BufferedImage getBgImage() {
        return bgImage;
    }
}
