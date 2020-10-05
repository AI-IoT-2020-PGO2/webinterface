package dist.ai.backend.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="played_songs")
public class PlayedSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer song_id;
    private Timestamp time_stamp;

    public PlayedSong(Integer id, Integer song_id, Timestamp time_stamp) {
        this.id = id;
        this.song_id = song_id;
        this.time_stamp = time_stamp;
    }

    public PlayedSong() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSong_id() {
        return song_id;
    }

    public void setSong_id(Integer song_id) {
        this.song_id = song_id;
    }

    public Timestamp getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Timestamp time_stamp) {
        this.time_stamp = time_stamp;
    }
}
