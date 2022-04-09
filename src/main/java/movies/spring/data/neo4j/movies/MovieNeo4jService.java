package movies.spring.data.neo4j.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieNeo4jService {

    @Autowired
    private MovieNeo4jRepository repository;

    public List<Movie> getAll(){
        return repository.findAll();
    }

    public void delete(String title){
        repository.deleteById(title);
    }

    public Movie save(Movie movie){
        return repository.save(movie);
    }
}
