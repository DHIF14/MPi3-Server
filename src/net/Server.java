package net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Server {
  
  private ServerSocket socket;
  private List<Connection> connections = new ArrayList<>();
  private Logger logger;
  private Logger connectionLogger;
  
  public Server(int port)
      throws IOException {
    
    socket = new ServerSocket(port);
    
    logger = Logger.getLogger("Server[" + port + "]");
    connectionLogger = Logger.getLogger("Connection[" + port + "]");
    connectionLogger.setParent(logger);
    
    logger.info("init");
    
    new Thread().start();
  }
  
  public void close() {
    if (!socket.isClosed()) {
      
      logger.info("closing ServerSocket");
      try {
        socket.close();
      } catch (IOException e) {
        logger.warning("Exception when closing ServerSocket: " + e.getMessage());
      }
      logger.info("ServerSocket closed");
      
    } else {
      logger.warning("ServerSocket already closed");
    }
    
    if (!connections.isEmpty()) {
      
      logger.info("closing Connections");
      for (Connection c : connections) {
        c.close();
        connections.remove(c);
      }
      logger.info("Connections closed");
    }
  }
  
  private class Thread
      extends java.lang.Thread {
    
    @Override
    public void run() {
      
      logger.info("Thread started");
      
      while (true) {
        try {
          logger.info("listening for new Connections");
          
          Connection c = new Connection(Server.this, socket.accept());
          connections.add(c);
          
          logger.info("new Connection: " + c.toString());
          
        } catch (SocketException e) {
          
          if (socket.isClosed()) {
            logger.info("save Thread interrupt");
            break;
          }
          logger.warning("unknown Exception when accepting Connection: " + e.getMessage());
          
        } catch (Exception e) {
          logger.severe("unknown Exception when accepting Connection: " + e.getMessage());
          close();
          break;
        }
      }
      logger.info("not accepting new Connections");
      
      logger.info("Thread stopped");
    }
  }
  
  Logger getLogger() { return connectionLogger; }
  
  @Override
  public String toString() {
    return String.format("Server[Socket:%s]", socket.toString());
  }
}
