package com.jmhreif.java17explore;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

public interface ProductionRepo extends Neo4jRepository<Production, String> {
    @Query("MATCH (m:Movie) RETURN *;")
    Iterable<Production> findMovies();

    @Query("MATCH (s:Show) RETURN *;")
    Iterable<Production> findShows();
}
