package movies.spring.data.neo4j.movies;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This repository is indirectly used in the {@code movies.spring.data.neo4j.api.MovieController} via a dedicated movie service.
 * It is not a public interface to indicate that access is either through the rest resources or through the service.
 */
interface MovieRepository extends Repository<Movie, String> {

	@Query("MATCH (movie:Movie) WHERE movie.title CONTAINS $title RETURN movie")
	List<Movie> findSearchResults(@Param("title") String title);



	@Query("CREATE (NewMovie:Movie {title:$title, released:$released, tagline:$tagline})\n" +
			"return NewMovie;")
	Movie createMovie(
			@Param("title") String title,
			@Param("released") Integer released,
			@Param("tagline") String tagline);


	//this method should be in RelationshipRepository (but for simplicity we do it in MovieRepository)
	@Query("MATCH (m:Movie {title: $title})\n" +
			"MATCH (p:Person {name: $actorName})\n" +
			"CREATE (p)-[r:ACTED_IN {roles:[$role]}]->(m)\n" +
			"return p")
	void createRelationship(
			@Param("title") String title,
			@Param("actorName") String actorName,
			@Param("role") String role);


	long deleteByTitle(String title);

}
