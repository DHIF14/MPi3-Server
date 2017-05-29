package player;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Playback {
    private final Logger logger=Logger.getLogger("Playback");
    private static final Playback instance=new Playback();
    private final AudioPlayer player=AudioPlayer.player;
    
    private Playback(){};
    
    public static Playback getInstance(){
        return instance;
    }
    
    public void playSong(Song song) 
            throws FileNotFoundException{
        try {
            InputStream in = new FileInputStream(song.getPath());
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Exception thrown when creating a new AudioStream: {0}", e.getMessage());
        }
        
    }
}
