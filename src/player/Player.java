package player;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Player {
    private Queue<Song> queue;
    private static final Player instance=new Player();
    private final Playback playback;
    private Playback player;
    
    private Player(){
        player=Playback.getInstance();
        this.playback=Playback.getInstance();
        this.queue=new LinkedList<>();
    };
    
    public static Player getInstance(){
        return instance;
    }
    
    public synchronized void addToQueue(Song s){
        queue.add(s);
    }
    
    public void play(){
        playback.resumePlaying();
    }
    
    public void stop(){
        playback.stopPlaying();
    }
    
    public synchronized void playNextSong(){
        try {
            playback.playSong(queue.poll());
        } catch (Exception e) {
        }
        
    }
}
