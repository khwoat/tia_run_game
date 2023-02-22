/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import static userinterface.GameMeneger.frame;
import static userinterface.GameMeneger.sound;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class StartScreen {
    private BufferedImage exclaSym, msgBox;
    private int msgX, msgY;
    
    private MainCharacter character;
    
    public StartScreen(MainCharacter c){
        character = c;
        character.x = -300;
        
        exclaSym = Resource.getResource("data/exclamation.png");
        msgBox = Resource.getResource("data/msg_box.png");
        msgX = 50;
        msgY = 300;
    }
    
    public void update(){
        character.update();
        if(frame < 90){            
            character.x += 4;   
        }
        else if(frame < 160){
            if(frame %2 == 0){
                msgX -= 3;
                msgY -= 3;
            }else{
                msgX += 3;
                msgY += 3;
            }
            if(frame > 120){
                if(sound.playMusic2){
                    sound.cutScreenSound();
                    sound.playMusic2 = false;
                }          
                character.jumpScare();
            }            
        }
        
    }
    
    public void draw(Graphics g){
        if(frame < 90){
            g.drawImage(character.dog.getAnimation().getFrame(), (int)character.x, (int)character.y, null);            
        }
        else if(frame < 160){
            g.drawImage(msgBox, msgX, msgY, null);
            if(frame < 120){
                g.drawImage(character.dog.getImageJump(), (int)character.x, (int)character.y, null);
            }else{
                g.drawImage(character.dog.getImageOver(), (int)character.x, (int)character.y, null);
                g.drawImage(exclaSym, (int)character.x + 235, (int)character.y - exclaSym.getHeight() + 20, null);
            }
        }else{
            g.drawImage(character.dog.getImageOver(), (int)character.x, (int)character.y, null);
        }
    }
    
    public void reset(){
        character.x = -300;
        msgX = 50;
        msgY = 300;
    }
}
