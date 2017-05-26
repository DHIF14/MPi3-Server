package net.sec;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by Michael Krickl in 2017.
 */
public class User {
  
  private static MessageDigest digest;
  static {
    try {
      digest = MessageDigest.getInstance("SHA-256");
      
    } catch (NoSuchAlgorithmException e) {
      Logger.getAnonymousLogger().severe("no SHA256 implementation found: " + e.getMessage());
      throw new RuntimeException("no SHA256 implementation found", e);
    }
  }
  
  public final String name;
  private final byte[] password;
  public Privilege privilege;
  
  User(String name, byte[] password) {
    this.name = name;
    this.password = Arrays.copyOf(password, password.length);
  }
  
  public User(String name, String password) {
    this(name, digest.digest(password.getBytes(StandardCharsets.UTF_8)));
  }
  
  public boolean isPassword(String password) {
    
    return isPassword(digest.digest(password.getBytes(StandardCharsets.UTF_8)));
  }
  
  boolean isPassword(byte[] sha256) { return Arrays.equals(password, sha256); }
  
  byte[] getPassword() { return Arrays.copyOf(password, password.length); }
  
  @Override
  public boolean equals(Object obj) {
    
    if (obj == this) return true;
    if (!(obj instanceof User)) return false;
    
    User user = (User) obj;
    return name.equals(user.name);
  }
}
