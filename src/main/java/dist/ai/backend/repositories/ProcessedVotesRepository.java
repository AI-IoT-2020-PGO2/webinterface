package dist.ai.backend.repositories;

import dist.ai.backend.models.ProcessedVote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProcessedVotesRepository extends CrudRepository<ProcessedVote, Integer> {
    List<ProcessedVote> findAll();

    @Query("select vote.total_score from ProcessedVote vote where vote.played_song_id = :id")
    Integer findScoreById(@Param("id") int id);

    @Query ("select vote.total_votes from ProcessedVote vote where vote.played_song_id = :id")
    Integer findVotesById(@Param("id") int id);
}
