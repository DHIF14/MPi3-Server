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
        player.addToQueue(Song.getSongByName("alabama_man3"));
        player.addToQueue(Song.getSongByName("alabama_man3"));
        player.play();
    }
    
}
