package movies.spring.data.neo4j.movies;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieNeo4jRepository extends Neo4jRepository<Movie, String> {
}
