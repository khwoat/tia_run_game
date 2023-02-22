package userinterface;

import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    
    private Clip menuSound, playSound, resSound, jumpUpSound,
                 wrongSound, buySound, overSound, getHeartSound, clickButSound, selectSound, countSound, cutSound, bakoSound;
    public boolean playMusic1, playMusic2;
    
    public Sound(){
        playMusic1 = true;    
        playMusic2 = true;    
        try {         
            playSound = AudioSystem.getClip();
            playSound.open(AudioSystem.getAudioInputStream(new File("data/sound/run_sound.wav"))); 
        } catch (Exception ex) {}  
    }
    
    /* MENU MUSIC */
    public void menuMusicStart(){
        try {     
            menuSound = AudioSystem.getClip();
            menuSound.open(AudioSystem.getAudioInputStream(new File("data/sound/menu_sound.wav")));  
            menuSound.start();
            menuSound.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {}          
    }
    public void menuMusicStop(){
        playMusic1 = true;
        playMusic2 = true;
        menuSound.stop();         
    }
    
    /* RUNNING MUSIC */
    public void playMusicStart(){
        playSound.start();
        playSound.loop(Clip.LOOP_CONTINUOUSLY);    
    }
    public void playMusicStop(){
        playMusic1 = true;
        playMusic2 = true;
        playSound.stop();   
    }
    
    /* RESULT MUSIC */
    public void resultMusicStart(){
        try {    
            resSound = AudioSystem.getClip();
            resSound.open(AudioSystem.getAudioInputStream(new File("data/sound/end_score.wav")));  
            resSound.start();
            resSound.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {}            
    }
    public void resultMusicStop(){
        playMusic1 = true;
        resSound.stop();        
    }
    
    /* COUNT SCORE SOUND */
    public void countScoreSoundStart(){
        try {    
            countSound = AudioSystem.getClip();
            countSound.open(AudioSystem.getAudioInputStream(new File("data/sound/score.wav")));  
            countSound.start();
            countSound.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {}            
    }
    public void countScoreSoundStop(){
        countSound.stop();        
    }
    
    /* GAMEOVER SOUND */
    public void gameOverSound(){
        try {    
            overSound = AudioSystem.getClip();
            overSound.open(AudioSystem.getAudioInputStream(new File("data/sound/gameover.wav")));  
            overSound.start();
        } catch (Exception ex) {}            
    }
    
    /* INTRO SOUND */
    public void introSound(){
        try {    
            bakoSound = AudioSystem.getClip();
            bakoSound.open(AudioSystem.getAudioInputStream(new File("data/sound/bako_sound.wav")));  
            bakoSound.start();
        } catch (Exception ex) {}            
    }
    
    /* GET HEART SOUND */
    public void getHeartSound(){
        try {    
            getHeartSound = AudioSystem.getClip();
            getHeartSound.open(AudioSystem.getAudioInputStream(new File("data/sound/getHeart.wav")));  
            getHeartSound.start();
        } catch (Exception ex) {}            
    }
    
    /* CLICK BUTTON SOUND */
    public void clickButtonSound(){
        try {    
            clickButSound = AudioSystem.getClip();
            clickButSound.open(AudioSystem.getAudioInputStream(new File("data/sound/click.wav")));  
            clickButSound.start();
        } catch (Exception ex) {}            
    }
    
    /* SELECT CHARACTER SOUND */
    public void selectCharSound(){
        try {    
            selectSound = AudioSystem.getClip();
            selectSound.open(AudioSystem.getAudioInputStream(new File("data/sound/dog_sound.wav")));  
            selectSound.start();
        } catch (Exception ex) {}            
    }
    
    /* CUTSCREEN SOUND */
    public void cutScreenSound(){
        try {    
            cutSound = AudioSystem.getClip();
            cutSound.open(AudioSystem.getAudioInputStream(new File("data/sound/exclamination.wav")));  
            cutSound.start();
        } catch (Exception ex) {}            
    }
    
    /* WRONG SOUND */
    public void wrongSound(){
        try {          
            wrongSound = AudioSystem.getClip();
            wrongSound.open(AudioSystem.getAudioInputStream(new File("data/sound/wrong_button.wav")));
            wrongSound.start();
        } catch (Exception ex) {}
    }
    
    /* BUY SOUND */
    public void buySound(){
        try {          
            buySound = AudioSystem.getClip();
            buySound.open(AudioSystem.getAudioInputStream(new File("data/sound/cash.wav")));
            buySound.start();
        } catch (Exception ex) {}
    }
    
    /* JUMP SOUND */
    public void jumpSound(){
        try {  
            jumpUpSound = AudioSystem.getClip();
            jumpUpSound.open(AudioSystem.getAudioInputStream(new File("data/sound/jump_up.wav")));
            jumpUpSound.start();
        } catch (Exception ex) {}               
    }
    
    /* RESET ALL SOUND & MUSIC */
    public void reset(){
        try {         
            playSound = AudioSystem.getClip();
            playSound.open(AudioSystem.getAudioInputStream(new File("data/sound/run_sound.wav"))); 
        } catch (Exception ex) {}  
        playMusic1 = true;
        playMusic2 = true;
    }
}
