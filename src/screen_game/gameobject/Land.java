/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game.gameobject;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static userinterface.PlayScreen.speed;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class Land {
    private BufferedImage imgLand1, imgLand2, imgLand3, imgLand4;
    
    private List<ListImage> listImage;
    private Random random;
    
    public Land(){
        random = new Random();
        listImage = new ArrayList<ListImage>();
        
        imgLand1 = Resource.getResource("data/gamebackground/land1.png");
        imgLand2 = Resource.getResource("data/gamebackground/land2.png");
        imgLand3 = Resource.getResource("data/gamebackground/land3.png");
        imgLand4 = Resource.getResource("data/gamebackground/land4.png");
        setLand();        
    }
    
    private void setLand(){
        int numberOfLandTitle = 1600 / imgLand1.getWidth() + 1;
        for(int i=0; i<numberOfLandTitle; i++){
            ListImage image = new ListImage();
            image.posX = (int)(i * imgLand1.getWidth());
            image.image = getImage();
            listImage.add(image);
        }
    }
    
    public void update(){
        for(ListImage image: listImage){
            image.posX -= 10 + (1 * speed);
        }
        
        if(listImage.size() > 0){
            ListImage firstElement = listImage.get(0);
            if(firstElement.posX + firstElement.image.getWidth() < 0){
                firstElement.posX = listImage.get(listImage.size() -1).posX + imgLand1.getWidth();
                listImage.add(firstElement);
                listImage.remove(0);
            }
        }
    }
    
    public void reset(){
        listImage.clear();
        setLand();
    }
    
    public void draw(Graphics g){
        for(int i=0; i<listImage.size(); i++){
            g.drawImage(listImage.get(i).image, listImage.get(i).posX, 540, null);
        }
    }
    
    public BufferedImage getImage(){
        int type = random.nextInt(5);
        
        switch(type){
            case 1: return imgLand1;
            case 2: return imgLand2;
            case 3: return imgLand3;
            default: return imgLand4;
        }
    }
    
    public class ListImage{
        int posX;
        BufferedImage image;
    }

    

    
}

