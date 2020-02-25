package com.example.vhs.controller;

import com.example.vhs.entity.MovieEntity;
import com.example.vhs.service.MovieService;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }

    @GetMapping("/movies/{id}")
    public Optional<MovieEntity> findOneById(@RequestParam("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/movies")
    public Iterable<MovieEntity> findAll() {
        return service.findAll();
    }

    @PostMapping("/movies")
    public MovieEntity save(@RequestBody MovieEntity movieEntity) {
        return service.save(movieEntity);
    }
}