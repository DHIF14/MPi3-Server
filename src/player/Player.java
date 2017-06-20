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
    private State state;
    
    private Player(){
        player=Playback.getInstance();
        this.playback=Playback.getInstance();
        this.queue=new LinkedList<>();
        this.init=false;
        state=new State();
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
            state.isPlaying=true;
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
            state.isPlaying=true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public Queue<Song> getQueue() {
        return queue;
    }
    
    public void changeVolume(double vol)
            throws Exception{
        if(vol<0||vol>1){
            throw new Exception("Volume must be a value between 0 and 1");
        }
        playback.changeVolume(vol);
        state.volume=vol;
    }
    
    public void goToStartOfSong(){
        playback.setTimeToZero();
        state.isPlaying=false;
    }
    
    private class State {
        private boolean isPlaying;
        private double volume;
        
        public State(){
            isPlaying=false;
            volume=0.5;
        }

        public void setIsPlaying(boolean isPlaying) {
            this.isPlaying = isPlaying;
        }

        public void setVolume(double volume) {
            this.volume = volume;
        }

        public boolean isPlaying() {
            return isPlaying;
        }

        public double getVolume() {
            return volume;
        }
        
        
    }
}
