package net.sec;

import net.util.Hash;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by Michael Krickl in 2017.
 */
public class User {
  
  public final String name;
  private final byte[] password; // SHA-256 of password encoded in UTF-8
  public Privilege privilege;
  
  public User(String name, byte[] passwordSHA256) {
    this.name = name;
    this.password = Arrays.copyOf(passwordSHA256, passwordSHA256.length);
  }
  
  public User(String name, String password) {
    this(name, Hash.SHA256(password.getBytes(StandardCharsets.UTF_8)));
  }
  
  public boolean isPassword(String password) {
    
    return isPassword(Hash.SHA256(password.getBytes(StandardCharsets.UTF_8)));
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
