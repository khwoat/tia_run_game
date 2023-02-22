/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game;

/**
 *
 * @author ADMINS
 */
public class Score {
    public static int score = 0;
    public static int hiScore = 0;
    
    public Score(){}
    
    public void update(){
        score += 1;
    }
    
    public void reset(){
        if(score > hiScore){
            hiScore = score;            
        }
        score = 0;
        
    }
}
