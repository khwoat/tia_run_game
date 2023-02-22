/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import screen_game.Money;
import screen_game.Score;
import screen_game.gameobject.Heart;
import userinterface.ContinueScreen.MOUSE_STATE_CONTINUE;
import static userinterface.ContinueScreen.useMouseState;
import static userinterface.ContinueScreen.cancelMouseState;
import userinterface.IntroScreen.DRAW_STATE;
import userinterface.MainMenu.MOUSE_STATE_MENU;
import static userinterface.MainMenu.playMouseState;
import static userinterface.MainMenu.charMouseState;
import static userinterface.MainMenu.exitMouseState;

/**
 *
 * @author ADMINS
 */
public class GameMeneger extends JPanel implements Runnable,KeyListener, MouseListener{ 
    public static int frame = 0;
    
    public static Font font; 
    private Thread thread;   
    private IntroScreen intro;
    private MainMenu mainMenu;
    private CharacterScreen characterScreen;
    private PlayScreen playScreen;
    private ContinueScreen  conScreen;
    private ResultScreen resultScreen;
    public static Sound sound;
    
    public static Score scoreClass;
    public static Money moneyClass;
    public static Heart heartClass;
    
    public GameMeneger(){
        thread = new Thread(this);  
        intro = new IntroScreen();
        mainMenu = new MainMenu();   
        playScreen = new PlayScreen(sound);
        characterScreen = new CharacterScreen(playScreen.character, mainMenu.sky);
        conScreen = new ContinueScreen(playScreen.character);
        resultScreen = new ResultScreen(playScreen);
        sound = new Sound();
        scoreClass = new Score();
        moneyClass = new Money();
        heartClass = new Heart();
        
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/west_england.ttf"));
        } catch (IOException|FontFormatException ex) {} 
    }
    
    public static SCREEN_STATE screenState  = SCREEN_STATE.INTRO;
    public static enum SCREEN_STATE{
        INTRO,
        MENU,
        GAME,
        CHARACTER,
        GAMEOVER,
        RESULT
    };
        
