/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game;

import screen_game.gameobject.Trash;
import screen_game.gameobject.PlayObject;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import screen_game.gameobject.Heart;
import screen_game.gameobject.Stone;
import static userinterface.GameMeneger.sound;

/**
 *
 * @author ADMINS
 */
public class ObjectMeneger {
    
    protected List<PlayObject> object;
    private PlayObject trash, stone, heartObj;
    private Random random;
    private MainCharacter character;
    
    public ObjectMeneger(MainCharacter character){
        this.character = character;
        object = new ArrayList<PlayObject>();
        random = new Random();
        
        
        setList(); 
    }
    
    private void setList(){
        int randNumber1 = random.nextInt(10);
        int randNumber2 = random.nextInt(3)+3;
        
        switch(randNumber1){
            case 0:
                heartObj = new Heart();
                object.add(heartObj);               
            default: break;
        }        
        for(int i=0;i<randNumber2;i++){
            trash = new Trash(i);
            stone = new Stone(i);
            object.add(randomType());           
        }     
    }
       
    public void update(){
        int index = 0;
        while(index< object.size()){
            object.get(index).update();
            if(object.get(index).getBound().intersects(character.getBound())){
                if(!(object.get(index) instanceof Heart)){
                    character.setAlive(false);
                }else{
                    Heart.numHeart++;
                    sound.getHeartSound();
                    object.remove(object.get(index));
                    continue;
                }
            }
            index ++;
        }
        
        if(object.size() > 0){
            PlayObject lastElement = object.get(object.size()-1);
            if(lastElement.getPosX() + lastElement.getImage().getWidth() < 0){
                object.clear();
                setList();
            }
        }
    }
    
    public void reset(){
        object.clear();
        setList();
    }
    
    public void draw(Graphics g){
        for(int i=0; i<object.size(); i++){
            object.get(i).draw(g);
        }
    }
    
    private PlayObject randomType(){
        int type = random.nextInt(2);
        switch(type){
            case 0: return trash;
            default: return stone;
        }
    }

}

