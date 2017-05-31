package start;

import net.Server;

import java.io.IOException;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Launcher {
  
  public static void main(String[] args) throws IOException {
    
    new Server(7777);
  }
}
