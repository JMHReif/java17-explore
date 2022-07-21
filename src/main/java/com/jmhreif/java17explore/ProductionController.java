package com.jmhreif.java17explore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productions")
public class ProductionController {
    private final ProductionRepo repo;

    public ProductionController(ProductionRepo repo) {
        this.repo = repo;
    }

    @GetMapping
    Iterable<Production> findAllProductions() { return repo.findAll(); }

    @GetMapping("/movies")
    Iterable<Production> findMovies() { return repo.findMovies(); }

    @GetMapping("/shows")
    Iterable<Production> findShows() { return repo.findShows(); }
}
