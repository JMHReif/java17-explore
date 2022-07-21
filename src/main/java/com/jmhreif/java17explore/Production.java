package com.jmhreif.java17explore;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import java.time.*;

@Node
public sealed class Production permits Episodic, NonEpisodic {
    @Id
    private String productionId;

    private String title, description, rating;
    private ZonedDateTime releaseDate;

    @JsonProperty("type")
    public String type(Production this) {
        return switch(this) {
            case Episodic episodic -> "Show";
            case NonEpisodic nonEpisodic -> "Movie";
            default -> "Other";
        };
    }

    @JsonProperty("age")
    public long age(Production this) {
        Instant current = InstantSource.system().instant();
        Instant releaseDate = this.releaseDate.toInstant();
        Duration duration = Duration.between(releaseDate, current);
        return duration.toDays() / 365;
    }

    public Production(String productionId, String title, String description, String rating, ZonedDateTime releaseYear) {
        this.productionId = productionId;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.releaseDate = releaseYear;
    }

    public String getProductionId() {
        return productionId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRating() {
        return rating;
    }

    public ZonedDateTime getReleaseDate() {
        return releaseDate;
    }
}
