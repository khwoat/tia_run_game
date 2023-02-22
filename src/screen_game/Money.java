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
public class Money {
    public static int totalMoney;
    public static int roundMoney;
    
    public void setMoney(){
        roundMoney = Score.score/100;
        totalMoney += roundMoney;
    }
    
}
