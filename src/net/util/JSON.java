package net.util;

import net.sec.User;
import org.json.JSONException;
import org.json.JSONObject;
import player.Song;

import java.nio.file.Paths;

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
      throw new Exception("illegal JSON in parseUser: " + e.getMessage(), e);
    }
  }
  
  public static Song parseSong(String json)
      throws Exception {
    
    Song song;
    
    try {
      JSONObject j = new JSONObject(json);
      
      String name = j.get("name").toString();
      song = Song.getSongByName(name);
      
    } catch (JSONException e) {
      throw new Exception("illegal JSON in parseSong: " + e.getMessage(), e);
    }
    
    if (song == null) {
      throw new Exception("no such Song");
    }
    
    return song;
  }
}
