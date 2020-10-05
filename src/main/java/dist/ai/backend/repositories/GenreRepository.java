package dist.ai.backend.repositories;

import dist.ai.backend.models.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenreRepository extends CrudRepository<Genre, Integer> {
    public List<Genre> findAll();

    public Genre findByIdEquals(Integer id);
}
