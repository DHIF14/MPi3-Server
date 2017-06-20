package player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayerBuilder;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Playback {
    private static final Playback instance=new Playback();
    private final Logger logger;
    private final JFXPanel fxPanel = new JFXPanel();
    private MediaPlayer mediaPlayer;
    
    private Playback(){
        this.logger=Logger.getLogger("Playback");
    };
    
    public static Playback getInstance(){
        return instance;
    }
    
    private void initMediaPlayer(Media media){
        mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setOnEndOfMedia(new OnEndOfMedia());
        mediaPlayer.setVolume(0.5);
    }
    
    public void playSong(Song song) 
            throws FileNotFoundException{
        if(mediaPlayer!=null){
            mediaPlayer.stop();  
        }
        initMediaPlayer(new Media(song.getPath().toUri().toString()));
        mediaPlayer.play();
    }
    
    public void stopPlaying(){
        mediaPlayer.pause();
    }
    
    public void resumePlaying(){
        mediaPlayer.play();
    }
   
    public void changeVolume(double vol){
        mediaPlayer.setVolume(vol);
    }
    
    public void setTimeToZero(){
        Media helpi=mediaPlayer.getMedia();
        mediaPlayer.stop();
        initMediaPlayer(helpi);
    }
}
