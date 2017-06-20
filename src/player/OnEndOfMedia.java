/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player;

/**
 *
 * @author benedikt
 */
public class OnEndOfMedia implements Runnable{

    @Override
    public void run() {
        Player player=Player.getInstance();
        player.playNextSong();
    }
    
}
