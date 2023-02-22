/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import static userinterface.GameMeneger.frame;
import util.Resource;
import static userinterface.GameMeneger.sound;

/**
 *
 * @author ken_m
 */
public class IntroScreen {
    private BufferedImage img1, img2;
    private float alpha;
    
    
    public DRAW_STATE drawState = DRAW_STATE.HIDE;
    public enum DRAW_STATE{
        HIDE,
        SHOW
    }
    
    public IntroScreen(){
        alpha = 0.0f;
        img1 = Resource.getResource("data/bako1.png");
        img2 = Resource.getResource("data/bako2.png");
    }
    
    public void update(){
        alpha += 0.02f;
        if(sound.playMusic2){
            sound.introSound(); 
            sound.playMusic2 = false;
        }   
        if(alpha >= 1.0f){                  
            alpha = 1.0f;
        }
        if (alpha > 0 && frame >= 150) {
            alpha -= 0.05f;
        }
    }
    
    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.gray);
        g.fillRect(0, 0, 1600, 900);
        
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        
        switch(drawState){
            case HIDE:
                g.drawImage(img1, 0, 0, null);
                break;
            case SHOW:
                g.drawImage(img2, 0, 0, null);
                break;
        }        
    }
}
