/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.test;

import java.io.FileNotFoundException;
import static java.lang.Thread.sleep;
import player.Playback;
import player.Song;


/**
 *
 * @author benedikt
 */
public class PlaybackTester {
    public static void main(String[] args)
            throws FileNotFoundException, InterruptedException{
        Playback playback=Playback.getInstance();
        playback.playSong(new Song("Test","songs/bitch_ass_in_kitchen_z.wav"));
        System.out.println("isPlaying: "+playback.isPlaying());
        sleep(5000);
        playback.stopPlaying();
        System.out.println("isPlaying: "+playback.isPlaying());
        sleep(1000);
        playback.resumePlaying();
        System.out.println("isPlaying: "+playback.isPlaying());
        sleep(3000);
        System.out.println("isPlaying: "+playback.isPlaying());
    }
}
