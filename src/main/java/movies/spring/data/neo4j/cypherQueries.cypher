//find all movies containing given string in the title
MATCH (movie:Movie) WHERE movie.title CONTAINS "Matrix" RETURN movie

//find details for movie with given title
MATCH (movie:Movie {title: "TestMovie"})
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

//change a movie name
MATCH (m:Movie {title: "The Matrix updated"})
SET m.title = "The Matrix"
return m

//create new movie:
CREATE (NewMovie:Movie {title:'New Movie', released:1999, tagline:'This is new movie'})
return NewMovie;

//Connect Keanu with New Movie
MATCH (NewMovie:Movie {title: "New Movie"})
MATCH (Keanu:Person {name: "Keanu Reeves"})
CREATE (Keanu)-[r:ACTED_IN {roles:['NewRole']}]->(NewMovie)
return Keanu,NewMovie,r

//movies where Keanu Reeves acted:
MATCH (m:Movie)<-[r:ACTED_IN]-(p:Person) WHERE p.name = "Keanu Reeves" RETURN m,p

//MATCH (m:Movie {title: "TestMovie"})
//return m

//create and delete a movie:
CREATE (m:Movie {title:'Movie10', released:1999, tagline:'This is new movie'})
return m;

MATCH (m:Movie {title: 'Movie10'})
DELETE m