package net.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

/**
 * Created by Michael Krickl in 2017.
 */
public class Hash {
  
  private static MessageDigest sha256;
  static {
    try {
      sha256 = MessageDigest.getInstance("SHA-256");
      
    } catch (NoSuchAlgorithmException e) {
      Logger.getAnonymousLogger().severe("no SHA256 implementation found: " + e.getMessage());
      throw new RuntimeException("no SHA256 implementation found", e);
    }
  }
  
  public static byte[] SHA256(byte[] input) {
    return sha256.digest(input);
  }
  
  public static byte[] hexToRaw(String hex) {
    return new BigInteger(hex, 16).toByteArray();
  }
}
