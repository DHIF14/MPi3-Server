package net;

import net.sec.Authenticator;
import net.sec.User;
import net.util.JSON;
import org.json.JSONException;

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
  
  Connection(Server server, Socket socket) {
    
    this.server = server;
    this.socket = socket;
    
    logger = server.getLogger();
    logger.info("init");
    
    Thread thread = new Thread();
    thread.setDaemon(true);
    thread.start();
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
          
          // TODO: implement request handling
          
        } while(true);
        
      } catch (IOException e) {
        logger.warning("IOException in Thread: " + e.getMessage());
        
      } catch (Exception e) {
        logger.warning("Exception in Thread: " + e.getMessage());
        e.printStackTrace();
        
      } finally {
        logger.info("not listening for new requests");
        close();
      }
      
      logger.info("Thread stopped");
    }
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
      
      return reader.readLine();
      
    } catch (IOException e) {
      logger.warning("IOException when reading from Socket: " + e.getMessage());
      throw e;
    }
  }
  
  private void write(String message)
      throws IOException {
    
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
    
    if(!auth.isValid(user)) {
      write("PUTZFRAU?");
      throw new Exception("authentication failed");
    }
    
    logger.info("authentication successful");
    write("DEEERE");
    
    logger.info("session initialization successful");
  }
  
  @Override
  protected void finalize()
      throws Throwable {
    
    if (!socket.isClosed()) {
      close();
    }
  }
  
  @Override
  public String toString() {
    return String.format("Connection[Socket:%s]", socket.toString());
  }
}
