/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import static userinterface.PlayScreen.speed;
import util.Resource;

/**
 *
 * @author ADMINS
 */
public class Sky3 {
    private BufferedImage img;
    
    private List<ListImage> listImage;
    
    public Sky3(){
        listImage = new ArrayList<ListImage>();
        
        img = Resource.getResource("data/gamebackground/sky3.png");
        setImageList();
    }
    
    private void setImageList(){
        for(int i=0; i<2; i++){
            ListImage image = new ListImage();
            image.posX = (int)(i * img.getWidth());
            image.image = img;
            listImage.add(image);
        }
    }
    
    public void update(){
        for(ListImage image: listImage){
            image.posX -= 1 + (1 * speed);
        }
        
        if(listImage.size() > 0){
            ListImage firstElement = listImage.get(0);
            if(firstElement.posX + firstElement.image.getWidth() < 0){
                firstElement.posX = listImage.get(listImage.size() -1).posX + img.getWidth();
                listImage.add(firstElement);
                listImage.remove(0);
            }
        }
    }
    
    public void reset(){
        listImage.clear();
        setImageList();
    }
    
    public void draw(Graphics g){
        for(int i=0; i<listImage.size(); i++){
            g.drawImage(listImage.get(i).image, listImage.get(i).posX, 0, null);
        }
    }
    
    public class ListImage{
        int posX;
        BufferedImage image;
    }
}
