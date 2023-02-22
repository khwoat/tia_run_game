/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game;

import java.awt.Graphics;
import java.awt.Rectangle;
import screen_game.gameobject.Dog;
import screen_game.gameobject.Krarok;
import screen_game.gameobject.Lion;
import screen_game.gameobject.Tia;
import static userinterface.PlayScreen.GROUNDY;
import static userinterface.PlayScreen.speed;
import userinterface.Sound;

/**
 *
 * @author ADMINS
 */
public class MainCharacter{
    public static float x = 60;
    public static float y;
    private float speedY=0;
    private boolean isAlive = true;
    public Dog dog;
    
    public Dog tia, lion, krarok; 
    private Rectangle rect; 
    
    public MainCharacter(){
        tia = new Tia();
        krarok = new Krarok();
        lion = new Lion();
        
        dog = tia;          
        y = dog.getPosY();        
        rect = dog.getBound();
    }
    
    public void setCharacter(String name){
        if("tia".equals(name)){dog = tia;}
        else if("lion".equals(name)){dog = lion;}
        else if("krarok".equals(name)){dog = krarok;}
        
        y = dog.getPosY();        
        rect = dog.getBound();
    }
    
    public void update(){
        dog.getAnimation().update();
        if(y >= GROUNDY - dog.getImageJump().getHeight()){
            speedY = 0;
            y = GROUNDY - dog.getImageJump().getHeight();
        }else{
            speedY += (0.5 + (0.1 * speed));
            
            y += speedY;
        }
        
        dog.setBound((int)y);
        
        dog.getBound();
    }
    
    public void draw(Graphics g){
        if(isAlive){
            if(y < GROUNDY - dog.getImageJump().getHeight()){
                g.drawImage(dog.getImageJump(), (int)x, (int)y, null);   
            }
            else{
                g.drawImage(dog.getAnimation().getFrame(), (int)x, (int)y, null);
            }
        }else
            g.drawImage(dog.getImageOver(), (int)x, (int)y, null);        
    }
    
    public void jump(Sound sound){
        if(y > GROUNDY - dog.getImageJump().getHeight() -14){
            speedY = -16f + (float)(-1 * speed);
            y += speedY;
            sound.jumpSound();
        }
        dog.setBound((int)y);
    }
    
    public void jumpScare(){
        if(y > GROUNDY - dog.getImageJump().getHeight() -8){
            speedY = -10f + (float)(-1 * speed);
            if(speedY < 0){
                y += speedY;
            }
        }
        dog.setBound((int)y);
    }
    
    public void reset(){
        y = 600;
    }
    
    public void setAlive(boolean alive){
        isAlive = alive;
    }
    
    public boolean getAlive(){
        return isAlive;
    }
    
    public Rectangle getBound(){
        return rect;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
    
    
}
