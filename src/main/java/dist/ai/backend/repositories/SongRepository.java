package dist.ai.backend.repositories;

import dist.ai.backend.models.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Integer> {
    List<Song> findAll();

    Song findByIdEquals(Integer id);

}
