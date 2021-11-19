//find all movies containing given string in the title
MATCH (movie:Movie) WHERE movie.title CONTAINS "Matrix" RETURN movie

//find details for movie with given title
MATCH (movie:Movie {title: "The Matrix"})
OPTIONAL MATCH (person:Person)-[r]->(movie)
WITH movie, COLLECT({ name: person.name, job: REPLACE(TOLOWER(TYPE(r)), '_in', ''), role: HEAD(r.roles) }) as cast
RETURN movie { .title, cast: cast }

//vote for a movie
MATCH (m:Movie {title: "The Matrix"})
WITH m, coalesce(m.votes, 0) AS currentVotes
SET m.votes = currentVotes + 1;

//find movies and for each movie, show actors sorted by name
MATCH (m:Movie) <- [r:ACTED_IN] - (p:Person)
WITH m, p ORDER BY m.title, p.name
RETURN m.title AS movie, collect(p.name) AS actors

