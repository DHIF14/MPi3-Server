package player;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017.
 */
public class Song {
    String name;
    String path;

    public Song(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
