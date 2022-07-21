package com.jmhreif.java17explore;

import org.springframework.data.neo4j.core.schema.Node;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Node("Movie")
public final class NonEpisodic extends Production {
    Integer runtime;

    public NonEpisodic(String productionId, String title, String description, String rating, ZonedDateTime releaseDate) {
        super(productionId, title, description, rating, releaseDate);
    }

    public Integer getRuntime() {
        return runtime;
    }
}
