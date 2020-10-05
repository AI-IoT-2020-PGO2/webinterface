package dist.ai.backend.models;

import javax.persistence.*;

@Entity
@Table(name="processed_votes")
public class ProcessedVote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer played_song_id;
    private Integer total_score;
    private Integer total_votes;

    public ProcessedVote(Integer id, Integer played_song_id, Integer total_score, Integer total_votes) {
        this.id = id;
        this.played_song_id = played_song_id;
        this.total_score = total_score;
        this.total_votes = total_votes;
    }

    public ProcessedVote() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlayed_song_id() {
        return played_song_id;
    }

    public void setPlayed_song_id(Integer played_song_id) {
        this.played_song_id = played_song_id;
    }

    public Integer getTotal_score() {
        return total_score;
    }

    public void setTotal_score(Integer total_score) {
        this.total_score = total_score;
    }

    public Integer getTotal_votes() {
        return total_votes;
    }

    public void setTotal_votes(Integer total_votes) {
        this.total_votes = total_votes;
    }
}
