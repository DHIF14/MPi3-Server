package net;

import net.sec.Authenticator;
import net.sec.User;

import java.io.*;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
class Connection {
  
  private Server server;
  private Socket socket;
  private Authenticator auth = Authenticator.getInstance();
  private Logger logger;
  
  Connection(Server server, Socket socket) {
    
    this.server = server;
    this.socket = socket;
    
    logger = Logger.getLogger("Connection");
    logger.setParent(server.getLogger());
    
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
        
        do {
          
          String message = read();
          
          // TODO: implement request handling
          
        } while(true);
        
      } catch (IOException e) {
        logger.warning("IOException in Thread: " + e.getMessage());
        
      } catch (Exception e) {
        logger.warning("Exception in Thread" + e.getMessage());
        
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
    
    StringBuilder builder = new StringBuilder();
    
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
      
      String line;
      while ((line = reader.readLine()) != null) {
        
        builder.append(line);
        builder.append('\n');
      }
      builder.deleteCharAt(builder.length() - 1);
      
    } catch (IOException e) {
      logger.warning("IOException when reading from Socket: " + e.getMessage());
      throw e;
    }
    
    return builder.toString();
  }
  
  private void write(String message)
      throws IOException {
    
    try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
      
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
    
    if (message.equals("DEERE")) {
      throw new Exception("initialization of Session failed");
    }
    
    write("AUTH");
    message = read();
    
    User user = null;
    // TODO: implement parse User
    
    if(!auth.isValid(user)) {
      throw new Exception("initial authentication of Session failed");
    }
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
