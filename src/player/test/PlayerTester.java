/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.test;

import static java.lang.Thread.sleep;
import player.Player;
import player.Song;

/**
 *
 * @author benedikt
 */
public class PlayerTester {
    public static void main(String[] args) throws InterruptedException {
        Player player=Player.getInstance();
        player.addToQueue(new Song("Test","songs/alabama_man3.wav"));
        player.addToQueue(new Song("Test","songs/bitch_ass_in_kitchen_z.wav"));
        player.addToQueue(new Song("Test","songs/alabama_man3.wav"));
        player.addToQueue(new Song("Test","songs/bitch_ass_in_kitchen_z.wav"));
        player.playNextSong();
        sleep(2000);
        player.playNextSong();
        sleep(2000);
        player.playNextSong();
        sleep(2000);
        player.playNextSong();
        sleep(2000);
    }
    
}
