package player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
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
    private final MediaPlayer mediaPlayer;
    
    private Playback(){
        this.logger=Logger.getLogger("Playback");
        this.mediaPlayer=MediaPlayerBuilder.create().build();
    };
    
    public static Playback getInstance(){
        return instance;
    }
    
    public void playSong(Song song) 
            throws FileNotFoundException{
        
    }
    
    public void stopPlaying(){
        
    }
    
    public void resumePlaying(){
        
    }
}
