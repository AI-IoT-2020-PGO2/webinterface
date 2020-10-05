package dist.ai.backend.models;

public class SongInfo {
    private String title;
    private String artist;
    private int songID;

    public SongInfo() {}

    public SongInfo(String title, String artist, int songID) {
        this.title = title;
        this.artist = artist;
        this.songID = songID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }
}
