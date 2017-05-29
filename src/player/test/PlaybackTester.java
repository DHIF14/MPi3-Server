/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.test;

import java.io.FileNotFoundException;
import player.Playback;
import player.Song;


/**
 *
 * @author benedikt
 */
public class PlaybackTester {
    public static void main(String[] args)
            throws FileNotFoundException{
        System.out.println(System.getProperty("user.dir"));
        Playback playback=Playback.getInstance();
        playback.playSong(new Song("Test","Songs/a2002011001-e02.wav"));
    }
}
