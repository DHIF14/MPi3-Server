package player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Thread.State;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Playback {
    private static final Playback instance=new Playback();
    private final Logger logger;
    private final AudioPlayer player;
    private AudioStream currentStream;
    private Boolean isPlaying;
    private Song currentSong;
    
    private Playback(){
        this.isPlaying=false;
        this.player=AudioPlayer.player;
        this.logger=Logger.getLogger("Playback");
    };
    
    public static Playback getInstance(){
        return instance;
    }
    
    public void playSong(Song song) 
            throws FileNotFoundException{
        try {
            currentSong=song;
            InputStream in = new FileInputStream(currentSong.getPath());
            currentStream = new AudioStream(in);
            player.start(currentStream);
            isPlaying=true;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception thrown when creating a new AudioStream: {0}", e.getMessage());
        }
    }
    
    public void stopPlaying(){
        player.stop(currentStream);
        isPlaying=false;
    }
    
    public void resumePlaying(){
        player.start(currentStream);
        isPlaying=true;
    }
    
    public Boolean isPlaying(){
        return isPlaying;
    }
}
