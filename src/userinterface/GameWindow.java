/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package userinterface;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class GameWindow extends JFrame{
    private GameMeneger gameMeneger;
    
    public GameWindow(){
        super("TiaRun");
        setSize(1600,900);
        setResizable(false);
        setUndecorated(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image icon = new ImageIcon("data/icon.png").getImage();
        setIconImage(icon);
        gameMeneger = new GameMeneger();
        add(gameMeneger);
        addKeyListener(gameMeneger);
        addMouseListener(gameMeneger);
    }
    
    public void startGame(){
        gameMeneger.startGame();
    }
    
    public static void main(String[] args){
        GameWindow gw = new GameWindow();
        gw.setVisible(true);
        gw.startGame();
    }

    

}
