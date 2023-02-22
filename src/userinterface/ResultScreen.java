/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import screen_game.Money;
import screen_game.Score;
import screen_game.Sky2;
import static userinterface.GameMeneger.font;
import static userinterface.GameMeneger.frame;
import static userinterface.GameMeneger.scoreClass;
import static userinterface.GameMeneger.sound;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class ResultScreen {
    private BufferedImage popup, backButton;
    private int popupY, score, money, countScore;
    private Font toppic, scoreFont, moneyFont;
    
    private Sky2 sky;
    private PlayScreen play;
    
    public ResultScreen(PlayScreen playScreen){
        sky = new Sky2();
        play = playScreen;
        
        popupY = -900;
        score = money = 0;
        popup = Resource.getResource("data/gamebackground/result.png");
        backButton = Resource.getResource("data/button/x.png");
    }
    
    public void update(){
        if(popupY < 2){
            popupY += 22;
        }
        if(frame >= 80){           
            if(score + Math.pow(10, (String.valueOf(Score.score).length()-2)) < Score.score){
                score += Math.pow(10, (String.valueOf(Score.score).length()-2));
            }else if(score < Score.score){                
                score += 1;
            }           
            if(score < Score.score){
                if(sound.playMusic2){
                    sound.countScoreSoundStart();  
                    sound.playMusic2 = false;
                }
            }else{
                if(!sound.playMusic2){
                    sound.countScoreSoundStop();
                    sound.playMusic2 = true;
                }
            }
        }
        if(frame >= 110){
            if(money < Money.roundMoney && score >= Score.score){
                money += 1;
                if(sound.playMusic2){
                    sound.countScoreSoundStart();  
                    sound.playMusic2 = false;
                }
            }
        }
        sky.update();
    }
    
    public void draw(Graphics g){
        sky.draw(g);
        g.drawImage(popup, 0, popupY, null);
        g.drawImage(backButton, 1270, popupY + 58, null);
        
        
        if(frame >= 50){
            toppic = font.deriveFont(Font.BOLD, 80f);
            g.setFont(toppic);
            g.drawString("Your Score!!", 600, 250);
        }if(frame >= 80){
            scoreFont = font.deriveFont(Font.PLAIN, 80f);
            g.setFont(scoreFont);
            g.drawString(String.valueOf(score), 900 - (String.valueOf(Score.score).length()*50)/2, 450);
        }if(frame >= 110){
            moneyFont = font.deriveFont(Font.PLAIN, 100f);
            g.setFont(moneyFont);
            g.drawString(String.valueOf(money), 900, 680);           
        }
    }
    
    public void reset(){
        popupY = (-1) * popup.getHeight();
        scoreClass.reset();
        play.writeSavesFile();
        score = money = 0;
    }
}
