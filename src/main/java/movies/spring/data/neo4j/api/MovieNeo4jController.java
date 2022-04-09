package movies.spring.data.neo4j.api;

import movies.spring.data.neo4j.movies.Movie;
import movies.spring.data.neo4j.movies.MovieDetailsDto;
import movies.spring.data.neo4j.movies.MovieNeo4jService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/neo4j")
public class MovieNeo4jController {

    private final MovieNeo4jService movieService;

    public MovieNeo4jController(MovieNeo4jService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @PostMapping("/movies")
    public Movie createMovie(
            @RequestBody Movie movie) {
        return movieService.save(movie);
    }

    @DeleteMapping("/movie")
    public void deleteMovie(
            @RequestParam("title") String title) {
        movieService.delete(title);
    }
}
