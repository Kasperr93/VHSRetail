package com.example.vhs.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.example.vhs.converter.MovieMapper;
import com.example.vhs.dto.MovieData;
import com.example.vhs.entity.MovieEntity;
import com.example.vhs.service.MovieService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(final MovieService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieData> findById(@PathVariable("id") final Long id) {
        Optional<MovieEntity> findById = service.findById(id);

        return findById.map(movieEntity -> new ResponseEntity<>(MovieMapper.INSTANCE.movieEntityToMovieDto(movieEntity),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<MovieData>> findAll() {
        List<MovieEntity> findAllMoviesEntity = service.findAll();

        if (findAllMoviesEntity.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(convertAllMovies(findAllMoviesEntity), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MovieData> save(@RequestBody final MovieData movieData) {

        if (movieData == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        MovieEntity entity = MovieMapper.INSTANCE.movieDtoToMovieEntity(movieData);
        MovieEntity movieEntitySaved = service.save(entity);
        MovieData movieDataSaved = MovieMapper.INSTANCE.movieEntityToMovieDto(movieEntitySaved);
        movieDataSaved.add(createRelLink(movieEntitySaved));

        return new ResponseEntity<>(movieDataSaved, HttpStatus.CREATED);
    }

    private List<MovieData> convertAllMovies(final List<MovieEntity> findAllMoviesEntity) {
        return findAllMoviesEntity.stream()
                .map(MovieMapper.INSTANCE::movieEntityToMovieDto)
                .collect(Collectors.toList());
    }

    private Link createRelLink(MovieEntity movieEntity) {
        return linkTo(MovieController.class).slash(movieEntity.getId()).withSelfRel();
    }
}