/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import screen_game.Sky3;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class MainMenu{    
    private BufferedImage mountain, playButton, playPressed,
                          logo,     charButton, charPressed,
                                    exitButton, exitPressed;
    private int playButtonX, playButtonY,
                charButtonX, charButtonY,
                exitButtonX, exitButtonY;
    
    public Sky3 sky;
    
    public MainMenu(){
        sky = new Sky3();
        mountain = Resource.getResource("data/gamebackground/mountain.png");
        logo = Resource.getResource("data/gamebackground/logo.png");
        
        playButton = Resource.getResource("data/button/play1.png");
        playPressed = Resource.getResource("data/button/play2.png"); 
        playButtonX = 1600/2 - playButton.getWidth()/2;
        playButtonY = 900/2 - playButton.getHeight()/2 + 100;
        
        charButton = Resource.getResource("data/button/store1.png");
        charPressed = Resource.getResource("data/button/store2.png"); 
        charButtonX = 1600/4 - charButton.getWidth()/2;
        charButtonY = 900/2 - charButton.getHeight()/2 + 100;
        
        exitButton = Resource.getResource("data/button/exit1.png");
        exitPressed = Resource.getResource("data/button/exit2.png"); 
        exitButtonX = 1600*3/4 - exitButton.getWidth()/2;
        exitButtonY = 900/2 - exitButton.getHeight()/2 + 100;
    }
    
    public static MOUSE_STATE_MENU playMouseState = MOUSE_STATE_MENU.NORMAL,
                              charMouseState = MOUSE_STATE_MENU.NORMAL,
                              exitMouseState = MOUSE_STATE_MENU.NORMAL;
    
    public enum MOUSE_STATE_MENU{
        NORMAL,
        PRESSED
    }
    
    public void update(){
        sky.update();
    }
    
    public void draw(Graphics g){
        // background
        sky.draw(g);
        g.drawImage(mountain, 0, 900-mountain.getHeight(), null);
        g.drawImage(logo, 0, 0, null);
        
         /* play button */
        switch (playMouseState) {
            case PRESSED:
                g.drawImage(playPressed, playButtonX, playButtonY, null);
                break;
            default:
                g.drawImage(playButton, playButtonX, playButtonY, null);
                break;
        }
        /* Choose Character page Button */
        switch (charMouseState) {
            case PRESSED:
                g.drawImage(charPressed, charButtonX, charButtonY, null);
                break;
            default:
                g.drawImage(charButton, charButtonX, charButtonY, null);
                break;
        }
        /* exit Button */
        switch (exitMouseState) {
            case PRESSED:
                g.drawImage(exitPressed, exitButtonX, exitButtonY, null);
                break;
            default:
                g.drawImage(exitButton, exitButtonX, exitButtonY, null);
                break;
        }
        
    }

    public int getPlayButtonX() {
        return playButtonX;
    }

    public int getPlayButtonY() {
        return playButtonY;
    }

    public int getCharButtonX() {
        return charButtonX;
    }

    public int getCharButtonY() {
        return charButtonY;
    }

    public int getExitButtonX() {
        return exitButtonX;
    }

    public int getExitButtonY() {
        return exitButtonY;
    }

    public int getWidthPlayButton() {
        return playButton.getWidth();
    }
    
    public int getHeightPlayButton(){
        return playButton.getHeight();
    }

    public int getWidthCharButton() {
        return charButton.getWidth();
    }
    
    public int getHeightCharButton(){
        return charButton.getHeight();
    }

    public int getWidthExitButton() {
        return exitButton.getWidth();
    }
    
    public int getHeightExitButton(){
        return exitButton.getHeight();
    }

    public BufferedImage getBgImage() {
        return mountain;
    }
    
}
