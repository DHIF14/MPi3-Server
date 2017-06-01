/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package player.test;

import java.io.File;
import static java.lang.Thread.sleep;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author benedikt
 */
public class GeneralTests {
    public static void main(String[] args) {
        try {
        final JFXPanel fxPanel = new JFXPanel();
        String bip = "songs/alabama_man3.wav";
        Media hit = new Media(new File(bip).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(null);
        mediaPlayer.play();
        System.out.println(mediaPlayer.getCurrentTime());
        sleep(3000);
        mediaPlayer.pause();
        System.out.println(mediaPlayer.getCurrentTime());
        sleep(3000);
        mediaPlayer.play();
        System.out.println(mediaPlayer.getCurrentTime());
        sleep(3000);
        System.out.println(mediaPlayer.getCurrentTime());
      } catch (Exception e) {
        System.out.println("Error with playing sound.");
        e.printStackTrace();
      }
    }
}
