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
public class Server
    implements Runnable {
  
  private Logger logger;
  
  private ServerSocket socket;
  
  private List<Connection> connections = new ArrayList<>();
  
  public Server(int port)
      throws IOException {
    
    socket = new ServerSocket(port);
    logger = Logger.getLogger("Server[" + port + "]");
    
    logger.info("init");
    
    new Thread(this).start();
  }
  
  @Override
  public void run() {
    
    logger.info("Thread started");
    
    while(true) {
      try {
        logger.info("listening for new Connections");
    
        Connection c = new Connection(this, socket.accept());
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
  
  public void close() {
    if(!socket.isClosed()) {
      
      logger.info("closing ServerSocket");
      try {
        socket.close();
      } catch (IOException e) {
        logger.warning("Exception when closing ServerSocket: " + e.getMessage());
      }
      logger.info("ServerSocket closed");
  
      logger.info("closing Connections");
      for(Connection c : connections) {
        c.close();
        connections.remove(c);
      }
      logger.info("Connections closed");
      
    } else {
      logger.warning("ServerSocket already closed");
    }
  }
  
  Logger getLogger() { return logger; }
  
  @Override
  public String toString() {
    return String.format("Server[Socket:%s]", socket.toString());
  }
}