/*
   vvvvvv              vvvvvv
    vvvv   RUN GAME     vvvv
     vv                  vv
*/ 
    
    public void startGame(){
        playScreen.readSavesFile();
        thread.start();
        
    }
    
    private void gameUpdate(){
        switch (screenState) {
            case INTRO:
                if(frame < 180){
                    intro.update();
                }else{
                    screenState = SCREEN_STATE.MENU;
                    frame = 0;
                }  
                frame ++;
                break;
            case MENU:
                mainMenu.update();
                if(sound.playMusic1){
                    sound.menuMusicStart();
                    sound.playMusic1 = false;
                }           
                break;
            case CHARACTER:
                characterScreen.update();
                break;
            case GAME:
                playScreen.update();
                frame++;
                if(sound.playMusic1){
                    sound.playMusicStart();
                    sound.playMusic1 = false;
                } 
                break;
            case GAMEOVER:
                sound.playMusicStop();
                if(frame < 23){
                    conScreen.update();
                }else if(frame > 83){
                    frame = 0;
                }         
                frame++;
                break;
            case RESULT:
                resultScreen.update();
                frame++;
                if(sound.playMusic1){
                    sound.resultMusicStart();
                    sound.playMusic1 = false;
                } 
                break;
            default:
                break;
        }
    }
     
    @Override
    public void run() {
        while(true){
            try {
                gameUpdate();
                repaint();
                Thread.sleep(17);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);       
        switch (screenState) {
            case INTRO:
                intro.draw(g);
                break;
            case MENU:
                mainMenu.draw(g);
                break;
            case CHARACTER:
                characterScreen.draw(g);
                break;
            case GAME:
                playScreen.draw(g);           
                break;
            case GAMEOVER:
                conScreen.draw(g);
                break;
            case RESULT:
                resultScreen.draw(g);
                break;
            default:
                break;
        }
    }
       
/*
   vvvvvv              vvvvvv
    vvvv KEYBORD INPUT  vvvv
     vv                  vv
*/    
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(screenState == SCREEN_STATE.GAME){
            if((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) && playScreen.gameState == playScreen.GAME_PLAY_STATE){
                playScreen.character.jump(sound);
                if(playScreen.isDrawHTP == true){
                    playScreen.isDrawHTP = false;
                }
            }
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {}
   
/*
   vvvvvv              vvvvvv
    vvvv   MOUSE INPUT  vvvv
     vv                  vv
*/    
    
    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mousePressed(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        
        switch (screenState) {
            case INTRO:
                if((mX >= 952 && mX <= 1172) && (mY >= 348 && mY <= 568)){
                    intro.drawState = DRAW_STATE.SHOW;
                    sound.getHeartSound();
                    frame = 90;
                }
                break;
                
            case MENU:
                /* PLAY BUTTON */
                if((mX >= mainMenu.getPlayButtonX() && mX <= mainMenu.getPlayButtonX() + mainMenu.getWidthPlayButton()) && 
                   (mY >= mainMenu.getPlayButtonY()+30 && mY <= mainMenu.getPlayButtonY() + mainMenu.getHeightPlayButton())){
                    playMouseState = MOUSE_STATE_MENU.PRESSED; 
                    sound.clickButtonSound();
                }   
                /* CHARACTER SELECT BUTTON */
                if((mX >= mainMenu.getCharButtonX() && mX <= mainMenu.getCharButtonX() + mainMenu.getWidthCharButton()) && 
                   (mY >= mainMenu.getCharButtonY()+30 && mY <= mainMenu.getCharButtonY() + mainMenu.getHeightCharButton())){
                    charMouseState = MOUSE_STATE_MENU.PRESSED; 
                    sound.clickButtonSound();
                }
                /* EXIT BUTTON */
                if((mX >= mainMenu.getExitButtonX() && mX <= mainMenu.getExitButtonX() + mainMenu.getWidthExitButton()) && 
                   (mY >= mainMenu.getExitButtonY()+30 && mY <= mainMenu.getExitButtonY() + mainMenu.getHeightExitButton())){
                    exitMouseState = MOUSE_STATE_MENU.PRESSED; 
                    sound.clickButtonSound();
                }   break;
            
            case CHARACTER:
                    if((mX >= 1470 && mX <= 1542) && (mY >= 80 && mY <= 144)){
                        sound.clickButtonSound();
                    }  
                    else if((mX >= 680 && mX <= 1520) && (mY >= 97 && mY <= 337)){
                        playScreen.character.setCharacter("tia");         // select tia
                        sound.selectCharSound();
                    }
                    
                    if((mX >= 680 && mX <= 1520) && (mY >= 357 && mY <= 597)){
                        if(playScreen.character.lion.isPurchased()){
                            playScreen.character.setCharacter("lion");         // select lion
                            sound.selectCharSound();
                        }else{                           
                            if(Money.totalMoney >= playScreen.character.lion.getPrice()){
                                Money.totalMoney -= playScreen.character.lion.getPrice();
                                playScreen.character.lion.setPurchased(true);
                                playScreen.character.setCharacter("lion");         // select lion
                                sound.buySound();
                            }else{
                                sound.wrongSound();
                            }
                        }
                    }   
                    if((mX >= 680 && mX <= 1520) && (mY >= 617 && mY <= 857)){
                        if(playScreen.character.krarok.isPurchased()){
                            playScreen.character.setCharacter("krarok");        // select krarok
                            sound.selectCharSound();
                        }else{                           
                            if(Money.totalMoney >= playScreen.character.krarok.getPrice()){
                                Money.totalMoney -= playScreen.character.krarok.getPrice();
                                playScreen.character.krarok.setPurchased(true);
                                playScreen.character.setCharacter("krarok");         // select krarok
                                sound.buySound();
                            }else{
                                sound.wrongSound();
                            }
                        }
                    } break;
                    
            case GAMEOVER:
                /* USE HEART BUTTON */
                if((mX >= 220 && mX <= 520) && (mY >= 468 && mY <= 644)){
                    useMouseState = MOUSE_STATE_CONTINUE.PRESSED; 
                    sound.clickButtonSound();
                }   
                /* CANCEL BUTTON */
                if((mX >= 1055 && mX <= 1355) && (mY >= 468 && mY <= 644)){
                    cancelMouseState = MOUSE_STATE_CONTINUE.PRESSED;
                    sound.clickButtonSound();
                } break;
            case RESULT:
                if((mX >= 1280 && mX <= 1352) && (mY >= 90 && mY <= 154)){
                    sound.clickButtonSound();
                }
                break;
                
            default: break;      
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        
        switch (screenState) {
            case MENU:
                /* PLAY BUTTON */
                if((mX >= mainMenu.getPlayButtonX() && mX <= mainMenu.getPlayButtonX() + mainMenu.getWidthPlayButton()) && 
                   (mY >= mainMenu.getPlayButtonY()+30 && mY <= mainMenu.getPlayButtonY() + mainMenu.getHeightPlayButton())){
                    screenState = SCREEN_STATE.GAME;     // go to play screen  
                    sound.menuMusicStop();
                    sound.playMusic2 = true;
                }
                
                /* CHARACTER SELECT BUTTON */
                if((mX >= mainMenu.getCharButtonX() && mX <= mainMenu.getCharButtonX() + mainMenu.getWidthCharButton()) && 
                   (mY >= mainMenu.getCharButtonY()+30 && mY <= mainMenu.getCharButtonY() + mainMenu.getHeightCharButton())){
                    screenState = SCREEN_STATE.CHARACTER;        // go to character select screen                  
                }  
                
                /* EXIT BUTTON */
                if((mX >= mainMenu.getExitButtonX() && mX <= mainMenu.getExitButtonX() + mainMenu.getWidthExitButton()) && 
                   (mY >= mainMenu.getExitButtonY()+30 && mY <= mainMenu.getExitButtonY() + mainMenu.getHeightExitButton())){
                    System.exit(0);
                }
                
                playMouseState = MOUSE_STATE_MENU.NORMAL;
                charMouseState = MOUSE_STATE_MENU.NORMAL;
                exitMouseState = MOUSE_STATE_MENU.NORMAL;
                break;
                
            case CHARACTER:
                if((mX >= 1470 && mX <= 1542) && (mY >= 80 && mY <= 144)){
                    screenState = SCREEN_STATE.MENU;        // back button(go to MENU)
                    playScreen.writeSavesFile();
                }  
                break;
                
            case GAMEOVER:
                /* USE HEART BUTTON */
                if((mX >= 220 && mX <= 520) && (mY >= 468 && mY <= 644)){
                    if(Heart.numHeart > 0){
                        screenState = SCREEN_STATE.GAME;                             // go to play screen
                        playScreen.gameState = playScreen.GAME_PLAY_STATE;
                        playScreen.revive();
                        Heart.numHeart--;                    
                    }else{
                        sound.wrongSound();
                    }
                }
                
                /* CANCEL BUTTON */
                if((mX >= 1055 && mX <= 1355) && (mY >= 468 && mY <= 644)){                   
                    screenState = SCREEN_STATE.RESULT;                            // go to result
                    playScreen.gameState = playScreen.GAME_START_STATE;
                    playScreen.reset();
                    sound.playMusicStop();
                }
                useMouseState = MOUSE_STATE_CONTINUE.NORMAL;
                cancelMouseState = MOUSE_STATE_CONTINUE.NORMAL;
                conScreen.reset();
                break;
                
            case RESULT:
                if((mX >= 1280 && mX <= 1352) && (mY > 90 && mY < 154)){
                    sound.resultMusicStop();
                    sound.countScoreSoundStop();
                    screenState = SCREEN_STATE.MENU;        // back button(go to MENU)                                     
                    resultScreen.reset();
                    sound.reset();
                    frame = 0;                   
                }
                break;
            default: break;  
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}