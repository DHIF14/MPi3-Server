package net.sec;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public final class Authenticator {
  
  private static Authenticator instance = new Authenticator();
  public static Authenticator getInstance() { return instance; }
  
  private List<User> users = new ArrayList<>();
  
  public synchronized boolean isValid(User user) {
    return users.contains(user) && users.get(users.indexOf(user)).isPassword(user.getPassword());
  }
  
  private Authenticator() {}
}
