package dist.ai.backend.repositories;

import dist.ai.backend.models.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AlbumRepository extends CrudRepository<Album, Integer> {
    public List<Album> findAll();

    public Album findByIdEquals(Integer id);
}
