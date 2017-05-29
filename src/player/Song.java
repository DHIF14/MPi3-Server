package player;

/**
 * Created by Michael Krickl and Benedikt Breid in 2017. Entpfuscht by feba6481
 */
public class Song {
    private final String name;
    private final String path;

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
