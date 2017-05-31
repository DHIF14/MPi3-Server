package net.util;

import net.sec.User;
import org.json.*;

/**
 * Created by Michael Krickl in 2017.
 */
public class JSON {
  
  public static User parseUser(String json)
      throws Exception {
    
    try {
      JSONObject j = new JSONObject(json);
  
      String name = j.get("user").toString();
      String pwHex = j.get("pw").toString();
      byte[] pw = Hash.hexToRaw(pwHex);

      return new User(name, pw);
    
    } catch (JSONException e) {
      throw new Exception("illegal JSON in parse user: " + e.getMessage(), e);
    }
  }
}
