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
    private boolean init;
    
    private Player(){
        player=Playback.getInstance();
        this.playback=Playback.getInstance();
        this.queue=new LinkedList<>();
        this.init=false;
    };
    
    public static Player getInstance(){
        return instance;
    }
    
    public synchronized void addToQueue(Song s){
        queue.add(s);
    }
    
    public void play(){
        try {
            if(init==false){
                playback.playSong(queue.poll());
                init=true;
            }
            else{
                playback.resumePlaying();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }
    
    public void stop(){
        playback.stopPlaying();
    }
    
    public synchronized void playNextSong(){
        try {
            init=true;
            playback.playSong(queue.poll());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Queue<Song> getQueue() {
        return queue;
    }
    
    
}
