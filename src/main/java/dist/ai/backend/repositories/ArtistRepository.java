package dist.ai.backend.repositories;

import dist.ai.backend.models.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    List<Artist> findAll();

    Artist findByIdEquals(Integer id);
}
