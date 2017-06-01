package player;

import java.util.Queue;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Player {
    private Queue<Song> queue;
    private static final Player instance=new Player();
    private Playback player;
    
    private Player(){
        player=Playback.getInstance();
    };
    
    public static Player getInstance(){
        return instance;
    }
    
    public synchronized void addToQueue(Song s){
        queue.add(s);
    }
    
    public void play(){
        
    }
    
    public void stop(){
        
    }
    
    public synchronized void skipSong(){
        
    }
}
