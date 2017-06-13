package net.sec;

import net.util.Hash;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public final class Authenticator {
  
  private static final Path USER_DB = Paths.get("/home/mpi3/user-db");
  
  private static Authenticator instance = new Authenticator();
  
  public static Authenticator getInstance() { return instance; }
  
  private List<User> users = new ArrayList<>();
  
  public synchronized boolean isValid(User user) {
    return users.contains(user) && users.get(users.indexOf(user)).isPassword(user.getPassword());
  }
  
  private Authenticator() {
    
    initUsers();
  }
  
  private void initUsers() {
    
    try (BufferedReader reader = Files.newBufferedReader(USER_DB)) {
      
      String line;
      while ((line = reader.readLine()) != null) {
        
        // Comments
        if (line.startsWith("#") || line.isEmpty()) continue;
        
        String[] col = line.split(":");
        if (col.length != 2) throw new Exception("illegal user-db format");
        
        String name = col[0];
        byte[] pw = Hash.hexToRaw(col[1]);
        
        users.add(new User(name, pw));
      }
      
    } catch (Exception e) {
      
      Logger.getAnonymousLogger().severe("Exception when reading user-db: " + e.getMessage());
      throw new RuntimeException("Exception when reading user-db", e);
    }
  }
}
