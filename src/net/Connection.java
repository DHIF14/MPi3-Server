package net;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
class Connection {
  
  private Server server;
  private Socket socket;
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
      
      // TODO
      
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
