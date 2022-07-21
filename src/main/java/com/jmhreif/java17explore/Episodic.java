package com.jmhreif.java17explore;

import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Node("Show")
public final class Episodic extends Production {
    Double seasons;

    public Episodic(String productionId, String title, String description, String rating, ZonedDateTime releaseDate) {
        super(productionId, title, description, rating, releaseDate);
    }

    public Double getSeasons() {
        return seasons;
    }
}
