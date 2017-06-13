package net;

import net.sec.Authenticator;
import net.sec.User;
import net.util.JSON;
import player.Player;
import player.Song;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
class Connection {
  
  private Server server;
  private Socket socket;
  private BufferedReader reader;
  private BufferedWriter writer;
  
  private Authenticator auth = Authenticator.getInstance();
  private Logger logger;
  
  private Player player = Player.getInstance();
  
  Connection(Server server, Socket socket) {
    
    this.server = server;
    this.socket = socket;
    
    logger = server.getLogger();
    logger.info("init");
    
    Thread thread = new Thread();
    thread.setDaemon(true);
    thread.start();
  }
  
  void close() {
    if (!socket.isClosed()) {
      
      logger.info("closing Socket");
      try {
        socket.close();
      } catch (IOException e) {
        logger.warning("Exception when closing Socket: " + e.getMessage());
      }
      logger.info("Socket closed");
      
    } else {
      logger.warning("Socket already closed");
    }
  }
  
  private String read()
      throws IOException {
    
    try {
      
      String line = reader.readLine();
      return line == null ? "" : line;
      
    } catch (IOException e) {
      logger.warning("IOException when reading from Socket: " + e.getMessage());
      throw e;
    }
  }
  
  private void write(String message)
      throws IOException {
    
    if (message == null) {
      message = "";
    }
    
    message = message.replaceAll("\n", "");
    
    try {
      
      writer.write(message);
      writer.newLine();
      writer.flush();
      
    } catch (IOException e) {
      logger.warning("IOException when writing to Socket: " + e.getMessage());
      throw e;
    }
  }
  
  private void initSession()
      throws Exception {
    
    String message = read();
    
    if (!message.equals("DEEERE")) {
      throw new Exception("illegal initialization message");
    }
    
    write("AUTH");
    message = read();
    
    // Authentication
    User user = JSON.parseUser(message);
    
    if (!auth.isValid(user)) {
      write("PUTZFRAU?");
      throw new Exception("authentication failed");
    }
    
    logger.info("authentication successful");
    write("DEEERE");
    
    logger.info("session initialization successful");
  }
  
  private void handleRequest(String request)
      throws Exception {
    
    switch (request.toUpperCase()) {
      case "PLAY_SONG_PLZ":
        handlePlaySong();
        break;
      case "NEXT_SONG_PLZ":
        handleNextSong();
        break;
      case "CONTINUE_PLAYING_PLZ":
        handlePlay();
        break;
      case "STOP_THIS_SHEEET":
        handlePause();
        break;
      case "BRING_THAT_BEAT_BACK":
        handleVolume();
        break;
      
      default:
        throw new Exception("unknown command");
    }
    
  }
  
  private void handlePlaySong()
      throws Exception {
    
    write("GIMME");
    
    Song song = JSON.parseSong(read());
    player.addToQueue(song);
    
    write("K");
  }
  
  private void handleNextSong()
      throws IOException {
    
    player.playNextSong();
    write("K");
  }
  
  private void handlePlay()
      throws IOException {
    
    player.play();
    write("K");
  }
  
  private void handlePause()
      throws IOException {
    
    player.stop();
    write("K");
  }
  
  private void handleVolume()
      throws IOException {
    
    // TODO Bene anmaulen
    write("K");
  }
  
  @Override
  public String toString() {
    return String.format("Connection[Socket:%s]", socket.toString());
  }
  
  @Override
  protected void finalize()
      throws Throwable {
    
    if (!socket.isClosed()) {
      close();
    }
  }
  
  private class Thread
      extends java.lang.Thread {
    @Override
    public void run() {
      
      logger.info("Thread started");
      
      try {
        
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        
        initSession();
        
        do {
          
          String message = read();
          handleRequest(message);
          
        } while (true);
        
      } catch (IOException e) {
        logger.warning("IOException in Thread: " + e.getMessage());
        
      } catch (Exception e) {
        logger.warning("Exception in Thread: " + e.getMessage());
        
      } finally {
        logger.info("not listening for new requests");
        close();
      }
      
      logger.info("Thread stopped");
    }
  }
}
