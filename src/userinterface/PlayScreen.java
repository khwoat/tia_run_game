/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Font;
import java.awt.Graphics;
import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import screen_game.MainCharacter;
import screen_game.Money;
import screen_game.Mountain;
import screen_game.ObjectMeneger;
import screen_game.Score;
import screen_game.Sky1;
import screen_game.StartScreen;
import screen_game.gameobject.Heart;
import screen_game.gameobject.Land;
import userinterface.GameMeneger.SCREEN_STATE;
import static userinterface.GameMeneger.screenState;
import static userinterface.GameMeneger.font;
import static userinterface.GameMeneger.frame;
import static userinterface.GameMeneger.heartClass;
import static userinterface.GameMeneger.scoreClass;
import static userinterface.GameMeneger.moneyClass;
import static userinterface.GameMeneger.sound;

/**
 *
 * @author ADMINS
 */
public class PlayScreen {
    public final int GAME_START_STATE =0;
    public final int GAME_PLAY_STATE =1;
    public final int GAME_OVER_STATE =2;
    public static final float GRAVITY = 0.5f;
    public static final float GROUNDY = 750;
    private static final int maxSpeed = 15;
    public static int speed = 0;   
    private String directory;
    public boolean isDrawHTP = true;
    
    protected int gameState = GAME_START_STATE;
    protected MainCharacter character;
    private Land land;
    protected Sky1 sky;
    private Mountain mountain;
    private ObjectMeneger object;
    private String scoreText, hiScoreText;
    private FileWriter writeFile;
    private FileReader readFile;
    private StartScreen startScreen;
    
    public PlayScreen(Sound sound){ 
        character = new MainCharacter();        
        land = new Land();
        mountain = new Mountain();
        object = new ObjectMeneger(character);
        sky = new Sky1(); 
        startScreen = new StartScreen(character);
        
        try {
            Files.createDirectories(Paths.get(System.getProperty("user.home") + "/AppData/Roaming/TiaRun"));
            Files.createFile(Paths.get(System.getProperty("user.home") + "/AppData/Roaming/TiaRun/save_file.txt"));
        } catch (IOException ex) {}
        
        directory = System.getProperty("user.home") + "/AppData/Roaming/TiaRun";
    }
    
    public void update(){
        switch(gameState){
            case GAME_START_STATE:
                startScreen.update();
                if(frame == 165){
                    frame = 0;
                    gameState = GAME_PLAY_STATE;
                }
                break;
            case GAME_PLAY_STATE:                    
                if(frame%6 == 0){
                    scoreClass.update();
                }
                if(maxSpeed >= speed){
                    if(frame%300 == 0){
                        frame =0;
                        speed += 1;                       
                    }
                }
                sky.update();
                mountain.update();
                land.update();                
                character.update();
                object.update();
                if(!character.getAlive()){
                    gameState = GAME_OVER_STATE;
                    sound.gameOverSound();
                }                    
                break;
            case GAME_OVER_STATE:  
                sound.playMusicStop();
                try {
                    TimeUnit.SECONDS.sleep(3);    
                } catch (InterruptedException ex) {}               
                screenState = SCREEN_STATE.GAMEOVER;
                break;                 
        }
    }
    
    public void reset(){
        startScreen.reset();
        character.reset();
        character.setAlive(true);
        object.reset();
        land.reset();
        mountain.reset(); 
        sky.reset();
        moneyClass.setMoney();  
        speed = 0;
        frame = 0;
    }
    
    public void revive(){
        character.setAlive(true);
        character.reset();
        object.reset();       
    }
    
    public void draw(Graphics g){
        sky.draw(g);
        mountain.draw(g);
        land.draw(g);       

        switch(gameState){
            case GAME_START_STATE:
                startScreen.draw(g);
                break;
            case GAME_PLAY_STATE: case GAME_OVER_STATE:  
                if(isDrawHTP == true){
                    if(frame % 30 >= 0 && frame % 30 <= 25){
                        g.setFont(font.deriveFont(Font.BOLD, 30f));
                        g.drawString("Press SPACE or UP to jump", 600, 450);
                    }                    
                }   
                character.draw(g);
                hiScoreText = "High score: " + String.valueOf(Score.hiScore);
                scoreText = "Score: " + String.valueOf(Score.score);   
   
                g.setFont(font.deriveFont(Font.BOLD, 24f));
                g.drawString(scoreText, 1350, 50);           
                g.drawString(hiScoreText, 1050, 50);          
                g.drawString("x" + String.valueOf(Heart.numHeart), 150, 50); 
        
                g.drawImage(heartClass.getSmallHeart(),120,60 - heartClass.getSmallHeart().getHeight(), null);
                break;
        }
        object.draw(g);
    }
    
/*
   vvvvvv                        vvvvvv
    vvvv READ & WRITE SAVES FILE  vvvv
     vv                            vv
*/ 
    
    public void writeSavesFile(){
        try {
            writeFile = new FileWriter(directory + File.separator + "save_file.txt");
            String fileContent = "high score:" + String.valueOf(Score.hiScore) + "\n";
            fileContent += "no. of heart:" + String.valueOf(Heart.numHeart) + "\n";
            fileContent += "money:" + String.valueOf(Money.totalMoney) + "\n";
            if(character.lion.isPurchased()){
                fileContent += "lion:1\n";
            }else{
                fileContent += "lion:0\n";
            }
            if(character.krarok.isPurchased()){
                fileContent += "krarok:1\n";
            }else{
                fileContent += "krarok:0\n";
            }
            writeFile.write(fileContent);
            writeFile.close(); 
        } catch (IOException e){e.printStackTrace();}
    }
    
    public void readSavesFile(){
        try{
            readFile = new FileReader(directory + File.separator + "save_file.txt");
            int c, firstIndex, lastIndex;
            String fileContent = "";
            ArrayList<Integer> listNum = new ArrayList<Integer>();
            
            while((c = readFile.read()) != -1){
                fileContent += (char)c;
                if((char)c == '\n'){
                    firstIndex = fileContent.indexOf(":");
                    lastIndex = fileContent.indexOf("\n");
                    listNum.add(Integer.parseInt(fileContent.substring(firstIndex+1, lastIndex)));
                    fileContent = "";
                }
            }
            if(!listNum.isEmpty()){
                Score.hiScore = listNum.get(0);
                Heart.numHeart = listNum.get(1);
                Money.totalMoney = listNum.get(2);
                if(listNum.get(3) == 1){
                    character.lion.setPurchased(true);
                }else{
                    character.lion.setPurchased(false);
                }
                if(listNum.get(4) == 1){
                    character.krarok.setPurchased(true);
                }else{
                    character.krarok.setPurchased(false);
                }                
            }        
            readFile.close();
        }catch(IOException ex){ex.printStackTrace();}
    }
}
