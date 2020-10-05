package dist.ai.backend.repositories;

import dist.ai.backend.models.PlayedSong;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayedSongRepository extends CrudRepository<PlayedSong, Integer> {
    List<PlayedSong> findAll();
}
