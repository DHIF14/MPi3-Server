/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.test;

import java.io.FileNotFoundException;
import static java.lang.Thread.sleep;
import java.nio.file.Paths;
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
        playback.playSong(new Song(Paths.get("/home/mpi3/songs/bitch_ass_in_kitchen_z.wav")));
    }
}
