/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import screen_game.MainCharacter;
import screen_game.Money;
import screen_game.Sky3;
import static userinterface.GameMeneger.font;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class CharacterScreen {
    private BufferedImage menu, mountain, backButton;
    private int posX = 680;
    private int posY = 67;
    
    private MainCharacter character;
    private Sky3 sky;
    private Font moneyFont;
    
    public CharacterScreen(MainCharacter character, Sky3 sky){
        this.sky = sky;
        this.character = character;
        menu = Resource.getResource("data/gamebackground/character_select.png");
        mountain = Resource.getResource("data/gamebackground/mountain.png");
        mountain = Resource.getResource("data/gamebackground/mountain.png");
        backButton = Resource.getResource("data/button/x.png");
        
        
    }
    
    public void update(){
        character.update();
        sky.update();
    }
    
    public void draw(Graphics g){
        sky.draw(g);
        g.drawImage(mountain, 0, 900 - mountain.getHeight(), null);
        g.drawImage(menu, 0, 0, null);

        g.drawImage(character.tia.getImageSelect(), posX, posY, null);
        if(!character.lion.isPurchased()){
            g.drawImage(character.lion.getImageBuy(), posX, posY + character.tia.getImageSelect().getHeight() + 20, null);
        }else{
            g.drawImage(character.lion.getImageSelect(), posX, posY + character.tia.getImageSelect().getHeight() + 20, null);
        }        
        if(!character.krarok.isPurchased()){
            g.drawImage(character.krarok.getImageBuy(), posX, posY + (character.tia.getImageSelect().getHeight() + 20)*2, null);
        }else{
            g.drawImage(character.krarok.getImageSelect(), posX, posY + (character.tia.getImageSelect().getHeight() + 20)*2, null);
        }
        g.drawImage(character.dog.getAnimation().getFrame(), 250, 550 - character.dog.getImageJump().getHeight(), null);
        
        if(character.dog == character.tia){
            g.drawImage(character.tia.getImageSelected(), posX, posY, null);
        }else if(character.dog == character.lion){
            g.drawImage(character.lion.getImageSelected(), posX, posY + character.tia.getImageSelect().getHeight() + 20, null);
        }else if(character.dog == character.krarok){
            g.drawImage(character.krarok.getImageSelected(), posX, posY + (character.tia.getImageSelect().getHeight() + 20)*2, null);
        }
        
        moneyFont = font.deriveFont(Font.PLAIN, 80f);
        g.setFont(moneyFont);
        g.drawString(String.valueOf(Money.totalMoney), 375, 760);
        
        g.drawImage(backButton, 1470, 50, null);
        
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public BufferedImage getBgImage() {
        return menu;
    }
}
