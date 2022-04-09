package movies.spring.data.neo4j.api;

import movies.spring.data.neo4j.movies.Movie;
import movies.spring.data.neo4j.movies.MovieDetailsDto;
import movies.spring.data.neo4j.movies.MovieResultDto;
import movies.spring.data.neo4j.movies.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
class MovieController {

	private final MovieService movieService;

	MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	@GetMapping("/movie/{title}")
	public MovieDetailsDto findByTitle(@PathVariable("title") String title) {
		return movieService.fetchDetailsByTitle(title);
	}

	@PostMapping("/movie/{title}/vote")
	public int voteByTitle(@PathVariable("title") String title) {
		return movieService.voteInMovieByTitle(title);
	}

	@PostMapping("/relationships")
	public void createRelationship(
			@RequestParam("title") String title,
			@RequestParam("actorName") String actorName,
			@RequestParam("role") String role) {
		movieService.createRelationship(title, actorName, role);
	}

	@PostMapping("/movies")
	public Movie createMovie(
			@RequestParam("title") String title,
			@RequestParam("release") Integer release,
			@RequestParam("tagline") String tagline) {
		return movieService.createMovie(title, release, tagline);
	}

	@DeleteMapping("/movie")
	public long deleteMovie(
			@RequestParam("title") String title) {
		return movieService.deleteByTitle(title);
	}

	@GetMapping("/search")
	List<MovieResultDto> search(@RequestParam("q") String title) {
		return movieService.searchMoviesByTitle(stripWildcards(title));
	}

	@GetMapping("/graph")
	public Map<String, List<Object>> getGraph() {
		return movieService.fetchMovieGraph();
	}

	private static String stripWildcards(String title) {
		String result = title;
		if (result.startsWith("*")) {
			result = result.substring(1);
		}
		if (result.endsWith("*")) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}
}
