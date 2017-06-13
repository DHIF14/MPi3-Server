package player;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017. Gepfuscht by feba6481
 */
public class Song {
  private final String name;
  private Path path;
  
  private static final Path SONGS_ROOT = Paths.get("/home/mpi3/songs");
  private static List<Song> songs = new ArrayList<>();
  
  static {
    
    try {
      songs = new ArrayList<>(
          Files.walk(SONGS_ROOT)
               .filter(p -> Files.isRegularFile(p))
               .map(Song::new)
               .distinct()
               .collect(Collectors.toList()));
    } catch (Exception e) {
      Logger.getAnonymousLogger().severe("Exception when collecting songs: " + e.getMessage());
      throw new RuntimeException("Exception when collecting songs", e);
    }
  }
  
  public static Song getSongByName(String name) {
    return songs.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
  }
  
  public static Song getSongByPath(Path path) {
    return songs.stream()
                .filter(s -> s.getPath().equals(path))
                .findFirst().orElse(null);
  }
  
  // deprecated?
  public Song(String path) {
    this(Paths.get(path));
  }
  
  private Song(Path path) {
    this.path = path;
    this.name = path.getFileName().toString().replaceAll("\\..*", "");
  }
  
  public String getName() {
    return name;
  }
  
  public Path getPath() {
    return path;
  }
  
  @Override
  public boolean equals(Object o) {
    
    if(!(o instanceof Song)) return false;
    
    Song other = (Song) o;
    if(!other.getName().equalsIgnoreCase(this.getName())) return false;
    
    return true;
  }
}
