package dist.ai.backend.models;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String song_name;
    private Integer artist_id;
    private Integer album_id;
    private Integer genre_id;
    private Time length;

    public Song(Integer id, String song_name, Integer artist_id, Integer album_id, Integer genre_id, Time length) {
        this.id = id;
        this.song_name = song_name;
        this.artist_id = artist_id;
        this.album_id = album_id;
        this.genre_id = genre_id;
        this.length = length;
    }

    public Song() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(Integer artist_id) {
        this.artist_id = artist_id;
    }

    public Integer getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(Integer album_id) {
        this.album_id = album_id;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public Time getLength() {
        return length;
    }

    public void setLength(Time length) {
        this.length = length;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

}
